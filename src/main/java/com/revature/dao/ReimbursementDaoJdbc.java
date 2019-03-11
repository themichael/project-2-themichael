package com.revature.dao;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.exception.StringEmptyException;
import com.revature.model.Reimbursement;
import com.revature.model.ReimbursementType;
import com.revature.model.User;
import com.revature.util.ConnectionUtil;
import com.revature.util.LogUtil;
import com.revature.util.StringUtil;

public class ReimbursementDaoJdbc implements ReimbursementDao {

	private static ReimbursementDao reimbursementDaoSingleton;
	
	private ReimbursementDaoJdbc() {

	}

	public static ReimbursementDao getReimbursementDao() {
		if(reimbursementDaoSingleton == null) {
			return new ReimbursementDaoJdbc();
		}
		else {
			return reimbursementDaoSingleton;
		}
	}

	@Override
	public boolean insert(Reimbursement reimbursement) {
		try(Connection connection = ConnectionUtil.getConnection()) {
			int statementIndex = 0;
			String command = "INSERT INTO REIMBURSEMENT VALUES (NULL,CURRENT_TIMESTAMP,NULL,?,?,?,?,NULL,"
					+ "(SELECT RS_ID FROM REIMBURSEMENT_STATUS WHERE RS_STATUS = ?),"
					+ "(SELECT RT_ID FROM REIMBURSEMENT_TYPE WHERE RT_TYPE = ?))";
				
			PreparedStatement statement = connection.prepareStatement(command);
			
			//Set attributes to be updated
			statement.setDouble(++statementIndex, reimbursement.getAmount());
			statement.setString(++statementIndex, reimbursement.getDescription());
			
			//Create blob from string blob
			Blob imageBlob = connection.createBlob();
			imageBlob.setBytes(1, reimbursement.getReceipt().toString().getBytes());
			statement.setBlob(++statementIndex, imageBlob);
			
			statement.setInt(++statementIndex, reimbursement.getEmployee().getId());
			statement.setString(++statementIndex, reimbursement.getStatus());
			statement.setString(++statementIndex, reimbursement.getType().getType().toUpperCase());
			
			if(statement.executeUpdate() > 0) {
				return true;
			}
		} catch (SQLException e) {
			LogUtil.logger.warn("Exception inserting reimbursement", e);
		}
		return false;
	}
	
	@Override
	public List<Reimbursement> selectAllPending(int employeeId) {
		try(Connection connection = ConnectionUtil.getConnection()) {
			String command = "SELECT "
					+ "R.R_ID,"
					+ "RT.RT_ID,"
					+ "UE.U_ID AS EMP_ID,"
					+ "UE.U_FIRSTNAME AS EMP_FIRSTNAME,"
					+ "UE.U_LASTNAME AS EMP_LASTNAME,"
					+ "UE.U_EMAIL AS EMP_EMAIL,"
					+ "TO_CHAR(R.R_REQUESTED,'dd/mm/yyyy hh:mm:ss') AS REQUESTED,"
					+ "TO_CHAR(R.R_RESOLVED,'dd/mm/yyyy hh:mm:ss') AS RESOLVED,"
					+ "R.R_AMOUNT,"
					+ "R.R_DESCRIPTION,"
					+ "R.R_RECEIPT,"
					+ "RT.RT_TYPE,"
					+ "RS.RS_STATUS "
					+ "FROM REIMBURSEMENT R, USER_T UE, REIMBURSEMENT_TYPE RT, REIMBURSEMENT_STATUS RS "
					+ "WHERE "
					+ "R.EMPLOYEE_ID = UE.U_ID AND "
					+ "R.RT_ID = RT.RT_ID AND "
					+ "R.RS_ID = RS.RS_ID AND "
					+ "RS.RS_STATUS = 'PENDING' ";
			
			//We want only the requests for a specific employee
			if(employeeId > 0) {
				command += "AND UE.U_ID = " + employeeId + " ";
			}

			command += "ORDER BY R.R_REQUESTED DESC";

			PreparedStatement statement = connection.prepareStatement(command);
			ResultSet result = statement.executeQuery();
			
			List<Reimbursement> reimbursementList = new ArrayList<>();
			while(result.next()) {
				int imageBlobLength = (int) (result.getBlob("R_RECEIPT").length());
				reimbursementList.add(new Reimbursement(
						result.getInt("R_ID"),
						result.getString("REQUESTED"),
						result.getString("RESOLVED"),
						result.getDouble("R_AMOUNT"),
						result.getString("R_DESCRIPTION"),
						new StringBuilder(new String(result.getBlob("R_RECEIPT").getBytes(1,imageBlobLength))),
						new User(result.getInt("EMP_ID"),StringUtil.toCamelCase(result.getString("EMP_FIRSTNAME")),StringUtil.toCamelCase(result.getString("EMP_LASTNAME")),"","",result.getString("EMP_EMAIL").toLowerCase(),""),
						new User(),
						StringUtil.toCamelCase(StringUtil.toCamelCase(result.getString("RS_STATUS"))),
						new ReimbursementType(result.getInt("RT_ID"), StringUtil.toCamelCase(result.getString("RT_TYPE")))
						));
			}

			return reimbursementList;
		}
		catch (SQLException e) {
			LogUtil.logger.warn("Exception selecting all pending reimbursement requests", e);
		} catch (StringEmptyException e) {
			LogUtil.logger.warn("Table returned empty string when not expected", e);
		}

		return new ArrayList<>();
	}

	@Override
	public List<Reimbursement> selectAllResolved(int employeeId) {
		try(Connection connection = ConnectionUtil.getConnection()) {
			String command = "SELECT "
					+ "R.R_ID,"
					+ "RT.RT_ID,"
					+ "UE.U_ID AS EMP_ID,"
					+ "UE.U_FIRSTNAME AS EMP_FIRSTNAME,"
					+ "UE.U_LASTNAME AS EMP_LASTNAME,"
					+ "UE.U_EMAIL AS EMP_EMAIL,"
					+ "UM.U_ID AS MNGR_ID,"
					+ "UM.U_FIRSTNAME AS MNGR_FIRSTNAME,"
					+ "UM.U_LASTNAME AS MNGR_LASTNAME,"
					+ "TO_CHAR(R.R_REQUESTED,'dd/mm/yyyy hh:mm:ss') AS REQUESTED,"
					+ "TO_CHAR(R.R_RESOLVED,'dd/mm/yyyy hh:mm:ss') AS RESOLVED,"
					+ "R.R_AMOUNT,"
					+ "R.R_DESCRIPTION,"
					+ "R.R_RECEIPT,"
					+ "RT.RT_TYPE,"
					+ "RS.RS_STATUS "
					+ "FROM REIMBURSEMENT R, USER_T UE, USER_T UM, REIMBURSEMENT_TYPE RT, REIMBURSEMENT_STATUS RS "
					+ "WHERE "
					+ "R.EMPLOYEE_ID = UE.U_ID AND "
					+ "R.MANAGER_ID = UM.U_ID AND "
					+ "R.RT_ID = RT.RT_ID AND "
					+ "R.RS_ID = RS.RS_ID AND "
					+ "RS.RS_STATUS <> 'PENDING' ";

			//We want only the requests for a specific employee
			if(employeeId > 0) {
				command += "AND UE.U_ID = " + employeeId + " ";
			}

			command += "ORDER BY R.R_REQUESTED";

			PreparedStatement statement = connection.prepareStatement(command);
			ResultSet result = statement.executeQuery();

			List<Reimbursement> reimbursementList = new ArrayList<>();
			while(result.next()) {
				int imageBlobLength = (int) (result.getBlob("R_RECEIPT").length());
				reimbursementList.add(new Reimbursement(
						result.getInt("R_ID"),
						result.getString("REQUESTED"),
						result.getString("RESOLVED"),
						result.getDouble("R_AMOUNT"),
						result.getString("R_DESCRIPTION"),
						new StringBuilder(new String(result.getBlob("R_RECEIPT").getBytes(1,imageBlobLength))),
						new User(result.getInt("EMP_ID"),StringUtil.toCamelCase(result.getString("EMP_FIRSTNAME")),StringUtil.toCamelCase(result.getString("EMP_LASTNAME")),"","",result.getString("EMP_EMAIL").toLowerCase(),""),
						new User(result.getInt("MNGR_ID"),StringUtil.toCamelCase(result.getString("MNGR_FIRSTNAME")),StringUtil.toCamelCase(result.getString("MNGR_LASTNAME")),"","","",""),
						StringUtil.toCamelCase(result.getString("RS_STATUS")),
						new ReimbursementType(result.getInt("RT_ID"), StringUtil.toCamelCase(result.getString("RT_TYPE")))
						));
			}

			return reimbursementList;
		}
		catch (SQLException e) {
			LogUtil.logger.warn("Exception selecting all resolved reimbursement requests", e);
		} catch (StringEmptyException e) {
			LogUtil.logger.warn("Table returned empty string when not expected", e);
		}

		return new ArrayList<>();
	}

	@Override
	public List<ReimbursementType> selectTypes() {
		try(Connection connection = ConnectionUtil.getConnection()) {
			String command = "SELECT * FROM REIMBURSEMENT_TYPE";
			PreparedStatement statement = connection.prepareStatement(command);
			ResultSet result = statement.executeQuery();

			List<ReimbursementType> types = new ArrayList<>();
			while(result.next()) {
				types.add(new ReimbursementType(result.getInt("RT_ID"),StringUtil.toCamelCase(result.getString("RT_TYPE"))));
			}
			return types;
		} catch (SQLException e) {
			LogUtil.logger.warn("Exception selecting types of reimbursement requests", e);
		} catch (StringEmptyException e) {
			LogUtil.logger.warn("Table returned empty string when not expected", e);
		}
		return new ArrayList<>();
	}

	@Override
	public boolean update(Reimbursement reimbursement) {
		try(Connection connection = ConnectionUtil.getConnection()) {
			int statementIndex = 0;
			String command = "UPDATE REIMBURSEMENT SET "
					+ "R_RESOLVED = CURRENT_TIMESTAMP,"
					+ "MANAGER_ID = ?,"
					+ "RS_ID = (SELECT RS_ID FROM REIMBURSEMENT_STATUS WHERE RS_STATUS = ?)"
					+ "WHERE R_ID = ?";
			PreparedStatement statement = connection.prepareStatement(command);

			//Set attributes to be updated
			statement.setInt(++statementIndex, reimbursement.getManager().getId());
			statement.setString(++statementIndex, reimbursement.getStatus().toUpperCase());

			//Set Id on where clause
			statement.setInt(++statementIndex, reimbursement.getId());

			if(statement.executeUpdate() > 0) {
				return true;
			}
		} catch (SQLException e) {
			LogUtil.logger.warn("Exception when updating a reimbursement request", e);
		}
		return false;
	}
}

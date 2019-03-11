/********** Drop and Create Database **********/
DROP USER REIMBURSEMENT_DB CASCADE;

CREATE USER REIMBURSEMENT_DB IDENTIFIED BY p4ssw0rd;
GRANT DBA TO REIMBURSEMENT_DB WITH ADMIN OPTION;

DISCONNECT;

/* With this line we connect to the database so we don't have to do REIMBURSEMENT_DB.TABLE_NAME */
CONNECT REIMBURSEMENT_DB/p4ssw0rd;

/* We call all sql files now in order */
@C:\ERS\CREATES-ALTERS.sql;

@C:\ERS\SEQUENCES.sql;

@C:\ERS\FUNCTIONS.sql;

@C:\ERS\TRIGGERS.sql;

@C:\ERS\INSERTS.sql;

COMMIT;
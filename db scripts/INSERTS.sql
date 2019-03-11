/********** Inserts **********/

INSERT INTO USER_ROLE VALUES (1,'EMPLOYEE');
INSERT INTO USER_ROLE VALUES (2,'MANAGER');

INSERT INTO USER_T VALUES (NULL,'PETER','ALAGNA','PALAGNAJR','123456','PALAGNAJR@GMAIL.COM',1);
INSERT INTO USER_T VALUES (NULL,'IRA','DASILVA','IDASILVA','123456','IRA@REVATURE.COM',1);
INSERT INTO USER_T VALUES (NULL,'STEVEN','KELSEY','SKELSEY','123456','STEVEN.KELSEY@REVATURE.COM',2);

INSERT INTO REIMBURSEMENT_TYPE VALUES (1,'OTHER');
INSERT INTO REIMBURSEMENT_TYPE VALUES (2,'COURSE');
INSERT INTO REIMBURSEMENT_TYPE VALUES (3,'CERTIFICATION');
INSERT INTO REIMBURSEMENT_TYPE VALUES (4,'TRAVELING');

INSERT INTO REIMBURSEMENT_STATUS VALUES (1,'PENDING');
INSERT INTO REIMBURSEMENT_STATUS VALUES (2,'DECLINED');
INSERT INTO REIMBURSEMENT_STATUS VALUES (3,'APPROVED');
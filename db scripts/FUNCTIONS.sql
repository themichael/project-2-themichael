/********** Functions **********/

CREATE OR REPLACE FUNCTION GET_HASH(STR VARCHAR2) RETURN VARCHAR2
IS
EXTRA VARCHAR2(10) := 'SHUFFLE';
BEGIN
  RETURN TO_CHAR(DBMS_OBFUSCATION_TOOLKIT.MD5(INPUT => UTL_I18N.STRING_TO_RAW(DATA => EXTRA || STR)));
END;
/
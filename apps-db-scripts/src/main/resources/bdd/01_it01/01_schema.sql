-- CREATION OF SCHEMA

-- TABLE T_ROLE
CREATE TABLE T_ROLE(
RO_ID BIGINT NOT NULL,
RO_CODE VARCHAR(50),
RO_NAME VARCHAR(255),
CRE_DATE TIMESTAMP default CURRENT_TIMESTAMP,
UP_DATE TIMESTAMP default CURRENT_TIMESTAMP,
PRIMARY KEY(RO_ID)
);
-- COMMENTS ON COLUMNS
COMMENT ON COLUMN T_ROLE.RO_ID IS 'ID of the role';
COMMENT ON COLUMN T_ROLE.RO_CODE IS 'Code of the role';
COMMENT ON COLUMN T_ROLE.RO_NAME IS 'Label of the role';
COMMENT ON COLUMN T_ROLE.CRE_DATE IS 'Date of creation';
COMMENT ON COLUMN T_ROLE.UP_DATE IS 'Update date';
-- CONSTRAINTS
ALTER TABLE T_ROLE ADD CONSTRAINT U_ROLE_CODE UNIQUE(RO_CODE);
-- SEQUENCE
CREATE SEQUENCE ROLE_SEQ        
  INCREMENT 1                     
  MINVALUE 1                      
  MAXVALUE 9223372036854775807    
  START 1                         
  CACHE 1;
  

-- TABLE T_FUNCTION
CREATE TABLE T_FUNCTION(
FU_ID BIGINT NOT NULL,
FU_CODE VARCHAR(50),
FU_NAME VARCHAR(255),
CRE_DATE TIMESTAMP default CURRENT_TIMESTAMP,
UP_DATE TIMESTAMP default CURRENT_TIMESTAMP,
PRIMARY KEY(FU_ID)
);
-- COMMENTS ON COLUMNS
COMMENT ON COLUMN T_FUNCTION.FU_ID IS 'ID of the function';
COMMENT ON COLUMN T_FUNCTION.FU_CODE IS 'Code of the function';
COMMENT ON COLUMN T_FUNCTION.FU_NAME IS 'Label of the function';
COMMENT ON COLUMN T_FUNCTION.CRE_DATE IS 'Date of creation';
COMMENT ON COLUMN T_FUNCTION.UP_DATE IS 'Update date';
-- CONSTRAINTS
ALTER TABLE T_FUNCTION ADD CONSTRAINT U_FONCTION_CODE UNIQUE(FU_CODE);
-- SEQUENCE
CREATE SEQUENCE FUNCTION_SEQ        
  INCREMENT 1                     
  MINVALUE 1                      
  MAXVALUE 9223372036854775807    
  START 1                         
  CACHE 1;

  
-- TABLE T_ROLE_FONCTION
CREATE TABLE T_ROLE_FUNCTION(
RO_ID BIGINT NOT NULL,
FU_ID BIGINT NOT NULL,
CRE_DATE TIMESTAMP default CURRENT_TIMESTAMP,
UP_DATE TIMESTAMP default CURRENT_TIMESTAMP,
PRIMARY KEY(RO_ID,FU_ID)
);
-- COMMENTS ON COLUMNS
COMMENT ON COLUMN T_ROLE_FUNCTION.RO_ID IS 'ID of the role';
COMMENT ON COLUMN T_ROLE_FUNCTION.FU_ID IS 'ID of the function';
-- CONSTRAINTS
ALTER TABLE T_ROLE_FUNCTION ADD CONSTRAINT FK_RO_ID FOREIGN KEY(RO_ID) REFERENCES T_ROLE(RO_ID);
ALTER TABLE T_ROLE_FUNCTION ADD CONSTRAINT FK_FU_ID FOREIGN KEY(FU_ID) REFERENCES T_FUNCTION(FU_ID);
  
  
-- TABLE T_CUSTOMER
CREATE TABLE T_CUSTOMER(
CU_ID BIGINT NOT NULL,
CU_NAME VARCHAR(250) NOT NULL,
CU_FIRST_NAME VARCHAR(250) NOT NULL,
CU_MAIL VARCHAR(250) NOT NULL,
CU_SALT VARCHAR(250),
CU_PASSWORD VARCHAR (250),
CU_PHONE VARCHAR(50),
CU_ROLE_ID BIGINT NOT NULL,
CRE_DATE TIMESTAMP default CURRENT_TIMESTAMP,
UP_DATE TIMESTAMP default CURRENT_TIMESTAMP,
PRIMARY KEY(CU_ID)
);
-- COMMENTS ON COLUMNS
COMMENT ON COLUMN T_CUSTOMER.CU_ID IS 'ID of the customer';
COMMENT ON COLUMN T_CUSTOMER.CU_NAME IS 'Name of the customer';
COMMENT ON COLUMN T_CUSTOMER.CU_FIRST_NAME IS 'First name of the customer';
COMMENT ON COLUMN T_CUSTOMER.CU_MAIL IS 'Mail of the customer';
COMMENT ON COLUMN T_CUSTOMER.CU_SALT IS 'Salt of control';
COMMENT ON COLUMN T_CUSTOMER.CU_PASSWORD IS 'Password of the customer';
COMMENT ON COLUMN T_CUSTOMER.CU_PHONE IS 'Phone number of the customer';
COMMENT ON COLUMN T_CUSTOMER.CU_ROLE_ID IS 'ID role of the customer';
COMMENT ON COLUMN T_CUSTOMER.CRE_DATE IS 'Date of creation';
COMMENT ON COLUMN T_CUSTOMER.UP_DATE IS 'Update date';
-- CONSTRAINTS
ALTER TABLE T_CUSTOMER ADD CONSTRAINT U_CUSTOMER_MAIL UNIQUE(CU_MAIL);
ALTER TABLE T_CUSTOMER ADD CONSTRAINT U_CUSTOMER_PASSWORD UNIQUE(CU_PASSWORD);
ALTER TABLE T_CUSTOMER ADD CONSTRAINT U_CUSTOMER_SALT UNIQUE(CU_SALT);
ALTER TABLE T_CUSTOMER ADD CONSTRAINT FK_CU_ROLE_ID FOREIGN KEY(CU_ROLE_ID) REFERENCES T_ROLE(RO_ID);
-- SEQUENCE
CREATE SEQUENCE CUSTOMER_SEQ        
  INCREMENT 1                     
  MINVALUE 1                      
  MAXVALUE 9223372036854775807    
  START 1                         
  CACHE 1;
  

-- TABLE T_TOKEN
CREATE TABLE T_TOKEN(
TT_ID BIGINT NOT NULL,
TT_VALUE VARCHAR(255) NOT NULL,
TT_EXPIRATION TIMESTAMP,
TT_CUSTOMER_ID BIGINT NOT NULL,
CRE_DATE TIMESTAMP default CURRENT_TIMESTAMP,
UP_DATE TIMESTAMP default CURRENT_TIMESTAMP,
PRIMARY KEY(TT_ID)
);
-- COMMENTS ON COLUMNS
COMMENT ON COLUMN T_TOKEN.TT_ID IS 'ID of the token';
COMMENT ON COLUMN T_TOKEN.TT_VALUE IS 'Value (GUID) of the token';
COMMENT ON COLUMN T_TOKEN.TT_EXPIRATION IS 'Expiration date of the token';
COMMENT ON COLUMN T_TOKEN.TT_CUSTOMER_ID IS 'ID of the customer';
COMMENT ON COLUMN T_TOKEN.CRE_DATE IS 'Date of creation';
COMMENT ON COLUMN T_TOKEN.UP_DATE IS 'Update date';
-- CONSTRAINTS
ALTER TABLE T_TOKEN ADD CONSTRAINT U_TOKEN_VALUE UNIQUE(TT_VALUE);
ALTER TABLE T_TOKEN ADD CONSTRAINT FK_TT_CUSTOMER_ID FOREIGN KEY(TT_CUSTOMER_ID) REFERENCES T_CUSTOMER(CU_ID);
-- SEQUENCE
CREATE SEQUENCE TOKEN_SEQ        
  INCREMENT 1                     
  MINVALUE 1                      
  MAXVALUE 9223372036854775807    
  START 1                         
  CACHE 1;
  
 
 -- TABLE T_LOG_API
CREATE TABLE T_LOG_API(
LA_ID BIGINT NOT NULL,
LA_CODE_API VARCHAR(100) NOT NULL,
LA_VERSION_API VARCHAR(10) NOT NULL,
CRE_DATE TIMESTAMP default CURRENT_TIMESTAMP,
UP_DATE TIMESTAMP default CURRENT_TIMESTAMP,
PRIMARY KEY(LA_ID)
);
-- COMMENTS ON COLUMNS
COMMENT ON COLUMN T_LOG_API.LA_ID IS 'ID of the picture';
COMMENT ON COLUMN T_LOG_API.LA_CODE_API IS 'Code of the API';
COMMENT ON COLUMN T_LOG_API.CRE_DATE IS 'Date of creation';
COMMENT ON COLUMN T_LOG_API.UP_DATE IS 'Update date';
-- CONSTRAINTS
-- SEQUENCE
CREATE SEQUENCE LOG_API_SEQ        
  INCREMENT 1                     
  MINVALUE 1                      
  MAXVALUE 9223372036854775807    
  START 1                         
  CACHE 1;
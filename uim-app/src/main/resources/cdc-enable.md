ALTER DATABASE JC_SCDatabase SET SINGLE_USER WITH ROLLBACK IMMEDIATE;

ALTER DATABASE JC_SCDatabase COLLATE Chinese_PRC_CI_AS;
ALTER DATABASE JC_SCDatabase COLLATE Chinese_PRC_CI_AS_WS;


ALTER DATABASE JC_SCDatabase SET MULTI_USER;



SELECT is_cdc_enabled FROM sys.databases WHERE name = 'JC_SCDatabase';

EXEC sys.sp_cdc_enable_db;




USE JC_SCDatabase  
GO  
EXEC sys.sp_cdc_enable_table  
@source_schema = 'dbo',  
@source_name   = 'work_order',  
@role_name     = 'cdc_role',  
@supports_net_changes = 1  
GO


SELECT  name ,
is_tracked_by_cdc ,
CASE WHEN is_tracked_by_cdc = 0 THEN 'CDC功能禁用'
ELSE 'CDC功能启用'
END 描述
FROM    sys.tables;


SELECT SERVERPROPERTY(N'Collation');


SELECT name, collation_name
FROM sys.databases;




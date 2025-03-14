-- 使用root用户进入容器
docker exec -it --user root dev-mssql bash
-- 启用SqlServeragent：
/opt/mssql/bin/mssql-conf set sqlagent.enabled true

-- 查詢JC_SCDatabase是否開啓cdc
SELECT is_cdc_enabled FROM sys.databases WHERE name = 'JC_SCDatabase';
-- 開啓cdc
EXEC sys.sp_cdc_enable_db;
-- 禁用
EXEC sys.sp_cdc_disable_db;
-- 設置表監聽
EXEC sys.sp_cdc_enable_table
@source_schema = 'dbo',
@source_name   = 'work_order',
@role_name     = 'cdc_role';


-- 启用
USE acre  
GO  
EXEC sys.sp_cdc_enable_table  
@source_schema = 'dbo',  
@source_name   = 'work_order',  
@role_name     = 'cdc_role,  
@supports_net_changes = 1
GO

-- 禁用
USE acre  
GO  
EXEC sys.sp_cdc_disable_table  
@source_schema = 'dbo',  
@source_name   = 'work_order',  
@capture_instance = 'dbo_work_order'  
GO


-- 启用
USE JC_SCDatabase  
GO  
EXEC sys.sp_cdc_enable_table  
@source_schema = 'dbo',  
@source_name   = 'work_order',  
@role_name     = 'cdc_role',  
@supports_net_changes = 1  
GO


-- 禁用
USE JC_SCDatabase  
GO  
EXEC sys.sp_cdc_disable_table  
@source_schema = 'dbo',  
@source_name   = 'work_order',  
@capture_instance = 'work_order'  
GO

-- 查询在当前数据库下所有的表：
SELECT * FROM INFORMATION_SCHEMA.TABLES

SELECT  name ,
is_tracked_by_cdc ,
CASE WHEN is_tracked_by_cdc = 0 THEN 'CDC功能禁用'
ELSE 'CDC功能启用'
END 描述
FROM    sys.tables;


SELECT name,is_cdc_enabled FROM sys.databases WHERE is_cdc_enabled = 1;
SELECT name,is_tracked_by_cdc FROM sys.tables WHERE is_tracked_by_cdc = 1;
ALTER AUTHORIZATION ON DATABASE::[work_order] TO [sa]
SELECT * FROM INFORMATION_SCHEMA.TABLES



[//]: # (USE db_name;)
[//]: # (EXEC sys.sp_cdc_enable_table)
[//]: # (@source_schema = N'schema_name',)
[//]: # (@source_name = N'table_name',)
[//]: # (@role_name     = N'cdc_role_name',)
[//]: # (@filegroup_name = N'cdc_filegroup',)
[//]: # (@supports_net_changes = 0)


https://blog.csdn.net/Jerry_991/article/details/129022976
# 修改编码
-- 查询一下当前数据库的默认编码
select SERVERPROPERTY('Collation');
SELECT SERVERPROPERTY(N'Collation');
SELECT name, collation_name
FROM sys.databases;

-- 将数据库设置为单用户模式
ALTER DATABASE YourDatabaseName SET SINGLE_USER WITH ROLLBACK IMMEDIATE;

-- 修改数据库的排序规则为支持中文的规则，例如 Chinese_PRC_CI_AS
ALTER DATABASE YourDatabaseName COLLATE Chinese_PRC_CI_AS;

-- 修改表编码
ALTER TABLE [work_order] COLLATE Chinese_PRC_CI_AS;

-- 将数据库设置回多用户模式
ALTER DATABASE YourDatabaseName SET MULTI_USER;


SET NAMES 'utf8';







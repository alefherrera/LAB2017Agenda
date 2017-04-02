"C:\Program Files (x86)\MySQL\MySQL Server 5.5\bin\MySQLInstanceConfig.exe" -i -q ServiceName=MySQL RootPassword=root ServerType=DEVELOPER DatabaseType=INNODB Port=3306 Charset=utf8
"C:\Program Files (x86)\MySQL\MySQL Server 5.5\bin\mysql.exe" --user=root --password=root < scriptAgenda.sql

SET backupPath="C:\ws\temp\"

@For /F "tokens=2,3,4 delims=/ " %%A in ('Date /t') do @( 
    Set Month=%%A
    Set Day=%%B
    Set Year=%%C   
)    

@echo DAY = %Day%
@echo Month = %Month%
@echo Year = %Year%  
  
mysqldump -u root -p plantbeheer --password=HteBmysql2017 > "%backupPath%BACKUP%Day%-%Month%.sql"
﻿# customer-phone-validator-back-end
# install packages
run maven clean install
# To run the application 
active profile in application.properties file
must be set as below to run the application 
spring.profiles.active=dev
(default is dev)

# To test the application 
- active profile in application.properties file
  must be set as below to run the application
  spring.profiles.active=test
- run maven clean test 

# Database with the updates
you can do any of the below options 
1) I have attached the sample.db with the updates needed to include the new table added 
you can use it directly
or 
2) Use the migrations scripts added in the resource/db/migration and allow the below properties  
spring.flyway.enabled=true
or
3) Run the project and a new database will be created in the project folder with empty data

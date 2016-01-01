# loop - UpSport backend

- new user
curl -v -H "Content-Type: application/json" -X POST -d "{\"name\":\"UpSport\", \"password\":\"password\", \"email\": \"local.part@domain.part\"}" http://localhost:1979/users/create

- new upload see project admin under upsport

- get all
http://localhost:1979/users
http://localhost:1979/uploads

Developer notes:
As the project using HK2 as dependency injection framework, to debug in Eclipse, setup maven to use jar dependency(http://stackoverflow.com/questions/2243722/m2eclipse-maven-dependencies-as-jars-not-projects). 
"Right click on the project, choose Properties, select Maven, and uncheck the 'Resolve dependencies from Workspace projects'"

Deployment: 
```
mvn clean install
cd app/web/web-rest/target
tar xvf web-rest-1.0-SNAPSHOT-bin.tar.gz
cd web-rest-1.0-SNAPSHOT/bin
./start.sh <--admin path-->
```

# loop
UpSport backend

- new user

curl -v -H "Content-Type: application/json" -X POST -d "{\"name\":\"UpSport\", \"password\":\"password\", \"email\": \"local.part@domain.part\"}" http://localhost:1979/users/create

- get all users

http://localhost:1979/users

As the project using HK2 as dependency injection framework, to debug in Eclipse, setup maven to use jar instead of workspace project as dependency is important.

http://stackoverflow.com/questions/2243722/m2eclipse-maven-dependencies-as-jars-not-projects
"Right click on the project, choose Properties, select Maven, and uncheck the 'Resolve dependencies from Workspace projects'"
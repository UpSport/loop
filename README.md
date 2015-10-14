# loop
UpSport backend

new user
curl -v -H "Content-Type: application/json" -X POST -d "{\"name\":\"UpSport\", \"password\":\"password\", \"email\": \"local.part@domain.part\"}" http://localhost:1979/api/users/new

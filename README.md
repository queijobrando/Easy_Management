DOCUMENTOS:
https://drive.google.com/drive/folders/1VV3BbzJcXm1neEhpjbmEd0KY67juPwSM?usp=sharing

docker network create easymanagement-network

docker run --name easymanagementdb -p 5432:5432 -e POSTGRES_PASSWORD=postgres -e POSTGRES_USER=postgres -e POSTGRES_DB=easymanagement --network easymanagement-network postgres:16.3

docker run --name pgadmin4 -p 15432:80 -e PGADMIN_DEFAULT_EMAIL=admin@admin.com -e PGADMIN_DEFAULT_PASSWORD=admin --network easymanagement-network dpage/pgadmin4
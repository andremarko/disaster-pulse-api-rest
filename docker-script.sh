
#!/bin/bash

SCHEMA=disaster_pulse
DB_USER=disasterpersist
DB_PASS=disasterpass

echo "Qual banco para persistência você deseja utilizar?"
echo "1 - Oracle 19c - FIAP"
echo "2 - MySQL 8.0"
read opt


if [ "$opt" = "1" ]; then 
    profile=oracle
    echo "Digite o seu usuário no Oracle FIAP 19c"
    read ORA_USER
    echo "Digite a sua senha"
    read -s ORA_PASS

    docker build -t disaster-pulse-backend-image .  

    docker run -d --name disaster-pulse-backend -p 8080:8080 \
        -e SPRING_PROFILES_ACTIVE=$profile \
        -e DB_USER=$ORA_USER \
        -e DB_PASS=$ORA_PASS \
        disaster-pulse-backend-image

elif [ "$opt" = "2" ]; then
    profile=mysql

    docker network create disaster-pulse-network

    docker volume create disaster-pulse-db-data

    docker run --name disaster-pulse-mysql --network disaster-pulse-network \
        -e MYSQL_ROOT_PASSWORD=disasteroot \
        -e MYSQL_DATABASE=$SCHEMA \
        -e MYSQL_USER=$DB_USER \
        -e MYSQL_PASSWORD=$DB_PASS \
        -v disaster-pulse-db-data:/var/lib/mysql \
        -p 3306:3306 \
        -d mysql:8.0    

    docker build -t disaster-pulse-backend-image .

    docker run -d --name disaster-pulse-backend --network disaster-pulse-network -p 8080:8080 \
        -e SPRING_PROFILES_ACTIVE=$profile \
        -e DB_USER=$DB_USER \
        -e DB_PASS=$DB_PASS \
        disaster-pulse-backend-image
else
    echo "Opção inválida"
fi



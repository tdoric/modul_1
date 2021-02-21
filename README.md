# Microservices
[Modul 1](https://github.com/tdoric/modul_1) - dao,repository and objects  <br />
[Modul 2](https://github.com/tdoric/modul_2) - login  <br />
[Modul 3](https://github.com/tdoric/module_3)- signup <br />
[Modul 4](https://github.com/tdoric/module_4)- security <br />
[Modul 5](https://github.com/tdoric/module_5)- likes <br />
[Modul 6](https://github.com/tdoric/module_6)- groups <br />
[Modul 7](https://github.com/tdoric/module_7)- groups_article <br />
[Modul 8](https://github.com/tdoric/module_8)- article <br />
[Modul 9](https://github.com/tdoric/module_9)- news <br />
<br /> <br />

# Prerequisites
- [PostgreSQL](https://hub.docker.com/_/postgres)
- [JAVA 11](https://jdk.java.net/11/)
- [MAVEN](https://maven.apache.org/download.cgi)



## USAGE
### MAVEN CLEAN INSTALL
```
CLEAN INSTALL ALL 9 MODULES
```
### CREATE DOCKER IMAGES
```
docker build -t spring-boot-login            -f Dockerfile .
docker build -t spring-boot-signup           -f Dockerfile .
docker build -t spring-boot-likes            -f Dockerfile .
docker build -t spring-boot-groups           -f Dockerfile .
docker build -t spring-boot-groups-articles  -f Dockerfile .
docker build -t spring-boot-article          -f Dockerfile .
docker build -t spring-boot-news             -f Dockerfile .
```
### CREATE DOCKER-COMPOSE.YML
```
version: "3"
services:
  postgres:
    image: postgres:latest
    network_mode: bridge
    container_name: postgres
    volumes:
      - postgres-data:/var/lib/postgresql/data
    expose:
    - 5432
    ports:
      - 5432:5432
    environment:
         - POSTGRES_PASSWORD=password
         - POSTGRES_USER=admin
         - POSTGRES_DB=psql_db
    restart: unless-stopped
#*****************************************
  spring-boot-login:
    image: spring-boot-login:latest
    network_mode: bridge
    #container_name: modul-login
    expose:
      - 8080
    ports:
      - 8080:8080
    restart: unless-stopped
    depends_on:
      - postgres
    links:
      - postgres
     
#*****************************************
  spring-boot-signup:
    image: spring-boot-signup:latest
    network_mode: bridge
    container_name: modul-signup
    expose:
      - 8090
    ports:
      - 8090:8090
    restart: unless-stopped
    depends_on:
      - postgres
    links:
      - postgres
#*****************************************
  spring-boot-likes:
    image: spring-boot-likes:latest
    network_mode: bridge
    container_name: modul-likes
    expose:
      - 8091
    ports:
      - 8091:8091
    restart: unless-stopped
    depends_on:
      - postgres
    links:
      - postgres
#*****************************************
  spring-boot-groups:
    image: spring-boot-groups:latest
    network_mode: bridge
    container_name: modul-groups
    expose:
      - 8092
    ports:
      - 8092:8092
    restart: unless-stopped
    depends_on:
      - postgres
    links:
      - postgres
#*****************************************
  spring-boot-groups-articles:
    image: spring-boot-groups-articles:latest
    network_mode: bridge
    container_name: modul-groups-articles
    expose:
      - 8093
    ports:
      - 8093:8093
    restart: unless-stopped
    depends_on:
      - postgres
    links:
      - postgres 
#*****************************************
  spring-boot-article:
    image: spring-boot-article:latest
    network_mode: bridge
    container_name: modul-article
    expose:
      - 8094
    ports:
      - 8094:8094
    restart: unless-stopped
    depends_on:
      - postgres
    links:
      - postgres  
#*****************************************
  spring-boot-news:
    image: spring-boot-news:latest
    network_mode: bridge
    container_name: modul-news
    expose:
      - 8095
    ports:
      - 8095:8095
    restart: unless-stopped
    depends_on:
      - postgres
    links:
      - postgres
volumes:
  postgres-data:
```
### RUN DOCKER-COMPOSE.YML
```
docker-compose up
```
### CREATE TABLES IN DATABASE 
``` 
CREATE TABLE accounts (
   user_id serial PRIMARY KEY,
   username VARCHAR ( 50 ) UNIQUE NOT NULL,
   password VARCHAR ( 80 ) NOT NULL,
   email VARCHAR ( 255 ) UNIQUE NOT NULL,
   created_on TIMESTAMP NOT NULL,
   last_login TIMESTAMP,
   status int not null
);
CREATE TABLE roles(
   role_id serial PRIMARY KEY,
   role_name VARCHAR (255) UNIQUE NOT null,
   status int not null
);
CREATE TABLE account_roles (
   user_id INT NOT NULL,
   role_id INT NOT NULL,
   grant_date TIMESTAMP,
   status int not null,
   PRIMARY KEY (user_id, role_id),
   FOREIGN KEY (role_id)
      REFERENCES roles (role_id),
   FOREIGN KEY (user_id)
      REFERENCES accounts (user_id)
);
CREATE TABLE articles(
   article_id serial PRIMARY KEY,
   title VARCHAR(150) unique NOT NULL,
   publish_date date,
   status int not null
);
CREATE TABLE account_articles (
   user_id INT NOT NULL,
   article_id INT NOT NULL,
   status int not null,
   PRIMARY KEY (user_id, article_id),
   FOREIGN KEY (article_id)
      REFERENCES articles (article_id),
   FOREIGN KEY (user_id)
      REFERENCES accounts (user_id)
);
CREATE TABLE likes (
   user_id INT NOT NULL,
   article_id INT NOT NULL,
   like_date TIMESTAMP,
   PRIMARY KEY (user_id, article_id),
   FOREIGN KEY (article_id)
      REFERENCES articles (article_id),
   FOREIGN KEY (user_id)
      REFERENCES accounts (user_id)
);
CREATE TABLE groups (
   groups_id serial primary key,
   user_id INT not null references accounts(user_id),
   groupname VARCHAR(80) not null,
   status int not null,
   unique(user_id,groupname)
);
CREATE TABLE groups_articles (
   groups_id INT NOT NULL,
   article_id INT NOT NULL,
   status int not null,
   FOREIGN KEY (article_id)
      REFERENCES articles (article_id),
   FOREIGN KEY (groups_id)
      REFERENCES groups (groups_id),
   unique(groups_id,article_id)
);
``` 
### INSERT ROLES IN TABLE
``` 
INSERT INTO roles(role_name, status) VALUES('Writer', 1);
INSERT INTO roles(role_name, status) VALUES('Reader', 1);
``` 
### REQUESTS
``` 
#SIGNUP REQUEST  - http://localhost:8090/api/auth/signup  - NO SECURITY
{
    "username": "test",
    "password": "user",
    "email": "user@gmail.com",
    "role": [ 1, 2]
}
#LOGIN REQUEST - http://localhost:8080/api/auth/signin - USERNAME AND PASSWORD REQUIRED
{
    "username": "test",
    "password": "user"
}
#CREATE ARTICLE - http://localhost:8094/api/article/create - JWT FROM LOGIN NEEDED AND ROLE WRITER
{
    "title":"Test article",
    "coAuthors":[]
}
#LIKE REQUEST - http://localhost:8091/api/like - JWT FROM LOGIN NEEDED
{
    "articleId": 13
}
#GROUP REQUEST - http://localhost:8092/api/create - JWT FROM LOGIN NEEDED
{
    "groupname":"Test group"
}
#ADD ARTICLE TO GROUP - http://localhost:8093/api/group/add-to-group - JWT FROM LOGIN NEEDED
{
    "groupId":6,
    "articleId": 13
}
#GET SPECIFIC NEWS - http://localhost:8095/api/news-feed/get - JWT FROM LOGIN NEEDED
{
    "type":"spec",
    "writerId": 9
}
#GET ALL NEWS - http://localhost:8095/api/news-feed/get - JWT FROM LOGIN NEEDED
{
    "type":"all"
}

``` 


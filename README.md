## В этом задании нужно спроектировать SQL схему для сайта по продажам машин.

На сайте должны быть объявления. В объявлении должно быть: описание, марка машины, тип кузова, фото.

Объявление имеет статус продано или нет.

Должны существовать пользователи. Автор объявления.

## Стек технологий:

![java](https://img.shields.io/badge/Java--17-ED8B00?style=for-the-badge&logo=java&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot--2.7.1-F2F4F9?style=for-the-badge&logo=spring-boot)
![Bootstrap](https://img.shields.io/badge/Bootstrap--5.2.2-563D7C?style=for-the-badge&logo=bootstrap&logoColor=white)
![PostgresSQL](https://img.shields.io/badge/PostgreSQL--42.3.6-316192?style=for-the-badge&logo=postgresql&logoColor=white)
[![Hibernate](https://img.shields.io/badge/Hibernate--5.6.11.Final-59666C?style=for-the-badge&logo=Hibernate&logoColor=white)](https://hibernate.org/)

![Thymeleaf](https://img.shields.io/badge/Thymeleaf-3.0.0.RELEASE-blue)
![Liquibase](https://img.shields.io/badge/Liquibase-4.9.1-red)
![Junit](https://img.shields.io/badge/JUNIT-4.12-orange)
![Mockito](https://img.shields.io/badge/MOCKITO-3.5.13-red)
![H2](https://img.shields.io/badge/hsqldb-2.1.214-yellowgreen)
![Log4j](https://img.shields.io/badge/Log4j-2.18.0-green)

## Задание.

1. Текущее задание выполняйте в проекте job4j_cars, который мы создали в предыдущем задании.

2. Спроектируйте SQL схему для сайта по продажам машин.

3. Добавьте POJO классы и hibernate mapping. В этом задании нужно использовать аннотации.

4. Загрузите схему в корень репозитория в папку db.

5. Оставьте ссылку на коммит. Переведите ответственного на Петра Арсентьева.

## Перед запуском установите:

- Java 17
- Apache Maven 3.x


## Запуск приложения

1. Создать бд:
```sql
create database car_version_3;
```

2. Запуск приложения с maven. Перейдите в корень проекта через командную строку и выполните команды:
```
    mvn clean install
    mvn spring-boot:run
```
# Прочитайте в первую очередь

* Данный проект является одним из под-проектов в рамках курса "Docker и K8S глазами разработчика".
* В данном проекта мы рассматриваем простой REST API, который имеет один ендпоинт, возвращающий предустановленные данные

# Старт проекта

* Для локального старта (без использования Docker) вам потребуется установленный Gradle, Postgres, JDK.
* Для локального старта (без использования Docker) вам потребуется запущенная БД Mongo DB
* 
* Необходимо выполнить команды
  <br/>`gradle build`
  <br/>`gradle bootRun`
  <br/>Отправить запрос для процессинга и наполнения БД:

```
curl --location --request POST 'localhost:9200/api/v1/countries/process'
```

Отправить запрос для получения списка всех стран:
```
curl --location 'localhost:9200/api/v1/countries'
```
* Пример ответа:

```
[
    {
        "capital": [
            "Chișinău"
        ],
        "cca2": "MD",
        "cca3": "MDA"
    },
    {
        "capital": [
            "Washington, D.C."
        ],
        "cca2": "US",
        "cca3": "USA"
    },
    ...
]
```
Отправить запрос для получения списка страны по CC2 коду (расширенный ДТО):
```
curl --location 'localhost:9200/api/v1/countries/cc2/MD'
```
* Пример ответа:

```
{
    "region": "Europe",
    "capital": [
        "Chișinău"
    ],
    "subregion": "Eastern Europe",
    "area": 33846,
    "population": 2617820,
    "independent": true,
    "cca2": "MD",
    "cca3": "MDA"
}
```

Отправить запрос для получения списка страны по CC2 коду (стандартный ДТО):
```
curl --location 'localhost:9200/api/v1/countries/cc2/PL'
```
* Пример ответа:

```
{
    "capital": [
        "Warsaw"
    ],
    "cca2": "PL",
    "cca3": "POL"
}
```

* Для старта с использованием **Docker** необходимо:
* Установить Docker
* Потянуть и запустить Mongo

```
docker pull mongo:latest
docker run -d -p 27017:27017 mongo
```
* Создать образ country-api:
```
docker build . --tag=country-api:latest   
```
* Запустить контейнер

```
docker run -p 9200:9200 country-api:latest
```

<br/>Отправить запрос для процессинга и наполнения БД:

```
curl --location --request POST 'localhost:9200/api/v1/countries/process'
```

Отправить запрос для получения списка всех стран:
```
curl --location 'localhost:9200/api/v1/countries'
```
* Пример ответа:

```
[
    {
        "capital": [
            "Chișinău"
        ],
        "cca2": "MD",
        "cca3": "MDA"
    },
    {
        "capital": [
            "Washington, D.C."
        ],
        "cca2": "US",
        "cca3": "USA"
    },
    ...
]
```
Отправить запрос для получения списка страны по CC2 коду (расширенный ДТО):
```
curl --location 'localhost:9200/api/v1/countries/cc2/MD'
```
* Пример ответа:

```
{
    "region": "Europe",
    "capital": [
        "Chișinău"
    ],
    "subregion": "Eastern Europe",
    "area": 33846,
    "population": 2617820,
    "independent": true,
    "cca2": "MD",
    "cca3": "MDA"
}
```

Отправить запрос для получения списка страны по CC2 коду (стандартный ДТО):
```
curl --location 'localhost:9200/api/v1/countries/cc2/PL'
```
* Пример ответа:

```
{
    "capital": [
        "Warsaw"
    ],
    "cca2": "PL",
    "cca3": "POL"
}
```

### Дополнительные ссылки

* [GitHub repository](https://github.com/proselytear/country/country-api)
* [Proselyte Community TG](https://t.me/pse_club)

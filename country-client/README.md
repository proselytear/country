# Прочитайте в первую очередь
* Данный проект является одним из под-проектов в рамках курса "Docker и K8S глазами разработчика".
* В данном проекта мы рассматриваем клиент для country-api, который отображает данные по странам.

# Старт проекта
* Для локального старта (без использования Docker) вам потребуется установленный node
* Необходимо выполнить команды
  <br/>`npm install`
  <br/>`npm start`
  <br/>Открыть ссылку
  <br/>`http://localhost:3000/`
* Для старта с использованием Docker необходимо:
* Установить Docker
* Создать образ
  <br/>`docker build . --tag=country-client:latest`
* Запустить контейнер
  <br/>`docker run -p 3000:3000 country-client:latest`
*Открыть ссылку
```
http://localhost:3000/
```
### Дополнительные ссылки
* [GitHub repository](https://github.com/proselytear/country/country-client)
* [Proselyte Community TG](https://t.me/pse_club)


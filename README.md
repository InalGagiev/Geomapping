SpringAngularEcommerce
Веб приложение созданное для геомаппинга, которое использует яндекс апи. В приложении присутствует кэширование запросов с помощью redis,


# Installation
- склонировать репозиторий
```
git clone https://github.com/InalGagiev/Geomapping
```

- сбилдить проект(создать джар файл)
```
gradle build
```

- в докер файле изменить директорию на котороую будут сохраняться файлы бд.
```yml
    volumes:
      - D:\postgres_datapart:/var/lib/postgresql/data
```
D:\postgres_datapart - мы меняем на абсолютный путь к той папке где вы хотите чтобы хранились файлы постгреса

- Запуск докер файла(предварительно должен быть установлен докер)
```cmd
docker-compose up --build
```
# Technologies:
- Spring Boot
- Redis
- actuator, prometheus
- grafana

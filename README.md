## Программа по считыванию информации из базы данных
#### Это простой пример использования Apache Camel для выполнения SQL-запросов к базе данных PostgreSQL с использованием компонента `camel-jdbc`.

## Требования

- Java 8 или выше
- Maven
- PostgreSQL

## Установка

1. Установите [PostgreSQL](https://www.postgresql.org/download/) и запустите сервер базы данных.
2. Склонируйте репозиторий используя команду: `git clone https://github.com/yourusername/taskAssigment`
3. Перейдите в в скачанный репозиторий используя команду `cd taskAssigment` 
4. Соберите проект с помощью Maven командой `mvn clean install`
5. Запустите приложение: `mvn exec:java -Dexec.mainClass="CamelTester`

## Описание
### Приложение выполняет следующие действия

- Создает базу данных **simple_db**
- Создает таблицу **company**
- Вставляет тестовые данные в таблицу **company**
- Запускает Apache Camel маршрут, который каждые 5 секунд выполняет SQL-запрос к таблице COMPANY и выводит результат в лог

## Пример вывода
При запуске приложения в консоли будет отображаться информация о запуске Apache Camel и результат выполнения SQL-запроса:
`[{id=1, name=Alex, salary=20000.0}, {id=2, name=Masha, salary=42000.0}, {id=3, name=Sim, salary=62000.0}, {id=4, name=Lola, salary=19000.0}, {id=5, name=Max, salary=50000.0}]`
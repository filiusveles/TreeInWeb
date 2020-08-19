# Задача
Разработать страницу, отображающую в браузере древовидную структуру.

# Функциональность
**(Все данные сервер передает и получает в формате JSON)**
* Выделенный элемент отмечен другим цветом.
* Возможность добавление/удаления/изменения узлов дерева
* Перенос папки, включая под-папки в выбранную папку
* Хранение данных в БД
* Реализована "ленивая" загрузка папок и задержка в 2 сек при раскрытии папок

# Использованные технологии
1. Клиент:
    * **jsTree** - jQuery плагин для построения дерева
    * **jQuery** - передача данных на сервер + обнавление информации на сайте
    * **Javascript** - добавление функциональности кнопкам

2. Сервер:
    * **Spring MVC** - реализация RESTful сервера на архитектуре MVC
    * **Spring ORM + Hibernate** - работа с БД
    * **Jackson** - работа с JSON данными 
    * **Tomcat** - сервер приложения
    
    

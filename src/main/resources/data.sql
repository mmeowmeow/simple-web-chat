-- клиенты
insert into `User` (first_name, last_name, email)
values ('Joycelin', 'Pasticznyk', 'jpasticznyk0@tuttocitta.it');
insert into `User` (first_name, last_name, email)
values ('Winifield', 'Wilderspoon', 'wwilderspoon1@spiegel.de');
insert into `User` (first_name, last_name, email)
values ('Tallie', 'Petrashkov', 'tpetrashkov2@smugmug.com');
insert into `User` (first_name, last_name, email)
values ('Kellby', 'McCauley', 'kmccauley3@nymag.com');
insert into `User` (first_name, last_name, email)
values ('Artair', 'Grayley', 'agrayley4@go.com');
insert into `User` (first_name, last_name, email)
values ('Leslie', 'Schutter', 'gschutter5@washington.edu');
insert into `User` (first_name, last_name, email)
values ('Sidney', 'Woolfenden', 'swoolfenden6@free.fr');
insert into `User` (first_name, last_name, email)
values ('Reggie', 'Baum', 'rbaum7@cnbc.com');
insert into `User` (first_name, last_name, email)
values ('Clemmie', 'Kale', 'ckale8@sohu.com');
insert into `User` (first_name, last_name, email)
values ('Michaella', 'Kindall', 'mkindall9@networksolutions.com');

-- сотрудники
insert into Employee (first_name, last_name)
values ('Alex', 'Levinski');
insert into Employee (first_name, last_name)
values ('Bob', 'Ross');
insert into Employee (first_name, last_name)
values ('Adolf', 'Hatler');
insert into Employee (first_name, last_name)
values ('James', 'Franco');

-- запросы от клиентов
insert into Request (UserID, message, date_of_contact)
values (1, 'Здравствуйте. У меня не добавляются товары в корзину.', '2022-02-04');
insert into Request (UserID, message, date_of_contact)
values (3, 'Здравствуйте. У меня не хотят добавляться товары в корзину.', '2022-06-02');
insert into Request (UserID, message, date_of_contact)
values (7, 'Здравствуйте. У меня проблема на этапе оплаты заказа.', '2022-03-29');
insert into Request (UserID, message, date_of_contact)
values (10, 'Здравствуйте. У меня списали больше денег, чем сумма заказа.', '2022-08-29');

-- инциденты, решенные с помощью информации из Incident_for_help
insert into Incident (RequestID, EmployeeID, solution, date_of_solution, rating)
values (1, 1, NULL, '2022-02-05', 10);
insert into Incident (RequestID, EmployeeID, solution, date_of_solution, rating)
values (2, 2, NULL, '2022-06-07', 10);
-- инциденты, решенные с помощью информации из Incident_for_help и каких-либо новых данных, которые нужно записать в solution
insert into Incident (RequestID, EmployeeID, solution, date_of_solution, rating)
values (3, 3, 'Доп информация...', '2022-08-30', 10);
-- инциденты, в которых Incident_for_help не помогла -> после решения инцидента заполняем solution
insert into Incident (RequestID, EmployeeID, solution, date_of_solution, rating)
values (4, 4,
        'Пришлось позвонить в банк клиента, уточнить информацию и вернуть часть суммы из-за ошибки в нашей системе. Ошибка была передана разработчику для исправления.',
        '2022-08-30', 8);

-- инциденты для помощи
insert into Incident_for_help (IncidentID, message, solution)
values (1, 'Не добавляются товары в корзину', 'Обновить сайт, попытаться удалить их и добавить снова');
insert into Incident_for_help (IncidentID, message, solution)
values (2, 'Не добавляются товары в корзину', 'Обновить сайт, попытаться удалить их и добавить снова');
insert into Incident_for_help (message, solution)
values ('Не добавляются товары в корзину', 'Тестовый инцидент для помощи');
insert into Incident_for_help (IncidentID, message, solution)
values (4, 'Не работает оплата', 'Обновить сайт, перепроверить введенные реквизиты, свзаться с банком');

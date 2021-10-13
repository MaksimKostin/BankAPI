insert into CLIENT(FIRST_NAME, LAST_NAME, EMAIL) values ('Максим', 'Костин', 'kostin@mail.ru');
insert into ACCOUNT(balance, number, client_id) values ( 1000, '23456788872345678887', 1 );
insert into ACCOUNT(balance, number, client_id) values ( 2000, '67456746757647646767', 1 );
insert into CARD(NUMBER, PIN, CVV, account_id) values ( '3456 2352 6755 8234', '4567', '170', 1);
insert into CARD(NUMBER, PIN, CVV, account_id) values ( '5676 5572 8323 4238', '8567', '070', 1);
insert into CARD(NUMBER, PIN, CVV, account_id) values ( '2344 4245 6451 5341', '7463', '070', 2);
insert into CARD(NUMBER, PIN, CVV, account_id) values ( '2335 6341 0986 0977', '6784', '834', 2);

insert into CLIENT(FIRST_NAME, LAST_NAME, EMAIL) values ( 'Антон', 'Золоторенко', 'zolotorenko1997@mail.com');
insert into ACCOUNT(balance, number, client_id) values ( 3000, '56876475342323434143', 2);
insert into ACCOUNT(balance, number, client_id) values ( 4000, '87564534523424769899', 2);
insert into CARD(NUMBER, PIN, CVV, account_id) values ( '3454 3459 7462 2428', '5647', '467', 3);
insert into CARD(NUMBER, PIN, CVV, account_id) values ( '7428 6634 0023 9281', '4566', '563', 3);
insert into CARD(NUMBER, PIN, CVV, account_id) values ( '2199 9149 8915 1367', '7345', '234', 4);
insert into CARD(NUMBER, PIN, CVV, account_id) values ( '2931 4821 2348 1951', '9567', '969', 4);
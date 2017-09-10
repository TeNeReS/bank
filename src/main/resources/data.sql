INSERT INTO users (name, address, age)
VALUES  ('User0', 'user0@mail.ru', 23),
        ('User1', 'user1@mail.ru', 24),
        ('User2', 'user2@mail.ru', 25);

INSERT INTO accounts (user_id, amount)
VALUES  (100001, 1000),
        (100001, 2000),
        (100001, 3000),
        (100002, 4000),
        (100002, 5000),
        (100000, 6000);

INSERT INTO transactions (debit_id, refill_id, amount, date, description)
VALUES  (100003, 100008, 100, '2017-09-01 12:00:00', 'To User0'),
        (100004, NULL,   150, '2017-09-01 13:00:00', 'Store'),
        (100008, 100003, 200, '2017-09-01 20:00:00', 'From User0'),
        (100003, 100006, 250, '2017-09-02 12:00:00', 'From User1'),
        (100007, 100008, 300, '2017-09-03 12:00:00', 'To User0'),
        (NULL,   100008, 350, '2017-09-05 12:00:00', 'Dividend');
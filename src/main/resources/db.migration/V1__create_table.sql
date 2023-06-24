INSERT INTO gifts(CONDITION, CREATED_DATE, DESCRIPTION, MAIN_CATEGORY, NAME, STATE, SUB_CATEGORY, CHARITIES_ID,
                  HOLIDAYS_ID, USERS_ID)
VALUES ('new', '2022-04-12',
        'iPhone 13 Pro - это новый флагманский смартфон Apple, представленный в сентябре 2021 года', 'Electronics',
        'Iphone 13 pro', 'Booked', 'Phone', 5, 2, 21);


INSERT INTO users(CITY, CLOTHES_SIZE, COUNTRY, CREATED_DATE, EMAIL, FIRST_NAME, GENDER, IMAGE, INTERESTS, LAST_NAME,
                  MAILING, PASSWORD, PASSWORD_CONFIRM, PHONE_NUMBER, ROLE, SHOES_SIZE)
VALUES ('Bishkek', 'S', 'Kyrgyzstan', '2023-06-23', 'Aika1998@gmail.com', 'Aida', 'Female', null,
        'dancing,foreign language,cooking', 'Karimova', true, 'aida98', 'aida98', '+996705230623', 'USER', '36'),

       ('Paris', 'M', 'France', '2023-04-12', 'Arsen@gmail.com', 'Arsen', 'Male', null, 'coding and reading articles',
        'Alimov', false, 'arsen', 'arsen', '+33 88549853', 'USER', 40),

       ('Moscow', 'S', 'Russia', '2023-06-22', 'Nurisa22@gmail.com', 'Nurisa', 'Female', null,
        'reading,learn languages,complete sudoku', 'Mamatisaeva', true, 'nurisa', 'nurisa', '+7991875****', 'ADMIN',
        '36');

INSERT INTO charities(CREATED_DATE, IMAGE, MAIN_CATEGORY, NAME, USERS_ID)
VALUES ('2023-04-15', null, null, 'Kamkorduk', 12);

INSERT INTO holidays(CREATED_DATE, DATE, NAME, USERS_ID)
VALUES ('2023-12-06', '2020-08-03', 'Women^s day', 17)



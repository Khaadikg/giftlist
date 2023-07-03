-- INSERT INTO gifts(CONDITION, CREATED_DATE, DESCRIPTION, MAIN_CATEGORY, NAME, STATE, SUB_CATEGORY, CHARITIES_ID,
--                   HOLIDAYS_ID, USERS_ID)
-- VALUES ('new','2022-04-12',
--         'iPhone 13 Pro - это новый флагманский смартфон Apple, представленный в сентябре 2021 года','Electronics',
--         'Iphone 13 pro','Booked','Phone',33,2,21);


INSERT INTO users(CITY, CLOTHES_SIZE, COUNTRY, CREATED_DATE, EMAIL, FIRST_NAME, GENDER, IMAGE, INTERESTS, LAST_NAME,
                  MAILING, PASSWORD,  PHONE_NUMBER, ROLE, SHOES_SIZE)
VALUES ('Bishkek','S','KYRGYZSTAN','2023-06-23','user1@gmail.com','Aida','FEMALE',null,
        'dancing,foreign language,cooking','Karimova',true,'$2a$12$vzHVGPVigP75lyeCEB3Skesz1uKHaPm.AkkRhKc0v2o6MifyHg3WO','+996705230623','USER','THIRTY_SIX'),

       ('Paris','M','France','2023-04-12','user2@gmail.com','Arsen','Male',null,'coding and reading articles',
        'Alimov',true,'$2a$12$m0sp6rsG.xqEsLtgMlXTXeD1Ls5gtQNHWqgZI/1sdpzYfkR2GYTIi','+33 88549853','USER',40),

       ('Moscow','S','Russia','2023-06-22','admin@gmail.com','Nurisa','Female',null,
        'reading,learn languages,complete sudoku','Mamatisaeva',true,'$2a$12$mTqKqDrY8Vgqt.JLArqDz.Xsm4hH7vqlsT82ylhKcheb/ccs0JCxi','+79917645633','ADMIN','36');
INSERT INTO charities(CREATED_DATE, IMAGE, MAIN_CATEGORY, NAME, USERS_ID)
VALUES ('2023-04-15',null,null,'Kamkorduk',1);

INSERT INTO holidays(CREATED_DATE, DATE, NAME, USERS_ID)
VALUES ('2023-12-06','2020-08-03','Women^s day',1)
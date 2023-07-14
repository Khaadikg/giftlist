insert into users(city, created_date,clothes_size, country,  email, first_name, gender, image, interests, last_name, mailing, password, phone_number, pin_code, role, shoes_size)

VALUES ('Bishkek', '2023-06-23', 'L', 'KYRGYZSTAN', 'admin@gmail.com', 'Admin', 'MALE', 'image',
        'dancing,foreign language,cooking', 'Adminov', false,
        '$2a$12$ydECP71Yu1rlvNtomnxve.e1h.sVqLYySedA52gId0Ii550vyic9u', '+996705230623','0', 'ADMIN', 'THIRTY_FIVE'),

       ('Osh', '2023-06-23', 'M', 'KYRGYZSTAN', 'user@gmail.com', 'User', 'FEMALE', 'image',
        'dancing,foreign language,cooking', 'Userov', true,
        '$2a$12$kDDkvxpyEKvDU2Yz3L/o7OnlIcNa1cOnD1MADwiEAILAipUwJo6TO', '+996705230623', '0','USER', 'THIRTY_FIVE'),

       ('Nookat', '2023-06-23', 'S', 'KYRGYZSTAN', 'nurisa@gmail.com', 'Nurisa', 'FEMALE', 'image',
        'dancing,foreign language,cooking', 'Mamatisaeva', true,
        '$2a$12$6rCvnv687GdaIhxeuvGkCOuurNpa5T1NtSGaEo9QNK3ljud.iUl9e', '+996705230623','0', 'USER', 'THIRTY_FIVE');
insert into gifts(condition, created_date, description, main_category, name, state, sub_category, charities_id, complaint_id, holidays_id, users_id)

INSERT INTO charities(created_date, image, main_category, name, users_id)
VALUES ('2023-04-15', null, null, 'Kamkorduk', 1);

INSERT INTO holidays(created_date, date, name, users_id)
VALUES ('2023-12-06', '2020-08-03', 'Women^s day', 1);

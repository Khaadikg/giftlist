insert into users(city, created_date, clothes_size, country, email, first_name, gender, image, interests, last_name,
                  mailing, password,pin_code, phone_number, role, shoes_size)

VALUES ('Bishkek', '2023-06-23', 'L', 'KYRGYZSTAN', 'admin@gmail.com', 'Admin', 'MALE', 'image',
        'dancing,foreign language,cooking', 'Adminov', false,
        '$2a$12$ydECP71Yu1rlvNtomnxve.e1h.sVqLYySedA52gId0Ii550vyic9u','0', '+996705230623', 'ADMIN', 'THIRTY_FIVE'),

       ('Osh', '2023-06-23', 'M', 'KYRGYZSTAN', 'user@gmail.com', 'User', 'FEMALE', 'image',
        'dancing,foreign language,cooking', 'Userov', true,
        '$2a$12$kDDkvxpyEKvDU2Yz3L/o7OnlIcNa1cOnD1MADwiEAILAipUwJo6TO','0', '+996705230623', 'USER', 'THIRTY_FIVE'),

       ('Nookat', '2023-06-23', 'S', 'KYRGYZSTAN', 'nurisa@gmail.com', 'Nurisa', 'FEMALE', 'image',
        'dancing,foreign language,cooking', 'Mamatisaeva', true,
        '$2a$12$6rCvnv687GdaIhxeuvGkCOuurNpa5T1NtSGaEo9QNK3ljud.iUl9e','0', '+996705230623', 'USER', 'THIRTY_FIVE');

INSERT INTO holidays(created_date, date, name, users_id)
VALUES ('2023-12-06', '2020-08-03', 'Women^s day', 1);


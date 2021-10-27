INSERT INTO phone_configuration (
    id,
    country_name,
    country_code,
    regex_pattern
)
VALUES (
        1,
           'Cameroon',
            '237',
           '\(237\)\ ?[2368]\d{7,8}$'
       ),(
          2,
    'Ethiopia',
    '251',
    '\(251\)\ ?[1-59]\d{8}$'
),(
   3,
    'Morocco',
    '212',
        '\(212\)\ ?[5-9]\d{8}$'
),(
   4,
    'Mozambique',
    '258',
     '\(258\)\ ?[28]\d{7,8}$'
),(
   5,
    'Uganda',
    '256',
    '\(256\)\ ?\d{9}$'
);

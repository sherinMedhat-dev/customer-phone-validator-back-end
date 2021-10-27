CREATE TABLE phone_configuration (
                      id                       INTEGER          NOT NULL    CONSTRAINT phone_config_pkey PRIMARY KEY AUTOINCREMENT,
                      country_code             VARCHAR(50)     NOT NULL    UNIQUE,
                      country_Name             VARCHAR(100)     NOT NULL    ,
                      regex_pattern             VARCHAR(200)    NOT NULL
);
CREATE SCHEMA IF NOT EXISTS pix;

CREATE TABLE pix.PixKeyType (
    id                  BIGINT          NOT NULL            AUTO_INCREMENT PRIMARY KEY,
    enumerator          VARCHAR(255)    NOT NULL            UNIQUE,

    created_at          TIMESTAMP(3)        NOT NULL DEFAULT CURRENT_TIMESTAMP(3)
);

INSERT INTO pix.PixKeyType (enumerator) VALUES ("cpf");
INSERT INTO pix.PixKeyType (enumerator) VALUES ("cnpj");
INSERT INTO pix.PixKeyType (enumerator) VALUES ("email");
INSERT INTO pix.PixKeyType (enumerator) VALUES ("phone");
INSERT INTO pix.PixKeyType (enumerator) VALUES ("random");

CREATE TABLE pix.PixKeyStatus (
    id                  BIGINT          NOT NULL            AUTO_INCREMENT PRIMARY KEY,
    enumerator          VARCHAR(255)    NOT NULL            UNIQUE,

    created_at          TIMESTAMP(3)        NOT NULL DEFAULT CURRENT_TIMESTAMP(3)
);

INSERT INTO pix.PixKeyStatus (enumerator) VALUES ("pending");
INSERT INTO pix.PixKeyStatus (enumerator) VALUES ("active");
INSERT INTO pix.PixKeyStatus (enumerator) VALUES ("inactive");
INSERT INTO pix.PixKeyStatus (enumerator) VALUES ("error");

CREATE TABLE pix.PixKey (
    id                  BIGINT          NOT NULL            AUTO_INCREMENT PRIMARY KEY,
    public_key          VARCHAR(36)     NOT NULL            UNIQUE,
    external_key        VARCHAR(36)     NULL                UNIQUE,
    pix_key             VARCHAR(77)     NULL                UNIQUE,
    pix_key_type_id     BIGINT          NOT NULL,
    pix_key_status_id   BIGINT          NOT NULL,

    updated_at                      TIMESTAMP(3)                    NOT NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3),
    created_at                      TIMESTAMP(3)                    NOT NULL DEFAULT CURRENT_TIMESTAMP(3),

    CONSTRAINT fk_pix_key_type FOREIGN KEY (pix_key_type_id) REFERENCES pix.PixKeyType (id),
    CONSTRAINT fk_pix_key_status FOREIGN KEY (pix_key_status_id) REFERENCES pix.PixKeyStatus (id)
)

CREATE TABLE pix.QrCodePayer (
     id                              BIGINT          NOT NULL        AUTO_INCREMENT PRIMARY KEY,
     public_key               VARCHAR(36)     NOT NULL        UNIQUE,
     name                            VARCHAR(255)    NOT NULL,
     document_number                 VARCHAR(14)     NOT NULL,

     updated_at                      TIMESTAMP(3)                    NOT NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3),
     created_at                      TIMESTAMP(3)                    NOT NULL DEFAULT CURRENT_TIMESTAMP(3)
);

CREATE INDEX idx_document_number_qcp ON pix.QrCodePayer (document_number);

CREATE TABLE pix.DynamicInstantQrCodeStatus (
      id                              BIGINT          NOT NULL        AUTO_INCREMENT PRIMARY KEY,
      enumerator                      VARCHAR(255)    NOT NULL        UNIQUE,

      created_at                      TIMESTAMP(3)                    NOT NULL DEFAULT CURRENT_TIMESTAMP(3)
);

INSERT INTO pix.DynamicInstantQrCodeStatus (enumerator) VALUES ("pending");
INSERT INTO pix.DynamicInstantQrCodeStatus (enumerator) VALUES ("active");
INSERT INTO pix.DynamicInstantQrCodeStatus (enumerator) VALUES ("error");

CREATE TABLE pix.DynamicInstantQrCode (
      id                                BIGINT          NOT NULL        AUTO_INCREMENT PRIMARY KEY,
      public_key        VARCHAR(36)     NOT NULL        UNIQUE,
      correlation_id                    VARCHAR(32)     NOT NULL        UNIQUE,
      external_key                      VARCHAR(36)     NULL            UNIQUE,
      qr_code_payer_id                  BIGINT          NOT NULL,
      amount                            DECIMAL(19, 2)  NOT NULL,
      description                       VARCHAR(140)    NULL,
      expiration                        INT             NOT NULL,
      br_code                           VARCHAR(512)    NULL,
      dynamic_instant_qr_code_status_id BIGINT          NOT NULL,

      updated_at                      TIMESTAMP(3)                    NOT NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3),
      created_at                      TIMESTAMP(3)                    NOT NULL DEFAULT CURRENT_TIMESTAMP(3),

      CONSTRAINT fk_di_qr_code_payer FOREIGN KEY (qr_code_payer_id) REFERENCES pix.QrCodePayer (id),
      CONSTRAINT fk_di_dynamic_instant_qr_code_status FOREIGN KEY (dynamic_instant_qr_code_status_id) REFERENCES pix.DynamicInstantQrCodeStatus (id)
);
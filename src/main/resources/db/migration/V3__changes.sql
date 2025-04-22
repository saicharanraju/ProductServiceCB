ALTER TABLE scaler.category
    ADD parent_category VARCHAR(255) NULL;

ALTER TABLE scaler.product
DROP
COLUMN discount;

ALTER TABLE scaler.product
    ADD discount DOUBLE NULL;
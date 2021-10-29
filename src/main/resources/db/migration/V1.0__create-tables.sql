CREATE TABLE IF not exists prices (
  brand_id BIGINT NOT NULL,
  start_date TIMESTAMP NOT NULL,
  end_date TIMESTAMP NOT NULL,
  price_list BIGINT NOT NULL,
  product_id BIGINT DEFAULT 1 NULL,
  priority INTEGER NOT NULL,
  price double precision NOT NULL,
  currency VARCHAR(255) NOT NULL,

  CONSTRAINT prices_pkey PRIMARY KEY (price_list)
);

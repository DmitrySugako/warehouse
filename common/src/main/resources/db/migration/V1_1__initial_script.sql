create table if not exists product
(
    id          bigserial
        constraint product_pk
            primary key,
    sku         varchar               not null,
    description varchar               not null,
    created     timestamp(6),
    changed     timestamp(6),
    is_deleted  boolean default false not null,
    srp1        varchar               not null,
    srp2        varchar               not null,
    srp3        varchar               not null,
    barcode     bigint                not null,
    weight      double precision
);

alter table product
    owner to dev;

create unique index product_id_uindex
    on product (id);

create unique index product_item_uindex
    on product (sku);

create index product_sku_index
    on product (sku);

create unique index product_barcode_id_uindex
    on product (barcode);

create table if not exists product_analytics
(
    id                bigint                not null
        constraint "product analytics_pk"
            primary key,
    batch_number      bigint                not null,
    country_of_import varchar               not null,
    product_id        bigint                not null
        constraint "product analytics_product_id_fk"
            references product,
    created           timestamp(6)          not null,
    changed           timestamp(6)          not null,
    is_deleted        boolean default false not null
);

alter table product_analytics
    owner to dev;

create unique index "product analytics_id_uindex"
    on product_analytics (id);

create index "product analytics_batch_number_index"
    on product_analytics (batch_number desc);

create index "product analytics_country_of_import_index"
    on product_analytics (country_of_import);

create table if not exists barcode
(
    id         bigint                not null
        constraint barcode_pk
            primary key,
    barcode    bigint                not null,
    created    timestamp(6)          not null,
    changed    timestamp(6)          not null,
    is_deleted boolean default false not null,
    product_id bigint                not null
        constraint barcode_product_id_fk
            references product
);

alter table barcode
    owner to dev;

create unique index barcode_id_item_uindex
    on barcode (id);

create index barcode_barcode_index
    on barcode (barcode);

create index barcode_barcode_index_2
    on barcode (barcode);

create unique index barcode_product_id_uindex
    on barcode (product_id);

create table if not exists с_encoding_format
(
    id              bigserial
        constraint с_encoding_format_pk
            primary key,
    encoding_format varchar               not null,
    is_deleted      boolean default false not null,
    barcode_id      bigint                not null
        constraint с_encoding_format_barcode_id_fk
            references barcode
);

alter table с_encoding_format
    owner to dev;

create unique index с_encoding_format_id_uindex
    on с_encoding_format (id);

create table if not exists receipt_order
(
    id             bigserial
        constraint receipt_order_pk
            primary key,
    order_number   bigint                not null,
    income_data    date                  not null,
    receipt_status boolean default false not null,
    product_id     bigint                not null
        constraint receipt_order_product_id_fk
            references product,
    created        timestamp(6)          not null,
    changed        timestamp(6)          not null,
    is_deleted     boolean default false not null
);

alter table receipt_order
    owner to dev;

create unique index receipt_order_id_uindex
    on receipt_order (id);

create index receipt_order_income_data_index
    on receipt_order (income_data desc);

create index receipt_order_order_number_index
    on receipt_order (order_number);

create unique index receipt_order_order_number_uindex
    on receipt_order (order_number);

create index receipt_order_receipt_status_index
    on receipt_order (receipt_status);

create table if not exists c_standard_receiving_products
(
    id         bigserial
        constraint c_standard_receiving_products_pk
            primary key,
    srp1_type  varchar               not null,
    srp2_type  varchar               not null,
    srp3_type  varchar               not null,
    created    timestamp(6)          not null,
    is_deleted boolean default false not null,
    product_id bigint                not null
        constraint c_standard_receiving_products_product_id_fk
            references product,
    constraint c_standard_receiving_products_pk_2
        unique (srp1_type, srp2_type, srp3_type)
);

alter table c_standard_receiving_products
    owner to dev;

create unique index c_standard_receiving_products_id_uindex
    on c_standard_receiving_products (id);

create table if not exists storage_address
(
    id                bigserial
        constraint storage_address_pk
            primary key,
    storage_cell_type varchar               not null,
    cell_address      bigint                not null,
    created           timestamp(6)          not null,
    changed           timestamp(6)          not null,
    is_deleted        boolean default false not null
);

alter table storage_address
    owner to dev;

create index storage_address_address_index
    on storage_address (cell_address);

create unique index storage_address_id_uindex
    on storage_address (id);

create index storage_address_storage_cell_type_index
    on storage_address (storage_cell_type);

create table if not exists stock_status
(
    id                 bigint                not null
        constraint stock_status_pk
            primary key,
    product_id         bigint                not null
        constraint stock_status_product_id_fk
            references product,
    storage_address_id bigint                not null
        constraint stock_status_storage_address_id_fk
            references storage_address,
    availability       boolean default false not null,
    created            timestamp(6)          not null,
    changed            timestamp(6)          not null
);

alter table stock_status
    owner to dev;

create index stock_status_availability_index
    on stock_status (availability);

create table if not exists shipment
(
    id              bigserial
        constraint shipment_pk
            primary key,
    shipment_number bigint                not null,
    shipping_date   date                  not null,
    shipment_status boolean default false not null,
    stock_id        bigint                not null
        constraint shipment_stock_status_id_fk
            references stock_status,
    created         timestamp(6)          not null,
    changed         timestamp(6)          not null,
    is_deleted      boolean default false not null
);

alter table shipment
    owner to dev;

create unique index shipment_id_uindex
    on shipment (id);

create index shipment_shipment_number_index
    on shipment (shipment_number);

create index shipment_shipment_status_index
    on shipment (shipment_status);

create index shipment_shipping_date_index
    on shipment (shipping_date);

create table if not exists users
(
    id         bigserial
        constraint users_pk
            primary key,
    name       varchar               not null,
    surname    varchar               not null,
    login      varchar               not null,
    password   varchar               not null,
    roles_id   bigint                not null,
    created    timestamp(6)          not null,
    changed    timestamp(6)          not null,
    is_deleted boolean default false not null
);

alter table users
    owner to dev;

create unique index users_id_uindex
    on users (id);

create unique index users_login_uindex
    on users (login);

create unique index users_password_uindex
    on users (password);

create index users_surname_index
    on users (surname);

create table if not exists access_rights
(
    id       bigserial
        constraint access_rights_pk
            primary key,
    role     varchar      not null,
    created  timestamp(6) not null,
    changed  timestamp(6) not null,
    users_id bigint       not null
        constraint access_rights_users_id_fk
            references users
);

alter table access_rights
    owner to dev;

create unique index access_rights_id_uindex
    on access_rights (id);

create index access_rights_role_index
    on access_rights (role);


create table if not exists product
(
    id          bigserial
    constraint product_pk
    primary key,
    sku         varchar(100)               not null,
    description varchar(250)               not null,
    srp1        varchar(100)               not null,
    srp2        varchar(100)               not null,
    srp3        varchar(100)               not null,
    barcode     bigint               not null,
    weight      double precision,
    created     timestamp(6) not null,
    changed     timestamp(6) not null,
    is_deleted  boolean default false not null

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

create table if not exists analytics
(
    id                bigint                not null
    constraint "product analytics_pk"
    primary key,
    batch_number      bigint                not null,
    country_of_import varchar(100)               not null,


    created           timestamp(6)          not null,
    changed           timestamp(6)          not null,
    is_deleted        boolean default false not null
    );

alter table analytics
    owner to dev;

create unique index "analytics_id_uindex"
    on analytics (id);

create index "analytics_batch_number_index"
    on analytics (batch_number desc);

create index "analytics_country_of_import_index"
    on analytics (country_of_import);

create table l_product_analytics
(
    id           bigserial
        constraint l_product_analytics_pk
            primary key,
    product_id   bigint       not null
        constraint l_product_analytics_product_id_fk
            references product
            on update cascade on delete cascade,
    analytics_id bigint       not null
        constraint l_product_analytics_analytics_id_fk
            references analytics
            on update cascade on delete cascade,
    created      timestamp(6) not null,
    changed      timestamp(6) not null
);

create index l_product_analytics_analytics_id_index
    on l_product_analytics (analytics_id desc);

create unique index l_product_analytics_id_uindex
    on l_product_analytics (id);

create index l_product_analytics_product_id_index
    on l_product_analytics (product_id desc);

create table if not exists stock_status
(
    id                 bigint                not null
    constraint stock_status_pk
    primary key,
    ordered_quantity bigint,
    available_quantity bigint not null,
    reserved_quantity bigint,

    created            timestamp(6)          not null,
    changed            timestamp(6)          not null
    );

alter table stock_status
    owner to dev;
create index stock_status_available_quantity_index
    on stock_status (available_quantity);

create index stock_status_ordered_quantity_index
    on stock_status (ordered_quantity);

create index stock_status_reserved_quantity_index
    on stock_status (reserved_quantity);



create table if not exists receipt_order
(
    id             bigserial
    constraint receipt_order_pk
    primary key,
    order_number   bigint                not null,
    income_data    timestamp(6)                  not null,
    receipt_status boolean default false not null,
    quantity bigint not null,
    stock_id bigint not null,
    created        timestamp(6)          not null,
    changed        timestamp(6)          not null,
    is_deleted     boolean default false not null
    );

alter table receipt_order
    add constraint receipt_order_stock_status_id_fk
        foreign key (stock_id) references stock_status
            on update cascade;

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


create table if not exists storage_address
(
    id                bigserial
    constraint storage_address_pk
    primary key,
    storage_cell_type varchar               not null,
    cell_address      varchar(100)                not null,
    created           timestamp(6)          not null,
    changed           timestamp(6)          not null

    );

alter table storage_address
    owner to dev;

create index storage_address_address_index
    on storage_address (cell_address);

create unique index storage_address_id_uindex
    on storage_address (id);

create index storage_address_storage_cell_type_index
    on storage_address (storage_cell_type);


create table if not exists l_inventory_addresses
(
    id         bigserial
        constraint l_inventory_addresses_pk
            primary key,
    stock_id   bigint not null
        constraint l_inventory_addresses_stock_status_id_fk
            references stock_status
            on update cascade,
    address_id bigint not null
        constraint l_inventory_addresses_storage_address_id_fk
            references storage_address
            on update cascade
);

create index inventory_addresses_address_id_index
    on l_inventory_addresses (address_id);

create index inventory_addresses_stock_id_index
    on l_inventory_addresses (stock_id);

create unique index l_inventory_addresses_id_uindex
    on l_inventory_addresses (id);



create table if not exists shipment
(
    id              bigserial
    constraint shipment_pk
    primary key,
    shipment_number bigint                not null,
    shipping_date   timestamp(6)                  not null,
    stock_id bigint not null,
    shipment_status boolean default false not null,
    quantity        bigint                not null,
    created         timestamp(6)          not null,
    changed         timestamp(6)          not null,
    is_deleted      boolean default false not null
    );

alter table shipment
    add constraint shipment_stock_status_id_fk
        foreign key (stock_id) references stock_status
            on update cascade on delete cascade;



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


create table if not exists l_products_incoming
(
    id         bigserial
        constraint l_products_incoming_pk
            primary key,
    product_id bigint       not null
        constraint l_products_incoming_product_id_fk
            references product
            on update cascade on delete cascade,
    receipt_id bigint       not null
        constraint l_products_incoming_receipt_order_id_fk
            references receipt_order,
    created    timestamp(6) not null,
    changed    timestamp(6) not null
);

create unique index l_products_incoming_id_uindex
    on l_products_incoming (id);

create index l_products_incoming_product_id_index
    on l_products_incoming (product_id);

create index l_products_incoming_receipt_id_index
    on l_products_incoming (receipt_id);
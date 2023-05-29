create table item
(
    id          bigserial
        constraint item_pk
            primary key,
    sku_code    varchar(100) not null,
    description varchar(250) not null,
    category    varchar(100) not null,
    created     timestamp(6),
    changed     timestamp(6),
    is_deleted  boolean default false
);

alter table item
    owner to dev;

create index item_category_index
    on item (category desc);

create index item_sku_code_index
    on item (sku_code desc);

create table storage_address
(
    id           bigserial
        constraint storage_address_pk
            primary key,
    cell_address varchar(20)           not null,
    created      timestamp(6)          not null,
    changed      timestamp(6)          not null,
    is_deleted   boolean default false not null
);

alter table storage_address
    owner to dev;

create index storage_address_address_index
    on storage_address (cell_address);

create unique index storage_address_id_uindex
    on storage_address (id);

create table receipt_order
(
    id             bigserial
        constraint receipt_order_pk
            primary key,
    receipt_number varchar(100)          not null,
    created        timestamp(6)          not null,
    changed        timestamp(6)          not null,
    is_deleted     boolean default false not null
);

alter table receipt_order
    owner to dev;

create index receipt_order_created_index
    on receipt_order (created desc);

create unique index receipt_order_id_uindex
    on receipt_order (id);

create index receipt_order_receipt_number_index
    on receipt_order (receipt_number desc);

create table stock_status
(
    id                 bigserial
        constraint stock_status_pk
            primary key,
    receipt_id         bigint                not null
        constraint stock_status_receipt_order_id_fk
            references receipt_order
            on update cascade on delete cascade,
    item_id            bigint                not null
        constraint stock_status_item_id_fk
            references item
            on update cascade on delete cascade,
    address_id         bigint
        constraint stock_status_storage_address_id_fk
            references storage_address,
    ordered_quantity   bigint  default 0     not null,
    available_quantity bigint  default 0     not null,
    reserved_quantity  bigint  default 0     not null,
    created            timestamp(6)          not null,
    changed            timestamp(6)          not null,
    is_deleted         boolean default false not null
);

alter table stock_status
    owner to dev;

create table shipment
(
    id                 bigserial
        constraint shipment_pk
            primary key,
    shipment_number    bigint                not null,
    shipment_status    boolean default false not null,
    remaining_quantity bigint                not null,
    created            timestamp(6)          not null,
    changed            timestamp(6)          not null,
    is_deleted         boolean default false not null,
    stock_id           bigint
        constraint shipment_stock_status_id_fk
            references stock_status
            on update cascade on delete cascade
);

alter table shipment
    owner to dev;

create unique index shipment_id_uindex
    on shipment (id);

create index shipment_shipment_number_index
    on shipment (shipment_number);

create index shipment_shipment_status_index
    on shipment (shipment_status);

create unique index stock_status_id_uindex
    on stock_status (id);


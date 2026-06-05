create table trades
(
    id              bigint auto_increment primary key,
    currency_pair   varchar(255)   not null,
    amount          decimal(19, 6) not null,
    trade_side      varchar(255)   not null,
    price           decimal(19, 6) not null,
    counter_party   varchar(255)   not null,
    trader_name     varchar(255)   not null,
    trade_status    varchar(255)   not null,
    trade_date      datetime(6)   not null,
    settlement_date datetime(6)   null,
    created_at      datetime(6)   not null,
    updated_at      datetime(6)   null
);
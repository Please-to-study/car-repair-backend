-- 用户表
create table repair.user
(
    id       int auto_increment primary key,
    userid   varchar(16)  not null,
    name     varchar(255) not null,
    mail     varchar(255) not null,
    phone    varchar(11)  not null,
    password varchar(32)  not null,
    others   longtext
);


-- 维修员表
create table repair.maintenance
(
    id            int auto_increment primary key,
    maintenanceId varchar(16)  not null,
    name          varchar(255) not null,
    phone         varchar(11)  not null,
    password      varchar(32)  not null,
    deleteFlag    int          not null
);

-- 订单表
create table repair.order
(
    id              int auto_increment primary key,
    userid          varchar(16) not null,
    problem         longtext    not null,
    orderTime       datetime    not null,
    maintenanceTime datetime,
    finishTime      datetime,
    maintenanceId   varchar(16),
    status          int         not null,
    comment         longtext

);

-- 管理员表
create table repair.manager
(
    managerId varchar(16) not null,
    password  varchar(32) not null
);
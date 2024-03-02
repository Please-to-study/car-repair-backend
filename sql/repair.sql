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
    id         int auto_increment primary key,
    name       varchar(255) not null,
    phone      varchar(11)  not null,
    age        varchar(3)   not null,
    deleteFlag int          not null,

    major      varchar(255),
    seniority  varchar(3)
);

-- 订单表
create table repair.order
(
    id                 int auto_increment primary key,
    userid             varchar(16) not null,
    problem            longtext    not null,
    maintenanceProject varchar(20) not null,
    orderTime          datetime    not null,
    orderDate          datetime    not null,
    maintenanceTime    datetime,
    finishTime         datetime,
    maintenance        varchar(16),
    status             int         not null,
    comment            longtext,

    phone              varchar(11),
    model              varchar(20),
    plateNumber        varchar(20)

);

-- 管理员表
create table repair.manager
(
    managerId varchar(16) not null,
    password  varchar(32) not null
);


create table repair.board
(
    id         int auto_increment primary key,
    context    text        not null,
    title      varchar(32) not null,
    time       datetime    not null,
    deleteFlag int         not null
);
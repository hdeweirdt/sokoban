create table user
(
    id         bigint not null auto_increment,
    first_name varchar(255),
    last_name  varchar(255),
    password   varchar(255),
    user_name  varchar(255),
    roles      varchar(255),
    primary key (id),
    unique (user_name)
);

create table game
(
    id   bigint not null auto_increment,
    name varchar(255),
    primary key (id)
);

create table board
(
    id      bigint not null auto_increment,
    fields  char(100),
    game_id bigint,
    player_row int,
    player_column int,
    primary key (id),
    foreign key (game_id) references game(id)
);

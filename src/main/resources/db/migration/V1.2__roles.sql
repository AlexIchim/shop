    create table if not exists users (id integer,
        email varchar(255),
        enabled integer not null,
        password varchar(255),
        username varchar(255),
        primary key (id)
    );

    create table if not exists  role (
       id bigint,
        name varchar(255),
        primary key (id)
    );

    create table  if not exists   user_role (
       user_id integer not null,
        role_id bigint not null,
        primary key (user_id, role_id)
    );

    alter table user_role
       add constraint FKa68196081fvovjhkek5m97n3y
       foreign key (role_id)
       references role;

    alter table user_role
       add constraint FK859n2jvi8ivhui0rl0esws6o
       foreign key (user_id)
       references users;

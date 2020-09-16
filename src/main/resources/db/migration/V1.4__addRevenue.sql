create table revenue (
       revenue_id integer,
        local_date_time timestamp,
        sum decimal(19,2),
        location_id integer,
        primary key (revenue_id)
    );

  alter table revenue
       add constraint FK6xukepd8ssa1ok6iakkhp83p7
       foreign key (location_id)
       references location;
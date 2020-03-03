delete from message;

insert into message (id,text,tag,user_id)
values  (1,'first','gg',1),
        (2,'first1','gg',1),
        (3,'first2','gg',1),
        (4,'first3','gg',1);

alter table message AUTO_INCREMENT 10;
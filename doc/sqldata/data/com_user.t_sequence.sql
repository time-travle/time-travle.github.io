delete from com_user.t_sequence where id =1;
insert into  com_user.t_sequence (id, name, value, increment, min_value, max_value, create_time)
value (1,'DEFAULT_SEQ',99999999,1,10000000,99999999,now());

select com_user.get_seq_current('DEFAULT_SEQ');
select com_user.get_seq_max('DEFAULT_SEQ');
select com_user.get_seq_next1('DEFAULT_SEQ');
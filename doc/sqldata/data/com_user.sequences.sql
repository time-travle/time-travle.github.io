insert into com_user.sequences (id_sequences, seq_name, current_val, increment_val)value (1,'DEFAULT_SEQ',100000,1);
select com_user.nextval('DEFAULT_SEQ');
select com_user.currentval('DEFAULT_SEQ');

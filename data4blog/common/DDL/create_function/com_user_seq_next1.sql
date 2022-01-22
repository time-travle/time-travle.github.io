create
    definer = com_user@`%` function com_user.get_seq_next1(seq_name char(100)) returns bigint
BEGIN
    IF (com_user.get_seq_current(seq_name) >= com_user.get_seq_max(seq_name)) THEN
        update t_sequence SET value = min_value where name = seq_name;
    ELSE
        update t_sequence SET value = (value + increment) where name = seq_name;
    END IF;
    RETURN com_user.get_seq_current(seq_name);
END;


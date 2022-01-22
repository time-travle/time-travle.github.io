create
    definer = com_user@`%` function com_user.get_seq_max(seq_name char(100)) returns bigint
BEGIN
    DECLARE max BIGINT;
    SET max = 0;
    SELECT max_value into max FROM t_sequence WHERE name = seq_name;
    RETURN max;
END;


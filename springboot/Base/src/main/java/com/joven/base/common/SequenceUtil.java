/**
 * Project Name: ershuaizhang.github.io
 * File Name: SequenceUtil
 * Package Name: com.joven.base.common
 * Date: 2019/11/8 21:28
 * Copyright (c) 2019,All Rights Reserved.
 */
package com.joven.base.common;

import lombok.extern.slf4j.Slf4j;

import java.util.UUID;
import java.math.BigDecimal;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * CreateBy Administrator
 * Date: 2019/11/8 21:28
 * Version: 
 * Remark: 做一个获取序列化的公共类
 */
@Slf4j
public final class SequenceUtil {
    /**
     * 全局变量
     */
    private static BigDecimal sequenceNum = new BigDecimal(100000000);

    //加读写锁
    private static ReadWriteLock rwl = new ReentrantReadWriteLock();
    /**
     * 保护起来不允许 new
     */
    private SequenceUtil() {}

    private static SequenceUtil sequenceUtil = null;
    /**
     * 获取类的实例化
     *
     * @Title: getInstance
     * @return SequenceUtil
     * @author Administrator
     * @date 2018年8月11日下午4:11:47
     */
    public static SequenceUtil getInstance() {
        rwl.readLock().lock();
        try {
            if(null == sequenceUtil){
                rwl.readLock().unlock();
                rwl.writeLock().lock();
                sequenceUtil = new SequenceUtil();
                rwl.writeLock().unlock();
            }
            rwl.readLock().lock();
        }catch (Exception e){
            e.printStackTrace();
            log.error("SequenceUtil error !");
            rwl.readLock().unlock();
        }finally {
            rwl.readLock().unlock();
        }
        return sequenceUtil;
    }
    /**
     * 获取下一个序列号码
     *
     * @Title: getNextSequence
     * @return BigDecimal
     * @author Administrator
     * @date 2018年8月11日下午4:11:57
     */
    public BigDecimal getNextSequence() {
        sequenceNum = sequenceNum.add(new BigDecimal(1));
        return sequenceNum;
    }
    /**
     * 获取当前的序列号码
     *
     * @Title: getNextSequence
     * @return BigDecimal
     * @author Administrator
     * @date 2018年8月11日下午4:11:57
     */
    public BigDecimal getCurrentSequence() {
        return sequenceNum;
    }
    /**
     * 生成唯一序列号
     *
     * @Title: getUniqueSequenceUuid
     * @return UUID
     * @author Administrator
     * @date 2018年8月11日下午3:52:22
     */
    public UUID getUniqueSequenceUuid() {
        return UUID.randomUUID();
    }

    /**
     * 生成唯一序列号
     *
     * @Title: getUniqueSequenceUuidString
     * @return String
     * @author Administrator
     * @date 2018年8月11日下午3:52:22
     */
    public String getUniqueSequenceUuidString() {
        return UUID.randomUUID().toString();
    }
}

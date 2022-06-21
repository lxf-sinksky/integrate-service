package com.yuepong.integrate.systemic.config;

import org.hibernate.engine.transaction.jta.platform.internal.AbstractJtaPlatform;

import javax.transaction.TransactionManager;
import javax.transaction.UserTransaction;

/**
 * @ClassName AtomikosJtaPlatform
 * @Description TODO
 * @Author lixuefei
 * @Date 2022.6.14 14:51
 **/
public class AtomikosJtaPlatform extends AbstractJtaPlatform {
    
    private static final long serialVersionUID = 1L;
    
    static TransactionManager transactionManager;
    static UserTransaction transaction;
    
    @Override
    protected TransactionManager locateTransactionManager() {
        return transactionManager;
    }
    
    @Override
    protected UserTransaction locateUserTransaction() {
        return transaction;
    }
}

package com.gabil.kdvapp.dao;



import com.gabil.kdvapp.config.SingletonDBConnection;

import java.sql.Connection;

public interface IDaoImplements<T> extends ICrud<T>,IGenericsMethod<T> {

    // Gövdeli Method
    default Connection iDaoImplementsDatabaseConnection() {
        // Singleton DB
        //return SingletonDBConnection.getInstance().getConnection();

        // Singleton Config
        return SingletonDBConnection.getInstance().getConnection();
    }
}
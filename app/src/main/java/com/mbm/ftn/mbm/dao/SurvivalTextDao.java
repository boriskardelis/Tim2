package com.mbm.ftn.mbm.dao;

import android.content.Context;

import com.mbm.ftn.mbm.database.Crud;
import com.mbm.ftn.mbm.database.DatabaseHelper;
import com.mbm.ftn.mbm.database.DatabaseManager;
import com.mbm.ftn.mbm.models.Number;
import com.mbm.ftn.mbm.models.SurvivalText;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Boris K on 02-Jun-17.
 */

public class SurvivalTextDao implements Crud{

    private DatabaseHelper helper;

    public SurvivalTextDao(Context context) {
        DatabaseManager.init(context);
        helper = DatabaseManager.getInstance().getHelper();
    }

    @Override
    public int create(Object item) {

        int index = -1;

        Number object = (Number) item;
        try {
            index = helper.getNumberDao().create(object);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return index;
    }

    @Override
    public int update(Object item) {

        int index = -1;

        Number object = (Number) item;

        try {
            helper.getNumberDao().update(object);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return index;
    }

    @Override
    public int delete(Object item) {

        int index = -1;

        Number object = (Number) item;

        try {
            helper.getNumberDao().delete(object);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return index;

    }

    @Override
    public Object findById(int id) {

        Number number = null;
        try {
            number = helper.getNumberDao().queryForId(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return number;

    }

    @Override
    public List<SurvivalText> findAll() {

        List<SurvivalText> items = null;

        try {
            items = helper.getSurvivalTextDao().queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return items;
    }
}

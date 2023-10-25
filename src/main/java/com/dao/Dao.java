package com.dao;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

import com.utils.DBUnitHelper;

public class Dao<T> {
	String clazzname;
	Class clazz;

	public Dao(T entity) {
		clazz = entity.getClass();
		clazzname = clazz.getSimpleName().toLowerCase();
	}

	public List<T> queryByKey(String key, String keyvalue) {
		String sql = "select * from " + clazzname + " where " + key + "=?";
		List<T> list = (List<T>) DBUnitHelper.executeQuery(sql, clazz, keyvalue);
		return list;
	}

	public List<T> query(T entity) {
		StringBuffer sbf = new StringBuffer("select * from " + clazzname + " where 1=1 ");
		for (Field field : clazz.getDeclaredFields()) {
			if (!field.getName().equals("id")) {
				if (getValue(entity, field.getName(), clazz) != null
						&& !getValue(entity, field.getName(), clazz).equals("")) {
					sbf.append(
							" and " + field.getName() + " like '%" + getValue(entity, field.getName(), clazz) + "%'");
				}
			}
		}
		System.out.println(sbf.toString());
		List<T> list = (List<T>) DBUnitHelper.executeQuery(sbf.toString(), clazz);
		return list;
	}

	public List<T> getAll() {
		String sql = "select * from " + clazzname + " order by id asc";
		List<T> list = (List<T>) DBUnitHelper.executeQuery(sql, clazz);
		return list;
	}

	public void executeSql(String sql) {
		System.out.println(sql);
		DBUnitHelper.executeUpdate(sql);
	}

	// 根据主键修改
	public void update(T entity) {
		Integer value1 = (Integer) getValue(entity, getKey(), clazz);
		StringBuffer sbf = new StringBuffer();
		sbf.append("update " + clazzname + " set ");
		for (Field field : clazz.getDeclaredFields()) {
			Object o = getValue(entity, field.getName(), clazz);
			sbf.append(field.getName().toLowerCase()).append("='").append(getValue(entity, field.getName(), clazz))
					.append("'").append(",");
		}
		sbf = new StringBuffer(sbf.subSequence(0, sbf.length() - 1));
		sbf.append(" where id='" + value1 + "'");
		executeSql(sbf.toString());
	}

	public void add(T t) {
		StringBuffer sbf = new StringBuffer();
		sbf.append("insert into " + clazzname).append("(");
		for (Field field : clazz.getDeclaredFields()) {
			if (!field.getName().equals("id")) {
				sbf.append(field.getName().toLowerCase()).append(",");
			}
		}
		sbf = new StringBuffer(sbf.subSequence(0, sbf.length() - 1));
		sbf.append(") values(");
		for (Field field : clazz.getDeclaredFields()) {
			if (!field.getName().equals("id")) {
				sbf.append("'").append(getValue(t, field.getName(), clazz)).append("'").append(",");
			}
		}
		sbf = new StringBuffer(sbf.subSequence(0, sbf.length() - 1));
		sbf.append(")");
		executeSql(sbf.toString());

	}

	public Integer delBykey(String key, Object keyvalue) {
		String sql = "delete from " + clazzname + " where " + key + "=?";
		return DBUnitHelper.executeUpdate(sql, keyvalue);
	}

	public Object getValue(Object entity, String fieldName, Class clazz) {
		PropertyDescriptor pd;
		try {
			pd = new PropertyDescriptor(fieldName, clazz);
			Method wM = pd.getReadMethod();
			return wM.invoke(entity) == null ? "" : wM.invoke(entity);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public void setValue(String fieldName, Class clazz, Object o, Object fieldValue) {
		try {
			PropertyDescriptor pd = new PropertyDescriptor(fieldName, clazz);
			Method wM = pd.getWriteMethod();
			wM.invoke(o, fieldValue);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getKey() {
		try {
			for (Field field : clazz.getDeclaredFields()) {
				return field.getName();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}

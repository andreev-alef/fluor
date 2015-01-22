package ru.miacn.utils;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Query;

public class JpaUtils {
	public static Query getNativeQuery(EntityManager em, String nativeQuery, Map<String, Object> params, Class<?> mapperClass) {
		Query query = em.createNativeQuery(nativeQuery, mapperClass);
		
		setParameters(query, params);
		
		return query;
	}

	@SuppressWarnings("unchecked")
	public static <T> List<T> getNativeResultList(EntityManager em, String nativeQuery, Map<String, Object> params, Class<T> mapperClass) {
		Query query = getNativeQuery(em, nativeQuery, params, mapperClass);
		
		return query.getResultList();
	}

	private static void setParameters(Query query, Map<String, Object> params) {
		if (params != null) {
			for (String key : params.keySet()) {
				query.setParameter(key, params.get(key));
			}
		}
	}
	
	public static Query getNativeQuery(EntityManager em, String nativeQuery, Map<String, Object> params, Class<?> mapperClass, String mapperString) {
		Query query = em.createNativeQuery(nativeQuery, mapperString);
		
		setParameters(query, params);
		
		return query;
	}

	@SuppressWarnings("unchecked")
	public static <T> List<T> getNativeResultList(EntityManager em, String nativeQuery, Map<String, Object> params, Class<T> mapperClass, String mapperString) {
		Query query = getNativeQuery(em, nativeQuery, params, mapperClass, mapperString);
		
		return query.getResultList();
	}
}

package com.pe.sercosta.scks.util;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

	private static SessionFactory sessionFactory;

	private static final ThreadLocal<Session> threadlocal = new ThreadLocal<Session>();

	static {
		try {
			Configuration configuration = new Configuration().configure();
			sessionFactory = configuration.buildSessionFactory();
		} catch (Throwable ex) {
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public static Session getSession() throws HibernateException {
		Session session = threadlocal.get();
		if (session == null || !session.isOpen()) {
			if (sessionFactory == null) {
				rebuildSessionFactory();
			}
			session = (sessionFactory != null) ? sessionFactory.openSession() : null;
			threadlocal.set(session);
		}
		return session;
	}

	public static void closeSession() throws HibernateException {
		Session session = threadlocal.get();
		threadlocal.set(null);
		if (session != null) {
			session.close();
		}
	}

	public static void rebuildSessionFactory() {
		try {
			Configuration configuration = new Configuration().configure();
			sessionFactory = configuration.buildSessionFactory();
		} catch (Exception e) {
			System.out.println("Error creating sessionFactory!");
			e.printStackTrace();
		}
	}

	public static void shutdown() {
		getSessionFactory().close();
	}
}

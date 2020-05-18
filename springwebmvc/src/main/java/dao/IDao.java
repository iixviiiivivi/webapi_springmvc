package dao;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public interface IDao<T> {

	static Session getSession() {
		Configuration cfg = new Configuration().configure();
		SessionFactory sf = cfg.buildSessionFactory();
		Session session = sf.openSession();
		return session;
	}
	
	List<T> findAll();
	T findOne(Integer id);
	T save(T obj);
	T update(Integer id, T obj);
	boolean delete(Integer id);
}

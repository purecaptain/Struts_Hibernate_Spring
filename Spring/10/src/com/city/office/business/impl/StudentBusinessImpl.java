package com.city.office.business.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.city.office.business.IStudentBusiness;
import com.city.office.value.StudentValue;

public class StudentBusinessImpl implements IStudentBusiness {

	private SessionFactory sf = null;
	
	public SessionFactory getSf() {
		return sf;
	}

	public void setSf(SessionFactory sf) {
		this.sf = sf;
	}

	
	@Override
	public void add(StudentValue sv) throws Exception {
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		
		session.save(sv);
		
		tx.commit();
		session.close();
		sf.close();
	}

	@Override
	public StudentValue getStudent(String id) throws Exception {
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		
		StudentValue sv = session.get(StudentValue.class, id);
		
		tx.commit();
		session.close();
		sf.close();
		
		return sv;
	}

	@Override
	public List<StudentValue> getList() throws Exception {
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		
		String hql="from StudentValue";
		Query query=session.createQuery(hql);
		List<StudentValue> list = query.list();
		
		tx.commit();
		session.close();
		sf.close();
		
		return list;
	}

	@Override
	public int getStudentCount() throws Exception {
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		
		String hql="select count(sv.sid) from StudentValue sv";
		Query query=session.createQuery(hql);
		int count = ((Long)query.uniqueResult()).intValue();
		
		tx.commit();
		session.close();
		sf.close();
		
		return count;
	}

	@Override
	public int getCountByAge(int min, int max) throws Exception {
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		
		String hql="select count(sv.age) from StudentValue sv where sv.age > :min and sv.age < :max";
		Query query=session.createQuery(hql);
		query.setInteger("min", min);
		query.setInteger("max", max);
		int count = ((Long)query.uniqueResult()).intValue();
		
		tx.commit();
		session.close();
		sf.close();
		
		return count;
	}

	@Override
	public double getAvgAge() throws Exception {
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		
		String hql="select avg(sv.age) from StudentValue sv";
		Query query=session.createQuery(hql);
		double age = ((Double)query.uniqueResult()).doubleValue();
		
		tx.commit();
		session.close();
		sf.close();
		
		return age;
	}

}

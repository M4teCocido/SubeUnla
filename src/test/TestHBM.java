package test;

import org.hibernate.Session;
import dao.HibernateUtil;

public class TestHBM {
	
	public static void main(String[] args) throws Exception{
		//Simplemente chequea la conexion.
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		session.beginTransaction();
		session.close();
		System.out.println("Conexion OK!");
	}

}

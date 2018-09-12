package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;
import com.luv2code.hibernate.demo.entity.Student;

public class DeleteInstructorDetailDemo {

	public static void main(String[] args) {
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Instructor.class)
								.addAnnotatedClass(InstructorDetail.class)
								.buildSessionFactory();
		
		Session session = factory.getCurrentSession();
		
		try{
			//建立联系
			session.beginTransaction();
			int theId =3;
			InstructorDetail tempInstructorDetail = 
					session.get(InstructorDetail.class, theId);
			
			System.out.println("tempD: " + tempInstructorDetail);
			
			System.out.println("the assio instructor: " + tempInstructorDetail.getInstructor());
			
			//remove the associated object reference
			//break bi-directional link
			
			tempInstructorDetail.getInstructor().setInstructorDetail(null);
			
			session.delete(tempInstructorDetail);
			session.getTransaction().commit();
			System.out.println("Done");
			
		}
		catch(Exception exc){
			exc.printStackTrace();
		}
		finally{
			//handle connection leak issue
			session.close();
			factory.close();
		}
	}

}

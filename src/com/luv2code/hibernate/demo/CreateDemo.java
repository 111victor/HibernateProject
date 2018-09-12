package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;
import com.luv2code.hibernate.demo.entity.Student;

public class CreateDemo {

	public static void main(String[] args) {
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Instructor.class)
								.addAnnotatedClass(InstructorDetail.class)
								.buildSessionFactory();
		
		Session session = factory.getCurrentSession();
		
		try{
			System.out.println("Creating");
			//创建对象
			Instructor tempInstructor =
					new Instructor("Chad","Darby","1234@123.com");
			InstructorDetail tempInstructorDetail =
					new InstructorDetail("http://www.luv2code.com/youtube","luv 2 code!!!");
			
			//建立联系
			tempInstructor.setInstructorDetail(tempInstructorDetail);
			session.beginTransaction();
			//save detail对象，因为 cascadeType.ALL
			
			System.out.println("Saving "+tempInstructor);
			session.save(tempInstructor);
			
			session.getTransaction().commit();
			System.out.println("Done");
			
		}
		finally{
			factory.close();
		}
	}

}

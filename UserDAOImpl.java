package com.lti.shopping.DAOImpl;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.lti.shopping.DAO.UserDAO;
import com.lti.shopping.model.UserDetails;
import com.lti.shopping.service.UserService;






@Repository("userDAO")
 
public class UserDAOImpl implements UserDAO {
	private static final Logger logger = 			
			LoggerFactory.getLogger(UserDAOImpl.class);
	
 

 
	Transaction tx;
	@Autowired
	private SessionFactory sessionFactory;
	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}


//	@Override
//	public String getByEmail(String email) {
//		//String selectQuery = "FROM User WHERE email = :email";
//		String password=null;
//		 Transaction trns = null;
//         Session session = this.sessionFactory.openSession();
//          trns = session.beginTransaction();
//          System.out.println(email);
//          Query query = session.createQuery("select password from User u  where u.email=:email");
//         
//          query.setParameter("email", email);
//          
//          List<String> passwords =  query.list();
//         Iterator iterator = query.iterate();
//         
//         while(iterator.hasNext())
//         {
//        	  password =  (String)iterator.next();
//        	 
//         }
//         System.out.println(password);
//         
//         return  password;
//   }

							
	

	@Override
	public void addUser(UserDetails u) {
		Session session = this.sessionFactory.openSession();
		tx = session.beginTransaction();
		session.save(u);
		tx.commit();
		session.close();
		logger.info("User details saved successfully, User Details="+ u);
	}


@Override
	public UserDetails get(int id) {
		try {			
			return (UserDetails) sessionFactory.getCurrentSession().get(UserDetails.class, id);			
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
			return null;
		}
		
	}


@Override
public boolean verifyUser(String email, String password) {
	Session session = this.sessionFactory.openSession();
	 tx=session.beginTransaction();
 
	 
	  String query="select email,password from UserDetails where email=:email and password=:password";

	  Query q=session.createQuery(query);
	  q.setString("email",email);
	  q.setString("password",password);
	 
	  System.out.println(5);
		  List<UserDetails> l=q.list();
		  if(l.size()==0)
		  {
		    return false;
		  }
	   
		 tx.commit(); 
	  session.close();
	  return true;


}


	

	

	

	/*@Override
	public Address getAddress(int addressId) {
		try {			
			return sessionFactory.getCurrentSession().get(Address.class, addressId);			
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
			return null;
		}
	}*/

}

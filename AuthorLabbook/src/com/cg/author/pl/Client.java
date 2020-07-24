package com.cg.author.pl;

import java.util.List;
import java.util.Scanner;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import com.cg.author.entity.Author;



public class Client {

	public static void main(String[] args) {
		EntityManagerFactory emf =Persistence.createEntityManagerFactory("JPA-PU");
		EntityManager em = emf.createEntityManager();
		int choice=0;
		 Scanner scanner = new Scanner(System.in);
		em.getTransaction().begin();
		while(choice!=7)
		 {
		 System.out.println("1.Add Author");
		 System.out.println("2.Search Author");
		 
		 System.out.println("3.Update Author");
		 System.out.println("4.Delete  Author");
		 System.out.println("5.list of Authors");
		 System.out.println("6.Exit");
		 
		 System.out.println(" Enter your choice ");
		 choice =scanner.nextInt();
		 
		 switch(choice)
		 {
		 case 1 :
			 Author author= new Author();
			 scanner.nextLine();
			 System.out.println("enter Author first name");
			 String fname =scanner.nextLine();
			 System.out.println("enter Author middle name");
			 String mname= scanner.nextLine();
			 System.out.println("enter last name");
			 String lname = scanner.nextLine();
			 System.out.println("enter phone no.");
			 long phno =scanner.nextLong();
			 author.setFirstName(fname);
			 author.setMiddleName(mname);
			 author.setLastName(lname);
			 author.setPhoneNo(phno);
			 em.persist(author);
			 System.out.println(author);
			 em.getTransaction().commit();
              break;
		 case 2: 
			 System.out.println("enter author id");
			 int authorId = scanner.nextInt();
			  author = em.find(Author.class,authorId);
				if(author==null)
				{
					System.out.println(" NOT FOUND ");
				}
				else
				{
					System.out.println(author);
				}
				//em.getTransaction().commit();
				 break;
		 case 3: 
			 System.out.println("enter author id");
			  authorId = scanner.nextInt();
			  author = em.find(Author.class,authorId);
				if(author==null)
				{
					System.out.println("Id NOT FOUND ");
				}
				else
				{
					scanner.nextLine();
					 System.out.println("enter Author first name");
					 fname =scanner.nextLine();
					 System.out.println("enter Author middle name");
					  mname= scanner.nextLine();
					 System.out.println("enter last name");
					  lname = scanner.nextLine();
					 System.out.println("enter phone no.");
					  phno =scanner.nextLong();
					 author.setFirstName(fname);
					 author.setMiddleName(mname);
					 author.setLastName(lname);
					 author.setPhoneNo(phno);
					 em.merge(author);
					 System.out.println(author);
					 System.out.println(" Details Updated");
					 em.getTransaction().commit();

				}
				 break;
		 case 4:
			 System.out.println("enter author id");
			  authorId = scanner.nextInt();
			  author = em.find(Author.class,authorId);
				if(author==null)
				{
					System.out.println("Id NOT FOUND ");
				}
				else
				{
					em.remove(author);
					em.getTransaction().commit();
					System.out.println("deleted author with "+ author.getAuthorId());
				
				}
				 break;
		 case 5 :
			 String query="select  a from  Author a";
				
				TypedQuery<Author> qry = em.createQuery(query,Author.class);
				
				List<Author> list = qry.getResultList();
				
				for(Author auth :  list)
				{
					System.out.println(auth);
				}
			 break;
		 case 6: System.out.println("thank you ");
		        return;
			 
			 
			 
		 }
		 }
		
em.close();
emf.close();
	}

}

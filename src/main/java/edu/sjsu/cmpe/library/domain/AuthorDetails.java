package edu.sjsu.cmpe.library.domain;

import org.hibernate.validator.constraints.NotEmpty;


public class AuthorDetails {
	private int id=0;
	@NotEmpty
	private String name;

	private static int key=1;
	
	public AuthorDetails ()
	{
		
		this.id = ++key;
	}	
	
	/*public int getId()
	{
		return id;
	}
	
	public void setId(int tempId)
	{
		this.id=tempId;
	}*/
	
	
	public String getName()
	{
		return name;
	}
	
	public void setName(String bAuth)
	{
		this.name=bAuth;
	}
}

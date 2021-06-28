package edu.sjsu.cmpe.library.dto;

import edu.sjsu.cmpe.library.domain.AuthorDetails;

public class AuthorDto {
	
private AuthorDetails author;
	
	public AuthorDto(AuthorDetails author)
	{
		super();
		this.author=author;
	}
	
	public AuthorDetails getAuthor()
	{
		return author;
	}
	
	public void setAuthor(AuthorDetails author)
	{
		this.author=author;
	}

}

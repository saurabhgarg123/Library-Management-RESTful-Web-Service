package edu.sjsu.cmpe.library.domain;

import org.hibernate.validator.constraints.NotEmpty;


public class ReviewDetails {
	
	private int reviewId;
	@NotEmpty
	private int rating;
	@NotEmpty
	private String comment;
	
	public int getId()
	{
		return reviewId;
	}
	
	public void setId(int tempId)
	{
		reviewId=tempId;
	}
	
	public int getRating()
	{
		return rating;
	}
	
	public void setRating(int tempRating)
	{
		rating=tempRating;
	}
	
	public String getComment()
	{
		return comment;
	}
	
	public void setComment(String tempReview)
	{
		comment=tempReview;
	}
}


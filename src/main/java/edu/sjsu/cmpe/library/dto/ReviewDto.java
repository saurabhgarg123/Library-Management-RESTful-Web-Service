package edu.sjsu.cmpe.library.dto;

import edu.sjsu.cmpe.library.domain.ReviewDetails;


public class ReviewDto extends LinksDto{
	
	private ReviewDetails review;
	
	public ReviewDto(ReviewDetails review)
	{
		super();
		this.review= review;
	}
	
	public ReviewDetails getReview()
	{
		return review;
	}
	
	public void setReview(ReviewDetails review)
	{
		this.review=review;
	}
}

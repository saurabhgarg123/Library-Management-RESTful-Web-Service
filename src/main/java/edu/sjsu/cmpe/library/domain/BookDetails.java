package edu.sjsu.cmpe.library.domain;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;
import org.hibernate.validator.constraints.NotEmpty;

public class BookDetails 
{
	private int isbnKey;
	@NotEmpty
	private String title;
	@NotEmpty
	@JsonProperty("publication-date")
	private String pubDate;
	private String language;
	@JsonProperty("num-pages")
	private int numPages;
	private String status;
	//private List <String> comments= new ArrayList<String>();
	private List <AuthorDetails> authors = new ArrayList<AuthorDetails>();
	//public static HashMap<Integer, AuthorDetails> authMap= new HashMap<Integer, AuthorDetails>();
	//AuthorDetails authors= new AuthorDetails();
	//public static HashMap<Integer, AuthorDetails> tempauthMap= new HashMap<Integer, AuthorDetails>();
	//	
	public int getisbnKey()
	{

		return isbnKey;
	}

	public void setisbnKey(int num)
	{
		this.isbnKey=num;
	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String bTitle)
	{
		this.title=bTitle;
	}	
	
	public void setPubDate(String bDate)
	{
		this.pubDate=bDate;
	}
	
	public String getPubDate()
	{
		return pubDate;
	}

	public void setLanguage(String bLang)
	{
		this.language=bLang;
	}
	
	public String getLanguage()
	{
		return language;
	}
	
	public void setPages(int bPages)
	{
		this.numPages=bPages;
	}
	
	public int getPages()
	{
		return numPages;
	}
	
	public void setStatus(String bStatus)
	{
		this.status=bStatus;
	}
	
	public String getStatus()
	{
		return status;
	}
	
	public List<AuthorDetails> getAuthors()
	{
		return authors;
	}

	public void setAuthors(List<AuthorDetails> tempbAuthL)
	{		
		this.authors=tempbAuthL;
	}
	/*public HashMap<Integer, AuthorDetails> getAuthors()
	{
		return authMap;
	}

	public void setAuthors(AuthorDetails authors)
	{
		int mapId= tempauthMap.size()+1;
		tempauthMap.put(mapId, authors);
		authMap=tempauthMap;
	}*/

	/*public AuthorDetails getAuthorMapById(int x)
	{
			//return authMap.get(authors.getAuthId());
		
		return authMap.get(x);
	}*/


	/*public int getReviewId()
	{
		return reviewId;
	}

	public void setReviewId(int tempId)
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

	public List<String> getReview()
	{
		return comments;
	}

	public void setReview(int revId, String tempReview)
	{
		this.comments.add(revId, tempReview);
	}*/
}


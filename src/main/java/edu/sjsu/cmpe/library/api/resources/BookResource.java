package edu.sjsu.cmpe.library.api.resources;

	
	import java.util.ArrayList;
	import java.util.HashMap;
	import java.util.List;

	import javax.validation.Valid;
	import javax.ws.rs.Consumes;
	import javax.ws.rs.DELETE;
	import javax.ws.rs.GET;
	import javax.ws.rs.POST;
	import javax.ws.rs.PUT;
	import javax.ws.rs.Path;
	import javax.ws.rs.PathParam;
	import javax.ws.rs.Produces;
	import javax.ws.rs.QueryParam;
	import javax.ws.rs.core.MediaType;
	import javax.ws.rs.core.Response;

	import com.google.common.base.Optional;
	import com.yammer.dropwizard.jersey.params.IntParam;
	import com.yammer.metrics.annotation.Timed;

	import edu.sjsu.cmpe.library.domain.AuthorDetails;
	import edu.sjsu.cmpe.library.domain.BookDetails;
	import edu.sjsu.cmpe.library.domain.ReviewDetails;
	import edu.sjsu.cmpe.library.dto.BookDto;
	import edu.sjsu.cmpe.library.dto.LinkDto;
	import edu.sjsu.cmpe.library.dto.ReviewDto;


	@Path("/v1/books")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)

	public class BookResource {
		
		private static HashMap<Integer, BookDetails> bookMap = new HashMap<Integer, BookDetails>();
		private static HashMap<Integer, ReviewDetails> reviewMap= new HashMap<Integer, ReviewDetails>();
		
		public BookResource()
		{
		}
		
		@POST
			
		@Timed(name="create-books")
		@Produces(MediaType.APPLICATION_JSON)
		@Consumes(MediaType.APPLICATION_JSON)
		public Response addBook(@Valid BookDetails book)
		{
			int num=bookMap.size()+1;
			book.setisbnKey(num);
			bookMap.put(num,book) ;
			
			
			BookDto bookResponse = new BookDto(book);
			//bookResponse.setBook(book);
			bookResponse.addLink(new LinkDto("view-book","/books/" +book.getisbnKey(), "GET"));
			bookResponse.addLink(new LinkDto("update-book","/books/"+book.getisbnKey(), "PUT"));
			bookResponse.addLink(new LinkDto("delete-book","/books/"+book.getisbnKey(), "DELETE"));
			bookResponse.addLink(new LinkDto("create-review", "/books/"+book.getisbnKey(), "POST"));

			return Response.status(201).entity(bookResponse).build();
		}
		
		@GET
		@Path("{isbnKey}")
		@Timed(name="view-book")
		@Produces(MediaType.APPLICATION_JSON)
		@Consumes(MediaType.APPLICATION_JSON)
		public BookDto getBookByIsbn(@PathParam("isbnKey") IntParam isbnKey)
		{
			BookDetails book=new BookDetails();
			book=bookMap.get(isbnKey.get());
			
			BookDto bookResponse = new BookDto(book);
			bookResponse.addLink(new LinkDto("view-book","/books/" +book.getisbnKey(), "GET"));
			bookResponse.addLink(new LinkDto("update-book","/books/"+book.getisbnKey(), "PUT"));
			bookResponse.addLink(new LinkDto("delete-book","/books/"+book.getisbnKey(), "DELETE"));
			bookResponse.addLink(new LinkDto("create-review", "/books/"+book.getisbnKey(), "POST"));
			bookResponse.addLink(new LinkDto("view-all-reviews", "/books/"+book.getisbnKey(), "GET"));
			return bookResponse;
			
		}
		
		@DELETE
		@Path("{isbnKey}")
		@Timed(name="delete-book")
		@Produces(MediaType.APPLICATION_JSON)
		@Consumes(MediaType.APPLICATION_JSON)
		public BookDto deleteBookByIsbn(@PathParam("isbnKey") IntParam isbnKey)
		{
			BookDetails book = new BookDetails();
			book.setisbnKey(isbnKey.get());
			bookMap.remove(isbnKey.get());
			
			BookDto bookResponse = new BookDto(book);
			bookResponse.addLink(new LinkDto("create-review", "/books/"+book.getisbnKey(), "POST"));
			return bookResponse;
		}
		
		@PUT
		@Path("{isbnKey}")
		@Timed(name="update-book")
		@Produces(MediaType.APPLICATION_JSON)
		@Consumes(MediaType.APPLICATION_JSON)
		public BookDto updateBookByIsbn(@PathParam("isbnKey") IntParam isbnKey, @QueryParam("status") Optional<String> status)
		{
			String tempName="";
			int tempId=0;
			for(BookDetails iter: bookMap.values())
			{
				if(iter.getisbnKey()==isbnKey.get())
				{
					iter.setStatus(status.get());
					tempName=iter.getStatus();
					tempId=iter.getisbnKey();	
				}
			}
			BookDetails bookDisp=new BookDetails();
			bookDisp.setStatus(tempName);
			bookDisp.setisbnKey(tempId);
			
			BookDto bookResponse = new BookDto(bookDisp);
			bookResponse.addLink(new LinkDto("view-book","/books/" +bookDisp.getisbnKey(), "GET"));
			bookResponse.addLink(new LinkDto("update-book","/books/"+bookDisp.getisbnKey(), "PUT"));
			bookResponse.addLink(new LinkDto("delete-book","/books/"+bookDisp.getisbnKey(), "DELETE"));
			bookResponse.addLink(new LinkDto("create-review", "/books/"+bookDisp.getisbnKey(), "POST"));
			bookResponse.addLink(new LinkDto("view-all-reviews", "/books/"+bookDisp.getisbnKey(), "GET"));
			return bookResponse;	
		}
		
		@POST
		@Path("{isbnKey}/reviews")
		@Timed(name="create-review")
		@Produces(MediaType.APPLICATION_JSON)
		@Consumes(MediaType.APPLICATION_JSON)
		public Response addReview(@Valid @PathParam("isbnKey") IntParam isbnKey, ReviewDetails review)
		{
			for(BookDetails iter: bookMap.values())
			{
				if(iter.getisbnKey()==isbnKey.get())
				{
					int num1=reviewMap.size()+1;
					review.setId(num1);
					reviewMap.put(num1, review);
				}
			}
			return Response.status(201).entity(review).build();		
		}
		
		@GET
		@Path("{isbnKey}/reviews/{reviewId}")
		@Timed(name="view-review")
		@Produces(MediaType.APPLICATION_JSON)
		@Consumes(MediaType.APPLICATION_JSON)
		public ReviewDto viewReviewById(@PathParam("isbnKey") IntParam isbnKey, @PathParam("reviewId") IntParam reviewId)
		{
			ReviewDetails review= new ReviewDetails();
			for(BookDetails iter: bookMap.values())
			{
				if(iter.getisbnKey()==isbnKey.get())
				{
					
					review=reviewMap.get(reviewId.get());
				}
			}
			ReviewDto reviewResponse= new ReviewDto(review);
			reviewResponse.addLink(new LinkDto("view-review","/books/"+review.getId(), "GET"));
			return reviewResponse;
		}
		
		@GET
		@Path("{isbnKey}/reviews")
		@Timed(name="view-all-reviews")
		@Produces(MediaType.APPLICATION_JSON)
		@Consumes(MediaType.APPLICATION_JSON)
		public List<ReviewDetails> viewReviewbyId(@PathParam("isbnKey") IntParam isbnKey)
		{
			List<ReviewDetails> tempList= new ArrayList<ReviewDetails>();
			for(BookDetails iter: bookMap.values())
			{
				if(iter.getisbnKey()==isbnKey.get())
				{
					tempList.addAll(reviewMap.values());
				}
			}
			return tempList;
		}
		
		@GET
		@Path("{isbnKey}/authors")
		@Timed(name="view-all-authors")
		@Produces(MediaType.APPLICATION_JSON)
		@Consumes(MediaType.APPLICATION_JSON)
		public List<AuthorDetails> viewAuthorById(@PathParam("isbnKey") IntParam isbnKey)
		{
			List<AuthorDetails> tempList= new ArrayList<AuthorDetails>();
			
			for(BookDetails iter: bookMap.values())
			{
				if(iter.getisbnKey()==isbnKey.get())
				{
					tempList.addAll(iter.getAuthors());
				}
			}
			return tempList;
		}
		
		@GET
		@Path("{isbnKey}/authors/{id}")
		@Timed(name="view-all-authors")
		@Produces(MediaType.APPLICATION_JSON)
		@Consumes(MediaType.APPLICATION_JSON)
		public String viewAuthorById(@PathParam("isbnKey") IntParam isbnKey, @PathParam("id") IntParam id)
		{
			List<AuthorDetails> tempList= new ArrayList<AuthorDetails>();
			String tempStr="";
			for(BookDetails iter: bookMap.values())
			{
				if(iter.getisbnKey()==isbnKey.get())
				{
					tempList.addAll(iter.getAuthors());
					tempStr=tempList.get(id.get()).getName();
				}
			}
			return tempStr;
			/*AuthorDto authorResponse = new AuthorDto();
			authorResponse.addLink(new LinkDto("view-author","/books/"+isbnKey+"/authors/"++, "GET"));
			return authorResponse;*/
		}
	}
		



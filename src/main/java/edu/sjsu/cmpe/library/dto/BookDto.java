package edu.sjsu.cmpe.library.dto;

import edu.sjsu.cmpe.library.domain.BookDetails;

public class BookDto extends LinksDto {
	
    private BookDetails book;

    /**
     * @param book
     */
    public BookDto(BookDetails book) {
	super();
	this.book = book;
    }

    /**
     * @return the book
     */
    public BookDetails getBook() {
	return book;
    }

    /**
     * @param book
     *            the book to set
     */
    public void setBook(BookDetails book) {
	this.book = book;
    }

}

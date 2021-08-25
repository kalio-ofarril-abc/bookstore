package com.bookstore.bookstore.util;

//Even Book Response class to match the desired format and variables of the get request
public class EvenBookResponse {
	
	private String title;
	private int publicationYear;
	private int vowelsSum;
	
	public EvenBookResponse() {}
	
	public EvenBookResponse(String title, int publicationYear, int vowelsSum) {
		this.title = title;
		this.publicationYear = publicationYear;
		this.vowelsSum = vowelsSum;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getPublicationYear() {
		return publicationYear;
	}

	public void setPublicationYear(int publicationYear) {
		this.publicationYear = publicationYear;
	}

	public int getVowelsSum() {
		return vowelsSum;
	}

	public void setVowelsSum(int vowelsSum) {
		this.vowelsSum = vowelsSum;
	}

	@Override
	public String toString() {
		return "EvenBookResponse [title=" + title + ", publicationYear=" + publicationYear + ", vowelsSum=" + vowelsSum
				+ "]";
	}
	
}

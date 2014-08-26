package com.teamalpha.endpoint;


//@Api(name="quoteapi",version="v1", description="An API to manage famous quotes")
public class QuoteServiceAPI {

//	public static List<Quote> quotes = new ArrayList<Quote>();
	/*
	@ApiMethod(name="add")
	public Quote addQuote(@Named("name") String name) throws NotFoundException {
		
		//Patient p = DatabaseManager.createPatient(name);
		//Check for already exists
		//int index = quotes.indexOf(new Quote(id));
		//if (index != -1) throw new NotFoundException("Quote Record already exists");

		//Quote q = new Quote(1, "author", "message");
		//quotes.add(q);
		//return p.getModel();cccc
		//return q;
		return new Quote(1, "john doe", "something");
	}

	@ApiMethod(name="update")
	public Quote updateQuote(Quote q) throws NotFoundException {
		int index = quotes.indexOf(q);
		if (index == -1)
			throw new NotFoundException("Quote Record does not exist");
		  Quote currentQuote = quotes.get(index);
		  currentQuote.setAuthor(q.getAuthor());
		  currentQuote.setMessage(q.getMessage());
		  return q;
	}

	@ApiMethod(name="remove")
	public void removeQuote(@Named("id") Integer id) throws NotFoundException {
		int index = quotes.indexOf(new Quote(id));
		if (index == -1)
			throw new NotFoundException("Quote Record does not exist");
		quotes.remove(index);
	}

	@ApiMethod(name="list")
	public List<Quote> getQuotes() {
		return quotes;
	}

	@ApiMethod(name="listByAuthor")
	public List<Quote> getQuotesByAuthor(@Named("author") String author) {
		List<Quote> results = new ArrayList<Quote>();
		for (Quote quote : quotes) {
			if (quote.getAuthor().indexOf(author) != -1) {
				results.add(quote);
			}
		}
		return results;
	}

	@ApiMethod(name="getQuote")
	public Quote getQuote(@Named("id") Integer id) throws NotFoundException {
		int index = quotes.indexOf(new Quote(id));
		if (index == -1)
			throw new NotFoundException("Quote Record does not exist");
		return quotes.get(index);
	}
*/
}

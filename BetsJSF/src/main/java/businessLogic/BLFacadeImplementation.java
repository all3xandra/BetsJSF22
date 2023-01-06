package businessLogic;

import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import javax.jws.WebMethod;
import javax.jws.WebService;

import configuration.ConfigXML;
import dataAccess.DataAccessInterface;
import domain.Question;
import domain.Event;
import exceptions.EventFinished;
import exceptions.QuestionAlreadyExist;

/**
 * It implements the business logic as a web service.
 */
@WebService(endpointInterface = "businessLogic.BLFacade")
public class BLFacadeImplementation  implements BLFacade {
	DataAccessInterface dbManager;
	
    public BLFacadeImplementation(DataAccessInterface da)  {
		
		System.out.println("Creating BLFacadeImplementation instance with DataAccess parameter");

		dbManager=da;		
	}
	

	/**
	 * This method creates a question for an event, with a question text and the minimum bet
	 * 
	 * @param event to which question is added
	 * @param question text of the question
	 * @param betMinimum minimum quantity of the bet
	 * @return the created question, or null, or an exception
	 * @throws EventFinished if current data is after data of the event
 	 * @throws QuestionAlreadyExist if the same question already exists for the event
	 */
   @WebMethod
   public Question createQuestion(Event event, String question, float betMinimum) throws EventFinished, QuestionAlreadyExist{
	   
		Question qry=null;
		if(new Date().compareTo(event.getEventDate())>0)
			throw new EventFinished(ResourceBundle.getBundle("Etiquetas").getString("ErrorEventHasFinished"));

		 qry=dbManager.createQuestion(event,question,betMinimum);	
		
		return qry;
   };
	
	/**
	 * This method invokes the data access to retrieve the events of a given date 
	 * 
	 * @param date in which events are retrieved
	 * @return collection of events
	 */
    @WebMethod	
	public List<Event> getEvents(Date date)  {
		List<Event>  events=dbManager.getEvents(date);
		return events;
	}
    
    /**
	 * This method retrieves from the database all the existent events
	 * 
	 * @return collection of events
	 */
	@WebMethod public List<Event> getAllEvents(){
		List<Event> eventos = dbManager.getAllEvents();
		return eventos;
	}

    
	/**
	 * This method invokes the data access to retrieve the dates a month for which there are events
	 * 
	 * @param date of the month for which days with events want to be retrieved 
	 * @return collection of dates
	 */
	@WebMethod public List<Date> getEventsMonth(Date date) {
		List<Date>  dates=dbManager.getEventsMonth(date);
		return dates;
	}
	
	/**
	 * This method retrieves all the questions for a given event
	 * 
	 * @param event the event to retrieve the questions from
	 * @return collection of questions
	 */
	@WebMethod public List<Question> getQuestionsForEvent(Event event){
		List<Question> questions = dbManager.getQuestionsForEvent(event);
		return questions;
	}
	
	/**
	 * This method retrieves all the questions from the database
	 * 
	 * @return collection of questions
	 */
	@WebMethod public List<Question> getAllQuestions(){
		List<Question> questions = dbManager.getAllQuestions();
		return questions;
	}

	/**
	 * This method invokes the data access to initialize the database with some events and questions.
	 * It is invoked only when the option "initialize" is declared in the tag dataBaseOpenMode of resources/config.xml file
	 */	
    @WebMethod	
	 public void initializeBD(){
		dbManager.initializeDB();
	}

}


package modelo.bean;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import org.primefaces.event.SelectEvent;

import businessLogic.BLFacade;
import businessLogic.BLFacadeImplementation;
import domain.Event;
import domain.Question;
import configuration.UtilDate;
import dataAccess.HibernateDataAccess;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Date;

@ManagedBean
@SessionScoped
public class CreateQuestionBean {
	private List<Event> events;
	private List<Question> questions;
	private String newQuestion;
	private Event selectedEvent;
	private Date selectedDate;
	private List<Event> allEvents;
	private float bet;
	private String message;
	private Converter eventConverter;
	private static BLFacade ln;

	public CreateQuestionBean() {
		HomeBean homeBean = FacesContext.getCurrentInstance().getApplication()
				.evaluateExpressionGet(FacesContext.getCurrentInstance(), "#{home}", HomeBean.class);
		this.ln = homeBean.getLN();

		events = new ArrayList<Event>();
		questions = new ArrayList<Question>();
		allEvents = ln.getAllEvents();
		eventConverter = new EventConverter(allEvents);
	}

	public void setLN(BLFacade ln) {
		this.ln = ln;
	}

	public Converter getEventConverter() {
		return eventConverter;
	}

	public List<Event> getEvents() {
		return events;
	}

	public void setEvents(List<Event> events) {
		this.events = events;
	}

	public List<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}

	public String getNewQuestion() {
		return newQuestion;
	}

	public void setNewQuestion(String newQuestion) {
		this.newQuestion = newQuestion;
	}

	public Event getSelectedEvent() {
		System.out.println("getSelectedEvent ");
		return selectedEvent;
	}

	public void setSelectedEvent(Event selectedEvent) {
		System.out.println("setSelectedEvent ");
		this.selectedEvent = selectedEvent;
	}

	public Date getSelectedDate() {
		return selectedDate;
	}

	public void setSelectedDate(Date selectedDate) {
		this.selectedDate = selectedDate;
	}

	public float getBet() {
		return bet;
	}

	public void setBet(float bet) {
		this.bet = bet;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void updateEvents(SelectEvent event) {
		System.out.println("updateEvents for date: " + selectedDate);
		selectedDate = (Date) event.getObject();
		events = ln.getEvents(selectedDate);
		if (events.size() > 0) {
	        selectedEvent = events.get(0);
	    } else {
	        selectedEvent = null;
	    }
		System.out.println(selectedEvent);
	}

	public void addQuestion() {
		createQuestion(selectedEvent);
	}

	public void createQuestion(Event event) {
		if(event!=null) {
			System.out.println("Event " + event);
			List<Question> qst = ln.getQuestionsForEvent(event);
			System.out.println("Before adding the question, this event has " + qst.size());
			try {
				ln.createQuestion(event, newQuestion, bet);
				updateQuestionsInQueryQuestionsPage();
				
				qst = ln.getQuestionsForEvent(event);
				System.out.println("After adding the question, this event has " + qst.size());
				message = "Question added.";
			} catch(Exception ex) {
				System.out.println(ex.toString());
			}
		} else {
			message = "An error has occurred.";
			System.out.println("Event null.");
		}
	}
	
	private void updateQuestionsInQueryQuestionsPage() {
		QueryQuestionsBean queryQuestionsBean = FacesContext.getCurrentInstance().getApplication()
			.evaluateExpressionGet(FacesContext.getCurrentInstance(), "#{qq}", QueryQuestionsBean.class);
		queryQuestionsBean.updateQuestions();
	}

	public void updateSelectedEvent() {
		System.out.println("updateSelectedEvent method triggered.");
		if(selectedEvent!=null) {
			System.out.println(selectedEvent);
		}
	}

}

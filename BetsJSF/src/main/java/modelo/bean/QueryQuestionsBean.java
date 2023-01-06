package modelo.bean;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import org.primefaces.event.SelectEvent;

import businessLogic.BLFacade;
import businessLogic.BLFacadeImplementation;
//import configuration.UtilDate;
import dataAccess.HibernateDataAccess;
import domain.Event;
import domain.Question;

@ManagedBean
@SessionScoped
public class QueryQuestionsBean {

	private List<Event> events;
	private List<Question> questions;
	private Question question;
	private Event selectedEvent;
	private Date selectedDate;
	private Question selectedQuestion;
	private List<Event> allEvents;
	private List<Question> allQuestions;
	private Converter eventConverter;
	private Converter questionConverter;
	private static BLFacade ln;

	public QueryQuestionsBean() {
		// initialize the ln property
		HomeBean homeBean = FacesContext.getCurrentInstance().getApplication()
				.evaluateExpressionGet(FacesContext.getCurrentInstance(), "#{home}", HomeBean.class);
		this.ln = homeBean.getLN();

		events = new ArrayList<Event>();
		questions = new ArrayList<Question>();
		allEvents = ln.getAllEvents();
		allQuestions = new ArrayList<Question>();
		eventConverter = new EventConverter(allEvents);
		questionConverter = new QuestionConverter(allQuestions);
		
	}

	public void setLN(BLFacade ln) {
		this.ln = ln;
	}

	public Converter getEventConverter() {
		return eventConverter;
	}

	public Converter getQuestionConverter() {
		return questionConverter;
	}

	public List<Event> getEvents() {
		return events;
	}

	public void setEvents(List<Event> events) {
		this.events = events;
	}

	public List<Question> getQuestions() {
		System.out.println("getQuestions");
		return questions;
	}

	public void setQuestions(List<Question> questions) {
		System.out.println("setQuestions");
		this.questions = questions;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public Event getSelectedEvent() {
		return selectedEvent;
	}

	public void setSelectedEvent(Event selectedEvent) {
		this.selectedEvent = selectedEvent;
	}

	public Date getSelectedDate() {
		return selectedDate;
	}

	public void setSelectedDate(Date selectedDate) {
		this.selectedDate = selectedDate;
	}

	public Question getSelectedQuestion() {
		return selectedQuestion;
	}

	public void setSelectedQuestion(Question selectedQuestion) {
		this.selectedQuestion = selectedQuestion;
	}

	public void updateEvents(SelectEvent event) {
		System.out.println("updateEvents for date: " + selectedDate);
		questions.clear();

		if(event!=null) {
			selectedDate = (Date) event.getObject();
			events = ln.getEvents(selectedDate);
			if (events.size() > 0) {
				selectedEvent = events.get(0);
			} else {
				selectedEvent = null;
			}
			System.out.println(selectedEvent);
			updateSelectedEvent();
			updateSelectedQuestion();
		}

	}

	public void updateSelectedEvent() {
		System.out.println("updateSelectedEvent method triggered.");
		System.out.println("Updating questions list...");
		if(selectedEvent!=null) {
			questions = ln.getQuestionsForEvent(selectedEvent);
			System.out.println("System detects " + questions.size() + " questions.");
		}
	}

	public void updateQuestions() {
		// retrieve the updated list of questions
		allQuestions = ln.getAllQuestions();
	}

	public void updateSelectedQuestion() {
		// No additional code needed
	}
}

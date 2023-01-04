package modelo.bean;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.convert.Converter;

import configuration.UtilDate;
import domain.Event;
import domain.Question;

@ManagedBean
@SessionScoped
public class QueryQuestions {

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

	public QueryQuestions() {
		events = new ArrayList<Event>();
		questions = new ArrayList<Question>();
		allEvents = new ArrayList<Event>();
		allQuestions = new ArrayList<Question>();
		addEvents();
		eventConverter = new EventConverter(allEvents);
		questionConverter = new QuestionConverter(allQuestions);
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

	private void addEvents() {

		Calendar today = Calendar.getInstance();

		int month=today.get(Calendar.MONTH);
		month+=1;
		int year=today.get(Calendar.YEAR);
		if (month==12) { month=0; year+=1;}

		Event ev1=new Event(1, "Atlético-Athletic", UtilDate.newDate(year,month,17));
		Event ev2=new Event(2, "Eibar-Barcelona", UtilDate.newDate(year,month,17));
		Event ev3=new Event(3, "Getafe-Celta", UtilDate.newDate(year,month,17));
		Event ev4=new Event(4, "Alavés-Deportivo", UtilDate.newDate(year,month,17));
		Event ev5=new Event(5, "Español-Villareal", UtilDate.newDate(year,month,17));
		Event ev6=new Event(6, "Las Palmas-Sevilla", UtilDate.newDate(year,month,17));
		Event ev7=new Event(7, "Malaga-Valencia", UtilDate.newDate(year,month,17));
		Event ev8=new Event(8, "Girona-Leganés", UtilDate.newDate(year,month,17));
		Event ev9=new Event(9, "Real Sociedad-Levante", UtilDate.newDate(year,month,17));
		Event ev10=new Event(10, "Betis-Real Madrid", UtilDate.newDate(year,month,17));

		Event ev11=new Event(11, "Atletico-Athletic", UtilDate.newDate(year,month,1));
		Event ev12=new Event(12, "Eibar-Barcelona", UtilDate.newDate(year,month,1));
		Event ev13=new Event(13, "Getafe-Celta", UtilDate.newDate(year,month,1));
		Event ev14=new Event(14, "Alavés-Deportivo", UtilDate.newDate(year,month,1));
		Event ev15=new Event(15, "Español-Villareal", UtilDate.newDate(year,month,1));
		Event ev16=new Event(16, "Las Palmas-Sevilla", UtilDate.newDate(year,month,1));


		Event ev17=new Event(17, "Málaga-Valencia", UtilDate.newDate(year,month,28));
		Event ev18=new Event(18, "Girona-Leganés", UtilDate.newDate(year,month,28));
		Event ev19=new Event(19, "Real Sociedad-Levante", UtilDate.newDate(year,month,28));
		Event ev20=new Event(20, "Betis-Real Madrid", UtilDate.newDate(year,month,28));

		Question q1;
		Question q2;
		Question q3;
		Question q4;
		Question q5;
		Question q6;

		q1=ev1.addQuestion("¿Quién ganará el partido?",1);
		q2=ev1.addQuestion("¿Quién meterá el primer gol?",2);
		q3=ev11.addQuestion("¿Quién ganará el partido?",1);
		q4=ev11.addQuestion("¿Cuántos goles se marcarán?",2);
		q5=ev17.addQuestion("¿Quién ganará el partido?",1);
		q6=ev17.addQuestion("¿Habrá goles en la primera parte?",2);

		allQuestions.add(q1);
		allQuestions.add(q2);
		allQuestions.add(q3);
		allQuestions.add(q4);
		allQuestions.add(q5);
		allQuestions.add(q6);

		allEvents.add(ev1);
		allEvents.add(ev2);
		allEvents.add(ev3);
		allEvents.add(ev4);
		allEvents.add(ev5);
		allEvents.add(ev6);
		allEvents.add(ev7);
		allEvents.add(ev8);
		allEvents.add(ev9);
		allEvents.add(ev10);
		allEvents.add(ev11);
		allEvents.add(ev12);
		allEvents.add(ev13);
		allEvents.add(ev14);
		allEvents.add(ev15);
		allEvents.add(ev16);
		allEvents.add(ev17);
		allEvents.add(ev18);
		allEvents.add(ev19);
		allEvents.add(ev20);
	}

	public void updateEvents() {
		System.out.println("updateEvents for date: " + selectedDate);
		questions.clear();
		events = getEventsForDate(selectedDate);
		if (!events.isEmpty()) {
			selectedEvent = events.get(0);
			System.out.println(selectedEvent.getDescription());
		}
	}

	public List<Event> getEventsForDate(Date date) {
		Calendar selectedDateCal = Calendar.getInstance();
		selectedDateCal.setTime(date);
		int selectedYear = selectedDateCal.get(Calendar.YEAR);
		int selectedMonth = selectedDateCal.get(Calendar.MONTH);
		int selectedDay = selectedDateCal.get(Calendar.DAY_OF_MONTH);

		List<Event> eventsForDate = new ArrayList<Event>();
		for (Event event : allEvents) {
			Calendar eventDateCal = Calendar.getInstance();
			eventDateCal.setTime(event.getEventDate());
			int eventYear = eventDateCal.get(Calendar.YEAR);
			int eventMonth = eventDateCal.get(Calendar.MONTH);
			int eventDay = eventDateCal.get(Calendar.DAY_OF_MONTH);

			if (eventYear == selectedYear && eventMonth == selectedMonth && eventDay == selectedDay) {
				eventsForDate.add(event);
			}
		}
		return eventsForDate;
	}

	public void updateSelectedEvent() {
		System.out.println("updateSelectedEvent method triggered.");
		System.out.println("Updating questions list...");
		if(selectedEvent!=null) {
			questions = getQuestionsForEvent(selectedEvent);
			System.out.println("System detects " + questions.size() + " questions.");
		}
	}

	public List<Question> getQuestionsForEvent(Event event){
		System.out.println("getQuestionsForEvent method triggered.");
		List<Question> questions = event.getQuestions();
		System.out.println("Event " + event.getDescription() + "has " + questions.size() + " questions.");
		return questions;
	}
	
	public void updateSelectedQuestion() {
		// No additional code needed
	}
}

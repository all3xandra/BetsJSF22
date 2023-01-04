package modelo.bean;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import domain.Event;
import domain.Question;
import configuration.UtilDate;

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

	public CreateQuestionBean() {
		events = new ArrayList<Event>();
		questions = new ArrayList<Question>();
		allEvents = new ArrayList<Event>();
		addEvents();
	}

	public void addQuestion() {
		// add the new question to the list of questions
		Question question = new Question();
		question.setQuestion(newQuestion);
		if(selectedEvent!=null) {
			question.setEvent(selectedEvent);
			questions.add(question);
			newQuestion = "";
			System.out.println("Question added.");
		} else {
			System.out.println("Question not added. Event null.");
		}

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
	
	public void onDateSelect(){
		System.out.println("Método 'obtenerEventos' triggereado.");
		List<Event> eventos = new ArrayList<Event>();
		for(Event event: allEvents) {
			if(event.getEventDate() == this.selectedDate) {
				eventos.add(event);
			}
		}
		this.events = eventos;
	}
	
	public void updateEvents() {
//		// retrieve the events for the selected date
//	    List<Event> eventsForSelectedDate = eventService.getEventsForDate(selectedDate);
//	    // update the events field of the bean with the retrieved events
//	    events = eventsForSelectedDate;
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
}

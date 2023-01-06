package dataAccess;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.jws.WebMethod;

import org.hibernate.Query;
import org.hibernate.Session;

import configuration.UtilDate;
import domain.Event;
import domain.Question;
import exceptions.QuestionAlreadyExist;
import modelo.HibernateUtil;

/**
 * It implements the data access to the objectDb database
 */
public class HibernateDataAccess implements DataAccessInterface {

	public HibernateDataAccess()  {
		initializeDB();
	}


	/**
	 * This is the data access method that initializes the database with some events and questions.
	 * This method is invoked by the business logic (constructor of BLFacadeImplementation) when the option "initialize" is declared in the tag dataBaseOpenMode of resources/config.xml file
	 */	
	public void initializeDB(){

		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();

		try {
			
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


			session.save(ev1);
			session.save(ev2);
			session.save(ev3);
			session.save(ev4);
			session.save(ev5);
			session.save(ev6);
			session.save(ev7);
			session.save(ev8);
			session.save(ev9);
			session.save(ev10);
			session.save(ev11);
			session.save(ev12);
			session.save(ev13);
			session.save(ev14);
			session.save(ev15);
			session.save(ev16);
			session.save(ev17);
			session.save(ev18);
			session.save(ev19);
			session.save(ev20);	
			
			session.save(q1);
			session.save(q2);
			session.save(q3);
			session.save(q4);
			session.save(q5);
			session.save(q6);
	

			session.getTransaction().commit();
			System.out.println("Db initialized");
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}

	/**
	 * This method creates a question for an event, with a question text and the minimum bet
	 * 
	 * @param event to which question is added
	 * @param question text of the question
	 * @param betMinimum minimum quantity of the bet
	 * @return the created question, or null, or an exception
	 * @throws QuestionAlreadyExist if the same question already exists for the event
	 */
	public Question createQuestion(Event event, String question, float betMinimum) throws  QuestionAlreadyExist {

		System.out.println(">> DataAccess: createQuestion=> event= "+event+" question= "+question+" betMinimum="+betMinimum);
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();

		Event ev = (Event) session.get(Event.class, event.getEventNumber());

		if (ev.DoesQuestionExists(question)) throw new QuestionAlreadyExist(ResourceBundle.getBundle("Etiquetas").getString("ErrorQueryAlreadyExist"));

		Question q = ev.addQuestion(question, betMinimum);
		session.save(ev); 
		session.save(q);
		
		session.getTransaction().commit(); 
		return q;

	}

	/**
	 * This method retrieves from the database the events of a given date 
	 * 
	 * @param date in which events are retrieved
	 * @return collection of events
	 */
	public List<Event> getEvents(Date date) {
		System.out.println(">> DataAccess: getEvents");
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction(); 

		Query q = session.createQuery("SELECT ev FROM Event ev WHERE ev.eventDate= :date");
		q.setParameter("date", date);

		List res = q.list();
		List<Event> events = new ArrayList<Event>();

		for(Object o: res) {
			System.out.println(o);
			events.add((Event) o);
		}

		session.getTransaction().commit();
		return events;
	}

	public List<Event> getAllEvents() {
		System.out.println(">> DataAccess: getAllEvents");
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction(); 

		List res = session.createQuery("from Event").list();

		session.getTransaction().commit();
		return res;
	}

	/**
	 * 
	 */
	public List<Question> getQuestionsForEvent(Event event){
		System.out.println(">> DataAccess: getQuestionsForEvent " + event.getDescription());
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction(); 

		Query q = session.createQuery("select q from Question q where q.event= :event");
		q.setParameter("event", event);

		List<Question> res = q.list();
		return res;
	}

	/**
	 * This method retrieves from the database the dates a month for which there are events
	 * 
	 * @param date of the month for which days with events want to be retrieved 
	 * @return collection of dates
	 */
	public List<Date> getEventsMonth(Date date) {
		System.out.println(">> DataAccess: getEventsMonth");
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction(); 

		Date firstDayMonthDate= UtilDate.firstDayMonth(date);
		Date lastDayMonthDate= UtilDate.lastDayMonth(date);

		List<Date> dates = new ArrayList<Date>();

		Query q = session.createQuery("SELECT DISTINCT ev.eventDate FROM Event ev WHERE ev.eventDate BETWEEN :firstDayMonthDate and :lastDayMonthDate");
		List res = q.list();

		for(Object o: res) {
			System.out.println(o);
			dates.add((Date) o);
		}

		session.getTransaction().commit();
		return res;
	}
	
	/**
	 * This method retrieves all the questions from the database
	 * 
	 * @return collection of questions
	 */
	public List<Question> getAllQuestions() {
		System.out.println(">> DataAccess: getAllQuestions");
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction(); 

		List res = session.createQuery("from Question").list();

		session.getTransaction().commit();
		return res;
	}

	public boolean existQuestion(Event event, String question) {
		System.out.println(">> DataAccess: existQuestion=> event= "+event+" question= "+question);
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction(); 
		Event ev = (Event) session.get(Event.class, event.getEventNumber());
		session.getTransaction().commit();

		return ev.DoesQuestionExists(question);

	}

}


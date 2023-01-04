package modelo.bean;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import domain.Event;
import domain.Question;

import java.util.ArrayList;
import java.util.List;

@ManagedBean
@SessionScoped
public class CreateQuestionBean {
	private List<Event> events;
    private List<Question> questions;
    private String newQuestion;
    private Event selectedEvent;

    public CreateQuestionBean() {
        events = new ArrayList<Event>();
        questions = new ArrayList<Question>();
    }

    public void addQuestion() {
        // add the new question to the list of questions
    	Question question = new Question();
        question.setQuestion(newQuestion);
        question.setEvent(selectedEvent);
        questions.add(question);
        newQuestion = "";
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
}

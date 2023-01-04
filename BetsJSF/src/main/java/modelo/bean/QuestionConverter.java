package modelo.bean;

import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import domain.Question;

@FacesConverter("questionConverter")
public class QuestionConverter implements Converter {
	
	private List<Question> questions;

	  public QuestionConverter(List<Question> questions) {
	    this.questions = questions;
	  }

	  @Override
	  public Object getAsObject(FacesContext context, UIComponent component, String value) {
	    for (Question question : questions) {
	      if (question.getQuestion().equals(value)) {
	        return question;
	      }
	    }
	    return null;
	  }

	  @Override
	  public String getAsString(FacesContext context, UIComponent component, Object value) {
	    if (value instanceof Question) {
	    	Question question = (Question) value;
	      return question.getQuestion();
	    }
	    return null;
	  }
}

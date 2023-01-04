package modelo.bean;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import domain.Event;

import java.util.List;

@FacesConverter("eventConverter")
public class EventConverter implements Converter {

  private List<Event> events;

  public EventConverter(List<Event> events) {
    this.events = events;
  }

  @Override
  public Object getAsObject(FacesContext context, UIComponent component, String value) {
    for (Event event : events) {
      if (event.getDescription().equals(value)) {
        return event;
      }
    }
    return null;
  }

  @Override
  public String getAsString(FacesContext context, UIComponent component, Object value) {
    if (value instanceof Event) {
      Event event = (Event) value;
      return event.getDescription();
    }
    return null;
  }

}

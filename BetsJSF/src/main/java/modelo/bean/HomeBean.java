package modelo.bean;

import javax.faces.context.FacesContext;

import businessLogic.BLFacade;
import businessLogic.BLFacadeImplementation;
import dataAccess.HibernateDataAccess;

public class HomeBean {

	private BLFacade ln;

	public HomeBean(){
	}

	public BLFacade getLN() {
        if (ln == null) {
            ln = new BLFacadeImplementation(new HibernateDataAccess());
        }
        return ln;
    }

	public String navigateToCreateQuestion() {
		// pass the database to the create question bean
		CreateQuestionBean createQuestionBean = FacesContext.getCurrentInstance().getApplication()
				.evaluateExpressionGet(FacesContext.getCurrentInstance(), "#{cq}", CreateQuestionBean.class);
		createQuestionBean.setLN(getLN()); // use home.getLN() to get the ln property value

		// navigate to the create question page
		return "create";
	}

	public String navigateToQueryQuestions() {
		  // pass the database to the query questions bean
		  QueryQuestionsBean queryQuestionsBean = FacesContext.getCurrentInstance().getApplication()
		      .evaluateExpressionGet(FacesContext.getCurrentInstance(), "#{qq}", QueryQuestionsBean.class);
		  queryQuestionsBean.setLN(getLN()); // use home.getLN() to get the ln property value

		  // navigate to the query questions page
		  return "query";
		}

}

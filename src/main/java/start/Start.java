package start;

import bll.businessLayer.OrderBLL;
import bll.businessLayer.ProductBLL;
import bll.businessLayer.StudentBLL;
import model.Student;
import presentation.Controller;
import presentation.View;

/** The Start of the application. Initializes the logic classes, the view and the controller.
 */
public class Start {
	/** Main Method. */
	public static void main(String[] args) {

		StudentBLL studentBll = new StudentBLL();
		ProductBLL productBLL = new ProductBLL();
		OrderBLL orderBLL = new OrderBLL();
		View view = new View();
		Controller controller = new Controller(studentBll, productBLL, orderBLL, view);

		// obtain field-value pairs for object through reflection
		Student student = new Student("dummy name", "dummy address", "dummy@address.co", 12);
		ReflectionExample.retrieveProperties(student);

	}

}
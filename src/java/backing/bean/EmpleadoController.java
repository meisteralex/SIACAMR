package backing.bean;

import modelo.Empleado;
import session.beans.EmpleadoFacade;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.annotation.PostConstruct;

@ManagedBean(name = "empleadoController")
@ViewScoped
public class EmpleadoController extends AbstractController<Empleado> {

    @EJB
    private EmpleadoFacade ejbFacade;
    private UsuarioController iduserController;

    /**
     * Initialize the concrete Empleado controller bean. The AbstractController
     * requires the EJB Facade object for most operations.
     * <p>
     * In addition, this controller also requires references to controllers for
     * parent entities in order to display their information from a context
     * menu.
     */
    @PostConstruct
    @Override
    public void init() {
        super.setFacade(ejbFacade);
        FacesContext context = FacesContext.getCurrentInstance();
        iduserController = context.getApplication().evaluateExpressionGet(context, "#{usuarioController}", UsuarioController.class);
    }

    public EmpleadoController() {
        // Inform the Abstract parent controller of the concrete Empleado?cap_first Entity
        super(Empleado.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        iduserController.setSelected(null);
    }

    /**
     * Sets the "selected" attribute of the Usuario controller in order to
     * display its data in a dialog. This is reusing existing the existing View
     * dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareIduser(ActionEvent event) {
        if (this.getSelected() != null && iduserController.getSelected() == null) {
            iduserController.setSelected(this.getSelected().getIduser());
        }
    }
}

package backing.bean;

import modelo.Ordencompra;
import session.beans.OrdencompraFacade;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.annotation.PostConstruct;

@ManagedBean(name = "ordencompraController")
@ViewScoped
public class OrdencompraController extends AbstractController<Ordencompra> {

    @EJB
    private OrdencompraFacade ejbFacade;
    private DatosinstitucionalesController codigoinstitucionController;

    /**
     * Initialize the concrete Ordencompra controller bean. The
     * AbstractController requires the EJB Facade object for most operations.
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
        codigoinstitucionController = context.getApplication().evaluateExpressionGet(context, "#{datosinstitucionalesController}", DatosinstitucionalesController.class);
    }

    public OrdencompraController() {
        // Inform the Abstract parent controller of the concrete Ordencompra?cap_first Entity
        super(Ordencompra.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        codigoinstitucionController.setSelected(null);
    }

    /**
     * Sets the "selected" attribute of the Datosinstitucionales controller in
     * order to display its data in a dialog. This is reusing existing the
     * existing View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareCodigoinstitucion(ActionEvent event) {
        if (this.getSelected() != null && codigoinstitucionController.getSelected() == null) {
            codigoinstitucionController.setSelected(this.getSelected().getCodigoinstitucion());
        }
    }
}

package backing.bean;

import modelo.Listadonecesipaac;
import session.beans.ListadonecesipaacFacade;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.annotation.PostConstruct;

@ManagedBean(name = "listadonecesipaacController")
@ViewScoped
public class ListadonecesipaacController extends AbstractController<Listadonecesipaac> {

    @EJB
    private ListadonecesipaacFacade ejbFacade;
    private DatosinstitucionalesController codigoinstitucionController;

    /**
     * Initialize the concrete Listadonecesipaac controller bean. The
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

    public ListadonecesipaacController() {
        // Inform the Abstract parent controller of the concrete Listadonecesipaac?cap_first Entity
        super(Listadonecesipaac.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        codigoinstitucionController.setSelected(null);
    }

    /**
     * Sets the "items" attribute with a collection of Detallenecesipaac
     * entities that are retrieved from Listadonecesipaac?cap_first and returns
     * the navigation outcome.
     *
     * @return navigation outcome for Detallenecesipaac page
     */
    public String navigateDetallenecesipaacCollection() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Detallenecesipaac_items", this.getSelected().getDetallenecesipaacCollection());
        }
        return "/detallenecesipaac/index";
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

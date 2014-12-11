package backing.bean;

import modelo.Datosinstitucionales;
import session.beans.DatosinstitucionalesFacade;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.annotation.PostConstruct;

@ManagedBean(name = "datosinstitucionalesController")
@ViewScoped
public class DatosinstitucionalesController extends AbstractController<Datosinstitucionales> {

    @EJB
    private DatosinstitucionalesFacade ejbFacade;

    /**
     * Initialize the concrete Datosinstitucionales controller bean. The
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
    }

    public DatosinstitucionalesController() {
        // Inform the Abstract parent controller of the concrete Datosinstitucionales?cap_first Entity
        super(Datosinstitucionales.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
    }

    /**
     * Sets the "items" attribute with a collection of Listadonecesipaac
     * entities that are retrieved from Datosinstitucionales?cap_first and
     * returns the navigation outcome.
     *
     * @return navigation outcome for Listadonecesipaac page
     */
    public String navigateListadonecesipaacCollection() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Listadonecesipaac_items", this.getSelected().getListadonecesipaacCollection());
        }
        return "/listadonecesipaac/index";
    }

    /**
     * Sets the "items" attribute with a collection of Ordencompra entities that
     * are retrieved from Datosinstitucionales?cap_first and returns the
     * navigation outcome.
     *
     * @return navigation outcome for Ordencompra page
     */
    public String navigateOrdencompraCollection() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Ordencompra_items", this.getSelected().getOrdencompraCollection());
        }
        return "/ordencompra/index";
    }

    /**
     * Sets the "items" attribute with a collection of Paac entities that are
     * retrieved from Datosinstitucionales?cap_first and returns the navigation
     * outcome.
     *
     * @return navigation outcome for Paac page
     */
    public String navigatePaacCollection() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Paac_items", this.getSelected().getPaacCollection());
        }
        return "/paac/index";
    }

}

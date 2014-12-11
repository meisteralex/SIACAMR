package backing.bean;

import modelo.Unspsc;
import session.beans.UnspscFacade;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.annotation.PostConstruct;

@ManagedBean(name = "unspscController")
@ViewScoped
public class UnspscController extends AbstractController<Unspsc> {

    @EJB
    private UnspscFacade ejbFacade;

    /**
     * Initialize the concrete Unspsc controller bean. The AbstractController
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
    }

    public UnspscController() {
        // Inform the Abstract parent controller of the concrete Unspsc?cap_first Entity
        super(Unspsc.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
    }

    /**
     * Sets the "items" attribute with a collection of Detallesolicitudobra
     * entities that are retrieved from Unspsc?cap_first and returns the
     * navigation outcome.
     *
     * @return navigation outcome for Detallesolicitudobra page
     */
    public String navigateDetallesolicitudobraCollection() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Detallesolicitudobra_items", this.getSelected().getDetallesolicitudobraCollection());
        }
        return "/detallesolicitudobra/index";
    }

    /**
     * Sets the "items" attribute with a collection of Detallenecesipaac
     * entities that are retrieved from Unspsc?cap_first and returns the
     * navigation outcome.
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
     * Sets the "items" attribute with a collection of Detallepaac entities that
     * are retrieved from Unspsc?cap_first and returns the navigation outcome.
     *
     * @return navigation outcome for Detallepaac page
     */
    public String navigateDetallepaacCollection() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Detallepaac_items", this.getSelected().getDetallepaacCollection());
        }
        return "/detallepaac/index";
    }

}

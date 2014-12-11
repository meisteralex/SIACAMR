package backing.bean;

import modelo.Oferente;
import session.beans.OferenteFacade;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.annotation.PostConstruct;

@ManagedBean(name = "oferenteController")
@ViewScoped
public class OferenteController extends AbstractController<Oferente> {

    @EJB
    private OferenteFacade ejbFacade;

    /**
     * Initialize the concrete Oferente controller bean. The AbstractController
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

    public OferenteController() {
        // Inform the Abstract parent controller of the concrete Oferente?cap_first Entity
        super(Oferente.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
    }

    /**
     * Sets the "items" attribute with a collection of Detalleficharetirobases
     * entities that are retrieved from Oferente?cap_first and returns the
     * navigation outcome.
     *
     * @return navigation outcome for Detalleficharetirobases page
     */
    public String navigateDetalleficharetirobasesCollection() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Detalleficharetirobases_items", this.getSelected().getDetalleficharetirobasesCollection());
        }
        return "/detalleficharetirobases/index";
    }

    /**
     * Sets the "items" attribute with a collection of Expedienteproceso
     * entities that are retrieved from Oferente?cap_first and returns the
     * navigation outcome.
     *
     * @return navigation outcome for Expedienteproceso page
     */
    public String navigateExpedienteprocesoCollection() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Expedienteproceso_items", this.getSelected().getExpedienteprocesoCollection());
        }
        return "/expedienteproceso/index";
    }

    /**
     * Sets the "items" attribute with a collection of Oferta entities that are
     * retrieved from Oferente?cap_first and returns the navigation outcome.
     *
     * @return navigation outcome for Oferta page
     */
    public String navigateOfertaCollection() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Oferta_items", this.getSelected().getOfertaCollection());
        }
        return "/oferta/index";
    }

}

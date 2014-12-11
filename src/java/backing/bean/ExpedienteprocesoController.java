package backing.bean;

import modelo.Expedienteproceso;
import session.beans.ExpedienteprocesoFacade;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.annotation.PostConstruct;

@ManagedBean(name = "expedienteprocesoController")
@ViewScoped
public class ExpedienteprocesoController extends AbstractController<Expedienteproceso> {

    @EJB
    private ExpedienteprocesoFacade ejbFacade;
    private OferenteController nombreadjudicatarioController;

    /**
     * Initialize the concrete Expedienteproceso controller bean. The
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
        nombreadjudicatarioController = context.getApplication().evaluateExpressionGet(context, "#{oferenteController}", OferenteController.class);
    }

    public ExpedienteprocesoController() {
        // Inform the Abstract parent controller of the concrete Expedienteproceso?cap_first Entity
        super(Expedienteproceso.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        nombreadjudicatarioController.setSelected(null);
    }

    /**
     * Sets the "items" attribute with a collection of Adenda entities that are
     * retrieved from Expedienteproceso?cap_first and returns the navigation
     * outcome.
     *
     * @return navigation outcome for Adenda page
     */
    public String navigateAdendaCollection() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Adenda_items", this.getSelected().getAdendaCollection());
        }
        return "/adenda/index";
    }

    /**
     * Sets the "items" attribute with a collection of Detalleficharetirobases
     * entities that are retrieved from Expedienteproceso?cap_first and returns
     * the navigation outcome.
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
     * Sets the "selected" attribute of the Oferente controller in order to
     * display its data in a dialog. This is reusing existing the existing View
     * dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareNombreadjudicatario(ActionEvent event) {
        if (this.getSelected() != null && nombreadjudicatarioController.getSelected() == null) {
            nombreadjudicatarioController.setSelected(this.getSelected().getNombreadjudicatario());
        }
    }

    /**
     * Sets the "items" attribute with a collection of Oferta entities that are
     * retrieved from Expedienteproceso?cap_first and returns the navigation
     * outcome.
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

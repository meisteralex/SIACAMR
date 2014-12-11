package backing.bean;

import modelo.Oferta;
import session.beans.OfertaFacade;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.annotation.PostConstruct;

@ManagedBean(name = "ofertaController")
@ViewScoped
public class OfertaController extends AbstractController<Oferta> {

    @EJB
    private OfertaFacade ejbFacade;
    private OferenteController oferenteController;
    private ExpedienteprocesoController expedienteprocesoController;

    /**
     * Initialize the concrete Oferta controller bean. The AbstractController
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
        oferenteController = context.getApplication().evaluateExpressionGet(context, "#{oferenteController}", OferenteController.class);
        expedienteprocesoController = context.getApplication().evaluateExpressionGet(context, "#{expedienteprocesoController}", ExpedienteprocesoController.class);
    }

    public OfertaController() {
        // Inform the Abstract parent controller of the concrete Oferta?cap_first Entity
        super(Oferta.class);
    }

    @Override
    protected void setEmbeddableKeys() {
        this.getSelected().getOfertaPK().setNombreoferente(this.getSelected().getOferente().getNombreoferente());
        this.getSelected().getOfertaPK().setCodigoproceso(this.getSelected().getExpedienteproceso().getCodigoproceso());
    }

    @Override
    protected void initializeEmbeddableKey() {
        this.getSelected().setOfertaPK(new modelo.OfertaPK());
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        oferenteController.setSelected(null);
        expedienteprocesoController.setSelected(null);
    }

    /**
     * Sets the "items" attribute with a collection of Detalleoferta entities
     * that are retrieved from Oferta?cap_first and returns the navigation
     * outcome.
     *
     * @return navigation outcome for Detalleoferta page
     */
    public String navigateDetalleofertaCollection() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Detalleoferta_items", this.getSelected().getDetalleofertaCollection());
        }
        return "/detalleoferta/index";
    }

    /**
     * Sets the "selected" attribute of the Oferente controller in order to
     * display its data in a dialog. This is reusing existing the existing View
     * dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareOferente(ActionEvent event) {
        if (this.getSelected() != null && oferenteController.getSelected() == null) {
            oferenteController.setSelected(this.getSelected().getOferente());
        }
    }

    /**
     * Sets the "selected" attribute of the Expedienteproceso controller in
     * order to display its data in a dialog. This is reusing existing the
     * existing View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareExpedienteproceso(ActionEvent event) {
        if (this.getSelected() != null && expedienteprocesoController.getSelected() == null) {
            expedienteprocesoController.setSelected(this.getSelected().getExpedienteproceso());
        }
    }
}

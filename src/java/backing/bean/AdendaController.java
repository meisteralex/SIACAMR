package backing.bean;

import modelo.Adenda;
import session.beans.AdendaFacade;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.annotation.PostConstruct;

@ManagedBean(name = "adendaController")
@ViewScoped
public class AdendaController extends AbstractController<Adenda> {

    @EJB
    private AdendaFacade ejbFacade;
    private ExpedienteprocesoController expedienteprocesoController;

    /**
     * Initialize the concrete Adenda controller bean. The AbstractController
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
        expedienteprocesoController = context.getApplication().evaluateExpressionGet(context, "#{expedienteprocesoController}", ExpedienteprocesoController.class);
    }

    public AdendaController() {
        // Inform the Abstract parent controller of the concrete Adenda?cap_first Entity
        super(Adenda.class);
    }

    @Override
    protected void setEmbeddableKeys() {
        this.getSelected().getAdendaPK().setCodigoproceso(this.getSelected().getExpedienteproceso().getCodigoproceso());
    }

    @Override
    protected void initializeEmbeddableKey() {
        this.getSelected().setAdendaPK(new modelo.AdendaPK());
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        expedienteprocesoController.setSelected(null);
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

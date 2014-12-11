package backing.bean;

import modelo.Detalleficharetirobases;
import session.beans.DetalleficharetirobasesFacade;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.annotation.PostConstruct;

@ManagedBean(name = "detalleficharetirobasesController")
@ViewScoped
public class DetalleficharetirobasesController extends AbstractController<Detalleficharetirobases> {

    @EJB
    private DetalleficharetirobasesFacade ejbFacade;
    private OferenteController nombreoferenteController;
    private ExpedienteprocesoController expedienteprocesoController;

    /**
     * Initialize the concrete Detalleficharetirobases controller bean. The
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
        nombreoferenteController = context.getApplication().evaluateExpressionGet(context, "#{oferenteController}", OferenteController.class);
        expedienteprocesoController = context.getApplication().evaluateExpressionGet(context, "#{expedienteprocesoController}", ExpedienteprocesoController.class);
    }

    public DetalleficharetirobasesController() {
        // Inform the Abstract parent controller of the concrete Detalleficharetirobases?cap_first Entity
        super(Detalleficharetirobases.class);
    }

    @Override
    protected void setEmbeddableKeys() {
        this.getSelected().getDetalleficharetirobasesPK().setCodigoproceso(this.getSelected().getExpedienteproceso().getCodigoproceso());
    }

    @Override
    protected void initializeEmbeddableKey() {
        this.getSelected().setDetalleficharetirobasesPK(new modelo.DetalleficharetirobasesPK());
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        nombreoferenteController.setSelected(null);
        expedienteprocesoController.setSelected(null);
    }

    /**
     * Sets the "selected" attribute of the Oferente controller in order to
     * display its data in a dialog. This is reusing existing the existing View
     * dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareNombreoferente(ActionEvent event) {
        if (this.getSelected() != null && nombreoferenteController.getSelected() == null) {
            nombreoferenteController.setSelected(this.getSelected().getNombreoferente());
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

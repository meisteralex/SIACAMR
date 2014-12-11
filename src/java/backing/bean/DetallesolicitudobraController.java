package backing.bean;

import modelo.Detallesolicitudobra;
import session.beans.DetallesolicitudobraFacade;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.annotation.PostConstruct;

@ManagedBean(name = "detallesolicitudobraController")
@ViewScoped
public class DetallesolicitudobraController extends AbstractController<Detallesolicitudobra> {

    @EJB
    private DetallesolicitudobraFacade ejbFacade;
    private UnspscController codigounspscController;
    private SolicitudobraController idSolicitudController;

    /**
     * Initialize the concrete Detallesolicitudobra controller bean. The
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
        codigounspscController = context.getApplication().evaluateExpressionGet(context, "#{unspscController}", UnspscController.class);
        idSolicitudController = context.getApplication().evaluateExpressionGet(context, "#{solicitudobraController}", SolicitudobraController.class);
    }

    public DetallesolicitudobraController() {
        // Inform the Abstract parent controller of the concrete Detallesolicitudobra?cap_first Entity
        super(Detallesolicitudobra.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        codigounspscController.setSelected(null);
        idSolicitudController.setSelected(null);
    }

    /**
     * Sets the "selected" attribute of the Unspsc controller in order to
     * display its data in a dialog. This is reusing existing the existing View
     * dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareCodigounspsc(ActionEvent event) {
        if (this.getSelected() != null && codigounspscController.getSelected() == null) {
            codigounspscController.setSelected(this.getSelected().getCodigounspsc());
        }
    }

    /**
     * Sets the "selected" attribute of the Solicitudobra controller in order to
     * display its data in a dialog. This is reusing existing the existing View
     * dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareIdSolicitud(ActionEvent event) {
        if (this.getSelected() != null && idSolicitudController.getSelected() == null) {
            idSolicitudController.setSelected(this.getSelected().getIdSolicitud());
        }
    }
}

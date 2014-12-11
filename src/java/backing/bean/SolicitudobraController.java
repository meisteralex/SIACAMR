package backing.bean;

import modelo.Solicitudobra;
import session.beans.SolicitudobraFacade;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.annotation.PostConstruct;

@ManagedBean(name = "solicitudobraController")
@ViewScoped
public class SolicitudobraController extends AbstractController<Solicitudobra> {

    @EJB
    private SolicitudobraFacade ejbFacade;
    private UnidadSolicitanteController idUsController;

    /**
     * Initialize the concrete Solicitudobra controller bean. The
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
        idUsController = context.getApplication().evaluateExpressionGet(context, "#{unidadSolicitanteController}", UnidadSolicitanteController.class);
    }

    public SolicitudobraController() {
        // Inform the Abstract parent controller of the concrete Solicitudobra?cap_first Entity
        super(Solicitudobra.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        idUsController.setSelected(null);
    }

    /**
     * Sets the "items" attribute with a collection of Detallesolicitudobra
     * entities that are retrieved from Solicitudobra?cap_first and returns the
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
     * Sets the "selected" attribute of the UnidadSolicitante controller in
     * order to display its data in a dialog. This is reusing existing the
     * existing View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareIdUs(ActionEvent event) {
        if (this.getSelected() != null && idUsController.getSelected() == null) {
            idUsController.setSelected(this.getSelected().getIdUs());
        }
    }
}

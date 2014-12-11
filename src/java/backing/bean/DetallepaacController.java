package backing.bean;

import modelo.Detallepaac;
import session.beans.DetallepaacFacade;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.annotation.PostConstruct;

@ManagedBean(name = "detallepaacController")
@ViewScoped
public class DetallepaacController extends AbstractController<Detallepaac> {

    @EJB
    private DetallepaacFacade ejbFacade;
    private UnspscController codigounspscController;
    private PaacController paacController;

    /**
     * Initialize the concrete Detallepaac controller bean. The
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
        paacController = context.getApplication().evaluateExpressionGet(context, "#{paacController}", PaacController.class);
    }

    public DetallepaacController() {
        // Inform the Abstract parent controller of the concrete Detallepaac?cap_first Entity
        super(Detallepaac.class);
    }

    @Override
    protected void setEmbeddableKeys() {
        this.getSelected().getDetallepaacPK().setEjerciciofiscal(this.getSelected().getPaac().getEjerciciofiscal());
    }

    @Override
    protected void initializeEmbeddableKey() {
        this.getSelected().setDetallepaacPK(new modelo.DetallepaacPK());
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        codigounspscController.setSelected(null);
        paacController.setSelected(null);
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
     * Sets the "selected" attribute of the Paac controller in order to display
     * its data in a dialog. This is reusing existing the existing View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void preparePaac(ActionEvent event) {
        if (this.getSelected() != null && paacController.getSelected() == null) {
            paacController.setSelected(this.getSelected().getPaac());
        }
    }
}

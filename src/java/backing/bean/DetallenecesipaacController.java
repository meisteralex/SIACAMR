package backing.bean;

import modelo.Detallenecesipaac;
import session.beans.DetallenecesipaacFacade;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.annotation.PostConstruct;

@ManagedBean(name = "detallenecesipaacController")
@ViewScoped
public class DetallenecesipaacController extends AbstractController<Detallenecesipaac> {

    @EJB
    private DetallenecesipaacFacade ejbFacade;
    private UnspscController codigounspscController;
    private ListadonecesipaacController listadonecesipaacController;

    /**
     * Initialize the concrete Detallenecesipaac controller bean. The
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
        listadonecesipaacController = context.getApplication().evaluateExpressionGet(context, "#{listadonecesipaacController}", ListadonecesipaacController.class);
    }

    public DetallenecesipaacController() {
        // Inform the Abstract parent controller of the concrete Detallenecesipaac?cap_first Entity
        super(Detallenecesipaac.class);
    }

    @Override
    protected void setEmbeddableKeys() {
        this.getSelected().getDetallenecesipaacPK().setEjerciciofiscal(this.getSelected().getListadonecesipaac().getEjerciciofiscal());
    }

    @Override
    protected void initializeEmbeddableKey() {
        this.getSelected().setDetallenecesipaacPK(new modelo.DetallenecesipaacPK());
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        codigounspscController.setSelected(null);
        listadonecesipaacController.setSelected(null);
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
     * Sets the "selected" attribute of the Listadonecesipaac controller in
     * order to display its data in a dialog. This is reusing existing the
     * existing View dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareListadonecesipaac(ActionEvent event) {
        if (this.getSelected() != null && listadonecesipaacController.getSelected() == null) {
            listadonecesipaacController.setSelected(this.getSelected().getListadonecesipaac());
        }
    }
}

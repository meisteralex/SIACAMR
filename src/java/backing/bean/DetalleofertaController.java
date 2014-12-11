package backing.bean;

import modelo.Detalleoferta;
import session.beans.DetalleofertaFacade;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.annotation.PostConstruct;

@ManagedBean(name = "detalleofertaController")
@ViewScoped
public class DetalleofertaController extends AbstractController<Detalleoferta> {

    @EJB
    private DetalleofertaFacade ejbFacade;
    private OfertaController ofertaController;

    /**
     * Initialize the concrete Detalleoferta controller bean. The
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
        ofertaController = context.getApplication().evaluateExpressionGet(context, "#{ofertaController}", OfertaController.class);
    }

    public DetalleofertaController() {
        // Inform the Abstract parent controller of the concrete Detalleoferta?cap_first Entity
        super(Detalleoferta.class);
    }

    @Override
    protected void setEmbeddableKeys() {
        this.getSelected().getDetalleofertaPK().setNombreoferente(this.getSelected().getOferta().getOfertaPK().getNombreoferente());
        this.getSelected().getDetalleofertaPK().setCodigoproceso(this.getSelected().getOferta().getOfertaPK().getCodigoproceso());
    }

    @Override
    protected void initializeEmbeddableKey() {
        this.getSelected().setDetalleofertaPK(new modelo.DetalleofertaPK());
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        ofertaController.setSelected(null);
    }

    /**
     * Sets the "selected" attribute of the Oferta controller in order to
     * display its data in a dialog. This is reusing existing the existing View
     * dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareOferta(ActionEvent event) {
        if (this.getSelected() != null && ofertaController.getSelected() == null) {
            ofertaController.setSelected(this.getSelected().getOferta());
        }
    }
}

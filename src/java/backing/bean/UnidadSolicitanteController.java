package backing.bean;

import modelo.UnidadSolicitante;
import session.beans.UnidadSolicitanteFacade;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.annotation.PostConstruct;

@ManagedBean(name = "unidadSolicitanteController")
@ViewScoped
public class UnidadSolicitanteController extends AbstractController<UnidadSolicitante> {

    @EJB
    private UnidadSolicitanteFacade ejbFacade;

    /**
     * Initialize the concrete UnidadSolicitante controller bean. The
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
    }

    public UnidadSolicitanteController() {
        // Inform the Abstract parent controller of the concrete UnidadSolicitante?cap_first Entity
        super(UnidadSolicitante.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
    }

    /**
     * Sets the "items" attribute with a collection of Solicitudobra entities
     * that are retrieved from UnidadSolicitante?cap_first and returns the
     * navigation outcome.
     *
     * @return navigation outcome for Solicitudobra page
     */
    public String navigateSolicitudobraCollection() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Solicitudobra_items", this.getSelected().getSolicitudobraCollection());
        }
        return "/solicitudobra/index";
    }

}

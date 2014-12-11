package converter;

import modelo.Oferta;
import session.beans.OfertaFacade;
import backing.bean.util.JsfUtil;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(value = "ofertaConverter")
public class OfertaConverter implements Converter {

    @EJB
    private OfertaFacade ejbFacade;

    private static final String SEPARATOR = "#";
    private static final String SEPARATOR_ESCAPED = "\\#";

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
        if (value == null || value.length() == 0 || JsfUtil.isDummySelectItem(component, value)) {
            return null;
        }
        return this.ejbFacade.find(getKey(value));
    }

    modelo.OfertaPK getKey(String value) {
        modelo.OfertaPK key;
        String values[] = value.split(SEPARATOR_ESCAPED);
        key = new modelo.OfertaPK();
        key.setNombreoferente(values[0]);
        key.setCodigoproceso(values[1]);
        return key;
    }

    String getStringKey(modelo.OfertaPK value) {
        StringBuffer sb = new StringBuffer();
        sb.append(value.getNombreoferente());
        sb.append(SEPARATOR);
        sb.append(value.getCodigoproceso());
        return sb.toString();
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
        if (object == null
                || (object instanceof String && ((String) object).length() == 0)) {
            return null;
        }
        if (object instanceof Oferta) {
            Oferta o = (Oferta) object;
            return getStringKey(o.getOfertaPK());
        } else {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Oferta.class.getName()});
            return null;
        }
    }

}

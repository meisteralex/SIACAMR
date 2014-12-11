package converter;

import modelo.Adenda;
import session.beans.AdendaFacade;
import backing.bean.util.JsfUtil;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(value = "adendaConverter")
public class AdendaConverter implements Converter {

    @EJB
    private AdendaFacade ejbFacade;

    private static final String SEPARATOR = "#";
    private static final String SEPARATOR_ESCAPED = "\\#";

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
        if (value == null || value.length() == 0 || JsfUtil.isDummySelectItem(component, value)) {
            return null;
        }
        return this.ejbFacade.find(getKey(value));
    }

    modelo.AdendaPK getKey(String value) {
        modelo.AdendaPK key;
        String values[] = value.split(SEPARATOR_ESCAPED);
        key = new modelo.AdendaPK();
        key.setCodigoproceso(values[0]);
        key.setNocorrelativo(Integer.parseInt(values[1]));
        return key;
    }

    String getStringKey(modelo.AdendaPK value) {
        StringBuffer sb = new StringBuffer();
        sb.append(value.getCodigoproceso());
        sb.append(SEPARATOR);
        sb.append(value.getNocorrelativo());
        return sb.toString();
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
        if (object == null
                || (object instanceof String && ((String) object).length() == 0)) {
            return null;
        }
        if (object instanceof Adenda) {
            Adenda o = (Adenda) object;
            return getStringKey(o.getAdendaPK());
        } else {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Adenda.class.getName()});
            return null;
        }
    }

}

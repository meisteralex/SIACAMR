package converter;

import modelo.Detallenecesipaac;
import session.beans.DetallenecesipaacFacade;
import backing.bean.util.JsfUtil;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(value = "detallenecesipaacConverter")
public class DetallenecesipaacConverter implements Converter {

    @EJB
    private DetallenecesipaacFacade ejbFacade;

    private static final String SEPARATOR = "#";
    private static final String SEPARATOR_ESCAPED = "\\#";

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
        if (value == null || value.length() == 0 || JsfUtil.isDummySelectItem(component, value)) {
            return null;
        }
        return this.ejbFacade.find(getKey(value));
    }

    modelo.DetallenecesipaacPK getKey(String value) {
        modelo.DetallenecesipaacPK key;
        String values[] = value.split(SEPARATOR_ESCAPED);
        key = new modelo.DetallenecesipaacPK();
        key.setEjerciciofiscal(values[0]);
        key.setNocorrelativo(Integer.parseInt(values[1]));
        return key;
    }

    String getStringKey(modelo.DetallenecesipaacPK value) {
        StringBuffer sb = new StringBuffer();
        sb.append(value.getEjerciciofiscal());
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
        if (object instanceof Detallenecesipaac) {
            Detallenecesipaac o = (Detallenecesipaac) object;
            return getStringKey(o.getDetallenecesipaacPK());
        } else {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Detallenecesipaac.class.getName()});
            return null;
        }
    }

}

package converter;

import modelo.Detallepaac;
import session.beans.DetallepaacFacade;
import backing.bean.util.JsfUtil;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(value = "detallepaacConverter")
public class DetallepaacConverter implements Converter {

    @EJB
    private DetallepaacFacade ejbFacade;

    private static final String SEPARATOR = "#";
    private static final String SEPARATOR_ESCAPED = "\\#";

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
        if (value == null || value.length() == 0 || JsfUtil.isDummySelectItem(component, value)) {
            return null;
        }
        return this.ejbFacade.find(getKey(value));
    }

    modelo.DetallepaacPK getKey(String value) {
        modelo.DetallepaacPK key;
        String values[] = value.split(SEPARATOR_ESCAPED);
        key = new modelo.DetallepaacPK();
        key.setEjerciciofiscal(values[0]);
        key.setNocorrelativo(Integer.parseInt(values[1]));
        return key;
    }

    String getStringKey(modelo.DetallepaacPK value) {
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
        if (object instanceof Detallepaac) {
            Detallepaac o = (Detallepaac) object;
            return getStringKey(o.getDetallepaacPK());
        } else {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Detallepaac.class.getName()});
            return null;
        }
    }

}

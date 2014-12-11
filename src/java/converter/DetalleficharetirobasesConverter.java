package converter;

import modelo.Detalleficharetirobases;
import session.beans.DetalleficharetirobasesFacade;
import backing.bean.util.JsfUtil;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(value = "detalleficharetirobasesConverter")
public class DetalleficharetirobasesConverter implements Converter {

    @EJB
    private DetalleficharetirobasesFacade ejbFacade;

    private static final String SEPARATOR = "#";
    private static final String SEPARATOR_ESCAPED = "\\#";

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
        if (value == null || value.length() == 0 || JsfUtil.isDummySelectItem(component, value)) {
            return null;
        }
        return this.ejbFacade.find(getKey(value));
    }

    modelo.DetalleficharetirobasesPK getKey(String value) {
        modelo.DetalleficharetirobasesPK key;
        String values[] = value.split(SEPARATOR_ESCAPED);
        key = new modelo.DetalleficharetirobasesPK();
        key.setNocorrelativo(Integer.parseInt(values[0]));
        key.setCodigoproceso(values[1]);
        return key;
    }

    String getStringKey(modelo.DetalleficharetirobasesPK value) {
        StringBuffer sb = new StringBuffer();
        sb.append(value.getNocorrelativo());
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
        if (object instanceof Detalleficharetirobases) {
            Detalleficharetirobases o = (Detalleficharetirobases) object;
            return getStringKey(o.getDetalleficharetirobasesPK());
        } else {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Detalleficharetirobases.class.getName()});
            return null;
        }
    }

}

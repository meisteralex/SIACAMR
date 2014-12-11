package converter;

import modelo.Detalleoferta;
import session.beans.DetalleofertaFacade;
import backing.bean.util.JsfUtil;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(value = "detalleofertaConverter")
public class DetalleofertaConverter implements Converter {

    @EJB
    private DetalleofertaFacade ejbFacade;

    private static final String SEPARATOR = "#";
    private static final String SEPARATOR_ESCAPED = "\\#";

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
        if (value == null || value.length() == 0 || JsfUtil.isDummySelectItem(component, value)) {
            return null;
        }
        return this.ejbFacade.find(getKey(value));
    }

    modelo.DetalleofertaPK getKey(String value) {
        modelo.DetalleofertaPK key;
        String values[] = value.split(SEPARATOR_ESCAPED);
        key = new modelo.DetalleofertaPK();
        key.setNombreoferente(values[0]);
        key.setCodigoproceso(values[1]);
        key.setRubro(values[2]);
        return key;
    }

    String getStringKey(modelo.DetalleofertaPK value) {
        StringBuffer sb = new StringBuffer();
        sb.append(value.getNombreoferente());
        sb.append(SEPARATOR);
        sb.append(value.getCodigoproceso());
        sb.append(SEPARATOR);
        sb.append(value.getRubro());
        return sb.toString();
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
        if (object == null
                || (object instanceof String && ((String) object).length() == 0)) {
            return null;
        }
        if (object instanceof Detalleoferta) {
            Detalleoferta o = (Detalleoferta) object;
            return getStringKey(o.getDetalleofertaPK());
        } else {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Detalleoferta.class.getName()});
            return null;
        }
    }

}

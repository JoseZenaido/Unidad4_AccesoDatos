package mx.edu.utng.wscarta;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;

import java.util.Hashtable;

/**
 * Created by ANONYMOUS-PC on 20/04/2017.
 */

public class Carta implements KvmSerializable {
    private int id;
    private int folio;
    private int periodo;
    private int pagado;

    private String autorizo;
    private String fechaPago;
    private String fechaSolicitud;
    private String personaAutorizo;

    public Carta(int id, int folio, int periodo, int pagado, String autorizo, String fechaPago, String fechaSolicitud, String personaAutorizo) {
        this.id = id;
        this.folio = folio;
        this.periodo = periodo;
        this.pagado = pagado;
        this.autorizo = autorizo;
        this.fechaPago = fechaPago;
        this.fechaSolicitud = fechaSolicitud;
        this.personaAutorizo = personaAutorizo;
    }

    public Carta() {
        this(0,0,0,0,"","","","");
    }


    @Override
    public Object getProperty(int i) {
        switch (i){
            case 0:
                return  id;
            case 1:
                return  folio;
            case 2:
                return  periodo;
            case 3:
                return  pagado;
            case 4:
                return  autorizo;
            case 5:
                return  fechaPago;
            case 6:
                return  fechaSolicitud;
            case 7:
                return  personaAutorizo;

        }
        return null;
    }

    @Override
    public int getPropertyCount() {
        return 8;
    }

    @Override
    public void setProperty(int i, Object o) {
        switch (i){
            case 0:
                id=Integer.parseInt(o.toString());
                break;
            case 1:
                folio=Integer.parseInt(o.toString());
                break;
            case 2:
                periodo=Integer.parseInt(o.toString());
                break;
            case 3:
                pagado=Integer.parseInt(o.toString());
                break;
            case 4:
                autorizo=o.toString();
                break;
            case 5:
                fechaPago=o.toString();
                break;
            case 6:
                fechaSolicitud=o.toString();
                break;
            case 7:
                personaAutorizo=o.toString();
                break;
            default:
                break;
        }

    }

    @Override
    public void getPropertyInfo(int i, Hashtable hashtable, PropertyInfo propertyInfo) {
        switch (i){
            case 0:
                propertyInfo.type=PropertyInfo.INTEGER_CLASS;
                propertyInfo.name="id";
                break;
            case 1:
                propertyInfo.type=PropertyInfo.INTEGER_CLASS;
                propertyInfo.name="folio";
                break;
            case 2:
                propertyInfo.type=PropertyInfo.INTEGER_CLASS;
                propertyInfo.name="periodo";
                break;

            case 3:
                propertyInfo.type=PropertyInfo.INTEGER_CLASS;
                propertyInfo.name="pagado";
                break;
            case 4:
                propertyInfo.type=PropertyInfo.STRING_CLASS;
                propertyInfo.name="autorizo";
                break;
            case 5:
                propertyInfo.type=PropertyInfo.STRING_CLASS;
                propertyInfo.name="fechaPago";
                break;
            case 6:
                propertyInfo.type=PropertyInfo.STRING_CLASS;
                propertyInfo.name="fechaSolicitud";
                break;
            case 7:
                propertyInfo.type=PropertyInfo.STRING_CLASS;
                propertyInfo.name="personaAutorizo";
                break;

            default:
                break;
        }

    }
}

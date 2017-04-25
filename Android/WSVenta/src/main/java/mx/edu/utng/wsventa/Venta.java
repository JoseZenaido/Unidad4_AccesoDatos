package mx.edu.utng.wsventa;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;

import java.util.Hashtable;

/**
 * Created by ANONYMOUS-PC on 20/04/2017.
 */

public class Venta implements KvmSerializable {
    private int id;
    private int total;


    public Venta(int id, int total) {
        this.id = id;
        this.total = total;
    }

    public Venta() {
        this(0,0);
    }


    @Override
    public Object getProperty(int i) {
        switch (i){
            case 0:
                return  id;
            case 1:
                return  total;

        }
        return null;
    }

    @Override
    public int getPropertyCount() {
        return 2;
    }

    @Override
    public void setProperty(int i, Object o) {
        switch (i){
            case 0:
                id=Integer.parseInt(o.toString());
                break;
            case 1:
                total=Integer.parseInt(o.toString());
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
                propertyInfo.name="total";
                break;

            default:
                break;
        }

    }
}

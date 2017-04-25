package mx.edu.utng.wscarta;

import android.app.ListActivity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.SoapFault;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpResponseException;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;

/**
 * Created by ANONYMOUS-PC on 20/04/2017.
 */

public class ListaCartas extends ListActivity{

    final String NAMESPACE = "http://ws.utng.edu.mx";

    final SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);

    private ArrayList<Carta> cartas = new ArrayList<Carta>();
    private int idSelected;
    private int selectedPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SelectCarta select=new SelectCarta();
        select.execute();
        registerForContextMenu(getListView());

    }//end OnCreate


    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item_modificar:

                Carta carta = cartas.get(selectedPosition);
                Bundle bundleCarta = new Bundle();
                for (int i = 0; i < carta.getPropertyCount(); i++) {
                    bundleCarta.putString("valor" + i, carta.getProperty(i).toString());
                }
                bundleCarta.putString("accion", "modificar");
                Intent intent = new Intent(ListaCartas.this, MainActivity.class);
                intent.putExtras(bundleCarta);
                startActivity(intent);

                return true;
            case R.id.item_eliminar:
                DeleteCarta eliminar = new DeleteCarta();
                eliminar.execute();

                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }//End  OnContext

    @Override
    public boolean onMenuItemSelected(int featureId, MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item_regresar:
                startActivity(new Intent(ListaCartas.this, MainActivity.class));
                break;
            default:
                break;
        }
        return super.onMenuItemSelected(featureId, item);
    }//End onMenuItem


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();

        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;

        menu.setHeaderTitle(getListView().getAdapter().getItem(info.position).toString());
        idSelected = (Integer) cartas.get(info.position).getProperty(0);
        selectedPosition = info.position;

        inflater.inflate(R.menu.menu_contextual, menu);

    }//


    ///Clase estatica

    private class SelectCarta extends AsyncTask<String, Integer, Boolean> {

        protected Boolean doInBackground(String... params) {

            boolean result = true;

            final String METHOD_NAME = "getCartas";
            final String SOAP_ACTION = NAMESPACE + "/" + METHOD_NAME;

            cartas.clear();
            SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
            envelope.setOutputSoapObject(request);
            HttpTransportSE transporte = new HttpTransportSE(MainActivity.URL);

            try {
                transporte.call(SOAP_ACTION, envelope);
                Vector<SoapObject> response = (Vector<SoapObject>) envelope.getResponse();
                if (response != null) {

                    for (SoapObject objSoap : response) {
                        Carta carta = new Carta();
                        ///Estos se ponene como el modelo de android

                        carta.setProperty(0, Integer.parseInt(objSoap.getProperty("id").toString()));
                        carta.setProperty(1, Integer.parseInt(objSoap.getProperty("folio").toString()));
                        carta.setProperty(2, Integer.parseInt(objSoap.getProperty("periodo").toString()));
                        carta.setProperty(3, Integer.parseInt(objSoap.getProperty("pagado").toString()));

                        carta.setProperty(4, objSoap.getProperty("autorizo").toString());
                        carta.setProperty(5, objSoap.getProperty("fechaPago").toString());
                        carta.setProperty(6, objSoap.getProperty("fechaSolicitud").toString());
                        carta.setProperty(7, objSoap.getProperty("personaAutorizo").toString());

                        cartas.add(carta);
                    }
                }

            } catch (XmlPullParserException e) {
                Log.e("Error XMLPullParser", e.toString());
                result = false;
            } catch (HttpResponseException e) {
                Log.e("Error HTTP", e.toString());
                result = false;
            } catch (IOException e) {
                Log.e("Error IO", e.toString());
                result = false;
            } catch (ClassCastException e) {

                //Enviará aquí cuando exista un solo registro en la base.
                try {
                    SoapObject objSoap = (SoapObject) envelope.getResponse();
                    Carta carta = new Carta();

                    carta.setProperty(0, Integer.parseInt(objSoap.getProperty("id").toString()));
                    carta.setProperty(1, Integer.parseInt(objSoap.getProperty("folio").toString()));
                    carta.setProperty(2, Integer.parseInt(objSoap.getProperty("periodo").toString()));
                    carta.setProperty(3, Integer.parseInt(objSoap.getProperty("pagado").toString()));

                    carta.setProperty(4, objSoap.getProperty("autorizo").toString());
                    carta.setProperty(5, objSoap.getProperty("fechaPago").toString());
                    carta.setProperty(6, objSoap.getProperty("fechaSolicitud").toString());
                    carta.setProperty(7, objSoap.getProperty("personaAutorizo").toString());


                    cartas.add(carta);
                } catch (SoapFault e1) {
                    Log.e("Error SoapFault", e.toString());
                    result = false;
                }
            }

            return result;
        }

        @Override
        protected void onPostExecute(Boolean result) {
            if (result) {
                final String[] datos = new String[cartas.size()];
                for (int i = 0; i < cartas.size(); i++) {
                    datos[i] = cartas.get(i).getProperty(0) + " - "
                            + cartas.get(i).getProperty(1) + " - "
                            + cartas.get(i).getProperty(2) + " - "
                            + cartas.get(i).getProperty(3) + " - "
                            + cartas.get(i).getProperty(4) + " - "
                            + cartas.get(i).getProperty(5) + " - "
                            + cartas.get(i).getProperty(6) + " - "
                            + cartas.get(i).getProperty(7);
                }
//////////////////////////////////este layout
                ArrayAdapter<String> adaptador = new ArrayAdapter<String>(ListaCartas.this, android.R.layout.simple_list_item_1, datos);
                setListAdapter(adaptador);
            } else {
                Toast.makeText(getApplicationContext(), "No se encontraron datos.",
                        Toast.LENGTH_SHORT).show();
            }
        }
    }//






    ///Clase estatica For Delete

    private class DeleteCarta extends AsyncTask<String, Integer, Boolean> {

        @Override
        protected Boolean doInBackground(String... params) {
            boolean result = true;

            final String METHOD_NAME = "removeCarta";
            final String SOAP_ACTION = NAMESPACE + "/" + METHOD_NAME;

            SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
            request.addProperty("id", idSelected);

            envelope.setOutputSoapObject(request);
            HttpTransportSE transporte = new HttpTransportSE(MainActivity.URL);
            try {
                transporte.call(SOAP_ACTION, envelope);
                SoapPrimitive resultado_xml = (SoapPrimitive) envelope
                        .getResponse();
                String res = resultado_xml.toString();

                if (!res.equals("0")){
                    result = true;}

            } catch (Exception e) {
                Log.e("Error", e.toString());
                result = false;
            }
            return result;
        }

        @Override
        protected void onPostExecute(Boolean result) {
            if (result) {
                Toast.makeText(getApplicationContext(),
                        "Eliminado", Toast.LENGTH_SHORT).show();
                SelectCarta consulta = new SelectCarta();
                consulta.execute();
            } else {
                Toast.makeText(getApplicationContext(), "Error al eliminar",
                        Toast.LENGTH_SHORT).show();

            }
        }
    }
}//End class

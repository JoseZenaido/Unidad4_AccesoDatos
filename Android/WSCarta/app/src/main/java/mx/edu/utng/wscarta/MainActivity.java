package mx.edu.utng.wscarta;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.MarshalFloat;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpResponseException;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText etFolio;
    private EditText etPeriodo;
    private EditText etPagado;
    private EditText etAutorizo;
    private EditText etFechaPago;
    private EditText etFechaSolicitud;
    private EditText etPersonaAutorizo;


    private Button btnSave;
    private Button btnList;

    private Carta carta = null;

    final String NAMESPACE ="http://ws.utng.edu.mx";
    final SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
    //static String URL ="http://192.168.1.75:8080/WebService/services/MovieWS";
    static String URL ="http://192.168.24.14:8080/WebService/services/CartaCompromisoWS";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initComponents();
    }


    private void initComponents(){
        etFolio = (EditText)findViewById(R.id.tv_folio);
        etPeriodo = (EditText)findViewById(R.id.tv_periodo);
        etPagado = (EditText)findViewById(R.id.tv_pagado);
        etAutorizo = (EditText)findViewById(R.id.tv_autorizo);
        etFechaPago = (EditText)findViewById(R.id.tv_fecha_pago);
        etFechaSolicitud = (EditText)findViewById(R.id.tv_fecha_solicitud);
        etPersonaAutorizo = (EditText)findViewById(R.id.tv_persona_autorizo);


        btnSave = (Button) findViewById(R.id.btn_save);
        btnList = (Button)findViewById(R.id.btn_list);
        btnSave.setOnClickListener(this);
        btnList.setOnClickListener(this);
    }

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_consume_w, menu);
        return true;
    }*/
/*
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();
        if(id==R.id.action_settings){
            return  true;
        }
        return super.onOptionsItemSelected(item);
    }*/

    @Override
    public void onClick(View v) {
        if(v.getId()== btnSave.getId()){
            try {
                if (getIntent().getExtras().getString("accion").equals("modificar")) {
                    updateCarta tarea = new updateCarta();
                    tarea.execute();
                }

            } catch (Exception e) {
                //Cuando no se haya mandado una accion por defecto es insertar.
                InsertCarta tarea = new InsertCarta();
                tarea.execute();
            }
        }
        if (btnList.getId() == v.getId()) {
            startActivity(new Intent(MainActivity.this, ListaCartas.class));
        }
    }

    private class InsertCarta extends
            AsyncTask<String, Integer, Boolean> {

        @Override
        protected Boolean doInBackground(String... params) {
            boolean result = true;
            final String METHOD_NAME = "addCarta";
            final String SOAP_ACTION = NAMESPACE + "/" + METHOD_NAME;

            SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);

            carta = new Carta();
            carta.setProperty(0, 0);

            getData();

            PropertyInfo info = new PropertyInfo();
            info.setName("Carta");
            info.setValue(carta);
            info.setType(carta.getClass());
            request.addProperty(info);
            envelope.setOutputSoapObject(request);
            envelope.addMapping(NAMESPACE, "Carta", Carta.class);

            HttpTransportSE transporte = new HttpTransportSE(URL);
            try {
                transporte.call(SOAP_ACTION, envelope);
                SoapPrimitive response =      (SoapPrimitive) envelope.getResponse();
                String res = response.toString();
                if (!res.equals("1")) {
                    result = false;
                }

            } catch (Exception e) {
                Log.e("Error ", e.getMessage());
                result = false;
            }
            return result;
        }


        @Override
        protected void onPostExecute(Boolean result) {
            if(result){
                Toast.makeText(getApplicationContext(),
                        "Registro exitoso.",
                        Toast.LENGTH_SHORT).show();
                cleanBox();

            }else {
                Toast.makeText(getApplicationContext(),
                        "Error al insertar.",
                        Toast.LENGTH_SHORT).show();

            }
        }
    }//
    private void cleanBox(){
        etFolio.setText("");
        etPeriodo.setText("");
        etPagado.setText("");
        etAutorizo.setText("");
        etFechaPago.setText("");
        etFechaSolicitud.setText("");
        etPersonaAutorizo.setText("");

    }
    private class updateCarta extends
            AsyncTask<String, Integer, Boolean> {

        @Override
        protected Boolean doInBackground(String... params) {
            boolean result = true;

            final String METHOD_NAME = "updateCarta";
            final String SOAP_ACTION = NAMESPACE + "/" + METHOD_NAME;

            SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);

            carta = new Carta();
            carta.setProperty(0, getIntent().getExtras().getString("valor0"));
            getData();

            PropertyInfo info = new PropertyInfo();
            info.setName("carta");
            info.setValue(carta);
            info.setType(carta.getClass());

            request.addProperty(info);
            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.setOutputSoapObject(request);
            envelope.addMapping(NAMESPACE, "carta", carta.getClass());


            HttpTransportSE transporte = new HttpTransportSE(URL);

            try {
                transporte.call(SOAP_ACTION,envelope);
                SoapPrimitive resultado_xml = (SoapPrimitive) envelope.getResponse();
                String res = resultado_xml.toString();

                if (!res.equals("1")) {
                    result = false;
                }

            } catch (HttpResponseException e) {
                Log.e("Error HTTP", e.toString());
            } catch (IOException e) {
                Log.e("Error IO", e.toString());
            } catch (XmlPullParserException e) {
                Log.e("Error XmlPullParser", e.toString());
            }
            return result;
        }

        @Override
        protected void onPostExecute(Boolean result) {
            if (result) {
                Toast.makeText(getApplicationContext(), "Actualizado OK",
                        Toast.LENGTH_SHORT).show();
                cleanBox();
                startActivity(new Intent(MainActivity.this, MainActivity.class));

            } else {
                Toast.makeText(getApplicationContext(), "Error al actualizar",
                        Toast.LENGTH_SHORT).show();

            }
        }
    }//
    private void getData() {
        carta.setProperty(1, Integer.parseInt(etFolio.getText().toString()));
        carta.setProperty(2, Integer.parseInt(etPeriodo.getText().toString()));
        carta.setProperty(3, Integer.parseInt(etPagado.getText().toString()));

        carta.setProperty(4, etAutorizo.getText().toString());
        carta.setProperty(5, etFechaPago.getText().toString());
        carta.setProperty(6, etFechaSolicitud.getText().toString());
        carta.setProperty(7, etPersonaAutorizo.getText().toString());
    }//

    @Override
    protected void onResume() {
        super.onResume();
        Bundle datosRegreso = this.getIntent().getExtras();
        try {
            //Log.i("Dato", datosRegreso.getString("valor4"));

            etFolio.setText(datosRegreso.getString("valor1"));
            etPeriodo.setText(datosRegreso.getString("valor2"));
            etPagado.setText(datosRegreso.getString("valor3"));
            etAutorizo.setText(datosRegreso.getString("valor4"));
            etFechaPago.setText(datosRegreso.getString("valor5"));
            etFechaSolicitud.setText(datosRegreso.getString("valor6"));
            etPersonaAutorizo.setText(datosRegreso.getString("valor7"));

        } catch (Exception e) {
            Log.e("Error al Recargar", e.toString());
        }
    }
}

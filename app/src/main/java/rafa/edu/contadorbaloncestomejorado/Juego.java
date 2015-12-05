package rafa.edu.contadorbaloncestomejorado;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.sql.Array;
import java.util.ArrayList;

/**
 * Created by zaladriel on 1/12/15.
 */
public class Juego extends AppCompatActivity implements View.OnClickListener {
    TextView marcadorLocal,marcadorVisitante,nombreLocal,nombreVisitante;
    Button finalizar,estadisticas;
    Button sumarL,sumarV,restarL,restarV;
    Button[] local= new Button[5];
    Button[] visitante= new Button[5];
    ToggleButton[] togglelocal=new ToggleButton[5];
    ToggleButton[] toggleVisitante=new ToggleButton[5];
    ToggleButton puntos;
    RadioButton uno,dos,tres;
    Bundle bundle;
    String[] listaLocal;
    String[] listaVisitante;
    boolean intercambio = false;
    String sustitucion;
    ToggleButton dorsal;
    String[] dorsalesLocal = new String[10];
    String[] dorsalesVisitante = new String[10];
    String[] statLocal = new String[10];
    String[] statVisitante = new String[10];

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.stat:
                Intent stats = new Intent(Juego.this,Estadisticas.class);
                Bundle bundle = new Bundle();
                bundle.putString("nombrelocal", nombreLocal.getText().toString());
                bundle.putString("nombrevisitante", nombreVisitante.getText().toString());
                bundle.putString("marcadorlocal", marcadorLocal.getText().toString());
                bundle.putString("marcadorvisitante", marcadorVisitante.getText().toString());
                bundle.putStringArray("dorsaleslocal", dorsalesLocal);
                bundle.putStringArray("dorsalesVisitante", dorsalesVisitante);
                bundle.putStringArray("statLocal", statLocal);
                bundle.putStringArray("statVisitante", statVisitante);
                stats.putExtras(bundle);
                startActivity(stats);
                break;

        }
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.juego);
        //recogemos los datos
        bundle = this.getIntent().getExtras();
        listaLocal = bundle.getStringArray("local");
        listaVisitante = bundle.getStringArray("visitante");

        //asociación variable elemento xml
        nombreLocal = (TextView) findViewById(R.id.nombrelocal);
        nombreVisitante = (TextView) findViewById(R.id.nombrevisitante);
        marcadorLocal = (TextView) findViewById(R.id.marcadorLocal);
        marcadorVisitante = (TextView) findViewById(R.id.marcadorvisitante);
        local[0] = (Button) findViewById(R.id.lt1);
        local[1] = (Button) findViewById(R.id.lt2);
        local[2] = (Button) findViewById(R.id.lt3);
        local[3] = (Button) findViewById(R.id.lt4);
        local[4] = (Button) findViewById(R.id.lt5);
        togglelocal[0] = (ToggleButton) findViewById(R.id.lb1);
        togglelocal[1] = (ToggleButton) findViewById(R.id.lb2);
        togglelocal[2] = (ToggleButton) findViewById(R.id.lb3);
        togglelocal[3] = (ToggleButton) findViewById(R.id.lb4);
        togglelocal[4] = (ToggleButton) findViewById(R.id.lb5);
        visitante[0] = (Button) findViewById(R.id.vt1);
        visitante[1] = (Button) findViewById(R.id.vt2);
        visitante[2] = (Button) findViewById(R.id.vt3);
        visitante[3] = (Button) findViewById(R.id.vt4);
        visitante[4] = (Button) findViewById(R.id.vt5);
        toggleVisitante[0] = (ToggleButton) findViewById(R.id.vb1);
        toggleVisitante[1] = (ToggleButton) findViewById(R.id.vb2);
        toggleVisitante[2] = (ToggleButton) findViewById(R.id.vb3);
        toggleVisitante[3] = (ToggleButton) findViewById(R.id.vb4);
        toggleVisitante[4] = (ToggleButton) findViewById(R.id.vb5);
        puntos = (ToggleButton) findViewById(R.id.accion);
        finalizar = (Button) findViewById(R.id.end);
        estadisticas = (Button) findViewById(R.id.stat);
        uno = (RadioButton) findViewById(R.id.p1);
        dos = (RadioButton) findViewById(R.id.p2);
        tres = (RadioButton) findViewById(R.id.p3);
        sumarL = (Button) findViewById(R.id.sumarpuntol);
        sumarV = (Button) findViewById(R.id.sumarpuntov);
        restarL = (Button) findViewById(R.id.restarpuntol);
        restarV = (Button) findViewById(R.id.restarpuntov);
        //marcamos por defecto el radio de 2 puntos
        dos.setChecked(true);
        //asignamos el método click a los botones de los jugadores titulares
        for (int i = 0;i<5;i++){
            local[i].setOnClickListener(this);
            visitante[i].setOnClickListener(this);
        }
        //botones manuales evento
        sumarL.setOnClickListener(this);
        sumarV.setOnClickListener(this) ;
        restarL.setOnClickListener(this);
        restarV.setOnClickListener(this);
        //botones toggle evento
        togglelocal[0].setOnClickListener(this);
        togglelocal[1].setOnClickListener(this);
        togglelocal[2].setOnClickListener(this);
        togglelocal[3].setOnClickListener(this);
        togglelocal[4].setOnClickListener(this);
        toggleVisitante[0].setOnClickListener(this);
        toggleVisitante[1].setOnClickListener(this);
        toggleVisitante[2].setOnClickListener(this);
        toggleVisitante[3].setOnClickListener(this);
        toggleVisitante[4].setOnClickListener(this);
        //asignaciones del bundle
        nombreLocal.setText(listaLocal[0]);
        nombreVisitante.setText(listaVisitante[0]);
        //Array de las estadisticas
        for (int j = 0;j<dorsalesLocal.length;j++){
            dorsalesLocal[j]= listaLocal[j+1];
            dorsalesVisitante[j]=listaVisitante[j+1];
        }
        for (int k = 0;k<statLocal.length;k++){
            statLocal[k]= "0";
            statVisitante[k]="0";
        }
        //local
        local[0].setText(listaLocal[1]);
        local[1].setText(listaLocal[2]);
        local[2].setText(listaLocal[3]);
        local[3].setText(listaLocal[4]);
        local[4].setText(listaLocal[5]);
        togglelocal[0].setText(listaLocal[6]);
        togglelocal[1].setText(listaLocal[7]);
        togglelocal[2].setText(listaLocal[8]);
        togglelocal[3].setText(listaLocal[9]);
        togglelocal[4].setText(listaLocal[10]);
        //asignacion toogle
        togglelocal[0].setTextOff(listaLocal[6]);
        togglelocal[0].setTextOn(listaLocal[6]);
        togglelocal[1].setTextOff(listaLocal[7]);
        togglelocal[1].setTextOn(listaLocal[7]);
        togglelocal[2].setTextOff(listaLocal[8]);
        togglelocal[2].setTextOn(listaLocal[8]);
        togglelocal[3].setTextOff(listaLocal[9]);
        togglelocal[3].setTextOn(listaLocal[9]);
        togglelocal[4].setTextOff(listaLocal[10]);
        togglelocal[4].setTextOn(listaLocal[10]);
        //visitante
        visitante[0].setText(listaVisitante[1]);
        visitante[1].setText(listaVisitante[2]);
        visitante[2].setText(listaVisitante[3]);
        visitante[3].setText(listaVisitante[4]);
        visitante[4].setText(listaVisitante[5]);
        toggleVisitante[0].setText(listaVisitante[6]);
        toggleVisitante[1].setText(listaVisitante[7]);
        toggleVisitante[2].setText(listaVisitante[8]);
        toggleVisitante[3].setText(listaVisitante[9]);
        toggleVisitante[4].setText(listaVisitante[10]);
        //asignacion toogle
        toggleVisitante[0].setTextOff(listaVisitante[6]);
        toggleVisitante[0].setTextOn(listaVisitante[6]);
        toggleVisitante[1].setTextOff(listaVisitante[7]);
        toggleVisitante[1].setTextOn(listaVisitante[7]);
        toggleVisitante[2].setTextOff(listaVisitante[8]);
        toggleVisitante[2].setTextOn(listaVisitante[8]);
        toggleVisitante[3].setTextOff(listaVisitante[9]);
        toggleVisitante[3].setTextOn(listaVisitante[9]);
        toggleVisitante[4].setTextOff(listaVisitante[10]);
        toggleVisitante[4].setTextOn(listaVisitante[10]);




    }


    @Override
    public void onClick(View v) {

        switch(v.getId()){
            case R.id.lb1:
                gestionarTogglesButton(R.id.lb1,togglelocal[0],"local");
                break;
            case R.id.lb2:
                gestionarTogglesButton(R.id.lb2,togglelocal[1],"local");
                break;
            case R.id.lb3:
                gestionarTogglesButton(R.id.lb3,togglelocal[2],"local");
                break;
            case R.id.lb4:
                gestionarTogglesButton(R.id.lb4,togglelocal[3],"local");
                break;
            case R.id.lb5:
                gestionarTogglesButton(R.id.lb5,togglelocal[4],"local");
                break;
            case R.id.vb1:
                gestionarTogglesButton(R.id.vb1,toggleVisitante[0],"visitante");
                break;
            case R.id.vb2:
                gestionarTogglesButton(R.id.vb2,toggleVisitante[1],"visitante");
                break;
            case R.id.vb3:
                gestionarTogglesButton(R.id.vb3,toggleVisitante[2],"visitante");
                break;
            case R.id.vb4:
                gestionarTogglesButton(R.id.vb4,toggleVisitante[3],"visitante");
                break;
            case R.id.vb5:
                gestionarTogglesButton(R.id.vb5,toggleVisitante[4],"visitante");
                break;
            case R.id.sumarpuntol:
                marcadorLocal.setText(Integer.toString(Integer.parseInt(marcadorLocal.getText().toString())+1));
                break;
            case R.id.sumarpuntov:
                marcadorVisitante.setText(Integer.toString(Integer.parseInt(marcadorVisitante.getText().toString())+1));
                break;
            case R.id.restarpuntol:
                if (Integer.parseInt(marcadorLocal.getText().toString())!=0)
                marcadorLocal.setText(Integer.toString(Integer.parseInt(marcadorLocal.getText().toString())-1));
                break;
            case R.id.restarpuntov:
                if (Integer.parseInt(marcadorVisitante.getText().toString())!=0)
                marcadorVisitante.setText(Integer.toString(Integer.parseInt(marcadorVisitante.getText().toString())-1));
                break;
            case R.id.lt1:
                gestionarAccion(marcadorLocal,local[0]);
                break;
            case R.id.lt2:
                gestionarAccion(marcadorLocal,local[1]);
                break;
            case R.id.lt3:
                gestionarAccion(marcadorLocal,local[2]);
                break;
            case R.id.lt4:
                gestionarAccion(marcadorLocal,local[3]);
                break;
            case R.id.lt5:
                gestionarAccion(marcadorLocal,local[4]);
                break;
            case R.id.vt1:
                gestionarAccion(marcadorVisitante,visitante[0]);

                break;
            case R.id.vt2:
                gestionarAccion(marcadorVisitante,visitante[1]);

                break;
            case R.id.vt3:
                gestionarAccion(marcadorVisitante,visitante[2]);

                break;
            case R.id.vt4:
                gestionarAccion(marcadorVisitante,visitante[3]);

                break;
            case R.id.vt5:
                gestionarAccion(marcadorVisitante,visitante[4]);
                break;
        }
    }

    private void gestionarTogglesButton(int id, ToggleButton elemento, String cadena) {
        if (intercambio && dorsal.getId()!= id)
                elemento.setChecked(false);
        else {
            intercambio = !intercambio;
            sustitucion = cadena;
            dorsal = elemento;
        }
    }

    private void gestionarAccion(TextView marcador, Button v) {
        if (intercambio){
            realizarIntercambio(v.getId());
        }else{
            puntuar(marcador,v);

        }
    }

    private void realizarIntercambio(int v) {
        if (sustitucion.equals("local")){
            switch(v){
                case R.id.lt1:
                    gestionarCambio(local[0]);

                    break;
                case R.id.lt2:
                    gestionarCambio(local[1]);

                    break;
                case R.id.lt3:
                    gestionarCambio(local[2]);

                    break;
                case R.id.lt4:
                    gestionarCambio(local[3]);

                    break;
                case R.id.lt5:
                    gestionarCambio(local[4]);
                    break;
            }

        }else{
            switch(v){
                case R.id.vt1:
                    gestionarCambio(visitante[0]);

                    break;
                case R.id.vt2:
                    gestionarCambio(visitante[1]);

                    break;
                case R.id.vt3:
                    gestionarCambio(visitante[2]);

                    break;
                case R.id.vt4:
                    gestionarCambio(visitante[3]);

                    break;
                case R.id.vt5:
                    gestionarCambio(visitante[4]);

                    break;
            }
        }

    }

    private void gestionarCambio(Button jugador) {
        String auxiliar;
        auxiliar = jugador.getText().toString();
        jugador.setText(dorsal.getText().toString());
        dorsal.setText(auxiliar);
        dorsal.setTextOff(auxiliar);
        dorsal.setTextOn(auxiliar);
        dorsal.setChecked(false);
        intercambio=false;
    }

    private void puntuar(TextView marcador,Button v) {
        if (puntos.getText().toString().equals("Aumentar")) {
            int punto = comprobarPuntos();
            marcador.setText(Integer.toString(Integer.parseInt(marcador.getText().toString())+punto));
            asignarPuntosEstadistica(v,marcador,punto,"Aumentar");
        }else{
            int punto = comprobarPuntos();
            if(punto <= Integer.parseInt(marcador.getText().toString())) {
                marcador.setText(Integer.toString(Integer.parseInt(marcador.getText().toString()) - punto));
                asignarPuntosEstadistica(v, marcador, punto, "Disminuir");
            }
        }
    }

    private void asignarPuntosEstadistica(Button v, TextView marcador, int punto, String cadena) {

        if (marcador.getId()==R.id.marcadorLocal){
            for (int i = 0;i<dorsalesLocal.length;i++){
                if (dorsalesLocal[i].equals(v.getText().toString())) {
                    if (cadena.equals("Aumentar"))
                        statLocal[i] = Integer.toString(Integer.parseInt(statLocal[i]) + punto);
                    else
                        if (Integer.parseInt(statLocal[i])>punto)
                            statLocal[i] = Integer.toString(Integer.parseInt(statLocal[i]) - punto);
                }
            }
        }else{
            for (int i = 0;i<dorsalesVisitante.length;i++){
                if (dorsalesVisitante[i].equals(v.getText().toString())) {
                    if (cadena.equals("Aumentar"))
                        statVisitante[i] = Integer.toString(Integer.parseInt(statVisitante[i]) + punto);
                    else
                    if (Integer.parseInt(statVisitante[i])>punto)
                        statVisitante[i] = Integer.toString(Integer.parseInt(statVisitante[i]) - punto);
                }
            }
        }

    }

    private int comprobarPuntos() {
        if (uno.isChecked()){
            dos.setChecked(true);
            return 1;
        }else if(tres.isChecked()){
            dos.setChecked(true);
            return 3;
        }

        return 2;

    }
}
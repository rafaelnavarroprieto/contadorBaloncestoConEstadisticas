package rafa.edu.contadorbaloncestomejorado;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

/**
 * Created by zaladriel on 4/12/15.
 */
public class Estadisticas extends AppCompatActivity {
    String nombrelocal,nombreVisitante,marcadorlocal,marcadorvisitante;
    String[] dorsalesLocales,dorsalesVisitantes,canastasLocales,canastasVisitantes;
    TextView[] elementosLocal = new TextView[24];
    TextView[] elementosVisitante = new TextView[24];

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menuestadisticas,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.volver:
                this.finish();
                break;
            case R.id.end:
                Intent intent = new Intent(Estadisticas.this,MainActivity.class);
                this.finish();
                startActivity(intent);
                break;

        }
        return true;
    }
    Bundle bundle;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.estadisticas);

        bundle = this.getIntent().getExtras();
        nombrelocal = bundle.getString("nombrelocal");
        nombreVisitante = bundle.getString("nombrevisitante");
        marcadorlocal = bundle.getString("marcadorlocal");
        marcadorvisitante = bundle.getString("marcadorvisitante");
        dorsalesLocales = bundle.getStringArray("dorsaleslocal");
        dorsalesVisitantes = bundle.getStringArray("dorsalesVisitante");
        canastasLocales = bundle.getStringArray("statLocal");
        canastasVisitantes = bundle.getStringArray("statVisitante");
        //asignación elementos xml a variables
        //Nombre de equipos
        elementosLocal[0]= (TextView) findViewById(R.id.nombrelocal);
        elementosVisitante[0]= (TextView) findViewById(R.id.nombrevisitante);
        //marcador , total puntos, margen de error
        elementosLocal[1]= (TextView) findViewById(R.id.marcadorLocal);
        elementosVisitante[1]= (TextView) findViewById(R.id.marcadorvisitante);
        elementosLocal[2]= (TextView) findViewById(R.id.marcadorLocalJugadores);
        elementosVisitante[2]= (TextView) findViewById(R.id.marcadorvisitanteJugadores);
        elementosLocal[3]= (TextView) findViewById(R.id.margenErrorLocal);
        elementosVisitante[3]= (TextView) findViewById(R.id.margenErrorVisitante);
        //Dorsales
        elementosLocal[4]= (TextView) findViewById(R.id.jl1);
        elementosLocal[5]= (TextView) findViewById(R.id.jl2);
        elementosLocal[6]= (TextView) findViewById(R.id.jl3);
        elementosLocal[7]= (TextView) findViewById(R.id.jl4);
        elementosLocal[8]= (TextView) findViewById(R.id.jl5);
        elementosLocal[9]= (TextView) findViewById(R.id.jl6);
        elementosLocal[10]= (TextView) findViewById(R.id.jl7);
        elementosLocal[11]= (TextView) findViewById(R.id.jl8);
        elementosLocal[12]= (TextView) findViewById(R.id.jl9);
        elementosLocal[13]= (TextView) findViewById(R.id.jl10);
        elementosVisitante[4]= (TextView) findViewById(R.id.jv1);
        elementosVisitante[5]= (TextView) findViewById(R.id.jv2);
        elementosVisitante[6]= (TextView) findViewById(R.id.jv3);
        elementosVisitante[7]= (TextView) findViewById(R.id.jv4);
        elementosVisitante[8]= (TextView) findViewById(R.id.jv5);
        elementosVisitante[9]= (TextView) findViewById(R.id.jv6);
        elementosVisitante[10]= (TextView) findViewById(R.id.jv7);
        elementosVisitante[11]= (TextView) findViewById(R.id.jv8);
        elementosVisitante[12]= (TextView) findViewById(R.id.jv9);
        elementosVisitante[13]= (TextView) findViewById(R.id.jv10);
        //puntos
        elementosLocal[14]= (TextView) findViewById(R.id.pl1);
        elementosLocal[15]= (TextView) findViewById(R.id.pl2);
        elementosLocal[16]= (TextView) findViewById(R.id.pl3);
        elementosLocal[17]= (TextView) findViewById(R.id.pl4);
        elementosLocal[18]= (TextView) findViewById(R.id.pl5);
        elementosLocal[19]= (TextView) findViewById(R.id.pl6);
        elementosLocal[20]= (TextView) findViewById(R.id.pl7);
        elementosLocal[21]= (TextView) findViewById(R.id.pl8);
        elementosLocal[22]= (TextView) findViewById(R.id.pl9);
        elementosLocal[23]= (TextView) findViewById(R.id.pl10);
        elementosVisitante[14]= (TextView) findViewById(R.id.pv1);
        elementosVisitante[15]= (TextView) findViewById(R.id.pv2);
        elementosVisitante[16]= (TextView) findViewById(R.id.pv3);
        elementosVisitante[17]= (TextView) findViewById(R.id.pv4);
        elementosVisitante[18]= (TextView) findViewById(R.id.pv5);
        elementosVisitante[19]= (TextView) findViewById(R.id.pv6);
        elementosVisitante[20]= (TextView) findViewById(R.id.pv7);
        elementosVisitante[21]= (TextView) findViewById(R.id.pv8);
        elementosVisitante[22]= (TextView) findViewById(R.id.pv9);
        elementosVisitante[23]= (TextView) findViewById(R.id.pv10);

        //asignación de valores
        elementosLocal[0].setText(nombrelocal);
        elementosVisitante[0].setText(nombreVisitante);
        elementosLocal[1].setText(marcadorlocal);
        elementosVisitante[1].setText(marcadorvisitante);
        /**
         * saltamos los indices 2 y 3 porque necesitamos antes saber cuantas canastas puntuaron cada jugador, pasamos a asignar dorsales y puntos de estos
         * Para recorrerlo tanto los dorsales de ambos equipos como las canatas que encesto cada jugador, he creado un bucle for de la siguiente forma:
         * 1- El bucle se recorre 10 veces que es la longitud de cada array enviado en el bundle donde tendremos los dorsales de cada jugador y sus puntos en 4 arrays
         * 2- El inidice i indica el indice de los arrays enviados por el bundle
         * 3- El indice j indica el indice donde estan los jugadores en el array global de cada equipo, es decir, en el indice 4 empiezan los dorsales de cada jugador hasta 14 (5 titulares y 5 suplentes)
         * 4- El indice k indica el indice donde empiezan a almacenarse la puntuación de cada jugador
         */


        for (int i = 0, j=4; i <dorsalesLocales.length;i++,j++){
            elementosLocal[j].setText(dorsalesLocales[i]);
            elementosVisitante[j].setText(dorsalesVisitantes[i]);

        }

        for(int i = 0, k=14;i<canastasLocales.length;k++,i++) {
            elementosLocal[k].setText(canastasLocales[i]);
            elementosVisitante[k].setText(canastasVisitantes[i]);
        }


       //calculamos margen de error y marcador según la puntuación de cada jugador
         elementosLocal[2].setText(calcularPuntuacionJugadores(canastasLocales));
         elementosVisitante[2].setText(calcularPuntuacionJugadores(canastasVisitantes));
         elementosLocal[3].setText(Integer.toString(Integer.parseInt(elementosLocal[1].getText().toString())-Integer.parseInt(elementosLocal[2].getText().toString())));
         elementosVisitante[3].setText(Integer.toString(Integer.parseInt(elementosVisitante[1].getText().toString())-Integer.parseInt(elementosVisitante[2].getText().toString())));

    }

    private String calcularPuntuacionJugadores(String[] listaDePuntos) {
        int total = 0;
        for (int i  = 0; i< listaDePuntos.length;i++){
            total += Integer.parseInt(listaDePuntos[i]);
        }
        return Integer.toString(total);
    }
}

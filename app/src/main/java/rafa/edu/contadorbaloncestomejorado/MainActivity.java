package rafa.edu.contadorbaloncestomejorado;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    EditText[] local = new EditText[11];
    EditText[] visitante = new EditText[11];
    Button boton;


    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        local[0] = (EditText) findViewById(R.id.nlocal);
        visitante[0] = (EditText) findViewById(R.id.nvisitante);
        local[1] = (EditText) findViewById(R.id.l1);
        local[2] = (EditText) findViewById(R.id.l2);
        local[3] = (EditText) findViewById(R.id.l3);
        local[4] = (EditText) findViewById(R.id.l4);
        local[5] = (EditText) findViewById(R.id.l5);
        local[6] = (EditText) findViewById(R.id.lb1);
        local[7] = (EditText) findViewById(R.id.lb2);
        local[8] = (EditText) findViewById(R.id.lb3);
        local[9] = (EditText) findViewById(R.id.lb4);
        local[10] = (EditText) findViewById(R.id.lb5);
        visitante[1] = (EditText) findViewById(R.id.v1);
        visitante[2] =(EditText) findViewById(R.id.v2);
        visitante[3] = (EditText) findViewById(R.id.v3);
        visitante[4] = (EditText) findViewById(R.id.v4);
        visitante[5] = (EditText) findViewById(R.id.v5);
        visitante[6] = (EditText) findViewById(R.id.vb1);
        visitante[7] = (EditText) findViewById(R.id.vb2);
        visitante[8] = (EditText) findViewById(R.id.vb3);
        visitante[9] = (EditText) findViewById(R.id.vb4);
        visitante[10] = (EditText) findViewById(R.id.vb5);
        boton = (Button) findViewById(R.id.enviar);

       boton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        boolean resultadoLocal = comprobarArrays(local);
        boolean resultadovisitante = comprobarArrays(visitante);
        //comprobamos que no se produzcan coincidencias de dorsales dentro del mismo equipo y que los nombres de los equipos sean diferentes
        if (resultadoLocal && resultadovisitante && !(local[0].getText().toString().toUpperCase().equals(visitante[0].getText().toString().toUpperCase()))){
            String[] localR = pasarACadena(local);
            String[] visitanteR = pasarACadena(visitante);
            Intent intent = new Intent(MainActivity.this,Juego.class);
            Bundle b = new Bundle();
            b.putStringArray("local", localR);
            b.putStringArray("visitante", visitanteR);
            intent.putExtras(b);
            startActivity(intent);
        }

    }
    private String[] pasarACadena(EditText[] lista){
        String[] array= new String[11];
        for (int i=0;i<array.length;i++){
            array[i]=lista[i].getText().toString();
        }
        return array;
    }

    private boolean comprobarArrays(EditText[] lista) {
        if (lista[0].getText().toString().equals("")) //comprobamos el nombre del equípo no este vacío
            return false;
        if (lista[1].getText().toString().equals("")) //comprobamos el 1º dorsal que no este vacía
            return false;
        else {
            for (int i = 1; i < 10; i++) { //comprobamos los demas dorsales a partir del primer dorsal
                for (int j = 1; j < i; j++) {
                    if (lista[i].getText().toString().equals("")) {
                        return false;
                    } else {
                        if (Integer.parseInt(lista[i].getText().toString()) == Integer.parseInt(lista[j].getText().toString()))
                            return false;
                    }
                }
            }
            return true;
        }
    }

}

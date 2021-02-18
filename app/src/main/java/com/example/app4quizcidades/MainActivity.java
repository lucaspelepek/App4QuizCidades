package com.example.app4quizcidades;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    final Integer[] arrCortada = new Integer[5];
    final String[] nomeCidades = {"BARCELONA", "BRASILIA", "CURITIBA", "LAS VEGAS", "MONTREAL", "PARIS", "RIO DE JANEIRO", "SALVADOR", "SAO PAULO", "TOQUIO"};
    final int[] images = {R.drawable.barcelona, R.drawable.brasilia, R.drawable.curitiba, R.drawable.lasvegas, R.drawable.montreal,
            R.drawable.paris, R.drawable.riodejaneiro, R.drawable.salvador, R.drawable.saopaulo, R.drawable.toquio};
    ImageView imageView;
    Button button;
    EditText editText;
    TextView textView, textViewCorreto;
    int numeroDeVezesBotaoFoiPressionado;
    int contador;
    int score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.imageView);
        textViewCorreto = findViewById(R.id.textViewCorreto);
        textView = findViewById(R.id.textViewResultado);
        editText = findViewById(R.id.editTextInput);
        button = findViewById(R.id.button);

        numeroDeVezesBotaoFoiPressionado = 0;
        contador = 0;
        score = 0;

        randomizaCidades();
        imageView.setImageResource(images[arrCortada[contador]]);
    }

    private void randomizaCidades() {
        Integer[] arr = new Integer[10];

        for (int i = 0; i < arr.length; i++) {
            arr[i] = i;
        }
        Collections.shuffle(Arrays.asList(arr));

        System.arraycopy(arr, 0, arrCortada, 0, arrCortada.length);
    }

    public void verificaTentativa(View view) {
        String tentativa = editText.getText().toString();

        if (tentativa.isEmpty()) {
            Toast.makeText(this, "Digite o nome de uma cidade!", Toast.LENGTH_SHORT).show();
            return;
        }

        numeroDeVezesBotaoFoiPressionado++;

        //confere se está correto
        if (tentativa.equalsIgnoreCase(nomeCidades[arrCortada[contador]])) {
            textView.setText("ACERTOU!");
            textView.setTextColor(Color.GREEN);
            score += 25;
        } else {
            textView.setText("ERROU!");
            textView.setTextColor(Color.RED);
            textViewCorreto.setText("Cidade correta:");
            editText.setText(nomeCidades[arrCortada[contador]]);
        }

        editText.setInputType(InputType.TYPE_NULL);
        button.setText("Próximo");

        //uma vez pressionado para conferir a resposta, duas vez pressionado para ir para a próxima pergunta.
        if (numeroDeVezesBotaoFoiPressionado == 2) {

            //confere se já chegou no limite maximo de 4 perguntas e manda para a activityPontuacao
            if (contador == 4) {
                vaiParaActivityPontuacao();
                return;
            }

            editText.setInputType(InputType.TYPE_CLASS_TEXT);
            textViewCorreto.setText("");
            editText.setText("");
            textView.setText("Onde estou?");
            textViewCorreto.setText("");
            textView.setTextColor(Color.GRAY);
            button.setText("Confirmar");
            numeroDeVezesBotaoFoiPressionado = 0;
            imageView.setImageResource(images[arrCortada[contador]]);
        } else {
            contador++;
        }

        //se chegar na ultima cidade e troca o texto "proximo" por "resultado"
        if (contador == 4) {
            button.setText("Resultado");
        }
    }

    private void vaiParaActivityPontuacao() {
        Intent intent = new Intent(this, ActivityPontuacao.class);
        intent.putExtra("score", score);
        startActivity(intent);
        finish();
    }
}
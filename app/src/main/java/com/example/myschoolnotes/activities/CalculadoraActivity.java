package com.example.myschoolnotes.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.myschoolnotes.R;

public class CalculadoraActivity extends AppCompatActivity {

    float number1 = 0, number2 = 0, resultNum = 0;
    int operador = -1;
    boolean punto = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculadora);


        TextView introducido = (TextView) findViewById(R.id.textViewIntro);
        TextView resultado = (TextView) findViewById(R.id.textViewResult);

        Button btn0 = (Button) findViewById(R.id.btn0);
        Button btn1 = (Button) findViewById(R.id.btn1);
        Button btn2 = (Button) findViewById(R.id.btn2);
        Button btn3 = (Button) findViewById(R.id.btn3);
        Button btn4 = (Button) findViewById(R.id.btn4);
        Button btn5 = (Button) findViewById(R.id.btn5);
        Button btn6 = (Button) findViewById(R.id.btn6);
        Button btn7 = (Button) findViewById(R.id.btn7);
        Button btn8 = (Button) findViewById(R.id.btn8);
        Button btn9 = (Button) findViewById(R.id.btn9);
        Button btnMas = (Button) findViewById(R.id.btnMas);
        Button btnMenos = (Button) findViewById(R.id.btnMenos);
        Button btnPor = (Button) findViewById(R.id.btnPor);
        Button btnEntre = (Button) findViewById(R.id.btnEntre);
        Button btnInv = (Button) findViewById(R.id.btnInv);
        Button btnIgual = (Button) findViewById(R.id.btnIgual);
        Button btnBorrar = (Button) findViewById(R.id.btnBorrar);
        Button btnPunto = (Button) findViewById(R.id.btnPunto);
        Button btnCuadrado = (Button) findViewById(R.id.btnCuadrado);

        btn0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String texto = introducido.getText().toString();
                introducido.setText(texto+=0);
            }
        });

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String texto = introducido.getText().toString();
                introducido.setText(texto+=1);
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String texto = introducido.getText().toString();
                introducido.setText(texto+=2);
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String texto = introducido.getText().toString();
                introducido.setText(texto+=3);
            }
        });

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String texto = introducido.getText().toString();
                introducido.setText(texto+=4);
            }
        });

        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String texto = introducido.getText().toString();
                introducido.setText(texto+=5);
            }
        });

        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String texto = introducido.getText().toString();
                introducido.setText(texto+=6);
            }
        });

        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String texto = introducido.getText().toString();
                introducido.setText(texto+=7);
            }
        });

        btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String texto = introducido.getText().toString();
                introducido.setText(texto+=8);
            }
        });

        btn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String texto = introducido.getText().toString();
                introducido.setText(texto+=9);
            }
        });

        btnBorrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String texto = introducido.getText().toString();
                introducido.setText(" ");
                resultado.setText(" ");
                number1 = 0;
                number2 = 0;
                punto = true;
            }
        });

        btnInv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(introducido.getText().toString().equals(" ")){
                    introducido.setText(" ");
                }
                else {
                    int texto = Integer.parseInt(introducido.getText().toString());
                    int invertido = 0;
                    while (texto != 0) {
                        int resto = texto % 10;
                        invertido = invertido * 10 + resto;
                        texto /= 10;

                    }

                    introducido.setText(String.valueOf(invertido));
                }

            }
        });

        btnPunto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(punto){
                    String texto = introducido.getText().toString();
                    introducido.setText(texto+='.');
                    punto = false;
                }
                else{
                    String texto = introducido.getText().toString();
                    introducido.setText(texto);
                }
            }
        });

        btnMas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(introducido.getText().toString().equals(" ")){
                    introducido.setText(" ");
                }
                else {
                    float numIntro = Float.parseFloat(introducido.getText().toString());
                    if (number1 == 0) {
                        number1 = numIntro;
                        resultNum = number1;
                        resultado.setText(String.valueOf(resultNum) + "+");
                    } else if (number1 != 0) {
                        number2 = numIntro;
                        resultNum = number1 + number2;
                        resultado.setText(number1 + "+" + number2 + "=" + resultNum);

                    }
                }

                operador = 0;

                number1 = number1+number2;
                number2 = 0;
                introducido.setText(" ");




            }
        });

        btnMenos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(introducido.getText().toString().equals(" ")){
                    introducido.setText(" ");
                }
                else {
                    float numIntro = Float.parseFloat(introducido.getText().toString());
                    if (number1 == 0) {
                        number1 = numIntro;
                        resultNum = number1;
                        resultado.setText(String.valueOf(resultNum) + "-");

                    } else if (number1 != 0) {
                        number2 = numIntro;
                        resultNum = number1 - number2;
                        resultado.setText(number1 + "-" + number2 + "=" + resultNum);

                    }
                }
                operador = 1;
                number1 = number1-number2;
                number2 = 0;
                introducido.setText(" ");

            }
        });

        btnEntre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (introducido.getText().toString().equals(" ")) {
                    introducido.setText(" ");
                }
                else if (introducido.getText().toString().equals("0")){
                    resultado.setText("No se puede dividir entre 0");
                    introducido.setText(" ");
                    number1 = 0;
                    number2 = 0;
                    resultNum = 0;
                }
                else {
                    float numIntro = Float.parseFloat(introducido.getText().toString());
                    if (number1 == 0) {
                        number1 = numIntro;
                        resultNum = number1;
                        resultado.setText(String.valueOf(resultNum) + "/");
                        operador = 2;

                    } else if (number1 != 0) {
                        number2 = numIntro;
                        resultNum = number1 / number2;
                        resultado.setText(number1 + "/" + number2 + "=" + resultNum);

                    }
                }
                operador = 2;
                number1 = resultNum;
                number2 = 0;
                introducido.setText(" ");

            }
        });

        btnPor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(introducido.getText().toString().equals(" ")){
                    introducido.setText(" ");
                }
                else {
                    float numIntro = Float.parseFloat(introducido.getText().toString());
                    if (number1 == 0) {
                        number1 = numIntro;
                        resultNum = number1;
                        resultado.setText(String.valueOf(resultNum) + "*");

                    } else if (number1 != 0) {
                        number2 = numIntro;
                        resultNum = number1 * number2;
                        resultado.setText(number1 + "*" + number2 + "=" + resultNum);

                    }
                }
                operador = 3;
                number1 = resultNum;
                number2 = 0;
                introducido.setText(" ");

            }

        });

        btnCuadrado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(introducido.getText().toString().equals(" ")){
                    introducido.setText(" ");
                }
                else {
                    float texto = Float.parseFloat(introducido.getText().toString());
                    introducido.setText(String.valueOf(texto*texto));

                }

            }
        });

        btnIgual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                punto = true;
                if(number1 == 0 && number2 == 0){
                    resultado.setText(String.valueOf(0));

                }
                else if (introducido.getText().toString().equals(" ")){
                    resultado.setText(String.valueOf(resultNum));
                }

                else if (resultado.getText().toString().equals("No se puede dividir entre 0")){
                    resultado.setText(String.valueOf(0));
                    introducido.setText(" ");
                    number1 = 0;
                    number2 = 0;
                    resultNum = 0;

                }

                else if (resultado.getText().toString().equals(" ")){
                    resultado.setText(0);

                }

                else {
                    float numIntro = Float.parseFloat(introducido.getText().toString());
                    number2 = numIntro;
                    switch (operador) {
                        case 0:
                            resultNum = number1 + number2;
                            resultado.setText(String.valueOf(resultNum));
                            break;

                        case 1:
                            resultNum = number1 - number2;
                            resultado.setText(String.valueOf(resultNum));
                            break;

                        case 2:
                            if(number2 == 0){
                                resultado.setText("No se puede dividir entre 0");
                            }

                            else {
                                resultNum = number1/ number2;
                                resultado.setText(String.valueOf(resultNum));
                            }
                            break;

                        case 3:
                            resultNum = number1 * number2;
                            resultado.setText(String.valueOf(resultNum));
                            break;

                    }
                }
            }
        });

    }
}
package com.proje.serkan.bayrammesajlari.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.proje.serkan.bayrammesajlari.Modal.MessagesClass;
import com.proje.serkan.bayrammesajlari.R;
import com.proje.serkan.bayrammesajlari.Modal.MyDataBaseClass;

import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    List<MessagesClass> messageList;


    Button btnilk, btnorta, btnson;
    public int deger = 3;
    TextView textPage;
    public int pageNumber=1;

    int[] array = new int[51];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MainActivity.this.runOnUiThread(new Runnable() {

            @Override
            public void run() {

                final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Hoşgeldiniz");
                builder.setMessage("Paylaşmak istediğiniz yazının üzerine tılayınız.");
                builder.setIcon(android.R.drawable.ic_dialog_info);
                builder.setPositiveButton("Tamam", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                    }
                });
                builder.show();

            }
        });


        messageList = MyDataBaseClass.getInstance(getApplicationContext()).getAllMessageData();

        btnilk = (Button) findViewById(R.id.btn1);
        btnorta = (Button) findViewById(R.id.btn2);
        btnson = (Button) findViewById(R.id.btn3);
        textPage = (TextView) findViewById(R.id.txtPage);

        Random random = new Random();

        int new_number;
        boolean state = true;

        for (int i = 0; i < array.length; i++) {
            while (state) {
                new_number = random.nextInt(51);

                if (i == 0) {
                    array[0] = new_number;
                    break;
                }

                for (int k = 0; k < i; k++) {
                    if (array[k] == new_number) {
                        state = true;
                        break;
                    } else
                        state = false;
                }

                if (state == false)
                    array[i] = new_number;
            }
            state = true;
        }
        textPage.setText(+pageNumber+"/17");
        btnilk.setText(String.valueOf(messageList.get(array[0]).getIcerik()));
        btnorta.setText(String.valueOf(messageList.get(array[1]).getIcerik()));
        btnson.setText(String.valueOf(messageList.get(array[2]).getIcerik()));

    }

    @Override
    public void onBackPressed()

    {
        AlertDialog();
    }



    private void AlertDialog() {

        final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Bayram Mesajları");
        builder.setMessage("Çıkış yapmak istediğinizden emin misiniz?");
        builder.setIcon(android.R.drawable.ic_dialog_info);
        builder.setPositiveButton("Evet", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {

                finish();
            }
        });

        builder.setNegativeButton("Hayır", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {

            }
        });
        builder.show();
    }

    public void shareText(String share) {

        Intent sharingIntent = new Intent(Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, share);
        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Konu");
        startActivity(Intent.createChooser(sharingIntent, "Paylaş"));
    }

    public void tiklandi(View view) {

        switch (view.getId()) {

            case R.id.btnback:

                if (deger == 3) {

                    Intent intent = new Intent(getApplicationContext(), SplashScreen.class);
                    startActivity(intent);
                    finish();

                } else {
                    deger = deger - 3;

                    for (int i = deger - 3; i < deger - 2; i++) {

                        pageNumber--;
                        textPage.setText(+pageNumber+"/17");
                        btnilk.setText(String.valueOf(messageList.get(array[deger - 3]).getIcerik()));
                        btnorta.setText(String.valueOf(messageList.get(array[deger - 2]).getIcerik()));
                        btnson.setText(String.valueOf(messageList.get(array[deger - 1]).getIcerik()));

                    }
                }

                break;

            case R.id.btnnext:

                if (deger == 51) {
                    Intent intent = new Intent(getApplicationContext(), SplashScreen.class);
                    startActivity(intent);
                    finish();

                } else {
                    for (int i = deger; i < deger + 1; i++) {
                        pageNumber++;
                        textPage.setText(+pageNumber+"/17");
                        btnilk.setText(String.valueOf(messageList.get(array[deger]).getIcerik()));
                        btnorta.setText(String.valueOf(messageList.get(array[deger + 1]).getIcerik()));
                        btnson.setText(String.valueOf(messageList.get(array[deger + 2]).getIcerik()));

                    }
                    deger = deger + 3;
                }
                break;

            case R.id.btn1:

                shareText(btnilk.getText().toString());

                break;

            case R.id.btn2:

                shareText(btnorta.getText().toString());

                break;

            case R.id.btn3:

                shareText(btnson.getText().toString());

                break;
        }
    }
}

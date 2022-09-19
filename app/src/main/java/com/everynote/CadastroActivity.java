package com.everynote;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.everynote.db.bean.Nota;
import com.everynote.db.bean.Prioridade;
import com.everynote.db.dal.NotaDAL;

public class CadastroActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private String[] prioridades = {"Baixa", "Normal", "Alta"};
    private int prioridade = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        Spinner spin = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_dropdown_item_1line, prioridades);
        spin.setAdapter(aa);
        spin.setOnItemSelectedListener(this);

        Button btnSalvar = findViewById(R.id.btnSalvar);
        TextView txInputTitulo = findViewById(R.id.txInputTitulo);
        TextView txInputTexto = findViewById(R.id.txInputTexto);


        btnSalvar.setOnClickListener(v->{
            Nota nota = new Nota(0, txInputTitulo.getText().toString(), txInputTexto.getText().toString(), Prioridade.values()[this.prioridade]);
            NotaDAL dal = new NotaDAL(this);
            dal.salvar(nota);
            Toast.makeText(this, "Nota adicionada", Toast.LENGTH_LONG).show();
            finish();
        });

    }

    @Override
    public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {
        this.prioridade = position;
    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }

}

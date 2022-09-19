package com.everynote;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.everynote.db.bean.Nota;
import com.everynote.db.dal.NotaDAL;

import org.w3c.dom.Text;

public class DetalhesActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes);

        Intent intent = getIntent();
        String id = intent.getStringExtra("id");

        NotaDAL dal = new NotaDAL(this);

        Nota nota = dal.get(Integer.parseInt(id));

        TextView txTitulo = findViewById(R.id.txTitulo);
        TextView txTexto = findViewById(R.id.txTexto);
        TextView txPrioridade = findViewById(R.id.txPrioridade);

        txTitulo.setText(nota.getTitulo());
        txTexto.setText(nota.getTexto());
        txPrioridade.setText(nota.getPrioridade().name());

    }
}

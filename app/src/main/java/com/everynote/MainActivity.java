package com.everynote;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.everynote.db.bean.Nota;
import com.everynote.db.dal.NotaDAL;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private List<Nota> notas;
    private ArrayAdapter<Nota> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        listView = findViewById(R.id.listView);

        //NotaAdapter notaAdapter = new NotaAdapter(this, R.layout.item_listview, dados);
        NotaDAL dal = new NotaDAL(this);
        notas = dal.get("");
        adapter = new ArrayAdapter<Nota>(this, R.layout.item_listview, notas) {
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

                View view = super.getView(position, convertView, parent);
                int prioridade = MainActivity.this.notas.get(position).getPrioridade().getValue();

                switch(prioridade) {
                    case 1:
                        view.setBackgroundResource(R.color.blue);
                        break;
                    case 2:
                        view.setBackgroundResource(R.color.yellow);
                        break;
                    case 3:
                        view.setBackgroundResource(R.color.red);
                        break;
                }

                return view;
            }
        };
        listView.setAdapter(adapter);

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
                                           int pos, long id) {

                NotaDAL notaDAL = new NotaDAL(MainActivity.this);
                notaDAL.apagar(notas.get(pos).getId());
                MainActivity.this.atualizaListView(pos);
                Toast.makeText(MainActivity.this, "Nota apagada com sucesso", Toast.LENGTH_LONG).show();


                return true;
            }
        });

        listView.setOnItemClickListener((adapterView, view, i, l) -> {
            Intent intent = new Intent(MainActivity.this, DetalhesActivity.class);
            int id = this.notas.get(i).getId();
            intent.putExtra("id", ""+id);
            startActivity(intent);
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId())
        {
            case R.id.itFechar:
                finish();
                break;
            case R.id.itOrdenaPrioridade:
                this.ordenaByPrioridade();
                break;
            case R.id.itOrdenaOrdem:
                this.ordenaByTitulo();
                break;
            case R.id.itNovaAnotacao:
                Intent intent = new Intent(MainActivity.this, CadastroActivity.class);
                startActivityForResult(intent, 0);
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    public void ordenaByPrioridade()
    {
        NotaDAL dal = new NotaDAL(MainActivity.this);
        this.notas = dal.get("order by prioridade desc");
        adapter = new ArrayAdapter<Nota>(this, R.layout.item_listview, notas);
        this.listView.setAdapter(adapter);
    }
    public void ordenaByTitulo()
    {
        NotaDAL dal = new NotaDAL(MainActivity.this);
        this.notas = dal.get("order by titulo");
        adapter = new ArrayAdapter<Nota>(this, R.layout.item_listview, notas);
        this.listView.setAdapter(adapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        NotaDAL dal = new NotaDAL(MainActivity.this);
        this.notas = dal.get("");
        adapter = new ArrayAdapter<Nota>(this, R.layout.item_listview, notas);
        this.listView.setAdapter(adapter);
    }

    public void atualizaListView(int pos)
    {
        this.adapter.remove(notas.get(pos));
        this.adapter.notifyDataSetChanged();
        this.listView.invalidateViews();
    }

}
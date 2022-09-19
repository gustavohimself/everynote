package com.everynote.db.dal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.widget.Toast;

import com.everynote.db.bean.Nota;
import com.everynote.db.bean.Prioridade;
import com.everynote.db.util.Conexao;

import java.sql.ResultSet;
import java.util.ArrayList;



public class NotaDAL
{   private Conexao con;
    private Context context;
    private final String TABLE="notas";

    public NotaDAL(Context context) {
        this.context=context;
        con = new Conexao(context);
        try {
            con.conectar();
        }
        catch(Exception e)
        {
            Toast.makeText(context,e.getMessage(),Toast.LENGTH_LONG).show();
        }
    }

    public boolean salvar(Nota nota)
    {
        ContentValues dados = new ContentValues();
        dados.put("titulo", nota.getTitulo());
        dados.put("texto", nota.getTexto());
        dados.put("prioridade", nota.getPrioridade().getValue());
        return con.inserir(TABLE,dados)>0;
    }
    public boolean alterar(Nota nota)
    {
        ContentValues dados=new ContentValues();
        dados.put("id", nota.getId());
        dados.put("texto", nota.getTexto());
        dados.put("prioridade", nota.getPrioridade().getValue());
        return con.alterar(TABLE,dados,"id="+nota.getId())>0;
    }
    public boolean apagar(long chave)
    {
        return con.apagar(TABLE,"id="+chave)>0;
    }

    public Nota get(int id)
    {   NotaDAL ndal = new NotaDAL(context);
        Nota nota = null;
        Cursor cursor=con.consultar("select * from "+TABLE+" where id="+id);
        if(cursor.moveToFirst())
            nota = new Nota(cursor.getInt(0), cursor.getString(1), cursor.getString(2), Prioridade.values()[cursor.getInt(3)-1]);
        cursor.close();;
        return nota;
    }
    public ArrayList <Nota> get(String filtro)
    {   NotaDAL gdal=new NotaDAL(context);
        ArrayList <Nota> objs = new ArrayList();
        String sql="select * from "+TABLE + " ";
        if (!filtro.equals(""))
            sql += filtro;

        Cursor cursor=con.consultar(sql);
        if(cursor.moveToFirst())
            while (!cursor.isAfterLast()) {
                int id = cursor.getInt(0);
                String titulo = cursor.getString(1);
                String texto = cursor.getString(2);
                int prioridade = cursor.getInt(3);
                objs.add(new Nota(cursor.getInt(0), cursor.getString(1), cursor.getString(2), Prioridade.values()[cursor.getInt(3)-1]));
                cursor.moveToNext();
            }
        cursor.close();;
        return objs;
    }
}

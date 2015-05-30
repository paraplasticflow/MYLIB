package com.cilic.zlatan.mylib;

import android.app.ListActivity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import android.database.sqlite.*;
public class MainActivity extends ActionBarActivity {
    ArrayList<String> primjerListe = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*Knjiga k1 = new Knjiga();
        k1.setNaziv("Hamina");
        k1.setAutor("hamo");
        k1.setIsbn("dksldksdl");
        k1.setDatumObjave("saddsad");
        k1.setBrojStranica(300);
        k1.setOpis("odlicna");
        k1.setStatus("procitana");

        Knjiga k2 = new Knjiga();
        k2.setNaziv("Kemina");
        k2.setAutor("kemo");
        k2.setIsbn("dksldksdl");
        k2.setDatumObjave("saddsad");
        k2.setBrojStranica(300);
        k2.setOpis("odlicna");
        k2.setStatus("procitana");

        Knjiga k3 = new Knjiga();
        k3.setNaziv("Mirsina");
        k3.setAutor("Mirso");
        k3.setIsbn("dksldksdl");
        k3.setDatumObjave("saddsad");
        k3.setBrojStranica(300);
        k3.setOpis("odlicna");
        k3.setStatus("procitana");*/

        DBHelper db = new DBHelper(this);
/*
        Log.d("Insert:", "Inserting...");
        db.dodajKnjigu(k1);
        db.dodajKnjigu(k2);
        db.dodajKnjigu(k3);*/

        Log.d("Reading:", "Reading all contacts...");
        //List<Knjiga> sveKnjige = db.vratiSveKnjige();
        //List<Knjiga> jednaKnjiga = new ArrayList<Knjiga>();
        //Knjiga k = db.dajKnjigu(2);
        //k.setNaziv("Kemina noA");
        //db.promjeniKnjigu(k);
        //db.obrisiKnjigu(k);
        List<Knjiga> sveKnjige = db.vratiSveKnjige();

        for(Knjiga k1 : sveKnjige) {
            primjerListe.add(k1.getNaziv());
        }
        //primjerListe.add(String.valueOf(db.dajBrojKnjiga()));
        ListView lw = (ListView) findViewById(R.id.book_list);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, primjerListe);
        lw.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_activity_actions, menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

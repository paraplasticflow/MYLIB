package com.cilic.zlatan.mylib;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class AddBookActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        setContentView(R.layout.activity_add_book);

        final String[] str={"Read","Not read","Currently reading"};
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, str);
        spinnerArrayAdapter.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item );
        Spinner spinDate = (Spinner)findViewById(R.id.spinner);
        spinDate.setAdapter(spinnerArrayAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_book, menu);
        return true;
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

    public void dodajKnjigu(View view) {
        final DBHelper db = new DBHelper(this);
        final Knjiga k = new Knjiga();
        EditText nazivET = (EditText) findViewById(R.id.nazivET);
        EditText autorET = (EditText) findViewById(R.id.autorET);
        EditText isbnET = (EditText) findViewById(R.id.isbnET);
        EditText opisET = (EditText) findViewById(R.id.opisET);
        Spinner statusET = (Spinner) findViewById(R.id.spinner);
        EditText brojStrET = (EditText) findViewById(R.id.brojStrET);
        DatePicker datumdP = (DatePicker) findViewById(R.id.datumDP);

        String regexEmpty = "";

        Boolean validno = true;
        if(nazivET.getText().toString().equals(regexEmpty)) {
            nazivET.setError("Enter the title");
            validno = false;
        }
        else {
            nazivET.setError(null);
        }

        if(autorET.getText().toString().equals(regexEmpty)) {
            autorET.setError("Enter the author");
            validno = false;
        }
        else {
            autorET.setError(null);
        }

        if(isbnET.getText().toString().equals(regexEmpty)) {
            isbnET.setError("Enter the ISBN");
            validno = false;
        }
        else {
            if(!isValidIsbn(isbnET.getText().toString())) {
                isbnET.setError("Enter valid ISBN");
                validno = false;
            }
            else {
                isbnET.setError(null);
            }
        }

        if(brojStrET.getText().toString().equals(regexEmpty)) {
            brojStrET.setError("Enter number of pages");
            validno = false;
        }
        else {
            brojStrET.setError(null);
        }



        if(validno) {

            k.setNaziv(nazivET.getText().toString());
            k.setAutor(autorET.getText().toString());
            k.setIsbn(isbnET.getText().toString());
            k.setOpis(opisET.getText().toString());
            k.setStatus(statusET.getSelectedItem().toString());
            k.setBrojStranica(Integer.parseInt(brojStrET.getText().toString()));
            int day = datumdP.getDayOfMonth();
            int month = datumdP.getMonth() + 1;
            int year = datumdP.getYear();
            k.setDatumObjave(day + "." + month + "." + year);
            final com.cilic.zlatan.mylib.AddBookActivity vanjski = this;

            new AlertDialog.Builder(this)
                    .setTitle("Add book?")
                    .setMessage("Are you sure you want to add this book?")
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // do nothing
                        }
                    })
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            db.dodajKnjigu(k);
                            NavUtils.navigateUpFromSameTask(vanjski);
                        }
                    })
                    .setIcon(R.drawable.ic_action_done)
                    .show();
        }
    }

    private Boolean isValidIsbn(String isbn) {
        Pattern pattern = Pattern.compile("(\\d-?){13}");
        Matcher matcher = pattern.matcher(isbn);
        return matcher.matches();
    }
}

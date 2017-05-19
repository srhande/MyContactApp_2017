package com.example.handes6253.mycontactapp;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper myDb;
    EditText editName;
    EditText editAge;
    EditText editEmail;
    Button btnAddData;
    Button btnViewData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDb = new DatabaseHelper(this);

        //Add the layout vars - done
        editName = (EditText) findViewById(R.id.editText_name);
        editAge = (EditText) findViewById(R.id.editText_age);
        editEmail = (EditText) findViewById(R.id.editText_email);
    }

    public void addData(View v){
        boolean isInserted = myDb.insertData(editName.getText().toString(), editAge.getText().toString(), editEmail.getText().toString());

        if (isInserted == true){
            Log.d("MyContact", "Success inserting data");
            //Insert Toast message here - done
            Context context = getApplicationContext();
            CharSequence text = "Success";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
        else {
            Log.d("MyContact", "Failure inserting data");
            //Insert toast message - done
            Context context = getApplicationContext();
            CharSequence text = "Fail";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
    }

    public void viewData(View v) {
        Cursor res = myDb.getAllData();
        if(res.getCount() == 0) {
            showMessage("Error", "No data found in database");
            //Output message using Log.d and Toast
            Log.d("Error", "No data");
            Context context = getApplicationContext();
            CharSequence text = "No data";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
            return;
        }

        StringBuffer buffer = new StringBuffer();
        //setup a loop with Cursor (res) using moveToNext - done
        if (res != null) {
            res.moveToFirst();
            for (int i = 0; i < res.getCount(); i++) {
                for (int j = 0; j < res.getColumnNames().length; j++) {
                    buffer.append(res.getString(j) + "\n");
                }
                buffer.append("\n");
                res.moveToNext();
            }
        }
        //append each COL to the buffer - done
        //display message using showMessage - done
        showMessage("Data", buffer.toString());
    }

    private void showMessage(String title, String message) {
        //AlertDialg.Builder
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
}

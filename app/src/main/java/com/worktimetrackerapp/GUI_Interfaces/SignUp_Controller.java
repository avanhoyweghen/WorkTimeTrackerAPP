package com.worktimetrackerapp.GUI_Interfaces;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import com.worktimetrackerapp.DB;
import com.worktimetrackerapp.R;


public class SignUp_Controller extends AppCompatActivity {

    Spinner spinner;
    TextView company;
    TextView jobTitle;
    TextView employer;
    TextView hourlywage;
    TextView avghours;
    public Button btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_information);

        spinner = findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.spinner, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        company = findViewById(R.id.txt_company);
        jobTitle = findViewById(R.id.txt_job_title);
        employer= findViewById(R.id.txt_employer);
        hourlywage = findViewById(R.id.txt_hourly_wage);
        avghours = findViewById(R.id.txt_avg_hours);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {


            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position ==0) {
                    company.setVisibility(View.INVISIBLE);
                    jobTitle.setVisibility(View.INVISIBLE);
                    employer.setVisibility(View.INVISIBLE);
                    hourlywage.setVisibility(View.INVISIBLE);
                    avghours.setVisibility(View.INVISIBLE);

                }
                else if (position == 1){
                    company.setVisibility(View.VISIBLE);
                    jobTitle.setVisibility(View.VISIBLE);
                    employer.setVisibility(View.VISIBLE);
                    hourlywage.setVisibility(View.VISIBLE);
                    avghours.setVisibility(View.VISIBLE);
                } else {
                    company.setText(null);
                    company.setVisibility(View.INVISIBLE);
                    jobTitle.setVisibility(View.VISIBLE);
                    employer.setVisibility(View.VISIBLE);
                    hourlywage.setVisibility(View.VISIBLE);
                    avghours.setVisibility(View.VISIBLE);
                }
                String spinner = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void dialogevent(View view){
        btn = findViewById(R.id.btn_next);
        btn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
                AlertDialog.Builder btn_next = new AlertDialog.Builder(SignUp_Controller.this);
                btn_next.setMessage("Do you want to add another job?").setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                final DB app = (DB) getApplication();
                                Double dblavghours = 0.0;
                                String cmp = null;
                                try{
                                    if(!avghours.getText().toString().isEmpty()) {
                                        dblavghours = Double.parseDouble(avghours.getText().toString());
                                    }
                                    if(!company.getText().toString().isEmpty()){
                                        cmp = company.getText().toString();
                                    }
                                    System.out.println(hourlywage.getText().toString());
                                    app.AddJob(cmp, spinner.getSelectedItem().toString(), jobTitle.getText().toString(), employer.getText().toString(), Double.parseDouble(hourlywage.getText().toString()), dblavghours);
                                }catch(Exception e){System.out.println(e);}

                                spinner.setSelection(0);
                                company.setText(null);
                                company.setVisibility(View.INVISIBLE);
                                jobTitle.setText(null);
                                jobTitle.setVisibility(View.INVISIBLE);
                                employer.setText(null);
                                employer.setVisibility(View.INVISIBLE);
                                hourlywage.setText(null);
                                hourlywage.setVisibility(View.INVISIBLE);
                                avghours.setText(null);
                                avghours.setVisibility(View.INVISIBLE);


                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                final DB app = (DB) getApplication();
                                Double dblavghours = 0.0;
                                String cmp = null;
                                try{
                                    if(!avghours.getText().toString().isEmpty()) {
                                        dblavghours = Double.parseDouble(avghours.getText().toString());
                                    }
                                    if(!company.getText().toString().isEmpty()){
                                        cmp = company.getText().toString();
                                    }
                                    app.AddJob(cmp, spinner.getSelectedItem().toString(), jobTitle.getText().toString(), employer.getText().toString(), Double.parseDouble(hourlywage.getText().toString()), dblavghours);
                                }catch(Exception e){System.out.println(e);}

                                app.completeLogin();
                            }
                        })
                         .setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                             }
                        });

                AlertDialog alert = btn_next.create();
                alert.setTitle("WTT");
                alert.show();
            }
        });
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


}
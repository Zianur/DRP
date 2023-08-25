package com.drp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.drp.ml.Dt;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.tensorflow.lite.DataType;
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class DrpWork extends AppCompatActivity {

    EditText DPF, BP, age, insulin, skinThickness, glucose, pregnancies, BMI, result;
    TextView shareButton, calculateButton, output;
    LinearLayout linearLayout;


    private FirebaseAuth mAuth;
    private DatabaseReference UsersRef;
    private String CurrentUserid;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drp_work);

        mAuth = FirebaseAuth.getInstance();

        CurrentUserid = mAuth.getCurrentUser().getUid();//getting authentication unique id

        //creating a node/child under User with that authentication unique id in database
        UsersRef = FirebaseDatabase.getInstance().getReference().child("Users").child(CurrentUserid);




        DPF = findViewById(R.id.DPFId);
        BP = findViewById(R.id.BPId);
        age = findViewById(R.id.ageId);
        insulin = findViewById(R.id.insulinId);
        skinThickness = findViewById(R.id.STId);
        pregnancies = findViewById(R.id.pregnanciesId);
        glucose = findViewById(R.id.glucoseId);
        BMI = findViewById(R.id.BMIId);
        result = findViewById(R.id.resultId);
        shareButton = findViewById(R.id.shareDataButtonId);
        calculateButton = findViewById(R.id.calculateButtonId);
        output = findViewById(R.id.outputId);
        linearLayout = findViewById(R.id.outputLinearLayoutId);






        linearLayout.setVisibility(View.INVISIBLE);
        calculateButton.setVisibility(View.INVISIBLE);

        String key = getIntent().getStringExtra("key");

        if (key.equals("calculate"))
        {
            shareButton.setVisibility(View.INVISIBLE);
            result.setVisibility(View.INVISIBLE);
            calculateButton.setVisibility(View.VISIBLE);
        }


        shareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if( TextUtils.isEmpty(pregnancies.getText().toString()))
                {
                    pregnancies.setError("Please enter valid input");
                    pregnancies.requestFocus();
                    return;
                }

                if( TextUtils.isEmpty(glucose.getText().toString()))
                {
                    glucose.setError("Please enter valid input");
                    glucose.requestFocus();
                    return;
                }


                if( TextUtils.isEmpty(BP.getText().toString()))
                {
                    BP.setError("Please enter valid input");
                    BP.requestFocus();
                    return;
                }


                if( TextUtils.isEmpty(skinThickness.getText().toString()))
                {
                    skinThickness.setError("Please enter valid input");
                    skinThickness.requestFocus();
                    return;
                }

                if( TextUtils.isEmpty(insulin.getText().toString()))
                {
                    insulin.setError("Please enter valid input");
                    insulin.requestFocus();
                    return;
                }


                if(TextUtils.isEmpty(BMI.getText().toString()))
                {
                    BMI.setError("Please enter valid input");
                    BMI.requestFocus();
                    return;
                }


                if( TextUtils.isEmpty(DPF.getText().toString()))
                {
                    DPF.setError("Please enter valid input");
                    DPF.requestFocus();
                    return;
                }


                if(TextUtils.isEmpty(age.getText().toString()))
                {
                    age.setError("Please enter valid input");
                    age.requestFocus();
                    return;
                }

                else
                {

                    ShareData();

                }


            }
        });


        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if( TextUtils.isEmpty(pregnancies.getText().toString()))
                {
                    pregnancies.setError("Please enter valid input");
                    pregnancies.requestFocus();
                    return;
                }

                if( TextUtils.isEmpty(glucose.getText().toString()))
                {
                    glucose.setError("Please enter valid input");
                    glucose.requestFocus();
                    return;
                }


                if( TextUtils.isEmpty(BP.getText().toString()))
                {
                    BP.setError("Please enter valid input");
                    BP.requestFocus();
                    return;
                }


                if( TextUtils.isEmpty(skinThickness.getText().toString()))
                {
                    skinThickness.setError("Please enter valid input");
                    skinThickness.requestFocus();
                    return;
                }

                if( TextUtils.isEmpty(insulin.getText().toString()))
                {
                    insulin.setError("Please enter valid input");
                    insulin.requestFocus();
                    return;
                }


                if(TextUtils.isEmpty(BMI.getText().toString()))
                {
                    BMI.setError("Please enter valid input");
                    BMI.requestFocus();
                    return;
                }


                if( TextUtils.isEmpty(DPF.getText().toString()))
                {
                    DPF.setError("Please enter valid input");
                    DPF.requestFocus();
                    return;
                }


                if(TextUtils.isEmpty(age.getText().toString()))
                {
                    age.setError("Please enter valid input");
                    age.requestFocus();
                    return;
                }

                else
                {

                    Calculate();

                }






            }
        });



    }

    private void ShareData() {

        float v1 = Float.parseFloat(pregnancies.getText().toString());
        float v2 = Float.parseFloat(glucose.getText().toString());
        float v3 = Float.parseFloat(BP.getText().toString());
        float v4 = Float.parseFloat(skinThickness.getText().toString());
        float v5 = Float.parseFloat(insulin.getText().toString());
        float v6 = Float.parseFloat(BMI.getText().toString());
        float v7 = Float.parseFloat(DPF.getText().toString());
        float v8 = Float.parseFloat(age.getText().toString());

        int value = Integer.parseInt(result.getText().toString());



        if((v1 < 0) || (v1 > 17))
        {
            pregnancies.setError("Please enter valid input");
            pregnancies.requestFocus();
            return;
        }

        if(v2<0 || v2>199)
        {
            glucose.setError("Please enter valid input");
            glucose.requestFocus();
            return;
        }


        if(v3<0 || v3>122)
        {
            BP.setError("Please enter valid input");
            BP.requestFocus();
            return;
        }


        if(v4<0 || v4>99)
        {
            skinThickness.setError("Please enter valid input");
            skinThickness.requestFocus();
            return;
        }

        if(v5<0 || v5>846)
        {
            insulin.setError("Please enter valid input");
            insulin.requestFocus();
            return;
        }


        if(v6<0 || v6>67.1)
        {
            BMI.setError("Please enter valid input");
            BMI.requestFocus();
            return;
        }


        if(v7<0.07 || v7>2.42)
        {
            DPF.setError("Please enter valid input");
            DPF.requestFocus();
            return;
        }


        if(v8<21 || v8>81)
        {
            age.setError("Please enter valid input");
            age.requestFocus();
            return;
        }


        if(value<0 || value>1)
        {
            result.setError("Please enter valid input");
            result.requestFocus();
            return;
        }

        else
        {
            Calendar callForDate = Calendar.getInstance();
            SimpleDateFormat currentDate = new SimpleDateFormat("dd-MMMM-yyyy");
            String savecurrentdate = currentDate.format(callForDate.getTime());

            Calendar callForTime = Calendar.getInstance();
            SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm");
            String savecurrentTime = currentTime.format(callForTime.getTime());

            String postrandomName = savecurrentdate + savecurrentTime;


            HashMap userMap = new HashMap();
            userMap.put("pregnancies",v1);
            userMap.put("glucose",v2);
            userMap.put("BP",v3);
            userMap.put("skinThickness",v4);
            userMap.put("insulin",v5);
            userMap.put("BMI",v6);
            userMap.put("DPF",v7);
            userMap.put("age",v8);
            userMap.put("result",value);


            UsersRef.child(postrandomName).updateChildren(userMap).addOnCompleteListener(new OnCompleteListener() {
                @Override
                public void onComplete(@NonNull Task task) {

                    if(task.isSuccessful())
                    {
                        Toast.makeText(DrpWork.this, "Your data is shared", Toast.LENGTH_LONG).show();

                    }
                    else
                    {
                        String message = task.getException().getMessage();
                        Toast.makeText(DrpWork.this, "Error Occured" + " " + message, Toast.LENGTH_LONG).show();

                    }
                }
            });


        }

    }

    private void Calculate()
    {

        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(8*4);

        float v1 = Float.parseFloat(pregnancies.getText().toString());
        float v2 = Float.parseFloat(glucose.getText().toString());
        float v3 = Float.parseFloat(BP.getText().toString());
        float v4 = Float.parseFloat(skinThickness.getText().toString());
        float v5 = Float.parseFloat(insulin.getText().toString());
        float v6 = Float.parseFloat(BMI.getText().toString());
        float v7 = Float.parseFloat(DPF.getText().toString());
        float v8 = Float.parseFloat(age.getText().toString());



        if((v1 < 0) || (v1 > 17))
        {
            pregnancies.setError("Please enter valid input");
            pregnancies.requestFocus();
            return;
        }

        if(v2<0 || v2>199)
        {
            glucose.setError("Please enter valid input");
            glucose.requestFocus();
            return;
        }


        if(v3<0 || v3>122)
        {
            BP.setError("Please enter valid input");
            BP.requestFocus();
            return;
        }


        if(v4<0 || v4>99)
        {
            skinThickness.setError("Please enter valid input");
            skinThickness.requestFocus();
            return;
        }

        if(v5<0 || v5>846)
        {
            insulin.setError("Please enter valid input");
            insulin.requestFocus();
            return;
        }


        if(v6<0 || v6>67.1)
        {
            BMI.setError("Please enter valid input");
            BMI.requestFocus();
            return;
        }


        if(v7<0.07 || v7>2.42)
        {
            DPF.setError("Please enter valid input");
            DPF.requestFocus();
            return;
        }


        if(v8<21 || v8>81)
        {
            age.setError("Please enter valid input");
            age.requestFocus();
            return;
        }

        else
        {

            byteBuffer.putFloat(v1);
            byteBuffer.putFloat(v2);
            byteBuffer.putFloat(v3);
            byteBuffer.putFloat(v4);
            byteBuffer.putFloat(v5);
            byteBuffer.putFloat(v6);
            byteBuffer.putFloat(v7);
            byteBuffer.putFloat(v8);



//        byteBuffer.putFloat((int) Float.parseFloat(pregnancies.getText().toString()));
//        byteBuffer.putFloat((int) Float.parseFloat(glucose.getText().toString()));
//        byteBuffer.putFloat((int) Float.parseFloat(BP.getText().toString()));
//        byteBuffer.putFloat((int) Float.parseFloat(skinThickness.getText().toString()));
//        byteBuffer.putFloat((int) Float.parseFloat(insulin.getText().toString()));
//        byteBuffer.putFloat((int) Float.parseFloat(BMI.getText().toString()));
//        byteBuffer.putFloat((int) Float.parseFloat(DPF.getText().toString()));
//        byteBuffer.putFloat((int) Float.parseFloat(age.getText().toString()));

            Dt model = null;
            try {
                model = Dt.newInstance(getApplicationContext());
            } catch (IOException e) {
                e.printStackTrace();
            }


            // Creates inputs for reference.
            TensorBuffer inputFeature0 = TensorBuffer.createFixedSize(new int[]{1, 8}, DataType.FLOAT32);
            inputFeature0.loadBuffer(byteBuffer);

            // Runs model inference and gets result.
            assert model != null;
            Dt.Outputs outputs = model.process(inputFeature0);
            TensorBuffer outputFeature0;
            outputFeature0 = outputs.getOutputFeature0AsTensorBuffer();

//            Toast.makeText(this, "Calculate Button is clicked", Toast.LENGTH_SHORT).show();
            linearLayout.setVisibility(View.VISIBLE);
//            linearLayout.setFocusable(true);
            linearLayout.requestFocus();


            String value = String.valueOf(outputFeature0.getFloatArray()[0]);

            output.setText(value);
//            output.setFocusable(true);
//            output.requestFocus();

            Calendar callForDate = Calendar.getInstance();
            SimpleDateFormat currentDate = new SimpleDateFormat("dd-MMMM-yyyy");
            String savecurrentdate = currentDate.format(callForDate.getTime());

            Calendar callForTime = Calendar.getInstance();
            SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm");
            String savecurrentTime = currentTime.format(callForTime.getTime());

            String postrandomName = savecurrentdate + savecurrentTime;


            HashMap userMap = new HashMap();
            userMap.put("pregnancies",v1);
            userMap.put("glucose",v2);
            userMap.put("BP",v3);
            userMap.put("skinThickness",v4);
            userMap.put("insulin",v5);
            userMap.put("BMI",v6);
            userMap.put("DPF",v7);
            userMap.put("age",v8);
            userMap.put("result",value);


            UsersRef.child(postrandomName).updateChildren(userMap).addOnCompleteListener(new OnCompleteListener() {
                @Override
                public void onComplete(@NonNull Task task) {

                    if(task.isSuccessful())
                    {
                        Toast.makeText(DrpWork.this, "Your data is ready", Toast.LENGTH_LONG).show();

                    }
                    else
                    {
                        String message = task.getException().getMessage();
                        Toast.makeText(DrpWork.this, "Error Occured" + " " + message, Toast.LENGTH_LONG).show();

                    }
                }
            });


//        Toast.makeText(this, "Calculation "+ value, Toast.LENGTH_LONG).show();

            // Releases model resources if no longer used.
            model.close();




        }




    }
}
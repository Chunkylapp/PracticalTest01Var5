package ro.pub.cs.systems.eim.practicaltest01var05;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class PracticalTest01Var05MainActivity extends AppCompatActivity {

    private final Integer SERVICE_START = 4;

    Button center, topLeft, topRight, bottomLeft, bottomRight, moveToSecondaryActivity;

    EditText textView;

    private String outputConcatText = "";

    private Integer numberOfClicks = 0;

    private ActivityResultLauncher myActivityResultLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test_01_var05_main);

        center = findViewById(R.id.center);
        topLeft = findViewById(R.id.topLeft);
        topRight = findViewById(R.id.topRight);
        bottomLeft = findViewById(R.id.bottomLeft);
        bottomRight = findViewById(R.id.bottomRight);

        moveToSecondaryActivity = findViewById(R.id.moveToSecondaryActivity);

        textView = findViewById(R.id.outputTextView);

        if (savedInstanceState != null) {
            outputConcatText = savedInstanceState.getString("outputConcatText", "");
            textView.setText(outputConcatText);
            numberOfClicks = savedInstanceState.getInt("numberOfClicks", 0);
        }

        center.setOnClickListener(view -> {
            outputConcatText += ",Center ";
            textView.setText(outputConcatText);

            numberOfClicks++;

            if(numberOfClicks.equals(SERVICE_START)) {
                startService();
            }

        });

        topLeft.setOnClickListener(view -> {
            outputConcatText += ",Top Left ";
            textView.setText(outputConcatText);

            numberOfClicks++;

            if(numberOfClicks.equals(SERVICE_START)) {
                startService();
            }

        });

        topRight.setOnClickListener(view -> {
            outputConcatText += ",Top Right ";
            textView.setText(outputConcatText);

            numberOfClicks++;

            if(numberOfClicks.equals(SERVICE_START)) {
                startService();
            }

        });

        bottomLeft.setOnClickListener(view -> {
            outputConcatText += ",Bottom Left ";
            textView.setText(outputConcatText);

            numberOfClicks++;

            if(numberOfClicks.equals(SERVICE_START)) {
                startService();
            }

        });

        bottomRight.setOnClickListener(view -> {
            outputConcatText += ",Bottom Right ";
            textView.setText(outputConcatText);

            numberOfClicks++;

            if(numberOfClicks.equals(SERVICE_START)) {
                startService();
            }
        });

        moveToSecondaryActivity.setOnClickListener(view -> {
            Intent intent = new Intent(this, activity_practical_test01_var05_secondary.class);
            intent.putExtra("numberOfClicks", numberOfClicks);
            intent.putExtra("outputConcatText", outputConcatText);

            myActivityResultLauncher.launch(intent);
        });

        myActivityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
            if (result.getResultCode() == RESULT_OK) {
                Intent intent = result.getData();
                if (intent != null) {
                    numberOfClicks = intent.getIntExtra("numberOfClicks", 0);
                    textView.setText(outputConcatText);
                }
                Toast.makeText(this, "The verify button was pressed, result was: " + numberOfClicks, Toast.LENGTH_LONG).show();

                // Reset the values
                numberOfClicks = 0;
                outputConcatText = "";

                textView.setText(outputConcatText);
            }
            else
            {
                Toast.makeText(this, "The cancel button was pressed, there was no result :(", Toast.LENGTH_LONG).show();
            }
        });

    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("outputConcatText", outputConcatText);
        outState.putInt("numberOfClicks", numberOfClicks);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        outputConcatText = savedInstanceState.getString("outputConcatText", "");
        textView.setText(outputConcatText);
        numberOfClicks = savedInstanceState.getInt("numberOfClicks", -1);

        if(numberOfClicks != -1) {
            Toast.makeText(this, "Number of clicks: " + numberOfClicks, Toast.LENGTH_LONG).show();
        }
    }

    private void startService() {
        Intent intent = new Intent(this, PracticalTest01Var05Service.class);
        intent.putExtra("data", outputConcatText);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            startForegroundService(intent);
        }
        else {
            startService(intent);
        }
    }

    @Override
    protected void onDestroy() {
        Intent intent = new Intent(this, PracticalTest01Var05Service.class);
        stopService(intent);
        super.onDestroy();
    }

}
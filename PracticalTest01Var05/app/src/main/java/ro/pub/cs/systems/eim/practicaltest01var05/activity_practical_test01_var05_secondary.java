package ro.pub.cs.systems.eim.practicaltest01var05;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class activity_practical_test01_var05_secondary extends AppCompatActivity {

    Button verify, cancel;

    EditText theTextField;

    Integer numberOfClicks = 0;

    String outputConcatText = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_var05_secondary);

        verify = findViewById(R.id.verifyButton);
        cancel = findViewById(R.id.cancelButton);

        theTextField = findViewById(R.id.theTextField);

        numberOfClicks = getIntent().getIntExtra("numberOfClicks", 0);
        outputConcatText = getIntent().getStringExtra("outputConcatText");

        theTextField.setText(outputConcatText);

        verify.setOnClickListener(view -> {
            Intent intent = new Intent();
            intent.putExtra("numberOfClicks", numberOfClicks * 2);
            setResult(RESULT_OK, intent);
            finish();
        });

        cancel.setOnClickListener(view -> {
            setResult(RESULT_CANCELED, new Intent());
            finish();
        });
    }
}
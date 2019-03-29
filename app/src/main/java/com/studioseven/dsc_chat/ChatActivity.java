package com.studioseven.dsc_chat;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import nl.dionsegijn.konfetti.KonfettiView;
import nl.dionsegijn.konfetti.models.Shape;
import nl.dionsegijn.konfetti.models.Size;

public class ChatActivity extends AppCompatActivity {

    EditText messageBox;
    KonfettiView viewKonfetti;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        messageBox = findViewById(R.id.messageBox);
        viewKonfetti = findViewById(R.id.viewKonfetti);

    }

    public void onSendClick(View view) {
        String message = String.valueOf(messageBox.getText());

        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
        messageBox.setText("");

        if(message.toLowerCase().equals("confetti")){
            confetti();
        }

        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(messageBox.getWindowToken(), 0);
    }

    private void confetti() {
        viewKonfetti.build()
                .addColors(Color.YELLOW, Color.GREEN, Color.MAGENTA)
                .setDirection(0.0, 359.0)
                .setSpeed(1f, 5f)
                .setFadeOutEnabled(true)
                .setTimeToLive(2000L)
                .addShapes(Shape.RECT, Shape.CIRCLE)
                .addSizes(new Size(12, 5))
                .setPosition(-50f, viewKonfetti.getWidth() + 50f, -50f, -50f)
                .streamFor(300, 4000L);
    }
}

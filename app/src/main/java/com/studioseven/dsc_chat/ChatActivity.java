package com.studioseven.dsc_chat;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

import nl.dionsegijn.konfetti.KonfettiView;
import nl.dionsegijn.konfetti.models.Shape;
import nl.dionsegijn.konfetti.models.Size;

public class ChatActivity extends AppCompatActivity {

    EditText messageBox;
    KonfettiView viewKonfetti;

    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        db = FirebaseFirestore.getInstance();

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

        putUser();

        hideKeyboard();
    }

    private void getUser() {
        db.collection("users").document("7vzSDJwtAzZe4mpzftbw")
                .get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            /*for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d("FB", document.getId() + " => " + document.getData());
                            }*/
                            DocumentSnapshot document = task.getResult();
                            Log.d("FB", document.getId() + " => " + document.getData());
                        } else {
                            Log.w("FB", "Error getting documents.", task.getException());
                        }
                    }
                });
    }

    private void putUser() {
        // Create a new user with a first and last name
        Map<String, Object> user = new HashMap<>();
        user.put("first", "Alan");
        user.put("middle", "Mathison");
        user.put("last", "Turing");
        user.put("born", 1912);


        // Add a new document with a generated ID
        db.collection("users")
                .add(user)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d("FB", "DocumentSnapshot added with ID: " + documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("FB", "Error adding document", e);
                    }
                });
    }

    private void hideKeyboard() {
        // Got it from StackOverflow.com
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

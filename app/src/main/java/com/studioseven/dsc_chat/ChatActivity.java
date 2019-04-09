package com.studioseven.dsc_chat;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.studioseven.dsc_chat.Adapters.ChatAdapter;
import com.studioseven.dsc_chat.Models.Chat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import nl.dionsegijn.konfetti.KonfettiView;
import nl.dionsegijn.konfetti.models.Shape;
import nl.dionsegijn.konfetti.models.Size;

public class ChatActivity extends AppCompatActivity {

    EditText messageBox;
    KonfettiView viewKonfetti;

    RecyclerView messageListView;

    private ChatAdapter chatAdapter;
    private RecyclerView.LayoutManager layoutManager;

    FirebaseFirestore db;

    ArrayList<Chat> chatList;

    private void bindViews() {
        messageBox = findViewById(R.id.messageBox);
        viewKonfetti = findViewById(R.id.viewKonfetti);
        messageListView = findViewById(R.id.messageListView);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        setTitle((CharSequence) getIntent().getExtras().get("name"));

        db = FirebaseFirestore.getInstance();

        bindViews();

        setUpRecyclerView();
    }

    private void setUpRecyclerView() {
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        messageListView.setHasFixedSize(true);

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        ((LinearLayoutManager) layoutManager).setStackFromEnd(true);
        //((LinearLayoutManager) layoutManager).setReverseLayout(true);

        messageListView.setLayoutManager(layoutManager);

        chatList = new ArrayList<>();
        for(int i=0; i<20; i++) chatList.add(new Chat("YOLO", "9:1" + String.valueOf(i % 10) + "PM"));

        // specify an adapter (see also next example)
        chatAdapter = new ChatAdapter(chatList);
        messageListView.setAdapter(chatAdapter);

        messageListView.smoothScrollToPosition(chatAdapter.getItemCount());

        messageListView.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View v,
                                       int left, int top, int right, int bottom,
                                       int oldLeft, int oldTop, int oldRight, int oldBottom) {
                if (bottom < oldBottom) {
                    messageListView.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            messageListView.smoothScrollToPosition(
                                    messageListView.getAdapter().getItemCount() - 1);
                        }
                    }, 100);
                }
            }
        });
    }

    public void onSendClick(View view) {
        String message = String.valueOf(messageBox.getText());

        messageBox.setText("");

        if(message.toLowerCase().equals("confetti")){
            confetti();
        }

        chatAdapter.addMessage(new Chat(message, "9:12PM"));
        messageListView.smoothScrollToPosition(chatAdapter.getItemCount()); // <= use this.

        //putUser();

        //hideKeyboard();
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

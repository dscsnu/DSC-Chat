package com.studioseven.dsc_chat;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.studioseven.dsc_chat.Adapters.UserAdapter;
import com.studioseven.dsc_chat.Models.User;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView userRv;
    ArrayList<User> userList;

    private UserAdapter userAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bindViews();

        setUpRv();
    }

    private void bindViews() {
        userRv = findViewById(R.id.userRv);
    }

    private void setUpRv() {
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        userRv.setHasFixedSize(true);

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);

        userRv.setLayoutManager(layoutManager);

        userList = new ArrayList<>();
        userList.add(new User("Rajeev Singh", "In Meeting", "https://cse.snu.edu.in/sites/default/files/styles/faculty_profile_images_172x241/public/images/people/Rajeev%20pic%2017012017.jpg?itok=aDrA6fOP"));
        userList.add(new User("Sonia Khetarpaul", "Available", "https://cse.snu.edu.in/sites/default/files/styles/faculty_profile_images_172x241/public/images/people/Sonia-CSE.jpg?itok=eN6j0Sfw"));
        userList.add(new User("Pooja Malik", "GFG4LYF", "https://cse.snu.edu.in/sites/default/files/styles/faculty_profile_images_172x241/public/images/people/1.jpg?itok=0h3wi1Mb"));
        userList.add(new User("Santosh Singh", "Bored", "https://cse.snu.edu.in/sites/default/files/styles/faculty_profile_images_172x241/public/images/people/santosh-math.jpg?itok=kTwhsu15"));
        userList.add(new User("Snigdha Biswas", "Online", "https://cse.snu.edu.in/sites/default/files/styles/faculty_profile_images_172x241/public/images/people/IMG_47691.jpg?itok=_mO__t9A"));
        userList.add(new User("Niteesh Sahni", "...", "https://cse.snu.edu.in/sites/default/files/styles/faculty_profile_images_172x241/public/images/people/Niteesh-Sahni-Math.jpg?itok=Q6Y5SsEN"));
        userList.add(new User("Debopam Acharya", "On Vacation", "https://cse.snu.edu.in/sites/default/files/styles/faculty_profile_images_172x241/public/images/people/2016/Aug/04/Debopam%20Acharya.jpg?itok=8BeT4FmX"));
        userList.add(new User("Karmeshu", "Modelling", "https://cse.snu.edu.in/sites/default/files/styles/faculty_profile_images_172x241/public/images/people/ProfKarmeshe.jpg?itok=8K6s1iEr"));
        userList.add(new User("Divya Lohani", "Busy", "https://cse.snu.edu.in/sites/default/files/styles/faculty_profile_images_172x241/public/images/people/Divya-Lohani-computer-sc._0.jpg?itok=tFDJAuvI"));
        userList.add(new User("Dolly Sharma", "Busy", "https://cse.snu.edu.in/sites/default/files/styles/faculty_profile_images_172x241/public/images/people/IMG_4630.jpg?itok=RHP6fIbn"));

        // specify an adapter (see also next example)
        userAdapter = new UserAdapter(userList, this);
        userRv.setAdapter(userAdapter);
    }
}

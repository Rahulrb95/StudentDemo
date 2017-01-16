package com.example.vishal.mypersonalchat2;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.LinkedList;
import java.util.List;

public class ChatActivity extends AppCompatActivity {

    Button mBtnMessage;
    EditText mEdtChat;
    ListView mLstMessage;

    List<ChatMessage> messages;
    ArrayAdapter<ChatMessage> chatMessageArrayAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        mBtnMessage = (Button) findViewById(R.id.btn_message);
        mEdtChat = (EditText) findViewById(R.id.edt_chat);
        mLstMessage = (ListView) findViewById(R.id.lis_chat);


        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference("Chat");

        mBtnMessage.setOnClickListner(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ChatMessage chatMessage = new ChatMessage("rahul",mEdtChat.getText().toString());
                myRef.push().setValue(chatMessage);
            }
        });

        messages = new LinkedList<>();
        chatMessageArrayAdapter=new ArrayAdapter<ChatMessage>(this,android.R.layout.two_line_list_item,messages);

        {
            @NonNull
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {

            if(convertView==null)
            {
                convertView = getLayoutInflater().inflate
                        (android.R.layout.two_line_list_item,parent,false);

                ChatMessage chatMessage = messages.get(position);

                ((TextView)convertView.findViewById(android.R.id.text1)).setText(chatMessage.getName());
                ((TextView)convertView.findViewById(android.R.id.text2)).setText(chatMessage.getMymessage());


            }

            return convertView;
        }
        };

        mLstMessage.setAdapter(chatMessageArrayAdapter);




        myRef.addChildEventListener(new ChildEventListener()
        {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s)
            {
                //messages.clear();
                ChatMessage chatMessage = dataSnapshot.getValue(ChatMessage.class);
                messages.add(chatMessage);
                chatMessageArrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s)
            {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot)
            {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s)
            {

            }

            @Override
            public void onCancelled(DatabaseError databaseError)
            {

            }
        });

    }
}

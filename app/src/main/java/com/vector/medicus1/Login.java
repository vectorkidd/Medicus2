package com.vector.medicus1;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Login extends AppCompatActivity {
    FirebaseAuth mfirebaseAuth;
    FirebaseAuth.AuthStateListener mAuthStateListener;
    private static final String TAG = "MainActivity";

    public static final String ANONYMOUS = "anonymous";
    public static final int DEFAULT_MSG_LENGTH_LIMIT = 1000;
    public static final int RC_SIGN_IN=1;
    private ListView mMessageListView;
    private MessageAdapter mMessageAdapter;
    private ProgressBar mProgressBar;
    private ImageButton mPhotoPickerButton;
    private EditText mMessageEditText;
    private Button mSendButton;

    private String mUsername;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    private ChildEventListener childListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mUsername=ANONYMOUS;
        firebaseDatabase=FirebaseDatabase.getInstance();
        mfirebaseAuth = FirebaseAuth.getInstance();
        databaseReference=firebaseDatabase.getReference().child("messages");
        mProgressBar = (ProgressBar) findViewById(R.id.progress);
        mMessageListView = (ListView) findViewById(R.id.listView);

        mMessageEditText = (EditText) findViewById(R.id.message);
        mSendButton = (Button) findViewById(R.id.sendbtn);
        final List<ItemMessage> friendlyMessages = new ArrayList<>();
        mMessageAdapter = new MessageAdapter(this, R.layout.item_message, friendlyMessages);
        mMessageListView.setAdapter(mMessageAdapter);

        // Initialize progress bar
        mProgressBar.setVisibility(ProgressBar.INVISIBLE);
        mMessageEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.toString().trim().length() > 0) {
                    mSendButton.setEnabled(true);
                } else {
                    mSendButton.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
        mMessageEditText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(DEFAULT_MSG_LENGTH_LIMIT)});

        // Send button sends a message and clears the EditText
        mSendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO: Send messages on click
                ItemMessage itemMessage=new ItemMessage(mMessageEditText.getText().toString(),mUsername);
                databaseReference.push().setValue(itemMessage);
                // Clear input box
                mMessageEditText.setText("");
            }
        });


        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    onSignedInInitialize(user.getDisplayName());

                } else {
                    onSignedOutCleanup();
                    startActivityForResult(
                            AuthUI.getInstance()
                                    .createSignInIntentBuilder()
                                    .setIsSmartLockEnabled(false)
                                    .setAvailableProviders(
                                            Arrays.asList(new AuthUI.IdpConfig.Builder(AuthUI.EMAIL_PROVIDER).build()
                                            ))
                                    .build(),
                            RC_SIGN_IN);
                }
            }
        };
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()){
            case R.id.sign_out_menu:
                AuthUI.getInstance().signOut(this);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void onActivityResult(int requestcode, int resultcode, Intent data) {
        super.onActivityResult(requestcode, resultcode, data);
        if (requestcode == RC_SIGN_IN) {
            if (resultcode == RESULT_OK) {
                Toast.makeText(Login.this, "Signed in!", Toast.LENGTH_SHORT).show();
                Intent il=new Intent(Login.this,Choose.class);
                startActivity(il);
            } else if (resultcode == RESULT_CANCELED) {
                Toast.makeText(this, "SIgn in Cancelled", Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    }

    public void onResume(){
        super.onResume();
        mfirebaseAuth.addAuthStateListener(mAuthStateListener);
    }

    public void onPause(){
        super.onPause();
        if(mAuthStateListener!=null) {
            mfirebaseAuth.removeAuthStateListener(mAuthStateListener);
        }
        detachdatabasereadlistener();
        mMessageAdapter.clear();
    }
    void onSignedInInitialize(String username){
        mUsername=username;
        attachdatabasereadlistener();

    }
    void onSignedOutCleanup(){
        mUsername=ANONYMOUS;
        mMessageAdapter.clear();
        detachdatabasereadlistener();
    }

    void attachdatabasereadlistener(){
        if(childListener==null){
            childListener=new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    ItemMessage itemMessage=dataSnapshot.getValue(ItemMessage.class);
                    mMessageAdapter.add(itemMessage);
                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onChildRemoved(DataSnapshot dataSnapshot) {

                }

                @Override
                public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            };
            databaseReference.addChildEventListener(childListener);
        }}
    void detachdatabasereadlistener(){
        if(childListener!=null){
            databaseReference.removeEventListener(childListener);
            childListener=null;
        }
    }
}


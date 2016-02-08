package firebase.uf2multimediadiegoz;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.firebase.client.Firebase;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    private static final String URL_FIREBASE = "https://uf2multimediadiegoz.firebaseio.com/";
    private EditText etTitle;
    private EditText etDescription;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
//        tvMessage = (TextView) rootView.findViewById(R.id.tvMessage);
        Firebase.setAndroidContext(getContext());
        final Firebase firebase = new Firebase(URL_FIREBASE);

        final Firebase firebaseNote = firebase.child("notes");

        etTitle = (EditText) rootView.findViewById(R.id.etTitle);
        etDescription = (EditText) rootView.findViewById(R.id.etDescription);
        Button buttAddNote = (Button) rootView.findViewById(R.id.buttAddNote);
        buttAddNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //...
                firebaseNote.push().setValue(new Note(etTitle.getText().toString(), etDescription.getText().toString()));
            }
        });

//        alanRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot snapshot) {
//                System.out.println(snapshot.getValue());
////                tvMessage.setText(snapshot.getValue().toString());
//            }
//            @Override
//            public void onCancelled(FirebaseError firebaseError) {
//                System.out.println("The read failed: " + firebaseError.getMessage());
//            }
//        });

        return rootView;

    }




}

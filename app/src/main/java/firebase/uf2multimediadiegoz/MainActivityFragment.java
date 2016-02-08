package firebase.uf2multimediadiegoz;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    TextView tvMessage;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        tvMessage = (TextView) rootView.findViewById(R.id.tvMessage);
        Firebase.setAndroidContext(getContext());
        Firebase firebase = new Firebase("https://uf2multimediadiegoz.firebaseio.com/");
        firebase.child("mensajeee").setValue("Hola Firebase");

        Firebase alanRef = firebase.child("users").child("-K983VDi09Ikm0t358kR");
        User alan = new User("Alan Turing", 1912);
        //alanRef.setValue(alan);

        alanRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                System.out.println(snapshot.getValue());
                tvMessage.setText(snapshot.getValue().toString());
            }
            @Override
            public void onCancelled(FirebaseError firebaseError) {
                System.out.println("The read failed: " + firebaseError.getMessage());
            }
        });
        return rootView;

    }
}

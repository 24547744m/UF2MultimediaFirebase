package firebase.uf2multimediadiegoz;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.Firebase;

/**
 * A placeholder fragment containing a simple view.
 */
public class AddNoteActivityFragment extends Fragment {

    private static final String URL_FIREBASE = "https://uf2multimediadiegoz.firebaseio.com/";
    private EditText etTitle;
    private EditText etDescription;
    private Button buttAddNote;
    private LocationManager locationManager;
    private Location location;

    public AddNoteActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        locationManager = (LocationManager) getContext().getSystemService(getContext().LOCATION_SERVICE);
        View rootView = inflater.inflate(R.layout.fragment_add_note, container, false);

        etTitle = (EditText) rootView.findViewById(R.id.etTitle);
        etDescription = (EditText) rootView.findViewById(R.id.etDescription);
        buttAddNote = (Button) rootView.findViewById(R.id.buttAddNote);

        addNewNote();//Agregar nueva nota

        return rootView;
    }

    private void addNewNote() {
        Firebase.setAndroidContext(getContext());

        Firebase firebase = new Firebase(URL_FIREBASE);
        final Firebase firebaseNote = firebase.child("notes");


        buttAddNote.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //...
                boolean gpsStatus = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
                boolean networkStatus = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);

                Note note = new Note(etTitle.getText().toString(),
                        etDescription.getText().toString(),
                        "",
                        "",
                        ""
                );

                if (gpsStatus && networkStatus) {//comprobando si hay permiso a internet y el gps esta activado
                    if (ActivityCompat.checkSelfPermission(getContext(),
                            Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                            && ActivityCompat.checkSelfPermission(getContext(),
                            Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                        // TODO: Consider calling
                        //    ActivityCompat#requestPermissions
                        // here to request the missing permissions, and then overriding
                        //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                        //                                          int[] grantResults)
                        // to handle the case where the user grants the permission. See the documentation
                        // for ActivityCompat#requestPermissions for more details.
                        return;
                    }
                    location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                    note.setLatitude(String.valueOf(location.getLatitude()));
                    note.setLongitude(String.valueOf(location.getLongitude()));
                }

                firebaseNote.push().setValue(note);

                Toast.makeText(getContext(), "Nota agregada", Toast.LENGTH_SHORT).show();
            }
        });
    }


}

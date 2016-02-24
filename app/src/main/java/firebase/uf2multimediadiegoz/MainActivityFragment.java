package firebase.uf2multimediadiegoz;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.client.Firebase;
import com.firebase.ui.FirebaseListAdapter;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    private static final String URL_FIREBASE = "https://uf2multimediadiegoz.firebaseio.com/";
    private ListView lvNotes;
    private FirebaseListAdapter firebaseAdapter;
    private TextView tvNoteTitle;
    private TextView tvNoteDescription;

    public MainActivityFragment() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        lvNotes = (ListView) rootView.findViewById(R.id.lvNotes);

        Firebase.setAndroidContext(getContext());
        final Firebase firebase = new Firebase(URL_FIREBASE);
        final Firebase firebaseNotes = firebase.child("notes");

        firebaseAdapter = new FirebaseListAdapter(getActivity(), Note.class, R.layout.note_row, firebaseNotes) {
            @Override
            public Object getItem(int i) {
                return super.getItem(i);
            }

            @Override
            protected void populateView(View v, Object model, int position) {
//                super.populateView(v, model, position);
                tvNoteTitle = (TextView) v.findViewById(R.id.tvNoteTitle);
                tvNoteDescription = (TextView) v.findViewById(R.id.tvNoteDescription);

                Note note = (Note) getItem(position);
//                Log.d(note.getTitle(), "titleeeeeeeeeeeeee");
                tvNoteTitle.setText( (note.getTitle() != null) ? note.getTitle() : "");
                tvNoteDescription.setText( (note.getDescription() != null) ? note.getDescription() : "");
            }
        };

        lvNotes.setAdapter(firebaseAdapter);

        return rootView;
    }






}

package firebase.uf2multimediadiegoz;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private String pathFoto;
    private static final int IMAGE_SAVE = 2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton buttAddNewNote = (FloatingActionButton) findViewById(R.id.buttAddNewNote);
        buttAddNewNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(view.getContext(), AddNoteActivity.class);
                startActivity(intent);
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Toast.makeText(this, "Settingss..", Toast.LENGTH_SHORT).show();
            return true;

        }else if (id == R.id.makePicture){//haciendo la foto
            menuTakePhoto();
        }

        return super.onOptionsItemSelected(item);
    }


    private void menuTakePhoto() {
        Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (i.resolveActivity(getPackageManager()) != null) {
            File f;
            f = createImageFile();//llamando al metodo local para crear un archivo

            if (f != null) {//si no es nulo...
                i.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(f));
                startActivityForResult(i, IMAGE_SAVE);
            }
        }

    }

    public File createImageFile() {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss")
                .format(new Date());
        String fileName = "JPEG_" + timeStamp + "_";
        File storageDir = Environment
                .getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);//o directorio "DIRECTORY_DCIM"

        File image;
        try {
            image = File.createTempFile(fileName, ".jpg", storageDir);
            pathFoto = "file:" + image.getAbsolutePath();
            return image;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == IMAGE_SAVE && resultCode == RESULT_OK){
            Log.d("RESULT OK!!", "");
            Intent i = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
            File f = new File(pathFoto);
            Log.d("PATH FOTO: ", pathFoto);
            Uri uri = Uri.fromFile(f);
            i.setData(uri);
            this.sendBroadcast(i);
        }

        super.onActivityResult(requestCode, resultCode, data);
    }
}

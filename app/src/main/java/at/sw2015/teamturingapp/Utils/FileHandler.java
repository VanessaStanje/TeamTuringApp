package at.sw2015.teamturingapp.Utils;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

import java.net.URISyntaxException;

public class FileHandler {

    // Simmilar to a method from a tutorial:
    // http://stackoverflow.com/questions/7856959/android-file-chooser
    public static String getPath(Context context, Uri uri) throws URISyntaxException {
        if ("content".equalsIgnoreCase(uri.getScheme())) {
            String[] projection = {"_data"};
            Cursor cursor;

            try {
                cursor = context.getContentResolver().query(uri, projection, null, null, null);
                int column_index = cursor.getColumnIndexOrThrow("_data");
                if (cursor.moveToFirst())
                    return cursor.getString(column_index);

                cursor.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if ("file".equalsIgnoreCase(uri.getScheme())) {
            return uri.getPath();
        }

        return null;
    }
}

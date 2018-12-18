package cd.acgt.acgtexp.utils;

import android.graphics.Bitmap;
import android.os.Environment;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Sugar on 9/26/2018
 */
public class FIleStorage {

    private static File storageDir = null;

    public static String SaveImage(Bitmap bitmap, String directory){

        if(bitmap == null) { return null; }

        String filename = null;
        FileOutputStream fOut = null;
        try {
            if (bitmap != null) {
                File file = new File(Constant.PHOTO_PATH);
                if (!file.exists()) {
                    file.mkdirs();
                }

                filename = directory + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + ".jpg";
                //new AndroidBmpUtil().save(bitmap, Constant.PHOTO_PATH + filename);
                File imageFile = new File(file, filename);
                try {
                    fOut = new FileOutputStream(imageFile);
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 80, fOut);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return filename;
            }
        }catch (Exception ex) {
            ex.printStackTrace();
        }
        finally {
            /*try {
                if (fOut != null) {
                    fOut.close();
                }
            }catch (Exception ex){ }*/
        }
        return null;
    }

    public static File GetStorageDir()
    {
        try {
            if (storageDir == null) {
                storageDir = new File(Environment.getExternalStoragePublicDirectory(
                        Environment.DIRECTORY_DCIM), "dpmer");

                if (!storageDir.exists()) {
                    storageDir.mkdirs();
                }
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return storageDir;
    }
}


package com.example.hinovaoficinas.utils;

import static com.example.hinovaoficinas.utils.Constants.IMG_PREFIX;
import static com.example.hinovaoficinas.utils.Constants.IMG_SUFFIX;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.widget.ImageView;

import com.koushikdutta.ion.Ion;

import java.io.FileOutputStream;
import java.io.IOException;

public class ImageManager {
    private final Context context;
    private int idWorkshop;

    public ImageManager(Context context) {
        this.context = context;
    }

    public void displayImage(String base64Image, ImageView imageView, int idWorkshop) {
        Bitmap bitmap = decodeBase64Image(base64Image);
        this.idWorkshop = idWorkshop;
        saveImage(bitmap);
        loadImageFromFile(imageView);
    }

    private Bitmap decodeBase64Image(String base64Image) {
        byte[] imageBytes = Base64.decode(base64Image, Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
    }

    private void saveImage(Bitmap bitmap) {
        String filename = IMG_PREFIX + idWorkshop + IMG_SUFFIX;
        try (FileOutputStream fos = context.openFileOutput(filename, Context.MODE_PRIVATE)) {
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadImageFromFile(ImageView imageView) {
        String filename = IMG_PREFIX + idWorkshop + IMG_SUFFIX;
        Ion.with(imageView)
                .load(String.valueOf(context.getFileStreamPath(filename)));
    }
}

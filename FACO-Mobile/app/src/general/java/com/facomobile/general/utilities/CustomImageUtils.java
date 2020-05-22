package com.facomobile.doctors.utilities;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class CustomImageUtils {

    public static final String TEMP_SIGNUP_PROFILE_PIC_NAME = "signup_temp_profile_pic.jpg";

    public static Bitmap getTempProfilePic(Context context) {
        File cacheImage = new File(context.getCacheDir(), TEMP_SIGNUP_PROFILE_PIC_NAME);
        Bitmap bitmap;
        try {
            bitmap = BitmapFactory.decodeStream(new FileInputStream(cacheImage));
            return bitmap;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String convertImageToBase64String(String imagePath) {
        Bitmap bm = BitmapFactory.decodeFile(imagePath);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.JPEG, 80, baos); //bm is the bitmap object
        byte[] byteArrayImage = baos.toByteArray();
        return Base64.encodeToString(byteArrayImage, Base64.NO_WRAP);
    }

    public static Bitmap convertBase64StringToImage(String base64String) {
        byte[] decodedString = Base64.decode(base64String, Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
    }

    /*public static Bitmap getUserProfilePic(Context context) {
        AppRepository repository = InjectorUtils.provideRepository(context);
        String uid = repository.getUid();
        if (uid == null) return null;
        return getProfilePic(context, uid);
    }

    public static Bitmap getProfilePic(Context context, @NonNull String uid) {
        File profilePicFile = new File(context.getFilesDir(), uid);
        Bitmap bitmap;
        try {
            bitmap = BitmapFactory.decodeStream(new FileInputStream(profilePicFile));
            return bitmap;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Bitmap getUserMainProfilePicThumbnail(Context context) {
        AppRepository repository = InjectorUtils.provideRepository(context);
        String uid = repository.getUid();
        if (uid == null) return null;
        File profilePicDir = context.getFilesDir();
        File profilePicFile = new File(profilePicDir, uid);
        return getFileThumbnail(profilePicFile, 80, 80);
    }

    private static Bitmap getFileThumbnail(File imageFile, int width, int height) {
        try {
            return ThumbnailUtils.extractThumbnail(BitmapFactory.decodeStream(new FileInputStream(imageFile)),
                    width, height, ThumbnailUtils.OPTIONS_RECYCLE_INPUT);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static Bitmap getBitmapThumbnail(Bitmap bitmap, int width, int height) {
        return ThumbnailUtils.extractThumbnail(bitmap, width, height, ThumbnailUtils.OPTIONS_RECYCLE_INPUT);
    }

    public static Bitmap getDefaultUserProfilePic(Context context) {
        return BitmapFactory.decodeResource(context.getResources(), R.drawable.default_profile_pic);
    }

    public static Bitmap getDefaultUserProfilePicThumbnail(Context context) {
        return getBitmapThumbnail(getDefaultUserProfilePic(context), 96, 96);
    }

    public static void fetchUserProfilePic(Context context) {
        AppRepository repository = InjectorUtils.provideRepository(context);
        String uid = repository.getUid();
        if (!TextUtils.isEmpty(uid))
            fetchProfilePic(context, uid);
    }

    public static void fetchProfilePic(Context context, @NonNull String uid) {
        AppRepository repository = InjectorUtils.provideRepository(context);
        File profilePicDir = context.getFilesDir();
        File profilePicFile = new File(profilePicDir, uid);
        repository.fetchFile(profilePicFile, uid);
    }

    public static void saveUserProfilePic(Context context, Uri imageUri) {
        AppRepository repository = InjectorUtils.provideRepository(context);
        String uid = repository.getUid();
        if (!TextUtils.isEmpty(uid)) {
            saveImageToStorage(context, uid, imageUri);
        }
    }

    public static void saveImageToStorage(Context context, String uid, Uri imageUri) {
        File profilePicFile = new File(context.getFilesDir(), uid);
        FileOutputStream fileOutputStream;
        try {
            Bitmap bitmap = MediaStore.Images.Media.getBitmap(context.getContentResolver(), imageUri);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 80, baos);
            byte[] byteArray = baos.toByteArray();
            fileOutputStream = new FileOutputStream(profilePicFile);
            fileOutputStream.write(byteArray);
        } catch (FileNotFoundException ignored) {
        } catch (IOException e) {
            Crashlytics.logException(e);
        }
    }

    public static void deleteAllProfilePictures(Context context) {
        File profilePicDir = context.getFilesDir();
        if (profilePicDir.exists())
            profilePicDir.delete();
    }

    public static void loadUserProfilePic(Context context, ImageView imageView) {
        AppRepository repository = InjectorUtils.provideRepository(context);
        String uid = repository.getUid();
        if (!TextUtils.isEmpty(uid))
            loadProfilePic(context, uid, imageView);
    }

    public static void loadProfilePic(Context context, String uid, ImageView imageView) {
        File profilePicFile = new File(context.getFilesDir(), uid);
        Glide.with(context)
                .load(profilePicFile)
                .placeholder(R.drawable.default_profile_pic)
                .signature(new ObjectKey(profilePicFile.lastModified()))
                .into(imageView);
    }*/
}
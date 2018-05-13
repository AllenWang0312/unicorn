package edu.tjrac.swant.filesystem;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by wpc on 2018/1/25 0025.
 */

public class MediaUtil {


    public static HashMap<String, ArrayList<String>> getMediaMaps(Context context, MediaType type) {
        HashMap<String, ArrayList<String>> map = new HashMap<>();
        Uri media_type = null;
        switch (type) {
            case image:
                media_type = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                break;
            case video:
                media_type = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                break;
            case music:
                media_type = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                break;
        }
        ContentResolver contentResolver = context.getContentResolver();
        Cursor cursor = contentResolver.query(media_type, null, null, null, null);
        if (cursor == null || cursor.getCount() <= 0) {
            return null; // 没有图片
        }
        while (cursor.moveToNext()) {
            int index = cursor
                    .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            String path = cursor.getString(index); // 文件地址
            File file = new File(path);
            if (!map.keySet().contains(file.getParent())) {
                map.put(file.getParent(), new ArrayList<>());
            }
            map.get(file.getParent()).add(file.getAbsolutePath());
        }
        return  map;
    }

    /**
     * Created by wpc on 2018/1/5.
     */

    public static enum MediaType {
        all, file, image, video, music
    }

    public static List<String> getMediaDirs(Context context, MediaType type) {
        List<String> paths = new ArrayList<>();
        List<String> media = getMediaUris(context, type);
        for (String item : media) {
            File file = new File(item);
            if (!paths.contains(file.getParent())) {
                paths.add(file.getParent());
            }
        }
        return paths;
    }

    public static List<String> getMediaUris(Context context, MediaType type) {
        ArrayList<String> uris = new ArrayList<>();
        ArrayList<Uri> media_type = new ArrayList<>();
        switch (type) {
            case image:
                media_type.add(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                break;
            case video:
                media_type.add(MediaStore.Video.Media.EXTERNAL_CONTENT_URI);
                break;
            case music:
                media_type.add(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI);
                break;
            case all:
                media_type.add(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                media_type.add(MediaStore.Video.Media.EXTERNAL_CONTENT_URI);
                media_type.add(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI);
                break;
        }

        for (Uri uri : media_type) {
            ContentResolver contentResolver = context.getContentResolver();
            Cursor cursor = contentResolver.query(uri, null, null, null, null);
            if (cursor == null || cursor.getCount() <= 0) {
                return null; // 没有图片
            }
            while (cursor.moveToNext()) {
                int index = cursor
                        .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                String path = cursor.getString(index); // 文件地址
                File file = new File(path);
                if (file.exists()) {
                    uris.add(file.getAbsolutePath());
                }
            }
        }
        return uris;
    }

    public static List<MediaInfo> getMediaInfos(Context context, MediaType type) {
        List<MediaInfo> uris = new ArrayList<>();
        Uri media_type = null;
        switch (type) {
            case image:
                media_type = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                break;
            case video:
                media_type = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                break;
            case music:
                media_type = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                break;

        }
        ContentResolver contentResolver = context.getContentResolver();
        Cursor cursor = contentResolver.query(media_type,
                new String[]{
                        MediaStore.Images.Media.DATA,
                        MediaStore.Images.Media.SIZE,
                        MediaStore.Images.Media.DISPLAY_NAME,
                        MediaStore.Images.Media.WIDTH,
                        MediaStore.Images.Media.HEIGHT
                }

                , null, null, null);
        if (cursor == null || cursor.getCount() <= 0) {
            return null; // 没有图片
        }
        while (cursor.moveToNext()) {

            uris.add(
                    new MediaInfo(type,
                            cursor.getString(2),
                            cursor.getString(0),
                            cursor.getLong(1),
                            cursor.getInt(3),
                            cursor.getInt(4))


            );

        }
        return uris;
    }

}

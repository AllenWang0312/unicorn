package edu.tjrac.swant.filesystem;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.io.File;

import edu.tjrac.swant.unicorn.R;

/**
 * 类描述:
 * 创建人: Administrator
 * 创建时间: 2018/3/16 0016 上午 10:34
 * 修改人:
 * 修改时间:
 * 修改备注:
 */

public class FileSystemHelper {

    public static void loadFileIconToImageView(File file, ImageView fileImage, Context mContext) {
        if (file.isDirectory()) {
            Glide.with(mContext).load(R.drawable.file_dir).into(fileImage);
        } else {
            String filename = file.getName();
            String end = filename.substring(filename.lastIndexOf(".") + 1, filename.length());
            switch (end.toLowerCase()) {
                case "png":
                case "jpg":
                case "jpeg":
                case "gif":
                case "mp4":
                case "avi":
                    Glide.with(mContext).load(file).into(fileImage);
                    break;
                case "mp3":
                case "wav":
                    Glide.with(mContext).load(R.drawable.file_mic).into(fileImage);
                    break;
                case "doc":
                    Glide.with(mContext).load(R.drawable.file_doc).into(fileImage);
                    break;

                default:
//                    int resid = ResourcesUtils.getDrawableId(mContext, "file_" + end);
//                    Drawable drawable = mContext.getDrawable(resid);
//                    if (drawable != null) {
//                        Glide.with(mContext).load(drawable).into(fileImage);
//                    } else {
                    Glide.with(mContext).load(R.drawable.file).into(fileImage);
//                    }
                    break;

            }
        }

    }
}

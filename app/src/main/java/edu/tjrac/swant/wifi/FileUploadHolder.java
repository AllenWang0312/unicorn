package edu.tjrac.swant.wifi;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import com.yckj.baselib.util.FileUtils;

/**
 * Created by wpc on 2018/1/26 0026.
 */

public class FileUploadHolder {
    public String fileName;
    private File recievedFile;
    public BufferedOutputStream fileOutPutStream;
    public long totalSize;


    public BufferedOutputStream getFileOutPutStream() {
        return fileOutPutStream;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
        totalSize = 0;
        File download=new File(FileUtils.getAppDataDir(),"download");
        if (!download.exists()) {
            download.mkdirs();
        }
        this.recievedFile = new File(download, this.fileName);
//        Timber.d(recievedFile.getAbsolutePath());
        try {
            fileOutPutStream = new BufferedOutputStream(new FileOutputStream(recievedFile));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void reset() {
        if (fileOutPutStream != null) {
            try {
                fileOutPutStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        fileOutPutStream = null;
    }

    public void write(byte[] data) {
        if (fileOutPutStream != null) {
            try {
                fileOutPutStream.write(data);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        totalSize += data.length;
    }
}
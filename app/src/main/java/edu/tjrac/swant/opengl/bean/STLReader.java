package edu.tjrac.swant.opengl.bean;

import android.content.Context;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by wpc on 2018/4/19.
 */

public class STLReader {
    private  StlLoadListener stlLoadListener;

    public Model parserBinStlInSDCard(String path) throws IOException {
        File file = new File(path);
        FileInputStream fis = new FileInputStream(file);
        return parserBinStl(fis);
    }

    public Model parserBinStlInAssets(Context context,String fileName) throws IOException {
        InputStream is=context.getAssets().open(fileName);
        return parserBinStl(is);
    }

    private Model parserBinStl(InputStream is) throws IOException {
        if(stlLoadListener!=null){
            stlLoadListener.onStart();
        }
        Model model=new Model();
        is.skip(80);
        byte[] bytes=new byte[4];
        is.read(bytes);
        int faceCount = Util.byte4ToInt(bytes,0);
        model.setFacetCount(faceCount);

        if(faceCount ==0){
            is.close();
            return model;
        }
        byte[] facetBytes = new byte[50*faceCount];
        is.read(facetBytes);
        is.close();
        parseMode(model,facetBytes);

        if(stlLoadListener !=null)
            stlLoadListener.onFinished();
        return model;
    }

    private void parseMode(Model model,byte[]facetBytes){
        int facetCount=model.getFacetCount();
        float[]verts=new float[facetCount*3*3];

        float[]vnorms=new float[facetCount*3*3];

        short[] remarks=new short[facetCount];

        int stlOffset = 0;

        try{
            for(int i =0 ;i<facetCount;i++){
                if(stlLoadListener!=null){
                    stlLoadListener.onLoading(i,facetCount);
                }
                for(int j=0;j<4;j++){
                    float x =Util.byte4ToFloat(facetBytes,stlOffset);
                    float y =Util.byte4ToFloat(facetBytes,stlOffset+4);
                    float z = Util.byte4ToFloat(facetBytes,stlOffset+8);
                    stlOffset+=12;

                    if(j==0){
                        vnorms[i * 9] = x;
                        vnorms[i * 9 + 1] = y;
                        vnorms[i * 9 + 2] = z;
                        vnorms[i * 9 + 3] = x;
                        vnorms[i * 9 + 4] = y;
                        vnorms[i * 9 + 5] = z;
                        vnorms[i * 9 + 6] = x;
                        vnorms[i * 9 + 7] = y;
                        vnorms[i * 9 + 8] = z;
                    }else {//三个顶点
                        verts[i * 9 + (j - 1) * 3] = x;
                        verts[i * 9 + (j - 1) * 3 + 1] = y;
                        verts[i * 9 + (j - 1) * 3 + 2] = z;

                        //记录模型中三个坐标轴方向的最大最小值
                        if (i == 0 && j == 1) {
                            model.minX = model.maxX = x;
                            model.minY = model.maxY = y;
                            model.minZ = model.maxZ = z;
                        } else {
                            model.minX = Math.min(model.minX, x);
                            model.minY = Math.min(model.minY, y);
                            model.minZ = Math.min(model.minZ, z);
                            model.maxX = Math.max(model.maxX, x);
                            model.maxY = Math.max(model.maxY, y);
                            model.maxZ = Math.max(model.maxZ, z);
                        }
                    }
                }
                short r = Util.byte2ToShort(facetBytes, stlOffset);
                stlOffset = stlOffset + 2;
                remarks[i] = r;
            }
        }catch (Exception e){
            if(stlLoadListener!=null){
                stlLoadListener.onFailure(e);
            }else{
                e.printStackTrace();
            }
        }

        model.setVerts(verts);
        model.setVnorms(vnorms);
        model.setRemarks(remarks);
    }

    public static interface StlLoadListener{
        void onStart();
        void onLoading(int cur,int total);
        void onFinished();
        void onFailure(Exception e);
    }
}

package edu.tjrac.swant.opengl.bean;

import android.graphics.Color;
import android.opengl.GLSurfaceView;
import android.opengl.GLU;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * Created by wpc on 2018/4/19.
 */

public class GLRenderer implements GLSurfaceView.Renderer {

    private Model model;
    private Point mCenterPoint;
    private Point center = new Point(0, 0, 0);
    private Point eye = new Point(0, 0, -3);
    private Point up = new Point(0, 1, 0);
    private float mScalef = 1;
    private float mDegree = 0;

    //阴影色
    float[] ambient = {0.9f, 0.9f, 0.9f, 1.0f};
    //漫反射
    float[] diffuse = {0.5f, 0.5f, 0.5f, 1.0f};
    //高亮色
    float[] specular = {1.0f, 1.0f, 1.0f, 1.0f};
    //光源色
    float[] lightPosition = {0.5f, 0.5f, 0.5f, 0.0f};

//    protected GLRenderer(Context context) {
//        try {
//            model = new STLReader().parserBinStlInAssets(context, "huba.stl");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    public GLRenderer(String filePath) {
        try {
            model = new STLReader().parserBinStlInSDCard(filePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void rotate(float degree) {
        mDegree = degree;
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {

        //启用光照功能
//        gl.glEnable(GL10.GL_LIGHTING);
////开启0号灯
//        gl.glEnable(GL10.GL_LIGHT0);

        gl.glEnable(GL10.GL_DEPTH_TEST);
        gl.glClearColor(0f,0f,0f,0f);
        gl.glDepthFunc(GL10.GL_LEQUAL);
        gl.glShadeModel(GL10.GL_SMOOTH);

        openLight(gl);
        enableMaterial(gl);
        float r =model.getR();
        mScalef=0.5f/r;
        mCenterPoint=model.getCentrePoint();

//step 2
//        gl.glClearDepthf(1.0f);
//
//        gl.glDepthFunc(GL10.GL_LEQUAL);
//        gl.glShadeModel(GL10.GL_SMOOTH);
//        float r = model.getR();
//        mScalef = 0.5f / r;
//        mCenterPoint = model.getCentrePoint();

    }
    public final int LIGHT_TYPE_AMBIENT =0 ;
    public final int LIGHT_TYPE_DIFFUSE =1 ;
    public final int LIGHT_TYPE_SPECULAR =2 ;
    public final int LIGHT_TYPE_LIGHTPOSITION =3 ;


    public  void setColors(int type,int color){
        float[] c= new float[4];
        c[0]=Color.red(color)/255;
        c[1]=Color.green(color)/255;
        c[2]=Color.blue(color)/255;
        c[3]=Color.alpha(color)/255;

        if(type==LIGHT_TYPE_AMBIENT){
            ambient=c;
        }else if(type==LIGHT_TYPE_DIFFUSE) {
            diffuse=c;
        }else if(type==LIGHT_TYPE_SPECULAR){
            specular=c;
        }else if(type==LIGHT_TYPE_LIGHTPOSITION){
            lightPosition=c;
        }
    }
    private void openLight(GL10 gl) {
        gl.glEnable(GL10.GL_LIGHTING);
        gl.glEnable(GL10.GL_LIGHT0);

        gl.glLightfv(GL10.GL_LIGHT0,GL10.GL_AMBIENT, Util.floatToBuffer(ambient));
        gl.glLightfv(GL10.GL_LIGHT0, GL10.GL_DIFFUSE, Util.floatToBuffer(diffuse));
        gl.glLightfv(GL10.GL_LIGHT0, GL10.GL_SPECULAR, Util.floatToBuffer(specular));
        gl.glLightfv(GL10.GL_LIGHT0, GL10.GL_POSITION, Util.floatToBuffer(lightPosition));

    }

    float[] materialAmb = {0.4f, 0.4f, 1.0f, 1.0f,};
    float[] materialDiff = {0.0f, 0.0f, 1.0f, 1.0f,};
    float[] materialSpec = {1.0f, 0.5f, 0.0f, 1.0f,};
    public void enableMaterial(GL10 gl) {

        //材料对环境光的反射情况
        gl.glMaterialfv(GL10.GL_FRONT_AND_BACK, GL10.GL_AMBIENT, Util.floatToBuffer(materialAmb));
        //散射光的反射情况
        gl.glMaterialfv(GL10.GL_FRONT_AND_BACK, GL10.GL_DIFFUSE, Util.floatToBuffer(materialDiff));
        //镜面光的反射情况
        gl.glMaterialfv(GL10.GL_FRONT_AND_BACK, GL10.GL_SPECULAR, Util.floatToBuffer(materialSpec));

    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {

        gl.glViewport(0, 0, width, height);

        gl.glMatrixMode(GL10.GL_PROJECTION);

        gl.glLoadIdentity();

        GLU.gluPerspective(gl, 45.0f, (float) width / height, 1f, 100f);

        gl.glMatrixMode(GL10.GL_MODELVIEW);

        gl.glLoadIdentity();

    }

    @Override
    public void onDrawFrame(GL10 gl) {
        // 清除屏幕和深度缓存
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
        gl.glLoadIdentity();
        //眼睛对着原点看
        GLU.gluLookAt(gl, eye.x, eye.y, eye.z, center.x, center.y, center.z, up.x, up.y, up.z);

        gl.glRotatef(mDegree, 0, 1, 0);
        //将模型放缩到View刚好装下
        gl.glScalef(mScalef, mScalef, mScalef);
        //把模型移动到原点
        gl.glTranslatef(-mCenterPoint.x, -mCenterPoint.y, -mCenterPoint.z);

        //===================begin==============================//

        //允许给每个顶点设置法向量
        gl.glEnableClientState(GL10.GL_NORMAL_ARRAY);
        // 允许设置顶点
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
        // 允许设置颜色

        //设置法向量数据源
        gl.glNormalPointer(GL10.GL_FLOAT, 0, model.getVnormBuffer());
        // 设置三角形顶点数据源
        gl.glVertexPointer(3, GL10.GL_FLOAT, 0, model.getVertBuffer());

        // 绘制三角形
        gl.glDrawArrays(GL10.GL_TRIANGLES, 0, model.getFacetCount() * 3);

        // 取消顶点设置
        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
        //取消法向量设置
        gl.glDisableClientState(GL10.GL_NORMAL_ARRAY);

        //=====================end============================//


//        gl.glEnableClientState(GL10.GL_NORMAL_ARRAY);
//
//        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
//
//        gl.glNormalPointer(GL10.GL_FLOAT, 0, model.getVertBuffer());
//
//        gl.glVertexPointer(3, GL10.GL_FLOAT, 0, model.getVertBuffer());
//
//        gl.glDrawArrays(GL10.GL_TRIANGLES, 0, model.getFacetCount() * 3);
//
//        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
//
//        gl.glDisableClientState(GL10.GL_NORMAL_ARRAY);


    }
}

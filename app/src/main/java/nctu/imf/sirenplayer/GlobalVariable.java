package nctu.imf.sirenplayer;

import android.app.Application;
import android.util.Log;

/**
 * Created by IMF on 2016/1/3.
 */
public class GlobalVariable extends Application {
    public static int[][] dis;
    public static int cnum;
    public static int doingnum = 0;
    public static boolean finish = false;
    public static boolean acofinish = false;
    public static boolean acostop = false;
    public static int[] favorite;
    public static int i = 0;
    public static int j = 0;
    public static int[] btour;
    public static int bcitynum;


    public static void initdis(int citynum){
        cnum = citynum;
        dis = new int[citynum][citynum];


    }
    public static  void verifydismatrix(){
        for(int i=0;i<cnum;i++){
            String disp ="";
            for(int j=0;j<cnum;j++){
                disp = disp+"     " + Integer.toString(dis[i][j]);

            }
            Log.i("distance",disp);
        }
    }
    public static void initfav(int citynum){
        cnum=citynum;
        favorite = new int[citynum];
    }

    public static void setnull(){
        dis = null;
        cnum = 0;
        doingnum = 0;
        finish = false;
        acofinish = false;
        i = 0;
        j = 0;

    }

}

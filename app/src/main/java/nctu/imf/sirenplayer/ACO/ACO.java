package nctu.imf.sirenplayer.ACO;

import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOError;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Vector;

//import javax.print.attribute.standard.Sides;
 
 
public class ACO {
private Vector<Integer> ACOtabu; 
private Ant[] ants; //蚂蚁
private int antNum; //蚂蚁数量
private int cityNum; //城市数量
private int MAX_GEN; //运行代数
private float[][] pheromone; //信息素矩阵
private int[][] distance; //距离矩阵
private int bestLength; //最佳长度
private int[] bestTour; //最佳路径
private int[] favorite;
private int favorsum;
private int nowcitynum;
private int currentlength;
private int ifearlystop;
private int favor;
private int bestTourCityNum;
private int firstCity;

private float alpha;
private float beta;
private float rho;
 
public ACO() {
 
 
 
}
 
public ACO(int p_cityNum, int p_antNum, int p_max_gen,float p_alpha, float p_beta,float p_rho) {
	Num.setzero(); 
	cityNum = p_cityNum;
	antNum = p_antNum;
	ants = new Ant[antNum];
	MAX_GEN = p_max_gen;
	alpha = p_alpha;
	beta = p_alpha;
	rho = p_rho;
	favorsum = 0;
	favor = 0;
	bestTourCityNum = cityNum;
 
}
 
public void init( int[][] distance) throws IOException{
 
	int[] x;
	int[] y;
	String strbuff;
	this.distance = distance;

//	try
//	{
//		//取得SD卡儲存路徑
//		File mSDFile = Environment.getExternalStorageDirectory();
//		//讀取文件檔路徑
//		FileReader mFileReader = new FileReader(mSDFile.getParent() + "/" + mSDFile.getName() +"/"+ filename);
//		BufferedReader data = new BufferedReader(mFileReader);
//
//		distance = new int[cityNum][cityNum];
//		x = new int[cityNum];
//		y = new int[cityNum];
//		favorite = new int [cityNum];
//		for(int i = 0;i < cityNum; i++){
//
//			strbuff = data.readLine();
//			String[] strcol = strbuff.split(" ");
//			x[i] = Integer.valueOf(strcol[1]);
//			y[i] = Integer.valueOf(strcol[2]);
//			favorite[i] = Integer.valueOf(strcol[3]);
//
//
//			//	System.out.println("x: "+x[i]+"   y:  "+y[i]);
//		}

//		for(int i = 0;i < cityNum - 1; i++){
//
//			distance[i][i] = 0;
//			for(int j = i + 1; j < cityNum; j++){
//				// System.out.println("i:   "+i+"  j:  "+j);
//				double rij = Math.sqrt((x[i]-x[j])*(x[i] - x[j]) + (y[i]-y[j])*(y[i]-y[j]))/10.0;
//				int tij = (int)Math.round(rij);
//				if(tij < rij){
//
//					distance[i][j] = tij + 1;
//					distance[j][i] = distance[i][j];
//
//				}else{
//
//					distance[i][j] = tij;
//					distance[j][i] = distance[i][j];
//
//				}
//
//			}
//
//		}
//		BufferedReader mBufferedReader = new BufferedReader(mFileReader);
//		String mReadText = "";
//		String mTextLine = mBufferedReader.readLine();
//
//		//一行一行取出文字字串裝入String裡，直到沒有下一行文字停止跳出
//		while (mTextLine!=null)
//		{
//			mReadText += mTextLine+"\n";
//			mTextLine = mBufferedReader.readLine();
//		}
		//文字放入mText裡，並清空mEdit
//		mText.setText(mReadText);
//		Toast.makeText(, "已讀取文字", Toast.LENGTH_SHORT).show();
//		mEdit.setText("");

//		distance[cityNum - 1][cityNum - 1] = 0;
//		for(int i = 0;i<cityNum;i++){
//			for(int j = 0;j<cityNum;j++){
//				for (int k = 1;k<=3;k++){
//					if (distance[i][j]/(Math.pow(10, k))<1)
//						Log.i("ok", ("0"));
//				}
//				Log.i("ok",(distance[i][j]+"    "));
//
//			}
////		System.out.println();
//		}

		pheromone = new float[cityNum][cityNum];
		for(int i = 0;i < cityNum; i++){

			for(int j = 0;j < cityNum; j++){

				pheromone[i][j] = 0.1f;

			}

		}

		bestLength = Integer.MAX_VALUE;
		bestTour = new int[cityNum + 1];

		for(int i = 0;i < antNum ; i++){

			ants[i] = new Ant(cityNum);
			ants[i].init(distance, alpha, beta,favorite);
//把favor家到這
		}

//	}
//	catch(Exception e)
//	{
//		Log.i("ok",e.getMessage().toString());
//	}


 

 

//	writefile wr = new writefile(distance,cityNum);


 
}
 
public void solve(){
 
	for(int g = 0; g < MAX_GEN; g++){
		Log.i("ok", Integer.toString(g));
		for(int i = 0;i < antNum; i++){
			 nowcitynum = 0;
//			for(int j = 1;j < cityNum-Num.shortnum;j ++){
			 while(nowcitynum<cityNum){
			
				nowcitynum += 1;
				ants[i].selectNextCity(pheromone);
				//ACOtabu = null;
				//ACOtabu = ants[i].getTabu();
				//ACOtabu.add(ants[i].getFirstCity());
//				currentlength = ants[i].calculateTourlength(nowcitynum, ACOtabu);
				currentlength = ants[i].calculateTourlength(nowcitynum);
				if(currentlength > 3000 && nowcitynum > 0 || nowcitynum == cityNum - 1 ){
					
					favorsum = ants[i].getTotalFavor();
					
					
					
					if(ants[i].getFavor(nowcitynum) > favor){
						if(currentlength > 3000){
							bestTourCityNum = nowcitynum-1;
						}else{
							bestTourCityNum = nowcitynum;
						}
						favor = ants[i].getFavor(bestTourCityNum);
						Log.i("ok", (nowcitynum) + "      nowcitynum");
						bestLength = ants[i].calculateTourlength(bestTourCityNum);						
						firstCity = ants[i].getFirstCity();
						
						for(int k = 0 ;k < bestTourCityNum-Num.shortnum  ; k++){
						 
							bestTour[k] = ants[i].getTabu().get(k).intValue();
							
						}
					}
					break;
				}
				
				
			}
//			ants[i].getTabu().add(ants[i].getFirstCity());
//			if(ants[i].getTourLength() < bestLength){
//				System.out.println(ants[i].getTourLength()+  "gettourlength");
//				bestLength = ants[i].getTourLength();
//				for(int k = 0 ;k < cityNum-Num.shortnum + 1; k++){
// 
//					bestTour[k] = ants[i].getTabu().get(k).intValue();
// 
//				}
// 
//			}
			for(int j = 0;j < nowcitynum-Num.shortnum-1; j++){
 
//				ants[i].getDelta()[ants[i].getTabu().get(j).intValue()][ants[i].getTabu().get(j+1).intValue()] = 
//						(float) (1./ants[i].getTourLength());
//				ants[i].getDelta()[ants[i].getTabu().get(j+1).intValue()][ants[i].getTabu().get(j).intValue()] = 
//						(float) (1./ants[i].getTourLength());
				ants[i].getDelta()[ants[i].getTabu().get(j).intValue()][ants[i].getTabu().get(j+1).intValue()] = 
						(float) (1./currentlength);
				ants[i].getDelta()[ants[i].getTabu().get(j+1).intValue()][ants[i].getTabu().get(j).intValue()] = 
						(float) (1./currentlength);
  
			}
 
		}
		updatePheromone();
 
		for(int i = 0;i < antNum; i++){
 
			ants[i].init(distance, alpha, beta, favorite);
 
		}
		if (Num.shortnum<10){
		//Num.shortnum += 1;
		//System.out.println(Num.shortnum);
		}
	}
	printOptimal();
 
	}
 
private void updatePheromone(){
 
	for(int i = 0; i < cityNum; i++){
 
		for(int j = 0 ; j < cityNum; j++){
 
			pheromone[i][j] = pheromone[i][j] * (1 - rho);
 
		}
 
	}
 
	for(int i = 0;i < cityNum; i++){
 
		for(int j = 0;j < cityNum; j++){
 
			for(int k = 0;k < antNum;k++){
 
				pheromone[i][j] += ants[k].getDelta()[i][j];
 
			}
 
		}
 
	}	
 
}
 
private void printOptimal(){
 
	Log.i("ok",("The optimal length is:" + bestLength));
	Log.i("ok", ("the optimal tour is:"));
	Log.i("ok", ("Best city num is       " + (bestTourCityNum)));
	Log.i("ok", ("The first city is       " + firstCity));
	Log.i("ok", ("Favorsum is     " + favorsum));
	Log.i("ok", ("Favor is     " + favor));
	
	for(int i = 0;i < bestTourCityNum-Num.shortnum ; i++){

		Log.i("ok", Integer.toString(bestTour[i]));

 
	}

	Verify verify = new Verify(distance,bestTour,bestTourCityNum   );
	int[] varray = verify.getVarray();
	int vsum = verify.getSum();
	for(int i=0;i<bestTourCityNum-1;i++){
		Log.i("ok", ("verify   " + varray[i]));
	}
	Log.i("ok", ("verify length =  " + vsum));


 
}
 
public Ant[] getAnts() {
 
	return ants;
 
}
 
public void setAnts(Ant[] ants) {
 
	this.ants = ants;
 
}
 
public int getAntNum() {
 
	return antNum;
 
}
 
public void setAntNum(int antNum) {
 
	this.antNum = antNum;
 
}
 
public int getCityNum() {
 
	return cityNum;
 
}
 
public void setCityNum(int cityNum) {
 
	this.cityNum = cityNum;
 
}
 
public int getMAX_GEN() {
 
	return MAX_GEN;
 
}
 
public void setMAX_GEN(int mAXGEN) {
 
	MAX_GEN = mAXGEN;
 
}
 
public float[][] getPheromone() {
 
	return pheromone;
 
}
 
public void setPheromone(float[][] pheromone) {
 
	this.pheromone = pheromone;
 
}
 
public int[][] getDistance() {
 
	return distance;
 
}
 
public void setDistance(int[][] distance) {
 
	this.distance = distance;
 
}
 
public int getBestLength() {
 
	return bestLength;
 
}
 
public void setBestLength(int bestLength) {
 
	this.bestLength = bestLength;
 
}
 
public int[] getBestTour() {
 
	return bestTour;
 
}
 
public void setBestTour(int[] bestTour) {
 
	this.bestTour = bestTour;
 
}
 
public float getAlpha() {
 
	return alpha;
 
}
 
public void setAlpha(float alpha) {
 
	this.alpha = alpha;
 
}
 
public float getBeta() {
 
	return beta;
 
}
 
public void setBeta(float beta) {
 
	this.beta = beta;
 
}
 
public float getRho() {
 
	return rho;
 
}
 
public void setRho(float rho) {
 
	this.rho = rho;
 
}
 
 
//public static void main(String[] args) throws IOException{
//
//	ACO aco = new ACO(48, 100 ,10, 1.f, 5.f, 0.5f);
//	aco.init("/Users/dada/Documents/workspace/ACO/src/data.txt");
//	aco.solve();
//
//}
 
}
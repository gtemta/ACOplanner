package nctu.imf.sirenplayer.ACO;

import android.util.Log;

import java.util.Random;
import java.util.Vector;
 
 
public class Ant implements Cloneable{
 
private Vector<Integer> tabu; //禁忌矩阵
private Vector<Integer> allowedCities; //没有走过的城市
private float [][] delta; //信息数变化矩阵
private int [][] distance; //距离矩阵
private float alpha;
private float beta;
private int tourLength; // 路径长度
private int cityNum; //城市数量
private int firstCity; //起始城市
private int currentCity; //当前城市
private int[] favorite;
private int totalfavor;
private int now;  //目前第幾個城市

private Ant(){
	cityNum = 30;
	tourLength = 0;
}
 
public Ant(int num) {
	cityNum = num;
	tourLength = 0;
}
 
public void init(int [][]distance , float a , float b,int[] fav){
	favorite = fav;
	for(int i:favorite){
		Log.i("fav",Integer.toString(i)+"     fav");
	}
	alpha = a;
	beta = b;
	totalfavor = 0;
	allowedCities = new Vector<Integer>();
	tabu = new Vector<Integer>();
	this.distance = distance;
	delta = new float[cityNum][cityNum];
	for(int i = 0;i < cityNum; i++){
		totalfavor += favorite[i];
		Integer integer = new Integer(i);
		allowedCities.add(integer);
		for(int j = 0;j < cityNum; j++){
			delta[i][j] = 0.f;
		}
	}
	Random random = new Random(System.currentTimeMillis());
	firstCity = random.nextInt(cityNum);
	for(Integer i:allowedCities){
		if(i.intValue() == firstCity){
			break;
		}
	}
	tabu.add(Integer.valueOf(firstCity));
	allowedCities.remove(firstCity);
	currentCity = firstCity;
 
}
 
public void selectNextCity(float[][]pheromone) {
 
	float [] p = new float[cityNum];
	float sum = 0.0f;
 
	for(Integer i:allowedCities){
 
		sum += Math.pow(pheromone[currentCity][i.intValue()], alpha)*Math.pow(1.0/distance[currentCity][i.intValue()],beta);
 
	}
	for(int i = 0; i < cityNum;i++){
 
		boolean flag = false;
		for(Integer j:allowedCities){
 
			if(i == j.intValue()){
 
				p[i] = (float)(Math.pow(pheromone[currentCity][i],alpha)*Math.pow(1.0/distance[currentCity][i], beta))/sum;
				flag = true	;
				break;
 
			}
 
		}
		if(flag == false)
			p[i] = 0.f;
 
	}
 
 
	Random random = new Random(System.currentTimeMillis());
	float sleectP = random.nextFloat();
	int selectCity = 0;
	float sum1 = 0.f;
	//for(int i = 0;i < cityNum;i++){
	for(Integer i : allowedCities){
		sum1 += p[i];
		if(sum1 > sleectP){
 
			selectCity = i;
			break;
 
		}
	}
	//}
 
	for(Integer i:allowedCities){
 
		if(i.intValue() == selectCity){
 
			allowedCities.remove(i);
			break;
 
		}
 
	}
 
	tabu.add(Integer.valueOf(selectCity));
//	System.out.println(x);
	
	
	currentCity = selectCity;
 
	}
 
	private int calculateTourLength() {
 
		int len = 0;
		for(int i = 0; i < cityNum-Num.shortnum-1;i++){
 
			len += distance[this.tabu.get(i).intValue()][this.tabu.get(i+1).intValue()];
 
		}
		return len;
 
	}
	
	public int calculateTourlength(int rightnow, Vector<Integer> ACOtabu){
		int nowlen = 0;
		for(int i = 0; i < rightnow-1;i++){
 
			nowlen += distance[ACOtabu.get(i).intValue()][ACOtabu.get(i+1).intValue()];
 
		}
		return nowlen;

	}
	
	public int calculateTourlength(int rightnow){
		int nowlen = 0;
		for(int i = 0; i < rightnow-1;i++){
 
			nowlen += distance[this.tabu.get(i).intValue()][this.tabu.get(i+1).intValue()];
 
		}
		return nowlen;

	}
 
 
public Vector<Integer> getAllowedCities() {
 
	return allowedCities;
 
}
 
public void  setAllowedCities(Vector<Integer> allowedCities){
 
	this.allowedCities = allowedCities;
 
}
 
public Vector<Integer> getTabu() {
 
	return tabu;
 
}
 
public void setTabu(Vector<Integer> tabu) {
 
	this.tabu = tabu;
 
}
 
public float[][] getDelta() {
 
	return delta;
 
}
 
public void setDelta(float[][] delta) {
 
	this.delta = delta;
 
}
 
public int[][] getDistance() {
 
	return distance;
 
}
 
public void setDistance(int[][] distance) {
 
	this.distance = distance;
 
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
 
public int getTourLength() {
	int tourlength = calculateTourLength();  
	return tourlength;
 
}
 
public void setTourLength(int tourLength) {
 
	this.tourLength = tourLength;
 
}

public int getFavor(int rightnow){
	int sumfavor = 0;
	
	for(int i = 0; i < rightnow+1;i++){
		 
		sumfavor += favorite[this.tabu.get(i).intValue()];

	}
	
	
	return sumfavor;
}
 
public int getCityNum() {
 
	return cityNum;
 
}
 
public void setCityNum(int cityNum) {
 
	this.cityNum = cityNum;
 
}
 
public int getFirstCity() {
 
	return firstCity;
 
}
 
public void setFirstCity(int firstCity) {
 
	this.firstCity = firstCity;
 
}
 
public int getCurrentCity() {
 
	return currentCity;
 
}
 
public void setCurrentCity(int currentCity) {
 
	this.currentCity = currentCity;
 
}

public int getTotalFavor(){
	
	return totalfavor;
}
 
}
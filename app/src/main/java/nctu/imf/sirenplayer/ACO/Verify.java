package nctu.imf.sirenplayer.ACO;

public class Verify {
	private int[] varray;
	private int sum;
	public Verify(int[][] distance,int[] bestTour,int cityNum){
		 varray = new int[cityNum];
		 sum = 0;
		for (int i = 0;i<cityNum-1;i++){
//			if(i==0){
//			varray[i]=0;
//			}else{
				varray[i] = distance[bestTour[i]][bestTour[i+1]];
				sum+=varray[i];
			//}
		}
	}
	public int getSum(){
		return sum;
	}
	public int[] getVarray(){
		return varray;
	}
}

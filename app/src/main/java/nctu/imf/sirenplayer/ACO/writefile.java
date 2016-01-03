package nctu.imf.sirenplayer.ACO;

import java.io.BufferedReader;

import java.io.BufferedWriter;

import java.io.File;

import java.io.FileInputStream;

import java.io.FileNotFoundException;

import java.io.FileOutputStream;

import java.io.IOException;

import java.io.InputStreamReader;

import java.io.OutputStreamWriter;


public class writefile {
	public  writefile(int[][] distance,int cityNum) {

		BufferedWriter fw = null;

		try {

		File file = new File("/Users/dada/Documents/workspace/ACO/src/verify.txt");

		fw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, true), "UTF-8")); // 指點編碼格式，以免讀取時中文字符異常

//		fw.append("我寫入的內容");
		
		for(int i = 0;i<cityNum;i++){
			for(int j = 0;j<cityNum;j++){
				for (int k = 1;k<=3;k++){
					if (distance[i][j]/(Math.pow(10, k))<1)
						fw.append("0");
				}
				fw.append(distance[i][j] + " ");
				
			}
			fw.newLine();
		}
		
		
		

//		fw.newLine();

		fw.flush(); // 全部寫入緩存中的內容

		} catch (Exception e) {

		e.printStackTrace();

		} finally {

		if (fw != null) {

		try {

		fw.close();

		} catch (IOException e) {

		e.printStackTrace();

		}

		}

		}
	}
}

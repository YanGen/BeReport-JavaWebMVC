package com.report.file;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

public class ExcelProcess {
	// 处理漏斗图
		public Map<String,String> FunnelChart(String FilePath,String title,String unit){
			jxl.Workbook wb = null;
			ArrayList<String> xAxis = new ArrayList<>();
			Map<String,String> mapAllData = new HashMap<String,String>();
			
			try {
				InputStream instream = new FileInputStream(FilePath);
				wb = Workbook.getWorkbook(instream);
				Sheet sheet = wb.getSheet(0);
				int rsColumns = sheet.getColumns();
				int rsRows = sheet.getRows();
				ArrayList<String> eachColumnsName = new ArrayList<>();
		 		ArrayList<ArrayList<String>> allSeriesValue = new ArrayList<>();
				for (int i = 0; i < rsColumns; i++)   
		            {   
					 	ArrayList<String> eachSeriesValue = new ArrayList<>();
		                for (int j = 0; j < rsRows; j++)   
		                {   
		                    Cell cell = sheet.getCell(i, j);   
		                    if(i==0){
		                    	if(j!=0){
		                    	xAxis.add("'"+cell.getContents()+"'");
		                    	}
		                    }else{
		                    	if(j==0){
		                    		if(i!=0){
		                    		eachColumnsName.add("'"+cell.getContents()+"'");
		                    		}
		                    	}else{
		                    		eachSeriesValue.add(cell.getContents());
		                    	}
		                    }
		                }   
		                if(i!=0){
		                	allSeriesValue.add(eachSeriesValue);
		                }
		            }
				String eachSeries = "[";
				ArrayList<String> columnValue = allSeriesValue.get(0);
				ArrayList<String> dataList = new ArrayList<>();
				for(int i=0;i<xAxis.size();i++){
					dataList.add("{value:"+columnValue.get(i)+",name:"+xAxis.get(i)+"}");
				}
				eachSeries += "{name:"+eachColumnsName.get(0)+",type:'funnel',width: '40%',data:"+dataList.toString()+"},";
				eachSeries += "]";
				mapAllData.put("series",eachSeries);
				mapAllData.put("calculable", "true");
				String insertStr = "'{a} <br/>{b} : {c}%'}";
				System.out.println(insertStr);
				mapAllData.put("tooltip", "{trigger: 'item',formatter: "+insertStr);
				mapAllData.put("legend","{data:"+xAxis.toString()+"}");
				mapAllData.put("title", "{text:'"+title+"'}");				
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}finally{
				wb.close();
			}
			return mapAllData;
		}
	// 处理折线图
		public Map<String,String> LineChart(String FilePath,String title,String unit){
			jxl.Workbook wb = null;
			ArrayList<String> xAxis = new ArrayList<>();
			Map<String,String> mapAllData = new HashMap<String,String>();
			
			try {
				InputStream instream = new FileInputStream(FilePath);
				wb = Workbook.getWorkbook(instream);
				Sheet sheet = wb.getSheet(0);
				int rsColumns = sheet.getColumns();
				int rsRows = sheet.getRows();
				ArrayList<String> eachColumnsName = new ArrayList<>();
		 		ArrayList<ArrayList<String>> allSeriesValue = new ArrayList<>();
				for (int i = 0; i < rsColumns; i++)   
		            {   
					 	ArrayList<String> eachSeriesValue = new ArrayList<>();
		                for (int j = 0; j < rsRows; j++)   
		                {   
		                    Cell cell = sheet.getCell(i, j);   
		                    if(i==0){
		                    	if(j!=0){
		                    	xAxis.add("'"+cell.getContents()+"'");
		                    	}
		                    }else{
		                    	if(j==0){
		                    		if(i!=0){
		                    		eachColumnsName.add("'"+cell.getContents()+"'");
		                    		}
		                    	}else{
		                    		eachSeriesValue.add(cell.getContents());
		                    	}
		                    }
		                }   
		                if(i!=0){
		                	allSeriesValue.add(eachSeriesValue);
		                }
		            }
				String eachSeries = "[";
				System.out.println(eachColumnsName.size());
				for(int i=0;i<eachColumnsName.size();i++){
					String columnName = eachColumnsName.get(i);
					String columnValue = allSeriesValue.get(i).toString();
					eachSeries += "{name:"+columnName+",type:'line',data:"+columnValue+",markPoint : {data :[{type : 'max', name: '最大值'},{type : 'min', name: '最小值'}]},markLine : {data : [{type : 'average', name : '平均值'}]}},";
				}
				eachSeries += "]";
				mapAllData.put("series",eachSeries);
				mapAllData.put("yAxis", "[{type : 'value',axisLabel : {formatter: '{value} "+unit+"'}}]");
				mapAllData.put("xAxis","[{type : 'category',boundaryGap : false,data :"+xAxis.toString()+"}]");
				mapAllData.put("calculable", "true");
				mapAllData.put("tooltip", "{trigger: 'axis'}");
				mapAllData.put("grid","{x:40,x2:40,y2:24}");
				mapAllData.put("legend","{data:"+eachColumnsName.toString()+"}");
				mapAllData.put("title", "{text:'"+title+"'}");				
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}finally{
				wb.close();
			}
			return mapAllData;
		}
		// 处理柱状图
		public Map<String,String> Histogram(String FilePath,String title,String unit){
			jxl.Workbook wb = null;
			ArrayList<String> xAxis = new ArrayList<>();
			Map<String,String> mapAllData = new HashMap<String,String>();
			
			try {
				InputStream instream = new FileInputStream(FilePath);
				wb = Workbook.getWorkbook(instream);
				Sheet sheet = wb.getSheet(0);
				int rsColumns = sheet.getColumns();
				int rsRows = sheet.getRows();
				ArrayList<String> eachColumnsName = new ArrayList<>();
		 		ArrayList<ArrayList<String>> allSeriesValue = new ArrayList<>();
				for (int i = 0; i < rsColumns; i++)   
		            {   
					 	ArrayList<String> eachSeriesValue = new ArrayList<>();
		                for (int j = 0; j < rsRows; j++)   
		                {   
		                    Cell cell = sheet.getCell(i, j);   
		                    if(i==0){
		                    	if(j!=0){
		                    	xAxis.add("'"+cell.getContents()+"'");
		                    	}
		                    }else{
		                    	if(j==0){
		                    		if(i!=0){
		                    		eachColumnsName.add("'"+cell.getContents()+"'");
		                    		}
		                    	}else{
		                    		eachSeriesValue.add(cell.getContents());
		                    	}
		                    }
		                }   
		                if(i!=0){
		                	allSeriesValue.add(eachSeriesValue);
		                }
		            }
				String eachSeries = "[";
				System.out.println(eachColumnsName.size());
				for(int i=0;i<eachColumnsName.size();i++){
					String columnName = eachColumnsName.get(i);
					String columnValue = allSeriesValue.get(i).toString();
					eachSeries += "{name:"+columnName+",type:'bar',data:"+columnValue+",markPoint : {data :[{type : 'max', name: '最大值'},{type : 'min', name: '最小值'}]},markLine : {data : [{type : 'average', name : '平均值'}]}},";
				}
				eachSeries += "]";
				mapAllData.put("series",eachSeries);
				mapAllData.put("yAxis", "[{type : 'value',axisLabel : {formatter: '{value} "+unit+"'}}]");
				mapAllData.put("xAxis","[{type : 'category',boundaryGap : false,data :"+xAxis.toString()+"}]");
				mapAllData.put("calculable", "true");
				mapAllData.put("tooltip", "{trigger: 'axis'}");
				mapAllData.put("grid","{x:40,x2:40,y2:24}");
				mapAllData.put("legend","{data:"+eachColumnsName.toString()+"}");
				mapAllData.put("title", "{text:'"+title+"'}");				
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}finally{
				wb.close();
			}
			return mapAllData;
		}
		//处理散点图
		public Map<String,String> ScatterDiagram(String FilePath,String title,String unit){
			return null;
		}
		// 处理饼图
		public Map<String,String> PieChart(String FilePath,String title,String unit){
			jxl.Workbook wb = null;
			ArrayList<String> xAxis = new ArrayList<>();
			Map<String,String> mapAllData = new HashMap<String,String>();
			
			try {
				InputStream instream = new FileInputStream(FilePath);
				wb = Workbook.getWorkbook(instream);
				Sheet sheet = wb.getSheet(0);
				int rsColumns = sheet.getColumns();
				int rsRows = sheet.getRows();
				ArrayList<String> eachColumnsName = new ArrayList<>();
		 		ArrayList<ArrayList<String>> allSeriesValue = new ArrayList<>();
				for (int i = 0; i < rsColumns; i++)   
		            {   
					 	ArrayList<String> eachSeriesValue = new ArrayList<>();
		                for (int j = 0; j < rsRows; j++)   
		                {   
		                    Cell cell = sheet.getCell(i, j);   
		                    if(i==0){
		                    	if(j!=0){
		                    	xAxis.add("'"+cell.getContents()+"'");
		                    	}
		                    }else{
		                    	if(j==0){
		                    		if(i!=0){
		                    		eachColumnsName.add("'"+cell.getContents()+"'");
		                    		}
		                    	}else{
		                    		eachSeriesValue.add(cell.getContents());
		                    	}
		                    }
		                }   
		                if(i!=0){
		                	allSeriesValue.add(eachSeriesValue);
		                }
		            }
				String eachSeries = "[";
				ArrayList<String> columnValue = allSeriesValue.get(0);
				ArrayList<String> dataList = new ArrayList<>();
				for(int i=0;i<xAxis.size();i++){
					dataList.add("{value:"+columnValue.get(i)+",name:"+xAxis.get(i)+"}");
				}
				eachSeries += "{name:"+eachColumnsName.get(0)+",type:'pie',radius : '55%',center: ['50%', '60%'],data:"+dataList.toString()+"},";
				eachSeries += "]";
				mapAllData.put("series",eachSeries);
				mapAllData.put("calculable", "true");
				String insertStr = "'{a} <br/>{b} : {c} ({d}%)'}";
				System.out.println(insertStr);
				mapAllData.put("tooltip", "{trigger: 'item',formatter: "+insertStr);
				mapAllData.put("legend","{orient : 'vertical',x : 'left',data:"+xAxis.toString()+"}");
				mapAllData.put("title", "{x:'center',text:'"+title+"'}");				
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}finally{
				wb.close();
			}
			return mapAllData;
		}
}

package com.ailk.axis;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.axis2.AxisFault;

public class Test {
	
	static int fileIndex = 0;
	public static void main(String[] args) throws AxisFault {
	
		AxisRPCClient client=new AxisRPCClient(
				"http://10.3.63.114/axis/services/MobileInterfce?wsdl",
				"http://impl.tyeq.webinterface.nc");
		for(long i=0;i<100000;i++){ 
		try {
			doERPNumber(client);
			doERPBillList(client);
			Thread.currentThread().sleep(1000);
			
		} catch (Exception e) {
			// TODO: handle exception
			file_out_println("billQuantity;call-fail;" +"0000", "erp.log.txt");
		}
			
		}
		
	}
	public static void file_out_println(String msg, String filename) {
		FileWriter fw = null;
		String index = "_" + Integer.toString(fileIndex);

		try {
			fw = new FileWriter(  index
					+ filename, true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (null != fw) {
			try {

				fw.write(msg + "\n");
				fw.close();

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		File file = new File( index
				+ filename);
		long size = file.length();
		if (size < 10 * 1024 * 1024 ) {
			;
		} else {
			fileIndex++;
		}

	}
	
	public static String getTime(){
		Date now = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//可以方便地修改日期格式
		String hehe = dateFormat.format( now ); 
		System.out.println(hehe); 
		return hehe;
	}
	public static String doERPNumber(AxisRPCClient client) throws AxisFault{
		long start = System.currentTimeMillis();
		//file_out_println("billQuantity;start;" + getTime(), "erp.log.txt");
		String start_time=getTime();
		String retstr=client.execute( new Object[]{"000014148"}, "billQuantity");
		long cost=System.currentTimeMillis()-start;
		file_out_println("billQuantity;"+start_time+";" + cost, "erp.log_quantity.txt");
		System.out.println(retstr);
		return retstr;
	}
	public static String doERPBillList(AxisRPCClient client) throws AxisFault{
		long start = System.currentTimeMillis();
		//file_out_println("billQuantity;start;" + getTime(), "erp.log.txt");
		String start_time=getTime();
		String retstr=client.execute( new Object[]{"000014148","200","1"}, "billList");
		long cost=System.currentTimeMillis()-start;
		file_out_println("billList;"+start_time+";" + cost, "erp.log_list.txt");
		System.out.println(retstr);
		return retstr;
	}
	
}

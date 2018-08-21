package test;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadWriteExcelFile {

	public static void writeXLSFile(String[][] excelData) throws IOException {

		String excelFileName = "Test.xls";// name of excel file

		String sheetName = "Permissions";// name of sheet

		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet(sheetName);

		// iterating r number of rows
		for (int r = 0; r < excelData[1].length; r++) {
			HSSFRow row = sheet.createRow(r);

			// iterating c number of columns
			for (int c = 0; c < 2; c++) {
				HSSFCell cell = row.createCell(c);

				cell.setCellValue(excelData[c][r]);
			}
		}

		FileOutputStream fileOut = new FileOutputStream(excelFileName);

		// write this workbook to an Outputstream.
		wb.write(fileOut);
		fileOut.flush();
		fileOut.close();
	}

	public static String[][] readPermissionsText() throws IOException {

		BufferedReader in = new BufferedReader(new FileReader("permissions.txt"));

		// Get total number of subfolders
		String line;
		int numOfRows = 0;
		while ((line = in.readLine()) != null) {
			if (line.length() != 0) {
					numOfRows++;
			}
		}
		in.close();

		in = new BufferedReader(new FileReader("permissions.txt"));

		System.out.println(numOfRows);

		// Initialize the 2D matrix
		String excelData[][] = new String[2][numOfRows + 1];
		int row = 0;

		int currentRow = 0;
		while ((line = in.readLine()) != null) {

			if (line.length() != 0) {
				if (line.charAt(0) == 'S') {
					String lineTemp = line.substring(4, line.indexOf(" "));
					excelData[0][row] = lineTemp;
					// System.out.println(line);
					row++;
				}

				if (line.charAt(0) == ' ' && line.contains("LISTJP")) {

					excelData[1][row] = line.substring(line.indexOf("LISTJP"));
					excelData[0][row] = excelData[0][row - 1]; 
					row++;

				}
			}
		}
		in.close();

		return excelData;

	}

	public static void main(String[] args) throws IOException {

		writeXLSFile(readPermissionsText());

	}
}
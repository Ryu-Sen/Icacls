package permissions.logic;

import java.io.FileOutputStream;
import java.io.IOException;
import java.security.Permissions;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import lombok.experimental.Helper;

/**
 * @author r-senda
 *
 */
public class ExcelWriter {

	private static String[] columns = { "Folder Name", "Sub-folder Name", "Name of Account"};
	private static List<Permissions> permissions = new ArrayList<>();
	

	public static void main(String[] args) throws IOException, InvalidFormatException {
		
		
		// Create a Workbook
		Workbook workbook = new XSSFWorkbook(); // new HSSFWorkbook() for generating `.xls` file

		// Create a Sheet
		Sheet sheet = workbook.createSheet("Employee");

		// Create a Font for styling header cells
		Font headerFont = workbook.createFont();
		headerFont.setBold(true);
		headerFont.setFontHeightInPoints((short) 14);
		headerFont.setColor(IndexedColors.RED.getIndex());

		// Create a CellStyle with the font
		CellStyle headerCellStyle = workbook.createCellStyle();
		headerCellStyle.setFont(headerFont);

		// Create a Row
		Row headerRow = sheet.createRow(0);

		// Create cells
		for (int i = 0; i < columns.length; i++) {
			Cell cell = headerRow.createCell(i);
			cell.setCellValue(columns[i]);
			cell.setCellStyle(headerCellStyle);
		}


		// Create Other rows and cells with employees data
		int rowNum = 1;
		for (Permissions element : permissions) {
			Row row = sheet.createRow(rowNum++);

			row.createCell(0).setCellValue("Test Folder name");

			row.createCell(1).setCellValue("Test SubFolder name");

			String dirName = "";
			
			row.createCell(2).setCellValue("Test User name");
		}

		// Resize all columns to fit the content size
		for (int i = 0; i < columns.length; i++) {
			sheet.autoSizeColumn(i);
		}

		// Write the output to a file
		FileOutputStream fileOut = new FileOutputStream("poi-generated-file.xlsx");
		workbook.write(fileOut);
		fileOut.close();

		// Closing the workbook
		workbook.close();
	}
}
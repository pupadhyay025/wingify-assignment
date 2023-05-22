package dataProvider;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xslf.usermodel.XSLFSheet;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class ReadWriteExcel
{
    FileInputStream fileInput;
   XSSFWorkbook workBook;
   XSSFSheet sheet;

    public ReadWriteExcel() throws IOException
    {
        fileInput = new FileInputStream(new File(ConfigFileReader.getTestDataFile()));
        workBook = new XSSFWorkbook(fileInput);
    }

    public XSSFSheet getSheet(String sheetName)
    {
        sheet = workBook.getSheet(sheetName);
        return sheet;
    }
}

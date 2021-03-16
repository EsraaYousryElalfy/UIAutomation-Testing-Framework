package Utilities;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.IOException;

public class Excel_Sheet {
    // this class to add excel sheet to the project
    protected static String fileName="AssistaHealthLoginTestData.xlsx";
    protected static String filePath = System.getProperty("user.dir")+"\\Test_Data\\";
    public Object[][] readData() throws InvalidFormatException, IOException {
        File file = new File(filePath+fileName);
        File myDataFile = new File(".\\Test_Data\\AssistaHealthLoginTestData.xlsx");
        XSSFWorkbook wb = new XSSFWorkbook(myDataFile);
        XSSFSheet mySheet =wb.getSheet("AssistaHealthLoginPage");
        int NumberOfRows=mySheet.getPhysicalNumberOfRows();
        int NumberOfColumns=mySheet.getRow(0).getLastCellNum();
        Object[][] myArray = new Object[NumberOfRows-1][NumberOfColumns];
        for(int i=1 ; i<NumberOfRows ; i++)
        {
            for (int j=0 ; j<NumberOfColumns ; j++)
            {
                XSSFRow myRow = mySheet.getRow(i);
                myArray[i-1][j]=myRow.getCell(0).getStringCellValue();
            }
        }
        return myArray;
    }
}

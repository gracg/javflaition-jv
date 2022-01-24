package nl.capite.jaflaition.helpers;

import nl.capite.jaflaition.models.YearSeries;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class BlsHelper {
    final String pathToFile;


    public BlsHelper(String pathToFile) {
        this.pathToFile=pathToFile;
    }

    public List<YearSeries> analyze() throws IOException {
        final int startingRow = 12;

        FileInputStream file = new FileInputStream(new File(this.pathToFile));

        XSSFWorkbook wb = new XSSFWorkbook(file);
        XSSFSheet sh = wb.getSheetAt(0);

        List<YearSeries> ls = new ArrayList<>();
        for(int i=startingRow;i<i+this.getNumberOfYears();i++) {
            XSSFRow r = sh.getRow(i);

            if(r==null) {
                break;
            }

            int year = (int) r.getCell(0).getNumericCellValue();

            List<Double> months = new ArrayList<>();
            Double half1=null;
            Double half2=null;
            for(int j=1;j<=12;j++) {

                if(r.getCell(j)==null) {
                    break;
                }
                months.add(r.getCell(j).getNumericCellValue());
            }
            if(r.getCell(13)!=null && r.getCell(14)!=null) {
                half1=r.getCell(13).getNumericCellValue();
                half2=r.getCell(14).getNumericCellValue();
            }

            ls.add(new YearSeries(year,months,half1,half2));
        }
        return ls;
    }

    /*

     */
    public int getNumberOfYears() {
        final int startingYear = 1913;
        final int currentYear = LocalDate.now().getYear();
        int result = currentYear - startingYear;
        if(LocalDate.now().getMonthValue()==1) {
            result--;
        }
        return result;
    }
}

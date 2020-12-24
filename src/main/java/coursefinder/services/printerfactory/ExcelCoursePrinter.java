package coursefinder.services.printerfactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import coursefinder.domain.Course;

public class ExcelCoursePrinter implements CoursePrinter {

    @Override
    public void print(Course course) {
        String currentDirectory = System.getProperty("user.dir") + System.getProperty("file.separator")+"courses.xls";
        if (fileExist(currentDirectory)){
            try { //leggo l'ultima riga
                FileInputStream fileIn = new FileInputStream(currentDirectory);
                XSSFWorkbook coursesSheet = new XSSFWorkbook(fileIn);
                XSSFSheet worksheet = coursesSheet.getSheetAt(0);
                int lastRow = worksheet.getLastRowNum();
                fileIn.close();
                // scrivo la nuova riga in append
                FileOutputStream outputFile = new FileOutputStream(new File(currentDirectory));
                Row row = worksheet.createRow(++lastRow);
                row.createCell(0).setCellValue(course.getCourseNumber());
                row.createCell(1).setCellValue(course.getCourseTitle());
                row.createCell(2).setCellValue(course.getInstitution().getName());
                coursesSheet.write(outputFile);
                outputFile.close();
                coursesSheet.close();
                System.out.println(" scrittura completata con successo");
            } catch (Exception e) {
                System.err.println("Errore durante la scrittura del file: "+e.getMessage());
            }
        } else{
            //il file non esiste:lo creo mettendoci le intestazioni delle colonne
            this.createNewFile(currentDirectory); 
        }         
    }

    private boolean fileExist(String filePath){
        File file = new File(filePath);
        if (file.exists() == true) {
            return true;}
        else{
            return false;
        }
    }

    private void createNewFile(String filePath){
        try {
            XSSFWorkbook coursesBook = new XSSFWorkbook();
            XSSFSheet sheet = coursesBook.createSheet("Courses");
            Row row = sheet.createRow(0);
            row.createCell(0).setCellValue("Course Number");
            row.createCell(1).setCellValue("Course Title");
            row.createCell(2).setCellValue("Institution");
            File file = new File(filePath);
            file.createNewFile();
            FileOutputStream fileOut = new FileOutputStream(filePath);
            coursesBook.write(fileOut);
            fileOut.close();
            coursesBook.close();
        } catch (IOException e) {
            System.err.println("Impossibile creare il file: "+e.getMessage());
        }
    }
}
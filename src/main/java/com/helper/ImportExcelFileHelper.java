package com.helper;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;


@Component
public class ImportExcelFileHelper {
    public void importExcelFile(MultipartFile multipartFile) throws IOException {
        XSSFWorkbook xssfWorkbook = new XSSFWorkbook(multipartFile.getInputStream());
        XSSFSheet excelSheet = xssfWorkbook.getSheetAt(0);
        for(Row row:excelSheet) {
        	if(row.getRowNum()==0) {
        		continue;
        	}
        }

    }
}

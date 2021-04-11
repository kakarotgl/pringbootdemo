package com.zimug.boot.launch.service;

import com.zimug.boot.launch.AjaxResponse;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.stereotype.Service;

import java.io.IOException;

//@Service
public class ResponseToXlsConverter extends AbstractHttpMessageConverter<AjaxResponse> {

  private static final MediaType EXCEL_TYPE = MediaType.valueOf("application/vnd.ms-excel");

  ResponseToXlsConverter() {
    super(EXCEL_TYPE);
  }

  @Override
  protected boolean supports(Class<?> clazz) {
    return (AjaxResponse.class == clazz);
  }

  @Override
  protected AjaxResponse readInternal(Class<? extends AjaxResponse> clazz, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
    return null;
  }

  @Override
  protected void writeInternal(AjaxResponse ajaxResponse, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
    final Workbook workbook = new HSSFWorkbook();
    final Sheet sheet = workbook.createSheet();

    final Row row = sheet.createRow(0);
    row.createCell(0).setCellValue(ajaxResponse.getMessage());
    row.createCell(1).setCellValue(ajaxResponse.getData().toString());

    workbook.write(outputMessage.getBody());
  }
}

package com.ho.javapractice;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.printing.PDFPageable;

import javax.print.DocFlavor;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.PrinterName;
import java.awt.print.Pageable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.IOException;

public class PrintTest {
    public static void main(String[] args) {
        try {
            // 인쇄할 PDF 파일 경로
            String pdfFilePath = "I:/upload/shop/test/테스트.pdf"; // 실제 파일 경로로 바꿔주세요.

            // PDF 문서 열기
            PDDocument document = Loader.loadPDF(new File(pdfFilePath));
//            PDDocument document = PDDocument.(new File(pdfFilePath));

            // 인쇄할 프린터 찾기 (프린터 이름 지정)
            PrintService[] printServices = PrintServiceLookup.lookupPrintServices(DocFlavor.SERVICE_FORMATTED.PAGEABLE, null);
            PrintService printService = null;
            for (PrintService service : printServices) {
                if (service.getName().equals("THETHEepson (L1455 Series)")) {
                    printService = service;
                    break;
                }
            }

            if (printService != null) {
                // 프린트 요청 속성 설정
                PrintRequestAttributeSet attributes = new HashPrintRequestAttributeSet();
                // 프린터 설정 및 미리보기 대화 상자 생성
                Pageable pageable = new PDFPageable(document);

                // 프린터 작업 생성
                PrinterJob printerJob = PrinterJob.getPrinterJob();
                printerJob.setPrintService(printService);
                printerJob.setPageable(pageable);
                // 인쇄 작업 실행
                if (printerJob.printDialog(attributes)) {
                    printerJob.print(attributes);
                }
            } else {
                System.out.println("프린터를 찾을 수 없습니다.");
            }

            // PDF 문서 닫기
            document.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

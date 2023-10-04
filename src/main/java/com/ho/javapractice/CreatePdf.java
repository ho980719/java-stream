package com.ho.javapractice;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.UUID;

import com.github.jhonnymertz.wkhtmltopdf.wrapper.Pdf;
import com.github.jhonnymertz.wkhtmltopdf.wrapper.configurations.WrapperConfig;
import com.github.jhonnymertz.wkhtmltopdf.wrapper.params.Param;

public class CreatePdf {
    public static void main(String[] args) {
        try {
            create();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void create() throws IOException, InterruptedException {
        WrapperConfig wrapperConfig = new WrapperConfig("/C:/Program Files/wkhtmltopdf/bin/wkhtmltopdf.exe");

        String[] test = {};
        Pdf pdf = new Pdf(wrapperConfig);
        pdf.addPageFromUrl(String.format("http://localhost:8081/api/giro/1"));
//        pdf.addPageFromUrl(String.format("https://testbiz.gasstong.co.kr/gas/common/pdf-view/regular-inspection?id=1"));
//        pdf.addPageFromUrl(String.format("https://www.google.com"));
//        pdf.addPageFromUrl(String.format("https://www.google.com"));

        pdf.addParam(
                new Param("--margin-bottom", "2"),
                new Param("--margin-left", "2"),
                new Param("--margin-right", "2"),
                new Param("--margin-top", "2")
        );

        pdf.setSuccessValues(Arrays.asList(0, 1));

        pdf.saveAs("I:/upload/shop/test/테스트.pdf");
    }
}

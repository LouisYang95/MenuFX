package com.example;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.example.model.FinancialReport;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.time.LocalDate;


public class formFinancialReportController {
        // variable pour gérer les enregistrements
        private RecordManager recordManager;

        // variable pour générer les rapports
         private ReportGenerator reportGenerator;

        public formFinancialReportController() {
                recordManager = new RecordManager();
                reportGenerator = new ReportGenerator();
        }

        // Générer un rapport financier au format PDF
        public void generatePDFReport() {
                FinancialReport financialReport = reportGenerator.reportGenerator(recordManager);
                Document document = new Document();
                try {
                        PdfWriter.getInstance(document, new FileOutputStream("rapport_financier_" + LocalDate.now() + ".pdf"));
                        document.open();
                        document.add(new Paragraph("Rapport financier pour le restaurant"));
                        document.add(new Paragraph("Total recettes : " + financialReport.getTotalRecipes()));
                        document.add(new Paragraph("Total dépenses : " + financialReport.getTotalExpenditure()));
                        document.add(new Paragraph("Bénéfice : " + financialReport.getBenefice()));

                } catch (FileNotFoundException | DocumentException e) {
                        e.printStackTrace();
                }
        }
}



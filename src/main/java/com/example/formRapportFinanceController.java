package com.example;

import com.example.model.RapportFinancier;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.time.LocalDate;

public class formRapportFinanceController {



    // Générer le fichier PDF
    RapportFinancier rapportFinancier = new RapportFinancier()
    reportGenerator.generateReport(recordManager, "financial_report.pdf");
            }

public void genererRapportPDF() {
        Document document = new Document();
        try {
        PdfWriter.getInstance(document, new FileOutputStream("rapport_financier_" + LocalDate.now() + ".pdf"));
        document.open();
        document.add(new Paragraph("Rapport financier pour le restaurant"));
        document.add(new Paragraph("Total recettes : " + getTotalRecettes()));
        document.add(new Paragraph("Total dépenses : " + getTotalDepenses()));
        document.add(new Paragraph("Bénéfice : " + getBenefice()));
        document.close();
        } catch (FileNotFoundException | DocumentException e) {
        e.printStackTrace();
        }
        }
        }


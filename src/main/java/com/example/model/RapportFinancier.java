package com.example.model;

        import com.itextpdf.text.Document;
        import com.itextpdf.text.DocumentException;
        import com.itextpdf.text.Paragraph;
        import com.itextpdf.text.pdf.PdfWriter;

        import java.io.FileNotFoundException;
        import java.io.FileOutputStream;
        import java.time.LocalDate;
        import java.util.ArrayList;
        import java.util.List;

public class RapportFinancier {

    private List<Double> recettes;
    private List<Double> depenses;

    public RapportFinancier() {
        this.recettes = new ArrayList<>();
        this.depenses = new ArrayList<>();
    }

    public void ajouterDepense(double montant) {
        this.depenses.add(montant);
    }

    public double getTotalRecettes() {
        return this.recettes.stream().mapToDouble(Double::doubleValue).sum();
    }

    public double getTotalDepenses() {
        return this.depenses.stream().mapToDouble(Double::doubleValue).sum();
    }

    public double getBenefice() {
        return getTotalRecettes() - getTotalDepenses();
    }


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


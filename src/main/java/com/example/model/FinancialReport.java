package com.example.model;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

public class FinancialReport {

    private List<String> recipes;
    private List<String> Expenditure;

    public FinancialReport() {
        this.recipes = new ArrayList<>();
        this.Expenditure = new ArrayList<>();
    }

    public void addExpense(String amount) {
        this.Expenditure.add(amount);
    }

    public void addRecipe(String amount) {
        this.recipes.add(amount);
    }


    public String getTotalRecipes() {
        double total = this.recipes.stream().mapToDouble(Double::parseDouble).sum();
        return String.format("%.2f", total);
    }

    public String getTotalExpenditureAsString() {
        double total = this.Expenditure.stream().mapToDouble(Double::parseDouble).sum();
        return String.format("%.2f", total);
    }


    public String getBenefice() {
        return getBenefice();
    }

    public String getTotalExpenditure() {
        return getTotalExpenditure();
    }

    public void createFinancialReportPDF(String filePath) {
        try {
            // Create a new PDF document
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(filePath));
            document.open();

            // Add title to the document
            Paragraph title = new Paragraph("Financial Report");
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);

            // Add recipes to the document
            Paragraph recipesHeader = new Paragraph("Recipes");
            document.add(recipesHeader);
            for (String recipe : this.recipes) {
                Paragraph recipeParagraph = new Paragraph(recipe);
                document.add(recipeParagraph);
            }

            // Add expenditure to the document
            Paragraph expenditureHeader = new Paragraph("Expenditure");
            document.add(expenditureHeader);
            for (String expenditure : this.Expenditure) {
                Paragraph expenditureParagraph = new Paragraph(expenditure);
                document.add(expenditureParagraph);
            }

            // Add total recipes and expenditure to the document
            Paragraph totalRecipesParagraph = new Paragraph("Total Recipes: " + this.getTotalRecipes());
            document.add(totalRecipesParagraph);
            Paragraph totalExpenditureParagraph = new Paragraph("Total Expenditure: " + this.getTotalExpenditureAsString());
            document.add(totalExpenditureParagraph);

            // Add benefice to the document
            Paragraph beneficeParagraph = new Paragraph("Benefice: " + this.getBenefice());
            document.add(beneficeParagraph);

            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
package com.example.model;



import com.itextpdf.text.Document;

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


    public String getTotalRecipes() {
        double total = this.recipes.stream().mapToDouble(Double::parseDouble).sum();
        return String.format("%.2f", total);
    }

    public String getTotalExpenditureAsString() {
        double total = this.Expenditure.stream().mapToDouble(Double::doubleValue).sum();
        return String.format("%.2f", total);
    }


    public String getBenefice() {
        return getBenefice();
    }

    public String getTotalExpenditure() {
        return getTotalExpenditure();
    }


}





    public class FinancialReport {
        private List<String> Recipes;
        private List<String> Expenditure;

        public FinancialReport() {
            this.Recipes = new ArrayList<>();
            this.Expenditure = new ArrayList<>();
        }


        public void enregistrerRecipes(String Recipes) {
            this.Recipes.add(Recipes);
        }

        public void enregistrerExpenditure(String Expenditure) {
            this.Expenditure.add(Expenditure);
        }

        public double getTotalRecipes() {
            return this.Recipes.stream().mapToDouble(Double::doubleValue).sum();
        }

        public double getTotalExpenditure() {
            return this.Expenditure.stream().mapToDouble(Double::doubleValue).sum();
        }

        public void genererRapportFinancierPDF(String nomFichier) {
            Document  document =  new Document();
            Page page = document.getPages().add();
            TextBuilder textBuilder = new  TextBuilder(page);

            TextFragment titre = new TextFragment("Rapport Financier");
            titre.getTextState().setFontSize(20);
            titre.getTextState().setFont(FontRepository.findFont("TimesNewRoman"));
            titre.getPosition().setX(100);
            titre.getPosition().setY(700);
            textBuilder.appendText(titre);

            TextFragment totalRecipes = new TextFragment("Total des recettes : " + String.format("%.2f", getTotalRecipes()));
            totalRecipes.getPosition().setX(100);
            totalRecipes.getPosition().setY(650);
            textBuilder.appendText(totalRecipes);

            TextFragment totalExpenditure = new TextFragment("Total des dépenses : " + String.format("%.2f", getTotalExpenditure()));
            totalDepenses.getPosition().setX(100);
            totalDepenses.getPosition().setY(600);
            textBuilder.appendText(totalExpenditure);

            TextFragment resultat = new TextFragment("Résultat : " + String.format("%.2f", getTotalRecipes() - getTotalExpenditure()));
            resultat.getPosition().setX(100);
            resultat.getPosition().setY(550);
            textBuilder.appendText(resultat);

            document.save(nomFichier);
        }
    }

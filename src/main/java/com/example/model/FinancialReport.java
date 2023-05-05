package com.example.model;



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
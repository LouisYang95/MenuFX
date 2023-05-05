package com.example.controller.financial;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.example.model.FinancialReport;
import com.itextpdf.text.pdf.TextField;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class formFinancialReportController implements Initializable {

    @FXML
    private TextField expendituresTextField;
    @FXML
    private TextField recipesTextField;
    @FXML
    private Button financialButton;
    @FXML
    private Button pdfButton;

    FinancialReport financialReport = new FinancialReport();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        financialReport.addExpense("50.00");
        financialReport.addExpense("75.00");
        financialReport.addRecipe("100.00");
        financialReport.addRecipe("150.00");

        pdfButton.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                // Generate the PDF document
                String filePath = "financial-report.pdf";
                financialReport.createFinancialReportPDF(filePath);
            }
        });
    }
}
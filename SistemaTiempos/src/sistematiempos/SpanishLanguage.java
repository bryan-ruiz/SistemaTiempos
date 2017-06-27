/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistematiempos;

/**
 *
 * @author Bryan
 */
public class SpanishLanguage {
    
    
    private String passwordError, notNumberError, btnScanCodeBar, btnTotalUniquePrint, btnStadisticsString, lblMorningClosingString,
            lblNightClosingString, lblPeriodString, lblDayString, lblNightString, lblMoneyString, btnSaveString, lblNumbersListString,
            btnRemoveString, lblTotalString, btnPayString, lblNumberMoneyString, btnPrint, lblCompanyName, lblPercentage, lblPassword,
            startDate, finishDate, search, lblCodeBar, btnReset;
    private static SpanishLanguage instance = null;
    
    private SpanishLanguage() {
        this.passwordError = "Contraseña no valida."; 
        this.notNumberError = "No utilice simbolos."; 
        this.btnScanCodeBar = "Escanear código de barras"; 
        this.btnTotalUniquePrint = "Impresion total único";
        this.btnStadisticsString = "Estadísticas";
        this.lblPercentage = "Porcentaje estadístico:";
        this.lblMorningClosingString = "Cierre de la mañana:";
        this.lblNightClosingString = "Cierre de la noche:";
        this.lblPeriodString = "Periodo de apuestas:";
        this.lblDayString = "Día";  
        this.lblNightString = "Noche"; 
        this.lblMoneyString = "Plata";  
        this.btnSaveString = "Guardar"; 
        this.lblNumbersListString = "Números"; 
        this.btnRemoveString = "Excluir"; 
        this.lblTotalString = "Total:"; 
        this.btnPayString = "Pagar"; 
        this.lblNumberMoneyString = "Total número"; 
        this.btnPrint = "Imprimir"; 
        this.lblCompanyName = "Nombre de la empresa:";  
        this.lblPercentage = "Porcentaje estadístico:"; 
        this.lblPassword = "Contraseña:"; 
        this.startDate = "Fecha inicial: "; 
        this.finishDate = "Fecha final: "; 
        this.search = "Buscar"; 
        this.lblCodeBar = "Codigo de barras:"; 
        this.btnReset = "Reajustar";
    }
    
    public static SpanishLanguage getInstance() {
        if (instance == null) {
            instance = new SpanishLanguage();
        }
        return instance;
    }

    public String getBtnReset() {
        return btnReset;
    }

    public String getLblCodeBar() {
        return lblCodeBar;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getFinishDate() {
        return finishDate;
    }

    public String getSearch() {
        return search;
    }

    public String getLblCompanyName() {
        return lblCompanyName;
    }

    public String getLblPercentage() {
        return lblPercentage;
    }

    public String getLblPassword() {
        return lblPassword;
    }

    public String getBtnPrint() {
        return btnPrint;
    }

    public String getLblNumberMoneyString() {
        return lblNumberMoneyString;
    }
    
    public String getPasswordError() {
        return passwordError;
    }

    public String getNotNumberError() {
        return notNumberError;
    }

    public String getBtnScanCodeBar() {
        return btnScanCodeBar;
    }

    public String getBtnTotalUniquePrint() {
        return btnTotalUniquePrint;
    }

    public String getBtnStadisticsString() {
        return btnStadisticsString;
    }

    public String getLblMorningClosingString() {
        return lblMorningClosingString;
    }

    public String getLblNightClosingString() {
        return lblNightClosingString;
    }

    public String getLblPeriodString() {
        return lblPeriodString;
    }

    public String getLblDayString() {
        return lblDayString;
    }

    public String getLblNightString() {
        return lblNightString;
    }

    public String getLblMoneyString() {
        return lblMoneyString;
    }

    public String getBtnSaveString() {
        return btnSaveString;
    }

    public String getLblNumbersListString() {
        return lblNumbersListString;
    }

    public String getBtnRemoveString() {
        return btnRemoveString;
    }

    public String getLblTotalString() {
        return lblTotalString;
    }

    public String getBtnPayString() {
        return btnPayString;
    }
    
    
}

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
public class ChineseLanguage {
    private String passwordError, notNumberError, btnScanCodeBar, btnTotalUniquePrint, btnStadisticsString, lblMorningClosingString,
            lblNightClosingString, lblPeriodString, lblDayString, lblNightString, lblMoneyString, btnSaveString, lblNumbersListString,
            btnRemoveString, lblTotalString, btnPayString, lblNumberMoneyString, btnPrint, lblCompanyName, lblPercentage, lblPassword,
            startDate, finishDate, search, lblCodeBar, btnReset, lblDay, lblMonth, lblYear, lblTicket, 
            btnSearch, lblTime, lblDate, btnDelete;
    private static ChineseLanguage instance = null;
    
    private ChineseLanguage() {
        this.passwordError = "密码错误."; 
        this.notNumberError = "不可用符号."; 
        this.btnScanCodeBar = "订单搜索"; 
        this.btnTotalUniquePrint = "打印总单";
        this.btnStadisticsString = "统计";
        this.lblPercentage = "赔率:";
        this.lblMorningClosingString = "上午结束时间:";
        this.lblNightClosingString = "晚上结束时间:";
        this.lblPeriodString = "选择时段:";
        this.lblDayString = "上午";  
        this.lblNightString = "晚上";
        this.lblMoneyString = "钱"; 
        this.btnSaveString = "添加";
        this.lblNumbersListString = "号码";
        this.btnRemoveString = "删除号码";
        this.lblTotalString = "总计:";
        this.btnPayString = "支付";
        this.lblNumberMoneyString = "此号码还可以下";
        this.btnPrint = "打印";
        this.lblCompanyName = "生意名称:"; 
        this.lblPercentage = "赔率:";
        this.lblPassword = "密码:";
        this.startDate = "开始日期: ";
        this.finishDate = "结束日期: ";
        this.search = "搜索";
        this.lblCodeBar = "条形码:";
        this.btnReset = "重置";
        this.lblDay = "Diaa";
        this.lblMonth = "Mess";
        this.lblYear = "Añoo";
        this.lblTicket = "Tiquetee";
        this.btnSearch = "Buscarr";
        this.lblTime = "Tiempoo";
        this.lblDate = "Fechaa";
        this.btnDelete = "Eliminarr";    
    }

    public String getLblDay() {
        return lblDay;
    }

    public String getLblMonth() {
        return lblMonth;
    }

    public String getBtnSearch() {
        return btnSearch;
    }

    public String getLblTime() {
        return lblTime;
    }

    public String getLblDate() {
        return lblDate;
    }

    public String getBtnDelete() {
        return btnDelete;
    }

    public String getLblTicket() {
        return lblTicket;
    }

    public String getLblYear() {
        return lblYear;
    }

    public String getLblNumberMoneyString() {
        return lblNumberMoneyString;
    }
    
    public static ChineseLanguage getInstance() {
        if (instance == null) {
            instance = new ChineseLanguage();
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
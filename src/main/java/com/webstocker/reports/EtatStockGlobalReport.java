package com.webstocker.reports;

import com.webstocker.domain.wrapper.EtatStockGlobalAimasWrapper;
import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.base.expression.AbstractSimpleExpression;
import net.sf.dynamicreports.report.builder.column.TextColumnBuilder;
import net.sf.dynamicreports.report.builder.component.ComponentBuilder;
import net.sf.dynamicreports.report.builder.component.HorizontalListBuilder;
import net.sf.dynamicreports.report.builder.component.SubreportBuilder;
import net.sf.dynamicreports.report.constant.PageOrientation;
import net.sf.dynamicreports.report.constant.PageType;
import net.sf.dynamicreports.report.definition.ReportParameters;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;

import static net.sf.dynamicreports.report.builder.DynamicReports.*;

/**
 * @author Athanase
 */
public class EtatStockGlobalReport {
    List<EtatStockGlobalWrapper> reportData;
    String dateDebut;
    String dateFin;

    public EtatStockGlobalReport(List<EtatStockGlobalAimasWrapper> reportData, String dateDebut, String dateFin) {
        this.reportData = generateReportData(reportData);
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        build();
    }

    public JasperReportBuilder build() {
        SubreportBuilder subreport = cmp.subreport(new EtatStockGlobalReport.SubreportExpression())
            .setDataSource(new EtatStockGlobalReport.SubreportDataSourceExpression(reportData));
        JasperReportBuilder reportBuilder = report();

        try {
            reportBuilder
                .title(Templates.createTitleComponent("Etat du stock"),
                    afficherPeriode(),
                    cmp.verticalGap(10))
                .detail(subreport, cmp.verticalGap(20))
                .setPageFormat(PageType.A4, PageOrientation.LANDSCAPE)
                .pageFooter(Templates.footerComponent)
                .setDataSource(createDataSource());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return reportBuilder;
    }

    private JRDataSource createDataSource() {
        return new JREmptyDataSource(1);
    }

    private List<EtatStockGlobalWrapper> generateReportData(List<EtatStockGlobalAimasWrapper> etatStock) {

        List<EtatStockGlobalWrapper> wrapperList = new LinkedList<>();
        for (EtatStockGlobalAimasWrapper erm : etatStock) {
            wrapperList.add(new EtatStockGlobalWrapper(erm));
        }
        return wrapperList;
    }

    private void addCustomerAttribute(HorizontalListBuilder list, String label, String value) {
        if (value != null) {
            list.add(cmp.text(label + " :").setFixedColumns(8).setStyle(Templates.boldStyle), cmp.text(value)).newRow();
        }
    }

    private ComponentBuilder<?, ?> afficherPeriode() {
        HorizontalListBuilder list = cmp.horizontalList();//.setBaseStyle(stl.style().setTopBorder(stl.pen1Point()).setLeftPadding(10));

//        addCustomerAttribute(list, "Période du stock global", " ");
//        addCustomerAttribute(list, "Date début période", dateDebut);
//        addCustomerAttribute(list, "Date fin période", dateFin);
        // addCustomerAttribute(list, "Approuvé par", "Goussou K Lazare");

        LocalDate localdate1 = LocalDate.parse(dateDebut);
        LocalDate localdate2 = LocalDate.parse(dateFin);
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd MMMM yyyy");
        addCustomerAttribute(list, "Période du", dateFormat.format(localdate1) + " au " + dateFormat.format(localdate2));

        return cmp.verticalList(list);
    }

    private class SubreportExpression extends AbstractSimpleExpression<JasperReportBuilder> {

        private static final long serialVersionUID = 1L;

        @Override
        public JasperReportBuilder evaluate(ReportParameters reportParameters) {
            Object object = new Object();
            int masterRowNumber = reportParameters.getReportRowNumber();
            JasperReportBuilder report = report();
            report.setTemplate(Templates.reportTemplate);

            TextColumnBuilder<String> productColumn = col.column("Produit", "produit", type.stringType()).setFixedWidth(100);//.setPrintRepeatedDetailValues(false);
            TextColumnBuilder<Long> stockInitial = col.column("Stock initial", "stockInitial", type.longType());//.setFixedWidth(200);//.setPrintRepeatedDetailValues(false);
            TextColumnBuilder<Long> quantiteVendueColumn = col.column("Quantité Vendue", "quantiteVendue", type.longType());//.setPrintRepeatedDetailValues(false);
            TextColumnBuilder<Long> arrivageColumn = col.column("Valeur des ventes", "valeurVente", type.longType());//.setPrintRepeatedDetailValues(false);
            TextColumnBuilder<Long> qtePromoColumn = col.column("Quantité sortie (promotion)", "quantitePromotion", type.longType());//.setFixedWidth(200);//.setPrintRepeatedDetailValues(false);
            TextColumnBuilder<Long> balanceClotureColumn = col.column("stock", "quantiteFinale", type.longType());//.setFixedWidth(200);//.setPrintRepeatedDetailValues(false);
            TextColumnBuilder<Long> quantiteGlobalEnStock = col.column("Stock disponible", "quantiteGlobalEnStock", type.longType());//.setFixedWidth(200);//.setPrintRepeatedDetailValues(false);
            TextColumnBuilder<Long> qteTransfertColumn = col.column("Quantité transfert", "quantiteTransfert", type.longType());//.setFixedWidth(200);//.setPrintRepeatedDetailValues(false);
            TextColumnBuilder<Long> arrivage = col.column("Arrivage", "arrivage", type.longType());//.setFixedWidth(200);//.setPrintRepeatedDetailValues(false);
            TextColumnBuilder<Long> qtePerteColumn = col.column("Quantité perte", "quantitePerte", type.longType());
            report
                .columns(productColumn, stockInitial, quantiteVendueColumn, arrivageColumn, qtePromoColumn, qteTransfertColumn, arrivage, qtePerteColumn, quantiteGlobalEnStock)
                .setColumnStyle(stl.style().setBorder(stl.pen1Point()).setPadding(5));

            return report;
        }
    }

    private class SubreportDataSourceExpression extends AbstractSimpleExpression<JRDataSource> {

        private static final long serialVersionUID = 1L;
        List<EtatStockGlobalWrapper> reportData;

        public SubreportDataSourceExpression(List<EtatStockGlobalWrapper> reportData) {
            super();
            this.reportData = reportData;
        }

        @Override
        public JRDataSource evaluate(ReportParameters reportParameters) {
            JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(reportData);
            return beanCollectionDataSource;
        }
    }
}

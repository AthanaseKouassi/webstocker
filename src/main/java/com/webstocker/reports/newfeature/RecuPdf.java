package com.webstocker.reports.newfeature;

import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.Style;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.borders.SolidBorder;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Text;
import com.itextpdf.layout.properties.HorizontalAlignment;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;
import com.webstocker.domain.BonDeSortie;
import com.webstocker.domain.Facture;
import com.webstocker.domain.LigneBonDeSortie;
import com.webstocker.domain.Reglement;
import com.webstocker.repository.FactureRepository;
import com.webstocker.repository.ReglementRepository;
import com.webstocker.utilitaires.NombreEnChiffre;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.List;


@Slf4j
@Component
public class RecuPdf {

    private static final String TITRE_RECU = "RECU DE PAIEMENT CLIENT ";

    @Autowired
    private ReglementRepository reglementRepository;
    @Autowired
    private FactureRepository factureRepository;

    private BigDecimal totalSolde = BigDecimal.ZERO;
    private BigDecimal totalResteSolde = BigDecimal.ZERO;

    public void titreRecu(Document doc) {
        Table table = new Table(UnitValue.createPercentArray(new float[]{25, 50f, 25f})).useAllAvailableWidth();

        table.addCell(createCellTitre(" ", 90).setHorizontalAlignment(HorizontalAlignment.CENTER));
        table.addCell(createCellTitre(TITRE_RECU, 200).setHorizontalAlignment(HorizontalAlignment.CENTER));
        table.addCell(createCellTitre(" ", 90));
        table.setHorizontalAlignment(HorizontalAlignment.RIGHT);

        doc.add(table);
    }


    public Paragraph createBorderedText() {
        Paragraph container = new Paragraph();

        String info = "Date reglement :             \n" +
            "Client :             \n" +
            "Numero Facture :             \n" +
            "Commercial :             ";

        Text one = new Text(info).setBold();
        container.add(one);
        container.setBorder(new SolidBorder(ColorConstants.BLACK, 0f));
        container.setBorderRight(Border.NO_BORDER);
        container.setBorderLeft(Border.NO_BORDER);
        return container;
    }

    public Paragraph createBorderedText2(BonDeSortie bonDeSortie) {
        Paragraph container = new Paragraph();
        Facture facture = factureRepository.findByBonDeSortie(bonDeSortie);

        String repInfo = facture.getDateFacture() + "\n" +
            facture.getClient().getNomClient() + "\n" +
            bonDeSortie.getNumeroFactureNormalise() + "\n" +
            bonDeSortie.getDemandeur().getLastName() + " " + bonDeSortie.getDemandeur().getFirstName();

        Text two = new Text(repInfo);
        container.setBorder(new SolidBorder(ColorConstants.BLACK, 0f));
        container.setBorderLeft(Border.NO_BORDER);
        container.setBorderRight(Border.NO_BORDER);
        container.add(two);

        return container;
    }

    public void addTableRecu(Document doc, BonDeSortie bonDeSortie) {

        final Facture facture = factureRepository.findByBonDeSortie(bonDeSortie);
        List<Reglement> reglements = reglementRepository.findByFacture(facture);
        Table table = new Table(UnitValue.createPercentArray(new float[]{25, 20, 20f})).useAllAvailableWidth();
        addHeadTable(table);
        addTableRow(reglements, table);
        doc.add(table);
        Table table2 = new Table(UnitValue.createPercentArray(new float[]{25, 20, 20f})).useAllAvailableWidth();
        table2.setHorizontalAlignment(HorizontalAlignment.LEFT);
        addCellTotalHT(table2);
        doc.add(table2);
        doc.add(new Paragraph("Montant réglé en lettre : " + StringUtils.capitalize(NombreEnChiffre.getLettre(totalSolde.intValue())) + " Francs CFA"));
    }

    private void addHeadTable(Table table) {
        table.addHeaderCell(createHeaderCell("Produit", 60));
        table.addHeaderCell(createHeaderCell("Montant reglé (FCFA)", 15));
        table.addHeaderCell(createHeaderCell("Reste à payer (FCFA)", 10));
    }

    private void addTableRow(List<Reglement> reglements, Table table) {
        BigDecimal totalRegle = BigDecimal.ZERO;
        BigDecimal totalAsolde = BigDecimal.ZERO;
        for (Reglement reg : reglements) {
            double mont = 0;
            mont = reg.getFacture().getBonDeSortie().getLigneBonDeSorties().stream()
                .filter(r -> r.getProduit().equals(reg.getProduit())).mapToDouble(LigneBonDeSortie::getPrixDeVente).sum();

            table.addCell(createCellReglements(reg.getProduit().getNomProduit(), 60));
            table.addCell(createCellReglements(NumberFormat.getInstance().format(reg.getMontantReglement()), 40).setTextAlignment(TextAlignment.RIGHT));
            table.addCell(createCellReglements(NumberFormat.getInstance().format(BigDecimal.valueOf(mont).subtract(new BigDecimal(reg.getMontantReglement()))), 40).setTextAlignment(TextAlignment.RIGHT));
            totalRegle = totalRegle.add(new BigDecimal(reg.getMontantReglement()));
            totalAsolde = totalAsolde.add(BigDecimal.valueOf(mont).subtract(new BigDecimal(reg.getMontantReglement())));

        }
        totalSolde = totalRegle;
        totalResteSolde = totalAsolde;

    }

    private void addCellTotalHT(Table table) {
        table.addCell(createTotauxCell("TOTAL", 60)).setHeight(20);
        table.addCell(createTotauxCell(NumberFormat.getInstance().format(totalSolde), 40)
            .setTextAlignment(TextAlignment.RIGHT));
        table.addCell(createTotauxCell(NumberFormat.getInstance().format(totalResteSolde), 40)
            .setTextAlignment(TextAlignment.RIGHT));
    }

    private Cell createTotauxCell(String content, float with) {
        Cell cell = new Cell()
            .add(new Paragraph(content)).setPaddingTop(4f)
            .setWidth(with)
            .setBorderRight(Border.NO_BORDER)
            .setBorderLeft(Border.NO_BORDER)
            .setBorderTop(Border.NO_BORDER);

        Style style = new Style().setFontSize(10);
        cell.addStyle(style);
        return cell;
    }

    private Cell createCellReglements(String content, float width) {
        Cell cell = new Cell().add(new Paragraph(content)).setWidth(width);
        Style style = new Style().setFontSize(9);
        cell.addStyle(style);
        cell.setPaddingTop(10f);
        cell.setPaddingBottom(0f);
        return cell;
    }


    private Cell createHeaderCell(String header1, float width) {
        Cell cell = new Cell()
            .add(new Paragraph(header1).setTextAlignment(TextAlignment.CENTER))
            .setBackgroundColor(ColorConstants.LIGHT_GRAY)
            .setWidth(width)
            .setBold();

        Style style = new Style().setFontSize(10);
        cell.addStyle(style);
        return cell;
    }

    private Cell createCellTitre(String content, float width) {
        Cell cell = new Cell().add(new Paragraph(content)).setWidth(width).setBorder(Border.NO_BORDER);
        Style style = new Style().setFontSize(16).setBold().setFontColor(ColorConstants.BLACK);
        cell.addStyle(style);
        return cell;
    }


}

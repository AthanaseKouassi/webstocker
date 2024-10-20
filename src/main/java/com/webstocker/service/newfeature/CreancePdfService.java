package com.webstocker.service.newfeature;

import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.webstocker.domain.Produit;
import com.webstocker.reports.newfeature.CreanceClientPdf;
import com.webstocker.reports.newfeature.CreancePdf;
import com.webstocker.repository.ProduitRepository;
import com.webstocker.service.FactureService;
import com.webstocker.web.rest.dto.newfeature.CreanceDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.io.ByteArrayOutputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Transactional
@Service
public class CreancePdfService {

    @Autowired
    private CreanceClientPdf creanceClientPdf;
    @Autowired
    private CreancePdf creancePdf;
    @Inject
    private FactureService factureService;
    @Inject
    private ProduitRepository produitRepository;

    public ByteArrayOutputStream generatePdf(int numeroCategorie, Long idProduit) throws Exception {
        List<CreanceDto> creanceDtos = new ArrayList<>();
        Produit produit = new Produit();
        if (idProduit == 0) {
            creanceDtos = factureService.getFactureCreance(numeroCategorie);
        } else {
            produit = produitRepository.findOne(idProduit);
            creanceDtos = factureService.getFactureCreanceParProduit(numeroCategorie, idProduit);
        }
        final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        PdfWriter writer = new PdfWriter(outputStream);
        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf, PageSize.A4.rotate());
        document.add(new Paragraph(" ").setPadding(10f));

        creancePdf.titreRecu(document);
        document.add(new Paragraph(" "));
        document.add(new Paragraph(" "));

        Paragraph p = new Paragraph();
        p.add(creancePdf.createBorderedText(numeroCategorie, produit.getNomProduit()));
        document.add(p);
        document.add(new Paragraph(" "));
        creancePdf.addTableRecu(document, creanceDtos);

        document.close();
        return outputStream;
    }

    public ByteArrayOutputStream generatePdfCreanceClient(Long idClient, String dateDebut, String dateFin) throws Exception {
        List<CreanceDto> creanceDtos = factureService.getCreanceParClientAndPeriode(idClient, dateDebut, dateFin);

        LocalDate debut = LocalDate.parse(dateDebut, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        LocalDate fin = LocalDate.parse(dateFin, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        PdfWriter writer = new PdfWriter(outputStream);
        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf, PageSize.A4.rotate());
        document.add(new Paragraph(" ").setPadding(15f));

        creanceClientPdf.titreRecu(document);
        document.add(new Paragraph(" "));
        document.add(new Paragraph(" "));

        Paragraph p = new Paragraph();
        p.add(creanceClientPdf.createBorderedText(idClient, debut, fin));
        document.add(p);
        document.add(new Paragraph(" "));
        creanceClientPdf.addTableRecu(document, creanceDtos);

        document.close();

        return outputStream;
    }


}

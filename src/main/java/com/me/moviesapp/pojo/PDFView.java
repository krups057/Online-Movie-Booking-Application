package com.me.moviesapp.pojo; 

import java.awt.Color;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

public class PDFView extends AbstractPdfView {

	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document pdfdoc, PdfWriter pdfwriter, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		
		List<Cart> cartlist = (List<Cart>) model.get("cartitems");
		Paragraph title = new Paragraph("Thanks for booking");
		
		Phrase producttitle = new Phrase("Details:");
		
		Phrase thank = new Phrase("Thanks");
		
		PdfPTable table = new PdfPTable(3);
        table.setWidthPercentage(100.0f);
        table.setWidths(new float[] {3.0f, 2.0f, 1.0f});
        table.setSpacingBefore(10);
		
		PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.WHITE);
        cell.setPadding(5);
		
        cell.setPhrase(new Phrase("Product Title"));
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("Category"));
        table.addCell(cell);
 
        cell.setPhrase(new Phrase("Price"));
        table.addCell(cell);
        
        for (Cart ccc : cartlist) {
            table.addCell(ccc.getTitle());
            table.addCell(ccc.getGenre());
            table.addCell(String.valueOf(ccc.getTotalprice()));
        }
        
		pdfdoc.add(title);
		pdfdoc.add(producttitle);
		pdfdoc.add(table);
		pdfdoc.add(thank);
	}

	
}

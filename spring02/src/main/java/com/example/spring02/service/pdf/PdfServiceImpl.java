package com.example.spring02.service.pdf;

import java.io.FileOutputStream;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.example.spring02.model.shop.dto.CartDTO;
import com.example.spring02.service.shop.CartService;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

@Service
public class PdfServiceImpl implements PdfService {

	@Inject
	CartService cartService;
	
	@Override
	public String createPdf() {
		String result = "";
		try {
			Document document = new Document();
			PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("d:/sample.pdf"));
			document.open();
			BaseFont baseFont = BaseFont.createFont("c:/windows/fonts/malgun.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
			Font font = new Font(baseFont, 12);
			PdfPTable table = new PdfPTable(4);
			Chunk chunk = new Chunk("장바구니", font);
			Paragraph ph = new Paragraph(chunk);
			ph.setAlignment(Element.ALIGN_CENTER);
			document.add(ph);
			
			document.add(Chunk.NEWLINE);
			document.add(Chunk.NEWLINE);
			
			PdfPCell cell1 = new PdfPCell(new Phrase("상품명", font));
			cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
			PdfPCell cell2 = new PdfPCell(new Phrase("단가", font));
			cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
			PdfPCell cell3 = new PdfPCell(new Phrase("수량", font));
			cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
			PdfPCell cell4 = new PdfPCell(new Phrase("금액", font));
			cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
			
			table.addCell(cell1);
			table.addCell(cell2);
			table.addCell(cell3);
			table.addCell(cell4);
			
			List<CartDTO> items = cartService.listCart("kim");
			for(int i=0; i<items.size(); i++) {
				CartDTO dto = items.get(i);
				PdfPCell cellProductName = new PdfPCell(new Phrase(dto.getProduct_name(), font));
				PdfPCell cellPrice = new PdfPCell(new Phrase("" + dto.getPrice(), font));
				PdfPCell cellAmount = new PdfPCell(new Phrase("" + dto.getAmount(), font));
				PdfPCell cellMoney = new PdfPCell(new Phrase("" + dto.getMoney(), font));
				table.addCell(cellProductName);
				table.addCell(cellPrice);
				table.addCell(cellAmount);
				table.addCell(cellMoney);
			}
			document.add(table);
			document.close();
			result = "pdf파일이 생성되었습니다.";
		} catch (Exception e) {
			e.printStackTrace();
			result = "pdf파일 생성 실패...";
		}
		return result;
	}

}

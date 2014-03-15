package com.llamita.factullamita.view;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.llamita.factullamita.logic.ManageCustomerLogic;
import com.llamita.factullamita.util.Caster;
import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

public class GeneratePdf extends AbstractPdfView{
	@Autowired
	private ManageCustomerLogic manageCustomerLogic;
	
	@Override
	protected Document newDocument() {
		return new Document(new Rectangle(651.968503937f, 898.5826771654f), 14.17322834646f, 28.34645669291f, 0, 0);
	}
	
	protected void buildPdfDocument(Map<String, Object> model,Document document, PdfWriter writer, HttpServletRequest request,HttpServletResponse response) throws Exception {
		BillBean bill = (BillBean) model.get("bill");
		CustomerBean customer = bill.getCustomer();
		
		//SECCION ENCABEZADO				
		PdfPTable t1 = new PdfPTable (1);
		t1.getDefaultCell().setFixedHeight(92.2125984252f);	
		t1.getDefaultCell().setBorder(0);
		t1.addCell("");	
		
		//SECCION DATOS GENERALES
		PdfPTable t2 = new PdfPTable (4);
		t2.setWidthPercentage(100);
		t2.getDefaultCell().setFixedHeight(19.84251968504f);	
		t2.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);	
		t2.getDefaultCell().setBorder(0);	
		float[] columnWidths2 = {5,60,10,25};
		t2.setWidths(columnWidths2);
		
		SimpleDateFormat dt = new SimpleDateFormat("dd-MM-yyyy");
		
		Paragraph p1 = new Paragraph(bill.getReferralGuide(), new Font(Font.NORMAL,10));
		p1.setAlignment(Element.ALIGN_LEFT);
		
		Paragraph p2 = new Paragraph(dt.format(bill.getIssueDate()), new Font(Font.NORMAL,10));
		p2.setAlignment(Element.ALIGN_LEFT);
		
		Paragraph p3 = new Paragraph(customer.getName(), new Font(Font.NORMAL,10));		
		p3.setAlignment(Element.ALIGN_LEFT);
		
		Paragraph p4 = new Paragraph(customer.getAddress(), new Font(Font.NORMAL,10));		
		p4.setAlignment(Element.ALIGN_LEFT);
		
		t2.addCell(" ");
		t2.addCell(p3);
		t2.addCell(" ");
		t2.addCell(p1);
		t2.addCell(" ");
		t2.addCell(p4);
		t2.addCell(" ");
		t2.addCell(p2);		
		
		t2.addCell(" ");
		
		PdfPTable t21 = new PdfPTable(3);
		t21.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
		t21.getDefaultCell().setBorder(0);
		t21.addCell(new Paragraph(customer.getRuc(), new Font(Font.NORMAL,11)));
		t21.addCell(" ");
		t21.addCell(new Paragraph(bill.getExchangeRate(), new Font(Font.NORMAL,11)));		
		
		
		t2.addCell(t21);
		t2.addCell(" ");
		t2.addCell(new Paragraph(bill.getConditions(), new Font(Font.NORMAL,11)));	
		//SECCION LISTA ITEMS
		PdfPTable t3 = new PdfPTable (4);
		t3.setWidthPercentage(100);
		t3.getDefaultCell().setFixedHeight(15.00787401575f);	
		t3.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
		t3.getDefaultCell().setBorder(0);		
		float[] columnWidths3 = {5, 75, 10, 10};
		t3.setWidths(columnWidths3);
		
		t3.addCell("");t3.addCell("");t3.addCell("");t3.addCell("");		
		for (int i = 0; i < 30; i++) {		
			if(i<bill.getDetails().size()){

				BillDetailBean billDetail = bill.getDetails().get(i);
				String decription = billDetail.getDescription();
				int count = decription.length();
				int aux = count/75;
				
				for(int j=0; j<=aux; j++){
					int auxEnd = (((count-1)>((j+1)*75-1))?((j+1)*75-1):(count-1));
					if(j==0){					
						t3.addCell(new Paragraph(Integer.toString(billDetail.getQuantity()), new Font(Font.NORMAL,11)));
						t3.addCell(new Paragraph(decription.substring(j*75, auxEnd), new Font(Font.NORMAL,10)));
						t3.addCell(new Paragraph(billDetail.getUnitPrice(), new Font(Font.NORMAL,11)));
						t3.addCell(new Paragraph(billDetail.getAmount(), new Font(Font.NORMAL,11)));
					}else{
						t3.addCell(new Paragraph("", new Font(Font.NORMAL,11)));
						t3.addCell(new Paragraph(decription.substring(j*75, auxEnd), new Font(Font.NORMAL,10)));
						t3.addCell(new Paragraph("", new Font(Font.NORMAL,11)));
						t3.addCell(new Paragraph("", new Font(Font.NORMAL,11)));
					}				
				}
				i+=aux;
			}else{
				t3.addCell("");t3.addCell("");t3.addCell("");t3.addCell("");	
			}
		}
		
		
		//SECCION FINAL
		PdfPTable t4 = new PdfPTable (4);
		t4.setWidthPercentage(100);
//		t4.getDefaultCell().setFixedHeight(13.67716535433f);	
        t4.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
		t4.getDefaultCell().setBorder(0);		
		float[] columnWidths4 = {5,75,11,9};
		t4.setWidths(columnWidths4);
		
		Paragraph p5 = new Paragraph(bill.getSon(), new Font(Font.NORMAL,11));
		p5.setAlignment(Element.ALIGN_LEFT);
		
		Paragraph p6 = new Paragraph(bill.getSubtotal(), new Font(Font.NORMAL,11));
		p6.setAlignment(Element.ALIGN_RIGHT);
		
		t4.addCell("");
		t4.addCell(p5);
		t4.addCell("");
		t4.addCell(p6);	
		
		PdfPTable t5 = new PdfPTable (4);
		t5.setWidthPercentage(100);
		t5.getDefaultCell().setFixedHeight(22.67716535433f);	
		t5.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
		t5.getDefaultCell().setBorder(0);		
		float[] columnWidths5 = {5,75,11,9};
		t5.setWidths(columnWidths5);
		
		t5.addCell("");
		t5.addCell("");
		t5.addCell(new Paragraph("18", new Font(Font.NORMAL,11)));
		t5.addCell(new Paragraph(bill.getIgv(), new Font(Font.NORMAL,11)));	
		
		PdfPTable t6 = new PdfPTable (4);
		t6.setWidthPercentage(100);
		t6.getDefaultCell().setFixedHeight(22.67716535433f);	
		t6.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
		t6.getDefaultCell().setBorder(0);		
		float[] columnWidths6 = {5,75,11,9};
		t6.setWidths(columnWidths6);
		
		t6.addCell("");
		t6.addCell("");
		t6.addCell("");
		t6.addCell(new Paragraph(bill.getTotal(), new Font(Font.NORMAL,11)));		
		
	
		PdfPTable tb = new PdfPTable (1);
		tb.getDefaultCell().setFixedHeight(210.0f);	
		tb.getDefaultCell().setBorder(0);
		tb.addCell("");	

		document.add(t1);
		document.add(t2);
		document.add(t3);
		document.add(tb);
		document.add(t4);
		document.add(t5);
		document.add(t6);
	}
}

package com.llamita.factullamita.view;

import java.text.SimpleDateFormat;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.llamita.factullamita.logic.ManageCustomerLogic;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

public class GeneratePdf extends AbstractPdfView{
	@Autowired
	private ManageCustomerLogic manageCustomerLogic;
	
	@Override
	protected Document newDocument() {
		return new Document(new Rectangle(603.7795275591f, 847.5590551181f), 36.85039370079f, 25.51181102362f, 0, 0);
	}
	
	protected void buildPdfDocument(Map<String, Object> model,Document document, PdfWriter writer, HttpServletRequest request,HttpServletResponse response) throws Exception {
		BillBean bill = (BillBean) model.get("bill");
		CustomerBean customer = bill.getCustomer();
		
		//SECCION ENCABEZADO				
		PdfPTable t1 = new PdfPTable (1);
		t1.getDefaultCell().setFixedHeight(138.8976377953f);
		t1.getDefaultCell().setBorder(0);
		t1.addCell("");	
		
		//SECCION DATOS GENERALES
		PdfPTable t2 = new PdfPTable (4);
		t2.setWidthPercentage(100);
		t2.getDefaultCell().setFixedHeight(17.00787401575f);	
		t2.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);	
		t2.getDefaultCell().setBorder(0);	
		float[] columnWidths2 = {7.85f, 50.26f, 13.61f, 28.28f};
		t2.setWidths(columnWidths2);
		
		SimpleDateFormat dt = new SimpleDateFormat("dd-MM-yyyy");
		
		Paragraph p1 = new Paragraph(customer.getName(), new Font(Font.TIMES_ROMAN,10));		
		p1.setAlignment(Element.ALIGN_LEFT);
		
		Paragraph p2 = new Paragraph(bill.getReferralGuide(), new Font(Font.TIMES_ROMAN,10));
		p2.setAlignment(Element.ALIGN_LEFT);
		
		Paragraph p3 = new Paragraph(customer.getAddress(), new Font(Font.TIMES_ROMAN,10));		
		p3.setAlignment(Element.ALIGN_LEFT);
		
		Paragraph p4 = new Paragraph(dt.format(bill.getIssueDate()), new Font(Font.TIMES_ROMAN,10));
		p4.setAlignment(Element.ALIGN_LEFT);
				
		t2.addCell(" ");
		t2.addCell(p1);
		t2.addCell(" ");
		t2.addCell(p2);
		t2.addCell(" ");
		t2.addCell(p3);
		t2.addCell(" ");
		t2.addCell(p4);		
		
		PdfPTable t3 = new PdfPTable(6);
		t3.setWidthPercentage(100);
		t3.getDefaultCell().setFixedHeight(17.00787401575f);
		t3.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
		t3.getDefaultCell().setBorder(0);
		float[] columnWidths3 = {5.75f, 20.41f, 4.71f, 28.28f, 8.9f, 31.95f};
		t3.setWidths(columnWidths3);	
		
		Paragraph p31 = new Paragraph(customer.getRuc(), new Font(Font.TIMES_ROMAN,10));		
		p1.setAlignment(Element.ALIGN_LEFT);
		
		Paragraph p32 = new Paragraph(bill.getExchangeRate(), new Font(Font.TIMES_ROMAN,10));
		p2.setAlignment(Element.ALIGN_LEFT);
		
		Paragraph p33 = new Paragraph(bill.getConditions(), new Font(Font.TIMES_ROMAN,10));		
		p3.setAlignment(Element.ALIGN_LEFT);
		
		t3.addCell(" ");
		t3.addCell(p31);
		t3.addCell(" ");
		t3.addCell(p32);
		t3.addCell(" ");
		t3.addCell(p33);	
		
		//SECCION LISTA ITEMS
		PdfPTable t4 = new PdfPTable (4);
		t4.setWidthPercentage(100);
		t4.getDefaultCell().setFixedHeight(17.00787401575f);	
		t4.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
		t4.getDefaultCell().setBorder(0);		
		float[] columnWidths4 = {6.80f, 67.01f, 10.99f, 15.20f};
		t4.setWidths(columnWidths4);
		
		t4.addCell(" ");t4.addCell(" ");t4.addCell(" ");t4.addCell(" ");
		int f = 0;
		for (int i = 0; i < 30; i++) {
			if(f<bill.getDetails().size()){

				BillDetailBean billDetail = bill.getDetails().get(f);
				String decription = billDetail.getDescription();
				int count = decription.length();
				int auxMaxCar = 77;
				int aux = count/auxMaxCar;
				
				for(int j=0; j<=aux; j++){
					int auxEnd = (((count-1)>((j+1)*auxMaxCar-1))?((j+1)*auxMaxCar):(count));
					if(j==0){					
						t4.addCell(new Paragraph(Integer.toString(billDetail.getQuantity()), new Font(Font.TIMES_ROMAN,10)));
						t4.addCell(new Paragraph(decription.substring(j*auxMaxCar, auxEnd), new Font(Font.TIMES_ROMAN,10)));
						
						PdfPTable t41 = new PdfPTable (1);
						t41.getDefaultCell().setFixedHeight(17.00787401575f);	
						t41.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
						t41.getDefaultCell().setBorder(0);	
						t41.addCell(new Paragraph(billDetail.getUnitPrice(), new Font(Font.TIMES_ROMAN,10)));	
						t4.addCell(t41);
						
						PdfPTable t42 = new PdfPTable (1);
						t42.getDefaultCell().setFixedHeight(17.00787401575f);	
						t42.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
						t42.getDefaultCell().setBorder(0);	
						t42.addCell(new Paragraph(billDetail.getAmount(), new Font(Font.TIMES_ROMAN,10)));	
						t4.addCell(t42);
					}else{
						t4.addCell(new Paragraph(" ", new Font(Font.TIMES_ROMAN,10)));
						t4.addCell(new Paragraph(decription.substring(j*auxMaxCar, auxEnd), new Font(Font.TIMES_ROMAN,10)));
						t4.addCell(new Paragraph(" ", new Font(Font.TIMES_ROMAN,10)));
						t4.addCell(new Paragraph(" ", new Font(Font.TIMES_ROMAN,10)));
					}				
				}
				i+=aux;
			}else{
				t4.addCell(" ");t4.addCell(" ");t4.addCell(" ");t4.addCell(" ");	
			}
			f++;
		}
		
		
		//SECCION FINAL
		PdfPTable t5 = new PdfPTable (4);
		t5.setWidthPercentage(100);
		t5.getDefaultCell().setFixedHeight(17.00787401575f);	
		t5.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
		t5.getDefaultCell().setBorder(0);		
		float[] columnWidths5 = {4.71f,69.10f,10.99f,15.2f};
		t5.setWidths(columnWidths5);
		
		Paragraph p51 = new Paragraph(bill.getSon(), new Font(Font.TIMES_ROMAN,10));
		p51.setAlignment(Element.ALIGN_LEFT);
		
		PdfPTable t51 = new PdfPTable (1);
		t51.getDefaultCell().setFixedHeight(17.00787401575f);	
		t51.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
		t51.getDefaultCell().setBorder(0);	
		t51.addCell(new Paragraph(bill.getSubtotal(), new Font(Font.TIMES_ROMAN,10)));
		
		t5.addCell(" ");
		t5.addCell(p51);
		t5.addCell(" ");
		t5.addCell(t51);
		
		PdfPTable t6 = new PdfPTable (3);
		t6.setWidthPercentage(100);
		t6.getDefaultCell().setFixedHeight(17.00787401575f);	
		t6.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
		t6.getDefaultCell().setBorder(0);		
		float[] columnWidths6 = {73.81f,10.99f,15.2f};
		t6.setWidths(columnWidths6);
		
		t6.addCell(" ");
		
		PdfPTable t61 = new PdfPTable (1);
		t61.getDefaultCell().setFixedHeight(17.00787401575f);	
		t61.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
		t61.getDefaultCell().setBorder(0);	
		t61.addCell(new Paragraph(" 18", new Font(Font.TIMES_ROMAN,10)));	
		t6.addCell(t61);
		
		t6.addCell(new Paragraph(bill.getIgv(), new Font(Font.TIMES_ROMAN,10)));
		
		t6.addCell(" ");
		t6.addCell(new Paragraph(bill.getCurrency().getSymbol(), new Font(Font.TIMES_ROMAN,10)));
		t6.addCell(new Paragraph(bill.getTotal(), new Font(Font.TIMES_ROMAN,10)));	
			
		PdfPTable tb = new PdfPTable (1);
		tb.getDefaultCell().setFixedHeight(5.669291338583f);	
		tb.getDefaultCell().setBorder(0);
		tb.addCell(" ");
		
		PdfPTable tb2 = new PdfPTable (1);
		tb2.getDefaultCell().setFixedHeight(4.251968503937f);	
		tb2.getDefaultCell().setBorder(0);
		tb2.addCell(" ");	

		document.add(t1);
		document.add(t2);
		document.add(t3);
		document.add(tb);
		document.add(t4);
		document.add(tb2);
		document.add(t5);
		document.add(t6);
	}
}

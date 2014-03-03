package com.llamita.factullamita.view;

import java.util.Calendar;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.llamita.factullamita.logic.ManageCustomerLogic;
import com.llamita.factullamita.util.Caster;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

public class GeneratePdf extends AbstractPdfView{
	@Autowired
	private ManageCustomerLogic manageCustomerLogic;
	
	protected void buildPdfDocument(Map<String, Object> model,Document document, PdfWriter writer, HttpServletRequest request,HttpServletResponse response) throws Exception {
		BillBean bill = (BillBean) model.get("bill");
		CustomerBean customer = Caster.customerModelToBean(manageCustomerLogic.getCustomer(bill.getIdCustomer()));
		
		//SECCION ENCABEZADO				
		PdfPTable t1 = new PdfPTable (1);
		t1.getDefaultCell().setFixedHeight(99.2125984252f);	
		t1.getDefaultCell().setBorder(0);
		t1.addCell("");	
		
		//SECCION DATOS GENERALES
		PdfPTable t2 = new PdfPTable (2);
		t2.setWidthPercentage(100);
		t2.getDefaultCell().setFixedHeight(19.84251968504f);	
		t2.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);	
		t2.getDefaultCell().setBorder(0);	
		float[] columnWidths2 = {55, 45};
		t2.setWidths(columnWidths2);
		
		t2.addCell(customer.getName());
		t2.addCell(bill.getReferralGuide());
		t2.addCell(customer.getAddress());
		t2.addCell(bill.getIssueDate().toString());		
		
		PdfPTable t21 = new PdfPTable (2);
		t21.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
		t21.getDefaultCell().setBorder(0);
		t21.addCell(customer.getRuc());
		t21.addCell(bill.getExchangeRate());		
		t2.addCell(t21);
		t2.addCell(bill.getConditions());	
		
		//SECCION LISTA ITEMS
		PdfPTable t3 = new PdfPTable (4);
		t3.setWidthPercentage(100);
		t3.getDefaultCell().setFixedHeight(17.00787401575f);	
		t3.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
		t3.getDefaultCell().setBorder(0);		
		float[] columnWidths3 = {10, 60, 15, 15};
		t3.setWidths(columnWidths3);
		
		t3.addCell("");t3.addCell("");t3.addCell("");t3.addCell("");		
		for (int i = 0; i < 30; i++) {
			if(i<bill.getDetails().size()){
				BillDetailBean billDetail = bill.getDetails().get(i);
				t3.addCell(Integer.toString(billDetail.getQuantity()));
				t3.addCell(billDetail.getDescription());
				t3.addCell(billDetail.getUnitPrice());
				t3.addCell(billDetail.getAmount());
			}else{
				t3.addCell("");t3.addCell("");t3.addCell("");t3.addCell("");	
			}
		}
		
		//SECCION FINAL
		PdfPTable t4 = new PdfPTable (2);
		t4.setWidthPercentage(100);
		t4.getDefaultCell().setFixedHeight(22.67716535433f);	
		t4.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
		t4.getDefaultCell().setBorder(0);		
		float[] columnWidths4 = {70, 30};
		t4.setWidths(columnWidths4);
		
		t4.addCell("SON "+bill.getSon());
		t4.addCell(bill.getSubtotal());	
		
		PdfPTable t5 = new PdfPTable (3);
		t5.setWidthPercentage(100);
		t5.getDefaultCell().setFixedHeight(22.67716535433f);	
		t5.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
		t5.getDefaultCell().setBorder(0);		
		float[] columnWidths5 = {35, 35, 30};
		t5.setWidths(columnWidths5);
		
		t5.addCell("");t5.addCell("");
		t5.addCell(bill.getIgv());	
		
		PdfPTable t51 = new PdfPTable (3);
		t51.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
		t51.getDefaultCell().setBorder(0);		
		float[] columnWidths51 = {30, 40, 30};
		t51.setWidths(columnWidths51);
		
		Calendar cal = Calendar.getInstance();
	    cal.setTime(bill.getIssueDate());
		t51.addCell(Integer.toString(cal.get(Calendar.DAY_OF_MONTH)));
		t51.addCell(Integer.toString(cal.get(Calendar.MONTH)));
		t51.addCell(Integer.toString(cal.get(Calendar.YEAR)));	
		
		t5.addCell(t51);t5.addCell("");
		t5.addCell(bill.getTotal());	
		
		PdfPTable tb = new PdfPTable (1);
		tb.getDefaultCell().setFixedHeight(5.669291338583f);	
		tb.getDefaultCell().setBorder(0);	
		tb.addCell("");		

		//Rectangle pageSize = new Rectangle(654.8031496063f, 977.9527559055f);
		//document.setPageSize(pageSize);
				
		document.add(t1);
		document.add(t2);
		document.add(t3);
		document.add(tb);
		document.add(t4);
		document.add(t5);	   
	}
}

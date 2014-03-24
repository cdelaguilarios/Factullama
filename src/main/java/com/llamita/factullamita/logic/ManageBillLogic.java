package com.llamita.factullamita.logic;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;

import com.llamita.factullamita.model.Bill;
import com.llamita.factullamita.model.BillDetail;
import com.llamita.factullamita.model.Customer;
import com.llamita.factullamita.repository.BillRepository;

import javax.xml.bind.DatatypeConverter;

@Service
public class ManageBillLogic {

	@Autowired
	private BillRepository billRepository;
	
	public List<Bill> listBill(){
        List<Bill> listBillDecoded = new ArrayList<Bill>();
        List<Bill> listBill = billRepository.listBill();

        for(Bill bill : listBill) {

            byte[] decodeSubTotal = Base64.decodeBase64(bill.getSubtotal().getBytes());
            byte[] decodeTotal    = Base64.decodeBase64(bill.getTotal().getBytes());

            bill.setSubtotal(new String(decodeSubTotal));
            bill.setTotal(new String(decodeTotal));           
           
            listBillDecoded.add(bill);
        }

        return listBillDecoded;
	}
	
	public void addBill(Bill bill){

        byte[] encodeSubTotal = Base64.encodeBase64(bill.getSubtotal().getBytes());
        byte[] encodeTotal    = Base64.encodeBase64(bill.getTotal().getBytes());

        bill.setState("1");
        bill.setSubtotal(new String(encodeSubTotal));
        bill.setTotal(new String(encodeTotal));

		billRepository.addOrUpdateBill(bill);
	}
	
	public void addBillDetail(BillDetail detail){
        byte[] encodeUnitPrice    = Base64.encodeBase64(detail.getUnitPrice().getBytes());
		byte[] encodeAmount = Base64.encodeBase64(detail.getAmount().getBytes());

		detail.setUnitPrice(new String(encodeUnitPrice));
		detail.setAmount(new String(encodeAmount));
		
		billRepository.addOrUpdateBillDetail(detail);
	}
	
	public void updBill(Bill bill){
		byte[] encodeSubTotal = Base64.encodeBase64(bill.getSubtotal().getBytes());
        byte[] encodeTotal    = Base64.encodeBase64(bill.getTotal().getBytes());
        
		bill.setState("1");
        bill.setSubtotal(new String(encodeSubTotal));
        bill.setTotal(new String(encodeTotal));
        
		billRepository.addOrUpdateBill(bill);
	}
	
	public Bill getBillByNumber(String number){
		return billRepository.getBillByField("number", number);
	}
	
	public Bill getBill(Integer id){
		Bill bill = billRepository.getBill(id);
         for(BillDetail detail : bill.getDetails()){
         	byte[] decodeUnitPrice    = Base64.decodeBase64(detail.getUnitPrice().getBytes());
     		byte[] decodeAmount = Base64.decodeBase64(detail.getAmount().getBytes());
     		
     		detail.setUnitPrice(new String(decodeUnitPrice));
     		detail.setAmount(new String(decodeAmount));    
         }            
		return bill;
	}
	
	public void delBill(Integer idBill){
		if(idBill !=null){
			Bill bill = getBill(idBill);
			if(bill!=null){
				bill.setState("0");
				billRepository.addOrUpdateBill(bill);
			}
		}
	}
	
}

package com.llamita.factullamita.logic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.llamita.factullamita.model.Bill;
import com.llamita.factullamita.model.BillDetail;
import com.llamita.factullamita.repository.BillRepository;

@Service
public class ManageBillLogic {

	@Autowired
	private BillRepository billRepository;
	
	public List<Bill> listBill(){
		return billRepository.listBill();
	}
	
	public void addBill(Bill bill){
		bill.setState("1");
		billRepository.addOrUpdateBill(bill);
	}
	
	public void addBillDetail(BillDetail detail){
		billRepository.addOrUpdateBillDetail(detail);
	}
	
	public Bill getBillByNumber(String number){
		return billRepository.getBillByField("number", number);
	}
	
	public Bill getBill(Integer id){
		return billRepository.getBill(id);
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

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
		billRepository.addOrUpdateBill(bill);
	}
	
	public void addBillDetail(BillDetail detail){
		billRepository.addOrUpdateBillDetail(detail);
	}
	
	public Bill getBillByNumber(String number){
		return billRepository.getBillByField("number", number);
	}
	
}

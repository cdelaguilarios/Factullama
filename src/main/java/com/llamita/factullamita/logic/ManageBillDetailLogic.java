package com.llamita.factullamita.logic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.llamita.factullamita.model.BillDetail;
import com.llamita.factullamita.repository.BillDetailRepository;

@Service
public class ManageBillDetailLogic {
	@Autowired
	private BillDetailRepository billDetailRepository;
	
	public BillDetail getBillDetail(Integer id){
		return billDetailRepository.getBillDetail(id);
	}
	
	public void delBillDetail(Integer idBillDetail){
		if(idBillDetail !=null){
			BillDetail billDetail = getBillDetail(idBillDetail);
			if(billDetail!=null){
				billDetailRepository.deleteBillDetail(billDetail);
			}
		}
	}
}

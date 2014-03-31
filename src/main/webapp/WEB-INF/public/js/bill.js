$(window).load(function(){         	
	//Eventos JQuery
	
	$('.quantity').keyup (function () {
		var quantity = $(this).val();
		var pu = $($(this).closest('tr')).children('td').eq(3).children('input').val();			
		var imp = $($(this).closest('tr')).children('td').eq(4).text( Number(pu * quantity).toFixed(2));
		updateAmount();
	});
	
	$('.unitPrice').keydown (function () {
		$(this).data('val',  $(this).val());
	});
			
	$('.unitPrice').keyup (function () {
		
		var newValueOrig = $(this).val();
		var newValue = Number($(this).val()).toFixed(2);
		var arryNewValue = ($(this).val()).split(".");
		var arryOldValue = ($(this).data('val')).split(".");
		
		if (isNaN(newValue)){
			if(arryNewValue.length == 3){
				var n = ($(this).val()).indexOf(".."); 
				if(n != -1){
					var newValAux = "";
					for(var i= 0;  i < n ; i++){
						newValAux = newValAux + ($(this).val())[i]; 
					}
					$(this).val(newValAux + ".00");
					setCaretPosition(this, newValAux.length + 1);
				}else{
					$(this).val($(this).data('val'));					
				}
			}else{
				$(this).val($(this).data('val'));
			}
		}else if( $.trim($(this).val()) == "" || newValue == "0.00" ){
			
			if( newValueOrig == "00.00"){
				$(this).val( Number(newValueOrig).toFixed(2));
				setCaretPosition(this, 1);
			}else{
				$(this).val("0.00");
				setCaretPosition(this, 0);
			}
			
		}else{
			if(arryNewValue.length == 1){
				$(this).val($(this).val() + ".00");
				setCaretPosition(this, arryNewValue[0].length);
			}else if(arryNewValue.length == 2){
				
				if(arryNewValue[0] != arryOldValue[0]){
					if($(this).data('val') == "0.00"){
						$(this).val( ($(this).val()).replace("0.00",".00"));
						setCaretPosition(this, arryNewValue[0].length - 1);
					}
				}else{
					if(arryNewValue[1].length > 2){						
						if( arryNewValue[1][0] != arryOldValue[1][0]){
							$(this).val( arryNewValue[0] + "." + arryNewValue[1][0] + arryNewValue[1][2] );
							setCaretPosition(this, ($(this).val()).length - 1 );
						}else{
							$(this).val( arryNewValue[0] + "." + arryNewValue[1][0] + arryNewValue[1][1] );
							setCaretPosition(this, ($(this).val()).length);							
						}
						
					}
				}								
			}
		}
		
		//Importe			
		
		var pu 	 = $(this).val();		
		var quantity = $($(this).closest('tr')).children('td').eq(1).children('input').val();						
		var imp = $($(this).closest('tr')).children('td').eq(4).text( Number(pu * quantity).toFixed(2));
		
		updateAmount();
				
	});
	
	//Funciones JavaScript
	
	function setCaretPosition(elem, caretPos) {	    

	    if(elem != null) {
	        if(elem.createTextRange) {
	            var range = elem.createTextRange();
	            range.move('character', caretPos);
	            range.select();
	        }
	        else {
	            if(elem.selectionStart) {
	                elem.focus();
	                elem.setSelectionRange(caretPos, caretPos);
	            }
	            else
	                elem.focus();
	        }
	    }
	}
	
	function updateAmount(){
		
		var sum = 0;
		
		$("#tbody-content > tr").each(function () {
			sum = parseFloat(sum) + parseFloat($($(this).closest('tr')).children('td').eq(4).text());
		});
		
		$("#subtotal").text(Number(sum).toFixed(2));
		$("#igv").text(Number(sum * 18 / 100).toFixed(2));
		$("#total").text(Number( sum * ( 1 + 18/100 )  ).toFixed(2));
		
	}
	
});
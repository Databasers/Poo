package seleziona;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Ordinamento implements Serializable{

	private static final long serialVersionUID = 1L;
	
	/**Ordinamento contiene i lotti selezionati e in che modo ordinarli(flag=0 ord normal flag=1 ord inverso)*/
	
	public Ordinamento(int f, Seleziona sel) {
		flag=f;
		select=sel;
		selez=new ArrayList<>(Arrays.asList(select));

	}
	
	/**Il metodo sceltaOrd seleziona in base a quale valore effettuare l'ordinamento*/
	
	public void sceltaOrd() {
		switch(select.getScelta()) {
		case 0:
			Collections.sort(selez, new EfficComparator());
		
		        break;
		case 1: Collections.sort(selez, new InvComparator());
		        break;
		case 2: Collections.sort(selez, new ValComparator());
		        break;
		} 
		
	}
	


	//
	/**Il metodo coeffEff effettua il bubleSort della lista in base al coeff. di Efficienza*/
	
	/*private void coeffEff() {
		for(int i=0;i<select.getCount();i++) {
			boolean f=false;
			for(int j=0;j<select.getCount()-1;j++) {
				if(flag==0) {
					if(select.lista[j].getCeff()<select.lista[j+1].getCeff()) {
						Lotti k=select.lista[j];
						select.lista[j]=select.lista[j+1];
						select.lista[j+1]=k;
						String x=select.settLott.get(j);
						select.settLott.set(j, select.settLott.get(j+1));
						select.settLott.set(j+1, x);
						f=true;
					}
				}
				else {
					if(select.lista[j].getCeff()>select.lista[j+1].getCeff()) {
						Lotti k=select.lista[j];
						select.lista[j]=select.lista[j+1];
						select.lista[j+1]=k;
						String x=select.settLott.get(j);
						select.settLott.set(j, select.settLott.get(j+1));
						select.settLott.set(j+1, x);
						f=true;
					}
				}
					
			}
			if(!f) break;
		}
		
	}
	
	Il metodo coeffInv effettua il bubleSort della lista in base al coeff. di Invecchiamento.E' stato scartato e sto provando array.sort
	
	private void coeffInv() {
		for(int i=0;i<select.getCount();i++) {
			boolean f=false;
			for(int j=0;j<select.getCount()-1;j++) {
			    if(flag==0) {
			    	if(select.lista[j].getCinv()<select.lista[j+1].getCinv()) {
						Lotti k=select.lista[j];
						select.lista[j]=select.lista[j+1];
						select.lista[j+1]=k;
						String x=select.settLott.get(j);
						select.settLott.set(j, select.settLott.get(j+1));
						select.settLott.set(j+1, x);
						f=true;
					}
			    }
			    else{
			    	if(select.lista[j].getCinv()>select.lista[j+1].getCinv()) {
						Lotti k=select.lista[j];
						select.lista[j]=select.lista[j+1];
						select.lista[j+1]=k;
						String x=select.settLott.get(j);
						select.settLott.set(j, select.settLott.get(j+1));
						select.settLott.set(j+1, x);
						f=true;
					}
			    }
			}
			if(!f) break;
		}
		
	}
	Il metodo coeffInv effettua il bubleSort della lista in base al coeff. di Invecchiamento.E' stato scartato e sto provando array.sort
	
	Il metodo val effettua il bubbleSort della lista in base al Valore
	
	private void val() {
		for(int i=0;i<select.getCount();i++) {
			boolean f=false;
			for(int j=0;j<select.getCount()-1;j++) {
				if(flag==0) {
					if(select.lista[j].getValore()<select.lista[j+1].getValore()) {
						Lotti k=select.lista[j];
						select.lista[j]=select.lista[j+1];
						select.lista[j+1]=k;
						String x=select.settLott.get(j);
						select.settLott.set(j, select.settLott.get(j+1));
						select.settLott.set(j+1, x);
						f=true;
					}
				}
				else{
					if(select.lista[j].getValore()>select.lista[j+1].getValore()) {
						Lotti k=select.lista[j];
						select.lista[j]=select.lista[j+1];
						select.lista[j+1]=k;
						String x=select.settLott.get(j);
						select.settLott.set(j, select.settLott.get(j+1));
						select.settLott.set(j+1, x);
			
						f=true;
					}
				}
			}
			if(!f) break;
		}
		
	}
*/
	
	
	
	private int flag;
	private ArrayList selez;
	private Seleziona select;
	


	
}

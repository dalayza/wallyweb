package com.b2w.tax.model;

import java.util.Comparator;

public class ImpostoEstadoDestinoComparator implements Comparator<ImpostoEstado> {

	@Override
	public int compare(ImpostoEstado o1, ImpostoEstado o2) {
		if(TipoImposto.DESTINO.equals(o1.getTipoImposto())){
			return -1;
		} else {
			return 1;
		}
	}

}

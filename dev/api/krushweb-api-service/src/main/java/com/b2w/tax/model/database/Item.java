package com.b2w.tax.model.database;

import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * 
 * 
 * @author fagner.moura
 *
 */

@Entity
@Table(name = "MV_ITEM_GERAL", schema = "PRICE")
public class Item {

	/**
	 * {@link Item#itemIdItem} is equal to product SKU
	 */
	@Id
	@Column(name = "ITEG_ID", precision = 12, length = 22)
	private BigInteger itemIdItem;

	@Column(name = "ITEG_ID_NBM", precision = 10, length = 22)
	private BigInteger itemIdNbm;

	@Column(name = "ITEG_SEQ_NBM", precision = 4, length = 22)
	private BigInteger itemSeqNbm;

	@Column(name = "ITEG_ID_CIA", precision = 9, length = 22)
	private BigInteger itemIdCia;

	public BigInteger getItemIdItem() {
		return itemIdItem;
	}

	public void setItemIdItem(BigInteger itemIdItem) {
		this.itemIdItem = itemIdItem;
	}

	public BigInteger getItemIdNbm() {
		return itemIdNbm;
	}

	public void setItemIdNbm(BigInteger itemIdNbm) {
		this.itemIdNbm = itemIdNbm;
	}

	public BigInteger getItemSeqNbm() {
		return itemSeqNbm;
	}

	public void setItemSeqNbm(BigInteger itemSeqNbm) {
		this.itemSeqNbm = itemSeqNbm;
	}

	public BigInteger getItemIdCia() {
		return itemIdCia;
	}

	public void setItemIdCia(BigInteger itemIdCia) {
		this.itemIdCia = itemIdCia;
	}

}

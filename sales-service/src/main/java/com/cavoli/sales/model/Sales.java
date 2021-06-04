//Created date
//		Order Finish date
//		Customer
//		Products (Çoxlu məhsul seçilə bilər)
//		Order status: (in_process, cancelled, finished)

package com.cavoli.sales.model;
import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "sales")
public class Sales {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "sales_id")
	private Long saleId;

	@Column(name = "order_status")
	private String orderStatus;

	@Column(name = "creation_date")
	private Date created = new Date();

	@Column(name = "updated_at")
	private Date updated = new Date();

	@PreUpdate
	public void setLastUpdate() {  this.updated = new Date(); }

	public Long getSaleId() {
		return saleId;
	}

	public void setSaleId(Long saleId) {
		this.saleId = saleId;
	}

}

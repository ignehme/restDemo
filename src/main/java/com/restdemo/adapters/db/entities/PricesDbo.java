package com.restdemo.adapters.db.entities;

import java.io.Serializable;
import java.util.Currency;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "prices")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PricesDbo implements Serializable {

	private static final long serialVersionUID = 253573144850578618L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer priceList;

	@Column(name = "brand_id")
	private Long brandId;

	@Column(name = "start_date")
	private Date startDate;
	
	@Column(name = "end_date")
	private Date endDate;

	@Column(name = "product_id")
	private Long productId;

	@Column(name = "priority")
	private Integer priority;

	@Column(name = "price")
	private Double price;

	@Column(name = "currency")
	private Currency currency;

	
}

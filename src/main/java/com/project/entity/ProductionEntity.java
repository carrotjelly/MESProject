package com.project.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "production")
public class ProductionEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PRODUCTION_NUM")
	private String productionNum;
	@Column(name = "DATE_REGISTER")
	private String dateRegister;
	@Column(name = "DATE_FINISH")
	private String dateFinish;
	@Column(name = "PRODUCTION_AMOUNT")
	private	int amount;
	@Column(name = "P_PHASE")
	private String phase;
	
	// ProductEntity와 조인하기 위한 필드 추가
    @ManyToOne
    @JoinColumn(name = "P_NUM") // ProductEntity 클래스의 필드명을 입력
    private ProductEntity product;

    
    
	public ProductionEntity() {
	}

	public ProductionEntity(String productionNum, String dateRegister, String dateFinish, int amount, String phase,
			ProductEntity product) {
		this.productionNum = productionNum;
		this.dateRegister = dateRegister;
		this.dateFinish = dateFinish;
		this.amount = amount;
		this.phase = phase;
		this.product = product;
	}

	public String getProductionNum() {
		return productionNum;
	}

	public void setProductionNum(String productionNum) {
		this.productionNum = productionNum;
	}

	public String getDateRegister() {
		return dateRegister;
	}

	public void setDateRegister(String dateRegister) {
		this.dateRegister = dateRegister;
	}

	public String getDateFinish() {
		return dateFinish;
	}

	public void setDateFinish(String dateFinish) {
		this.dateFinish = dateFinish;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getPhase() {
		return phase;
	}

	public void setPhase(String phase) {
		this.phase = phase;
	}

	public ProductEntity getProduct() {
		return product;
	}

	public void setProduct(ProductEntity product) {
		this.product = product;
	}

	@Override
	public String toString() {
		return "ProductionEntity [productionNum=" + productionNum + ", dateRegister=" + dateRegister + ", dateFinish="
				+ dateFinish + ", amount=" + amount + ", phase=" + phase + ", product=" + product + "]";
	}
    
	
	
	
	
}
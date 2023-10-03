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
@Table(name = "sell")
public class SellEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "S_NUM")
	private String S_NUM;
	@Column(name = "C_NUM")
	private String cNum;
	@Column(name = "PRICE")
	private int price;
	
	// ProductEntity와 조인하기 위한 필드 추가
    @ManyToOne
    @JoinColumn(name = "P_NUM") // ProductEntity 클래스의 필드명을 입력
    private ProductEntity product;
	
	public SellEntity() {
	}

	public SellEntity(String s_NUM, String cNum, int price, ProductEntity product) {
		super();
		S_NUM = s_NUM;
		this.cNum = cNum;
		this.price = price;
		this.product = product;
	}

	public String getS_NUM() {
		return S_NUM;
	}

	public void setS_NUM(String s_NUM) {
		S_NUM = s_NUM;
	}

	public String getcNum() {
		return cNum;
	}

	public void setcNum(String cNum) {
		this.cNum = cNum;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public ProductEntity getProduct() {
		return product;
	}

	public void setProduct(ProductEntity product) {
		this.product = product;
	}

	@Override
	public String toString() {
		return "SellEntity [S_NUM=" + S_NUM + ", cNum=" + cNum + ", price=" + price + ", product=" + product + "]";
	}

	
	
}
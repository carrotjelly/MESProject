package com.project.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "product")
public class ProductEntity {

	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 @Column(name = "P_NUM")
	 private String pNum; // ProductEntity의 기본 키	 
	 @Column(name = "P_NAME")
	 private String productName;
	 @Column(name = "BOXCOUNT")
	 private int boxCount;
	 @Column(name = "RECIPE_NUM")
	 private String recipe_num;
	 
	 // ProductionEntity와 조인하기 위한 필드 추가
	 @OneToMany(mappedBy = "product") // ProductionEntity 클래스에서 정의한 변수명을 입력
	 private List<ProductionEntity> productions = new ArrayList<>();

	 // SellEntity와 조인하기 위한 필드 추가
	 @OneToMany(mappedBy = "product") // SellEntity 클래스에서 정의한 변수명을 입력
	 private List<SellEntity> sells = new ArrayList<>();
	 
	public ProductEntity() {
	}

	public ProductEntity(String pNum, String productName, int boxCount, String recipe_num,
			List<ProductionEntity> productions, List<SellEntity> sells) {
		this.pNum = pNum;
		this.productName = productName;
		this.boxCount = boxCount;
		this.recipe_num = recipe_num;
		this.productions = productions;
		this.sells = sells;
	}

	public String getpNum() {
		return pNum;
	}

	public void setpNum(String pNum) {
		this.pNum = pNum;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getBoxCount() {
		return boxCount;
	}

	public void setBoxCount(int boxCount) {
		this.boxCount = boxCount;
	}

	public String getRecipe_num() {
		return recipe_num;
	}

	public void setRecipe_num(String recipe_num) {
		this.recipe_num = recipe_num;
	}

	public List<ProductionEntity> getProductions() {
		return productions;
	}

	public void setProductions(List<ProductionEntity> productions) {
		this.productions = productions;
	}

	public List<SellEntity> getSells() {
		return sells;
	}

	public void setSells(List<SellEntity> sells) {
		this.sells = sells;
	}

	@Override
	public String toString() {
		return "ProductEntity [pNum=" + pNum + ", productName=" + productName + ", boxCount=" + boxCount
				+ ", recipe_num=" + recipe_num + ", productions=" + productions + ", sells=" + sells + "]";
	}

	
	
}
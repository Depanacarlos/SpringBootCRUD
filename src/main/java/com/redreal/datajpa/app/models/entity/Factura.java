package com.redreal.datajpa.app.models.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="facturas")
public class Factura implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String description;
	private String observation;
	@Temporal(TemporalType.DATE)
	@Column(name = "create_at")
	private Date createAt;
	@ManyToOne(fetch = FetchType.LAZY)
	private Cliente client;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name="bill_id")
	private List<ItemFactura> items;
	
	@PrePersist
	public void prePersist () {
		createAt = new Date();
	}
	
	public Factura () {
		this.items = new ArrayList<ItemFactura>();
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getObservation() {
		return observation;
	}

	public void setObservation(String observation) {
		this.observation = observation;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	public Cliente getClient() {
		return client;
	}

	public void setClient(Cliente client) {
		this.client = client;
	}
	
	public void addItemBills (ItemFactura item) {
		this.items.add(item);
	}
	
	public List<ItemFactura> getItems() {
		return items;
	}

	public void setItems(List<ItemFactura> items) {
		this.items = items;
	}

	public Double getTotal() {
		Double total = 0.0;
		
		for(ItemFactura item : items) {
			total+=item.calculateImport();
		}
		
		return total;
	}

	private static final long serialVersionUID = 1L;

}

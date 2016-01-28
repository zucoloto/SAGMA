package br.mil.eb.ccomsex.atv.model.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "atv_configurar")
public class Configurar implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;

	private String om;

	private String sigla;

	@Lob
	@Column(columnDefinition = "MEDIUMBLOB")
	private byte[] logo;

	@Column(name = "diretorio_pop")
	private String diretorioPop;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@NotBlank
	@Size(max = 100)
	public String getOm() {
		return om;
	}

	public void setOm(String om) {
		this.om = om;
	}

	@NotBlank
	@Size(max = 20)
	public String getSigla() {
		return sigla;
	}
	
	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	@NotNull
	public byte[] getLogo() {
		return logo;
	}

	public void setLogo(byte[] logo) {
		this.logo = logo;
	}

	@NotBlank
	@Size(max = 255)
	public String getDiretorioPop() {
		return diretorioPop;
	}

	public void setDiretorioPop(String diretorioPop) {
		this.diretorioPop = diretorioPop;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Configurar other = (Configurar) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}

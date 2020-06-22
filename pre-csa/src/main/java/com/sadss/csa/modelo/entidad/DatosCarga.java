package com.sadss.csa.modelo.entidad;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.sadss.csa.modelo.generic.GenericModel;

public class DatosCarga extends GenericModel<DatosCarga> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;
	private Integer claveAgente;
	private Date fechaAlta;
	private Date fechaBaja;
	private BigDecimal diasLaborados;
	private BigDecimal salarioDiario;
	private BigDecimal sdBase;
	private BigDecimal sueldo;
	private BigDecimal aguinaldo;
	private BigDecimal vacaciones;
	private BigDecimal primaVacacional;
	private BigDecimal repUtil;
	private BigDecimal indemnizacion;
	private BigDecimal veinteDias;
	private BigDecimal primaAnti;
	private BigDecimal compensacionV;
	private BigDecimal premios;
	private BigDecimal bono;
	private BigDecimal bonoLealtad;
	private BigDecimal bonoDigital;
	private BigDecimal bonoTraslado;
	private BigDecimal otroBono1;
	private BigDecimal otroBono2;
	private BigDecimal otroBono3;
	private BigDecimal otroBono4;
	private BigDecimal otroBono5;
	private BigDecimal otroBono6;
	private BigDecimal otroBono7;
	private BigDecimal otroBono8;
	private BigDecimal otroBono9;
	private BigDecimal otroBono10;

	private Set<CalculoIMSS> calculosImss = new HashSet<CalculoIMSS>();

	public DatosCarga() {

		this.salarioDiario = BigDecimal.ZERO;
		this.sdBase = BigDecimal.ZERO;
		this.sueldo = BigDecimal.ZERO;
		this.aguinaldo = BigDecimal.ZERO;
		this.vacaciones = BigDecimal.ZERO;
		this.primaVacacional = BigDecimal.ZERO;
		this.repUtil = BigDecimal.ZERO;
		this.indemnizacion =BigDecimal.ZERO;
		this.veinteDias = BigDecimal.ZERO;
		this.primaAnti = BigDecimal.ZERO;
		this.compensacionV = BigDecimal.ZERO;
		this.premios = BigDecimal.ZERO;
		this.bono = BigDecimal.ZERO;
		this.bonoLealtad = BigDecimal.ZERO;
		this.bonoDigital = BigDecimal.ZERO;
		this.bonoTraslado = BigDecimal.ZERO;
		this.otroBono1 = BigDecimal.ZERO;
		this.otroBono2 = BigDecimal.ZERO;
		this.otroBono3 = BigDecimal.ZERO;
		this.otroBono4 = BigDecimal.ZERO;
		this.otroBono5 = BigDecimal.ZERO;
		this.otroBono6 = BigDecimal.ZERO;
		this.otroBono7 = BigDecimal.ZERO;
		this.otroBono8 = BigDecimal.ZERO;
		this.otroBono9 = BigDecimal.ZERO;
		this.otroBono10 = BigDecimal.ZERO;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getClaveAgente() {
		return claveAgente;
	}

	public void setClaveAgente(Integer claveAgente) {
		this.claveAgente = claveAgente;
	}

	public Date getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	public Date getFechaBaja() {
		return fechaBaja;
	}

	public void setFechaBaja(Date fechaBaja) {
		this.fechaBaja = fechaBaja;
	}

	public BigDecimal getDiasLaborados() {
		return diasLaborados;
	}

	public void setDiasLaborados(BigDecimal diasLaborados) {
		this.diasLaborados = diasLaborados;
	}

	public BigDecimal getSalarioDiario() {
		return salarioDiario;
	}

	public void setSalarioDiario(BigDecimal salarioDiario) {
		this.salarioDiario = salarioDiario;
	}

	public BigDecimal getSdBase() {
		return sdBase;
	}

	public void setSdBase(BigDecimal sdBase) {
		this.sdBase = sdBase;
	}

	public BigDecimal getSueldo() {
		return sueldo;
	}

	public void setSueldo(BigDecimal sueldo) {
		this.sueldo = sueldo;
	}

	public BigDecimal getAguinaldo() {
		return aguinaldo;
	}

	public void setAguinaldo(BigDecimal aguinaldo) {
		this.aguinaldo = aguinaldo;
	}

	public BigDecimal getVacaciones() {
		return vacaciones;
	}

	public void setVacaciones(BigDecimal vacaciones) {
		this.vacaciones = vacaciones;
	}

	public BigDecimal getPrimaVacacional() {
		return primaVacacional;
	}

	public void setPrimaVacacional(BigDecimal primaVacacional) {
		this.primaVacacional = primaVacacional;
	}

	public BigDecimal getRepUtil() {
		return repUtil;
	}

	public void setRepUtil(BigDecimal repUtil) {
		this.repUtil = repUtil;
	}

	public BigDecimal getIndemnizacion() {
		return indemnizacion;
	}

	public void setIndemnizacion(BigDecimal indemnizacion) {
		this.indemnizacion = indemnizacion;
	}

	public BigDecimal getVeinteDias() {
		return veinteDias;
	}

	public void setVeinteDias(BigDecimal veinteDias) {
		this.veinteDias = veinteDias;
	}

	public BigDecimal getPrimaAnti() {
		return primaAnti;
	}

	public void setPrimaAnti(BigDecimal primaAnti) {
		this.primaAnti = primaAnti;
	}

	public BigDecimal getCompensacionV() {
		return compensacionV;
	}

	public void setCompensacionV(BigDecimal compensacionV) {
		this.compensacionV = compensacionV;
	}

	public BigDecimal getPremios() {
		return premios;
	}

	public void setPremios(BigDecimal premios) {
		this.premios = premios;
	}

	public BigDecimal getBono() {
		return bono;
	}

	public void setBono(BigDecimal bono) {
		this.bono = bono;
	}

	public BigDecimal getBonoLealtad() {
		return bonoLealtad;
	}

	public void setBonoLealtad(BigDecimal bonoLealtad) {
		this.bonoLealtad = bonoLealtad;
	}

	public BigDecimal getBonoDigital() {
		return bonoDigital;
	}

	public void setBonoDigital(BigDecimal bonoDigital) {
		this.bonoDigital = bonoDigital;
	}

	public BigDecimal getBonoTraslado() {
		return bonoTraslado;
	}

	public void setBonoTraslado(BigDecimal bonoTraslado) {
		this.bonoTraslado = bonoTraslado;
	}

	public BigDecimal getOtroBono1() {
		return otroBono1;
	}

	public void setOtroBono1(BigDecimal otroBono1) {
		this.otroBono1 = otroBono1;
	}

	public BigDecimal getOtroBono2() {
		return otroBono2;
	}

	public void setOtroBono2(BigDecimal otroBono2) {
		this.otroBono2 = otroBono2;
	}

	public BigDecimal getOtroBono3() {
		return otroBono3;
	}

	public void setOtroBono3(BigDecimal otroBono3) {
		this.otroBono3 = otroBono3;
	}

	public BigDecimal getOtroBono4() {
		return otroBono4;
	}

	public void setOtroBono4(BigDecimal otroBono4) {
		this.otroBono4 = otroBono4;
	}

	public BigDecimal getOtroBono5() {
		return otroBono5;
	}

	public void setOtroBono5(BigDecimal otroBono5) {
		this.otroBono5 = otroBono5;
	}

	public BigDecimal getOtroBono6() {
		return otroBono6;
	}

	public void setOtroBono6(BigDecimal otroBono6) {
		this.otroBono6 = otroBono6;
	}

	public BigDecimal getOtroBono7() {
		return otroBono7;
	}

	public void setOtroBono7(BigDecimal otroBono7) {
		this.otroBono7 = otroBono7;
	}

	public BigDecimal getOtroBono8() {
		return otroBono8;
	}

	public void setOtroBono8(BigDecimal otroBono8) {
		this.otroBono8 = otroBono8;
	}

	public BigDecimal getOtroBono9() {
		return otroBono9;
	}

	public void setOtroBono9(BigDecimal otroBono9) {
		this.otroBono9 = otroBono9;
	}

	public BigDecimal getOtroBono10() {
		return otroBono10;
	}

	public void setOtroBono10(BigDecimal otroBono10) {
		this.otroBono10 = otroBono10;
	}

	public Set<CalculoIMSS> getCalculosImss() {
		return calculosImss;
	}

	public void setCalculosImss(Set<CalculoIMSS> calculosImss) {
		this.calculosImss = calculosImss;
	}

}

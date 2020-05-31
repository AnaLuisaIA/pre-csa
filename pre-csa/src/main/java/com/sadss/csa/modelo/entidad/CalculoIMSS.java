package com.sadss.csa.modelo.entidad;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.sadss.csa.modelo.generic.GenericModel;
import com.sadss.csa.util.enums.TipoNominaIMSS;
import com.sadss.csa.util.enums.TipoPeriodo;

public class CalculoIMSS extends GenericModel<CalculoIMSS> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private TipoPeriodo tipoPeriodo;
	private TipoNominaIMSS tipoNomina;
	private Integer claveAgente;
	private BigDecimal cuotaFijaP;
	private BigDecimal excedenteP;
	private BigDecimal prestacionesP;
	private BigDecimal gastosMedP;
	private BigDecimal RTP;
	private BigDecimal guarderiaP;
	private BigDecimal invVidaP;
	private BigDecimal totalPatron;
	private BigDecimal excedenteT;
	private BigDecimal prestacionesT;
	private BigDecimal gastosMedicosT;
	private BigDecimal invVidaT;
	private BigDecimal totalTrabajador;
	private BigDecimal totalIMSS;
	private BigDecimal infonavitPatron;
	private BigDecimal infonavitTrabajador;
	private BigDecimal totalInfonavit;
	private Date fechaCalculo;
	private Usuario usuarioCalculo;
	private Date periodoInicio;
	private Date periodoFin;

	private DatosCarga datos;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public TipoPeriodo getTipoPeriodo() {
		return tipoPeriodo;
	}

	public void setTipoPeriodo(TipoPeriodo tipoPeriodo) {
		this.tipoPeriodo = tipoPeriodo;
	}

	public TipoNominaIMSS getTipoNomina() {
		return tipoNomina;
	}

	public void setTipoNomina(TipoNominaIMSS tipoNomina) {
		this.tipoNomina = tipoNomina;
	}

	public Integer getClaveAgente() {
		return claveAgente;
	}

	public void setClaveAgente(Integer claveAgente) {
		this.claveAgente = claveAgente;
	}

	public BigDecimal getCuotaFijaP() {
		return cuotaFijaP;
	}

	public void setCuotaFijaP(BigDecimal cuotaFijaP) {
		this.cuotaFijaP = cuotaFijaP;
	}

	public BigDecimal getExcedenteP() {
		return excedenteP;
	}

	public void setExcedenteP(BigDecimal excedenteP) {
		this.excedenteP = excedenteP;
	}

	public BigDecimal getPrestacionesP() {
		return prestacionesP;
	}

	public void setPrestacionesP(BigDecimal prestacionesP) {
		this.prestacionesP = prestacionesP;
	}

	public BigDecimal getGastosMedP() {
		return gastosMedP;
	}

	public void setGastosMedP(BigDecimal gastosMedP) {
		this.gastosMedP = gastosMedP;
	}

	public BigDecimal getRTP() {
		return RTP;
	}

	public void setRTP(BigDecimal rTP) {
		RTP = rTP;
	}

	public BigDecimal getInvVidaP() {
		return invVidaP;
	}

	public void setInvVidaP(BigDecimal invVidaP) {
		this.invVidaP = invVidaP;
	}

	public BigDecimal getTotalPatron() {
		return totalPatron;
	}

	public void setTotalPatron(BigDecimal totalPatron) {
		this.totalPatron = totalPatron;
	}

	public BigDecimal getExcedenteT() {
		return excedenteT;
	}

	public void setExcedenteT(BigDecimal excedenteT) {
		this.excedenteT = excedenteT;
	}

	public BigDecimal getPrestacionesT() {
		return prestacionesT;
	}

	public void setPrestacionesT(BigDecimal prestacionesT) {
		this.prestacionesT = prestacionesT;
	}

	public BigDecimal getGastosMedicosT() {
		return gastosMedicosT;
	}

	public void setGastosMedicosT(BigDecimal gastosMedicosT) {
		this.gastosMedicosT = gastosMedicosT;
	}

	public BigDecimal getTotalTrabajador() {
		return totalTrabajador;
	}

	public void setTotalTrabajador(BigDecimal totalTrabajador) {
		this.totalTrabajador = totalTrabajador;
	}

	public BigDecimal getTotalIMSS() {
		return totalIMSS;
	}

	public void setTotalIMSS(BigDecimal totalIMSS) {
		this.totalIMSS = totalIMSS;
	}

	public BigDecimal getInfonavitPatron() {
		return infonavitPatron;
	}

	public void setInfonavitPatron(BigDecimal infonavitPatron) {
		this.infonavitPatron = infonavitPatron;
	}

	public BigDecimal getInfonavitTrabajador() {
		return infonavitTrabajador;
	}

	public void setInfonavitTrabajador(BigDecimal infonavitTrabajador) {
		this.infonavitTrabajador = infonavitTrabajador;
	}

	public BigDecimal getTotalInfonavit() {
		return totalInfonavit;
	}

	public void setTotalInfonavit(BigDecimal totalInfonavit) {
		this.totalInfonavit = totalInfonavit;
	}

	public Date getFechaCalculo() {
		return fechaCalculo;
	}

	public void setFechaCalculo(Date fechaCalculo) {
		this.fechaCalculo = fechaCalculo;
	}

	public Usuario getUsuarioCalculo() {
		return usuarioCalculo;
	}

	public void setUsuarioCalculo(Usuario usuarioCalculo) {
		this.usuarioCalculo = usuarioCalculo;
	}

	public Date getPeriodoInicio() {
		return periodoInicio;
	}

	public void setPeriodoInicio(Date periodoInicio) {
		this.periodoInicio = periodoInicio;
	}

	public Date getPeriodoFin() {
		return periodoFin;
	}

	public void setPeriodoFin(Date periodoFin) {
		this.periodoFin = periodoFin;
	}

	public DatosCarga getDatos() {
		return datos;
	}

	public void setDatos(DatosCarga datos) {
		this.datos = datos;
	}

	public BigDecimal getGuarderiaP() {
		return guarderiaP;
	}

	public void setGuarderiaP(BigDecimal guarderiaP) {
		this.guarderiaP = guarderiaP;
	}

	public BigDecimal getInvVidaT() {
		return invVidaT;
	}

	public void setInvVidaT(BigDecimal invVidaT) {
		this.invVidaT = invVidaT;
	}

}

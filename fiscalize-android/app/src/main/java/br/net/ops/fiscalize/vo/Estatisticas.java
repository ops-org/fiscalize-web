package br.net.ops.fiscalize.vo;

import java.util.Date;

public class Estatisticas {

    private int quantidadeSuspeitas;
    private int quantidadeLimpas;
    private int quantidadeTotal;

    private Date dataInicio;

    public int getQuantidadeSuspeitas() {
        return quantidadeSuspeitas;
    }

    public void setQuantidadeSuspeitas(int quantidadeSuspeitas) {
        this.quantidadeSuspeitas = quantidadeSuspeitas;
    }

    public int getQuantidadeLimpas() {
        return quantidadeLimpas;
    }

    public void setQuantidadeLimpas(int quantidadeLimpas) {
        this.quantidadeLimpas = quantidadeLimpas;
    }

    public int getQuantidadeTotal() {
        return quantidadeTotal;
    }

    public void setQuantidadeTotal(int quantidadeTotal) {
        this.quantidadeTotal = quantidadeTotal;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

}

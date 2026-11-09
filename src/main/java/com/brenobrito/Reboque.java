package com.brenobrito;

public class Reboque implements Veiculo
{

  private final String placa;

  private final double valorVenal;

  private final int capacidadeKg;

  public Reboque(String placa, double valorVenal, int capacidadeKg)
  {
    this.placa = placa;
    this.valorVenal = valorVenal;
    this.capacidadeKg = capacidadeKg;
  }

  @Override
  public String getPlaca()
  {
    return placa;
  }

  @Override
  public double getValorVenal()
  {
    return valorVenal;
  }

  public int getCapacidadeKg()
  {
    return capacidadeKg;
  }

  @Override
  public String aceitar(VeiculoVisitor visitor)
  {
    if (visitor instanceof ReboqueVisitor interessado)
    {
      return interessado.visitarReboque(this);
    }
    return NAO_APLICAVEL;
  }
}

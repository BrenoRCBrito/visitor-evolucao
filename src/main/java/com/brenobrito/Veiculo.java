package com.brenobrito;

public interface Veiculo
{

  String NAO_APLICAVEL = "Nao aplicavel";

  String getPlaca();

  double getValorVenal();

  String aceitar(VeiculoVisitor visitor);
}

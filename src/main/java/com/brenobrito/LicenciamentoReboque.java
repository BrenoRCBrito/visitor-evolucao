package com.brenobrito;

public class LicenciamentoReboque implements ReboqueVisitor
{

  public String licenciar(Veiculo veiculo)
  {
    return veiculo.aceitar(this);
  }

  @Override
  public String visitarReboque(Reboque reboque)
  {
    return "Licenciamento do reboque " + reboque.getPlaca()
        + " para " + reboque.getCapacidadeKg() + " kg";
  }
}

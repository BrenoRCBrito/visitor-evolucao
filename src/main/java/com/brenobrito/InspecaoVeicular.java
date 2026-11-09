package com.brenobrito;

public class InspecaoVeicular implements CaminhaoVisitor
{

  public String inspecionar(Veiculo veiculo)
  {
    return veiculo.aceitar(this);
  }

  @Override
  public String visitarCaminhao(Caminhao caminhao)
  {
    return "Inspecao obrigatoria do caminhao " + caminhao.getPlaca()
        + " com " + caminhao.getEixos() + " eixos";
  }
}

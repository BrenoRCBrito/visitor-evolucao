package com.brenobrito;

public interface CaminhaoVisitor extends VeiculoVisitor
{

  String visitarCaminhao(Caminhao caminhao);
}

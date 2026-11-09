package com.brenobrito;

public interface ReboqueVisitor extends VeiculoVisitor
{

  String visitarReboque(Reboque reboque);
}

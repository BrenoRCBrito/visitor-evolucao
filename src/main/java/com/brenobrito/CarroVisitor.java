package com.brenobrito;

public interface CarroVisitor extends VeiculoVisitor
{

  String visitarCarro(Carro carro);
}

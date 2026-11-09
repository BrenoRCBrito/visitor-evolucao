package com.brenobrito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class AcyclicVisitorTest
{

  private final Veiculo carro = new Carro("ABC1D23", 50000.0, 4);

  private final Veiculo motocicleta = new Motocicleta("XYZ9W87", 20000.0, 150);

  private final Veiculo caminhao = new Caminhao("QRS4T56", 300000.0, 3);

  private final Veiculo reboque = new Reboque("RBQ7H10", 15000.0, 750);

  @Test
  void deveCalcularIpvaDosTresTiposQueOVisitanteAtende()
  {
    CalculoIpva ipva = new CalculoIpva();

    assertEquals("IPVA de 2000.00", ipva.calcular(carro));
    assertEquals("IPVA de 400.00", ipva.calcular(motocicleta));
    assertEquals("Isento", ipva.calcular(caminhao));
  }

  @Test
  void deveEmitirCrlvDosTresTiposQueOVisitanteAtende()
  {
    EmissaoCrlv crlv = new EmissaoCrlv();

    assertEquals("CRLV Carro ABC1D23 - 4 portas", crlv.emitir(carro));
    assertEquals("CRLV Motocicleta XYZ9W87 - 150 cilindradas", crlv.emitir(motocicleta));
    assertEquals("CRLV Caminhao QRS4T56 - 3 eixos", crlv.emitir(caminhao));
  }

  @Test
  void deveAtenderApenasOCaminhaoNaInspecaoVeicular()
  {
    InspecaoVeicular inspecao = new InspecaoVeicular();

    assertEquals("Inspecao obrigatoria do caminhao QRS4T56 com 3 eixos", inspecao.inspecionar(caminhao));
  }

  @Test
  void deveResponderNaoAplicavelParaOsTiposQueOVisitanteNaoDeclara()
  {
    InspecaoVeicular inspecao = new InspecaoVeicular();

    assertEquals(Veiculo.NAO_APLICAVEL, inspecao.inspecionar(carro));
    assertEquals(Veiculo.NAO_APLICAVEL, inspecao.inspecionar(motocicleta));
    assertEquals(Veiculo.NAO_APLICAVEL, inspecao.inspecionar(reboque));
  }

  @Test
  void naoDeveQuebrarOsVisitantesAntigosQuandoUmTipoNovoEntra()
  {
    assertEquals(Veiculo.NAO_APLICAVEL, new CalculoIpva().calcular(reboque));
    assertEquals(Veiculo.NAO_APLICAVEL, new EmissaoCrlv().emitir(reboque));
  }

  @Test
  void deveAtenderOTipoNovoComUmVisitanteNovo()
  {
    assertEquals("Licenciamento do reboque RBQ7H10 para 750 kg",
        new LicenciamentoReboque().licenciar(reboque));
  }

  @Test
  void naoDeveAtenderOsTiposAntigosComOVisitanteDoTipoNovo()
  {
    LicenciamentoReboque licenciamento = new LicenciamentoReboque();

    assertEquals(Veiculo.NAO_APLICAVEL, licenciamento.licenciar(carro));
    assertEquals(Veiculo.NAO_APLICAVEL, licenciamento.licenciar(caminhao));
  }

  @Test
  void deveResponderNaoAplicavelAoVisitanteDegenerado()
  {
    VeiculoVisitor semInteresse = new VeiculoVisitor() { };

    assertEquals(Veiculo.NAO_APLICAVEL, carro.aceitar(semInteresse));
    assertEquals(Veiculo.NAO_APLICAVEL, caminhao.aceitar(semInteresse));
  }

  @Test
  void deveManterAInterfaceBaseSemMetodos()
  {
    assertEquals(0, VeiculoVisitor.class.getDeclaredMethods().length);
  }

  @Test
  void deveDeclararCadaVisitanteApenasParaOsTiposQueTrata()
  {
    assertTrue(CaminhaoVisitor.class.isAssignableFrom(InspecaoVeicular.class));
    assertFalse(CarroVisitor.class.isAssignableFrom(InspecaoVeicular.class));
    assertFalse(MotocicletaVisitor.class.isAssignableFrom(InspecaoVeicular.class));
  }
}

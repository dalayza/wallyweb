package com.b2w.tax.calculator;

import com.b2w.tax.model.*;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;

public class ConsolidaValorImpostosVendaTest {

    @Test
    public void testIcmsValorBaseZeroQuandoAliquotaZero(){
        ImpostosVenda impostos = new ImpostosVenda();
        impostos.setValorBase(30f);
        Icms icms = new Icms("SP", "RJ", 0f, TipoImposto.DESTINO);
        impostos.setIcmsVendas(Arrays.asList(icms));
        BaseReduzida baseReduzida = new BaseReduzida("SP", "RJ", 66f, TipoImposto.DESTINO);
        impostos.setBaseReduzidas(Arrays.asList(baseReduzida));

        ConsolidaValorImpostosVenda calculador = new ConsolidaValorImpostosVenda(impostos);
        calculador.calcular();

        assertEquals(Float.valueOf(0f), impostos.getIcmsVendas().get(0).getValorBase());
        assertEquals(Float.valueOf(0f), impostos.getIcmsFinais().get(0).getValorBase());
    }

    @Test
    public void testIcmsValorBaseQuandoAliquotaMaiorQueZero(){
        ImpostosVenda impostos = new ImpostosVenda();
        impostos.setValorBase(30f);
        Icms icms = new Icms("SP", "RJ", 17f, TipoImposto.DESTINO);
        impostos.setIcmsVendas(Arrays.asList(icms));
        BaseReduzida baseReduzida = new BaseReduzida("SP", "RJ", 66f, TipoImposto.DESTINO);
        impostos.setBaseReduzidas(Arrays.asList(baseReduzida));

        ConsolidaValorImpostosVenda calculador = new ConsolidaValorImpostosVenda(impostos);
        calculador.calcular();

        assertEquals(Float.valueOf(30.0f), impostos.getIcmsVendas().get(0).getValorBase());
        assertEquals(Float.valueOf(30.0f), impostos.getIcmsFinais().get(0).getValorBase());
    }


}

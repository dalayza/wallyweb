package com.b2w.tax.service.util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.b2w.tax.model.TipoFornecedor;
import com.b2w.tax.model.database.IcmsNbmEstado;

public class TaxUtil {

    public static final int ID_CIA = 1;

    public static final List<String> sentidoSaida = Arrays.asList(new String[] {"S", "A"});
    public static final List<String> sentidoEntrada = Arrays.asList(new String[] {"E", "A"});

    public static Float multiplicaComArredondamento(Float x, Float y) {
        BigDecimal bigX = new BigDecimal(x);
        BigDecimal bigY = new BigDecimal(y);
        return bigX.multiply(bigY).setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
    }

    public static Float divideComArredondamento(Float x, Float y) {
        BigDecimal bigX = new BigDecimal(x);
        BigDecimal bigY = new BigDecimal(y);
        return bigX.divide(bigY, BigDecimal.ROUND_HALF_UP).floatValue();
    }

    public static String getSeqExternalFromNcm(Long ncm) {
        String ncmStr = ncm.toString();
        String seqExternal = null;
        if (ncmStr.length() == 12) {
            seqExternal = ncmStr.substring(8, ncmStr.length());
        } else if (ncmStr.length() > 8 && ncmStr.length() < 12) {
            // casos onde o NCM tem 0 a esquerda e possui sequencial externo
            seqExternal = ncmStr.substring(7, ncmStr.length());
        } else if (ncmStr.length() <= 4) {
            seqExternal = ncmStr;
        }
        return seqExternal;
    }

    public static Long getNcmFromOrmsNcm(Long ncm) {
        String ncmStr = ncm.toString();
        if (ncmStr.length() == 11 || ncmStr.length() == 7) {
            // casos onde o NCM tem 0 a esquerda
            return Long.parseLong(ncmStr.substring(0, 7));
        } else if (ncmStr.length() > 8) {
            return Long.parseLong(ncmStr.substring(0, 8));
        } else if (ncmStr.length() <= 4) {
            return 0l;
        }
        return Long.parseLong(ncmStr);
    }

    public static Float getValueOrZero(Float value) {
        if (null == value) {
            return new Float(0);
        } else {
            return value;
        }
    }

    public static Float subtractWithNull(Float minuend, Float subtrahend) {
        if (null == minuend) {
            minuend = 0f;
        }
        if (null == subtrahend) {
            subtrahend = 0f;
        }
        return minuend - subtrahend;
    }

    public static Float getValueOrNumber(Float value, Float number) {
        if (null == value) {
            return number;
        } else {
            return value;
        }
    }

    public static int getOrmsUtilizacao(boolean icmsSt, boolean mesmaUf, boolean optanteSimples,
            TipoFornecedor tipoFornecedor) throws Exception {
        List<Integer> utilizacoes = new ArrayList<Integer>(
                Arrays.asList(new Integer[] {1001, 1003, 1004, 1021, 1023, 1024}));
        if (icmsSt) {
            utilizacoes.remove(new Integer(1001));
            utilizacoes.remove(new Integer(1021));
        } else {
            utilizacoes.remove(new Integer(1003));
            utilizacoes.remove(new Integer(1004));
            utilizacoes.remove(new Integer(1023));
            utilizacoes.remove(new Integer(1024));
        }
        if (optanteSimples) {
            if (mesmaUf) {
                utilizacoes.remove(new Integer(1023));
            } else {
                utilizacoes.remove(new Integer(1024));
            }
            utilizacoes.remove(new Integer(1003));
            utilizacoes.remove(new Integer(1004));
        } else {
            utilizacoes.remove(new Integer(1023));
            utilizacoes.remove(new Integer(1024));
            utilizacoes.remove(new Integer(1021));
        }
        if (TipoFornecedor.DISTRIBUIDOR.equals(tipoFornecedor)) {
            if (mesmaUf) {
                utilizacoes.remove(new Integer(1003));
            } else {
                utilizacoes.remove(new Integer(1004));
            }
        } else {
            utilizacoes.remove(new Integer(1004));
        }

        if (utilizacoes.size() != 1) {
            throw new Exception(
                    "Não foi possível determinar o valor [utilizacao] a ser utilizado para busca de impostos no ORMS");
        }
        return utilizacoes.get(0);
    }

    public static int getOrmsCfop(boolean icmsSt, boolean mesmaUf) throws Exception {
        List<Integer> cfops =
                new ArrayList<Integer>(Arrays.asList(new Integer[] {1102, 1403, 2102, 2403}));
        if (mesmaUf) {
            cfops.remove(new Integer(2102));
            cfops.remove(new Integer(2403));
        } else {
            cfops.remove(new Integer(1102));
            cfops.remove(new Integer(1403));
        }
        if (icmsSt) {
            cfops.remove(new Integer(1102));
            cfops.remove(new Integer(2102));
        } else {
            cfops.remove(new Integer(1403));
            cfops.remove(new Integer(2403));
        }
        if (cfops.size() != 1) {
            throw new Exception(
                    "Não foi possível determinar o valor [cfop] a ser utilizado para busca de impostos no ORMS");
        }
        return cfops.get(0);
    }

    public static boolean isAtivoAndHasIcms(IcmsNbmEstado icmsNbmEstado) {

        return (null != icmsNbmEstado && "A".equalsIgnoreCase(icmsNbmEstado.getIcneSituacao())
                && null != icmsNbmEstado.getIcnePercIcms());

    }

    public static boolean isAtivoAndHasFecp(IcmsNbmEstado icmsNbmEstado) {

        return (null != icmsNbmEstado && "A".equalsIgnoreCase(icmsNbmEstado.getIcneSituacao())
                && null != icmsNbmEstado.getIcnePercIcmsFecp());

    }

    public static boolean isAtivoAndHasRedBase(IcmsNbmEstado icmsNbmEstado) {

        return (null != icmsNbmEstado && "A".equalsIgnoreCase(icmsNbmEstado.getIcneSituacao())
                && null != icmsNbmEstado.getIcnePercRedBase());

    }

}

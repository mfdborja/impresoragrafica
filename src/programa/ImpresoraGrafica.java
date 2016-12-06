package programa;

import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.util.ArrayList;
import java.util.List;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;

public class ImpresoraGrafica {
    //Aquí almacenaremos la impresora elegida en el momento para imprimir.

    private PrintService impresoraElegida;
    //Listado de las impresoras instaladas en el sistema.
    private PrintService[] impresoras;
    //Representa el objeto donde operar con el trabajo de impresión - Impresión actual.
    private PrinterJob trabajoImpresion;
    //Atributos de la página donde imprimiremos
    private PageFormat formato;
    //Para la orientación de la página
    public static final int vertical = 1;
    public static final int horizontal = 2;
    public static final int horizontalInvertido = 3;
    //Para pasar de pulgadas a centímetros
    public static final double pulgadaACentimetro = 2.54;

    public ImpresoraGrafica() {
        //Comienza con la impresora por defecto del sistema.
        //Si no hay impresora devolverá null.
        impresoraElegida = PrintServiceLookup.lookupDefaultPrintService();

        //Desde un vector vacío hasta más de un elemento.
        //Con los atributos. En este caso a null, se puede pedir que cumpla unos determinados requisitos.        
        impresoras = PrintServiceLookup.lookupPrintServices(null, null);

        //Crea un trabajo de impresión y lo guarda para que operemos con él.
        trabajoImpresion = PrinterJob.getPrinterJob();

        //Saca el formato por defecto
        formato = trabajoImpresion.getPageFormat(null);
    }

    public int imprimir(Printable aImprimir) {
        
        //Así llamará al método imprimir mostrando las opciones de impresión
        return imprimir(aImprimir, false);
    }

    public int imprimir(Printable aImprimir, boolean impresionRapida) {
        //Ejecutar la impresión
        try {
            //Por defecto usa la impresora principal. Pero le diremos que impresora queremos por si no es la normal.
            trabajoImpresion.setPrintService(impresoraElegida);

            //aImprimir será lo que queremos que salga en papel. Debe implementar la interfaz Printable. "implements Printable"
            //Será una ventana, o un panel...
            trabajoImpresion.setPrintable(aImprimir);

            boolean imprimir = true;
            //Si es una impresión rápida se cogen las opciones por defecto
            if (!impresionRapida) {
                //Hace que se abran las opciones de impresión. (Calidad del color, tamaño del papel...)
                //Si el usuario da a cancelar prinDialog devuelve false, si acepta devuelve true e imprimiremos.
                imprimir = trabajoImpresion.printDialog();
            }
            if (imprimir) {
                //Esto imprime
                trabajoImpresion.print();
            }

        } catch (PrinterException ex) {
            System.err.println(ex.getMessage());
            //Si falla devolvemos un -1 al código que pidió imprimir.
            return -1;
        }

        //Si se imprime correctamente se devuelve un 0.
        return 0;

    }

    public int cambiarImpresoraSeleccionada(String nombreImpresora) {
        for (int i = 0; i < impresoras.length; i++) {
            if (impresoras[i].getName().equalsIgnoreCase(nombreImpresora)) {
                //Busca la impresora en la lista y la cambia
                impresoraElegida = impresoras[i];
                //Devuelve el índice de la impresora por si queremos comprobar si ha funcionado el método
                //Y corta el bucle
                return i;
            }
        }
        //Si no ha funcionado
        return -1;
    }

    public List<String> listaImpresoras() {
        List<String> lista = new ArrayList<String>();

        //Guarda las impresoras encontradas en una lista y las devuelve
        for (int i = 0; i < impresoras.length; i++) {
            lista.add(impresoras[i].getName());
        }

        return lista;
    }

    public void cambiarNombreImpresion(String nombre) {
        //Cambia el nombre que será mostrado en la cola de impresión del sistema operativo.
        trabajoImpresion.setJobName(nombre);
    }

    public String verNombreImpresion() {
        return trabajoImpresion.getJobName();
    }

    public void cancelarImpresion() {
        //Si se está imprimiendo intenta cancelar la impresión.
        trabajoImpresion.cancel();
    }

    public void cambiarNumeroCopias(int copias) {
        trabajoImpresion.setCopies(copias);
    }

    public int verNumeroCopias() {
        return trabajoImpresion.getCopies();
    }

    public void cambiarOrientacionPapel(int orientacion) {
        //Orientación se debe pasar como una de las variables definidas en la clase en vez de hacerlo con un valor numérico.
        //cambiarOrientacionPapel(ImpresionGrafica.vertical);
        if (orientacion == vertical) {
            formato.setOrientation(java.awt.print.PageFormat.PORTRAIT);
        } else if (orientacion == horizontal) {
            formato.setOrientation(java.awt.print.PageFormat.LANDSCAPE);
        } else if (orientacion == horizontalInvertido) {
            formato.setOrientation(java.awt.print.PageFormat.REVERSE_LANDSCAPE);
        }
    }

    public int verOrientacionPapel() {
        if (formato.getOrientation() == java.awt.print.PageFormat.PORTRAIT) {
            return vertical;
        } else if (formato.getOrientation() == java.awt.print.PageFormat.LANDSCAPE) {
            return horizontal;
        } else if (formato.getOrientation() == java.awt.print.PageFormat.REVERSE_LANDSCAPE) {
            return horizontalInvertido;
        }

        //Se supone que nunca debería llegar aquí, pero para que compile se ha de poner un return para cada posible salida.
        return -1;
    }

    public double verAnchoImprimible() {
        //Pasamos de pulgadas a centímetros
        //Devuelve en 1/72 de pulgada así que operamos para cambiarlo
        return (formato.getImageableWidth()/72) * pulgadaACentimetro;
    }

    public double verAltoImprimible() {
        //Pasamos de pulgadas a centímetros
        //Devuelve en 1/72 de pulgada así que operamos para cambiarlo
        return (formato.getImageableHeight()/72) * pulgadaACentimetro;
    }

    public void opcionesImpresion() {
        
        //Muestra el cuadro de opciones de la impresión.
        trabajoImpresion.printDialog();
    }
}

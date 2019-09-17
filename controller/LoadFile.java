package controller;

import model.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LoadFile {

    public static void loadVehicles(File nameFile, Instancia instancia){
        FileReader fr = null;
        BufferedReader br = null;
        String linea = null;
        String[] datos = null;
        byte hprc = 0;
        byte lprc = 0;
        List<Vehicle> vehiculosNoSec = null;
        List<Vehicle> vehiculosSec = null;
        List<GrupoClase> clases = null;
        List<GrupoColor> colores = null;

        try {
            fr = new FileReader(nameFile);
            br = new BufferedReader(fr);

            linea = br.readLine();
            datos = linea.split(";");
            hprc = getNumberHPRC(datos);
            lprc = getNumberLPRC(datos);

            vehiculosNoSec = new ArrayList<>();
            vehiculosSec = new ArrayList<>();
            clases = new ArrayList<>();

            while ((linea = br.readLine()) != null) {
                datos = linea.split(";");
                Vehicle vehicle = new Vehicle(createFecha(datos[0]), Integer.parseInt(datos[1]), datos[2],
                        Byte.parseByte(datos[3]), createHPRC(datos, hprc, (byte)3), createLPRC(datos, lprc, (byte)(3 + hprc)));
                if (vehiculosNoSec.isEmpty()) {
                    vehiculosNoSec.add(vehicle);
                } else if (vehiculosNoSec.get(0).getDate().toString().equalsIgnoreCase(datos[0])){
                    vehiculosNoSec.add(vehicle);
                } else {
                    asignarClase(clases, vehiculosSec, vehicle);
                    vehiculosSec.add(vehicle);
                }// fin if
            }// fin wh
            instancia.setVehiclesNoSec(vehiculosNoSec);
            instancia.setVehiclesSec(vehiculosSec);
            instancia.setClases(clases);
        } catch (IOException e){
            System.out.println("Error en: " + LoadFile.class.getSimpleName()
                    + "::loadVehicles, Tipo de Error: " + e.getClass().getSimpleName() + ", " + e.getMessage());
        } finally {
            try {
                if (null != fr) {
                    fr.close();
                }//Fin if
            } catch (IOException e2) {
                System.out.println("Error en: " + LoadFile.class.getSimpleName()
                        + "::loadVehicles, Tipo de Error" + e2.getClass().getSimpleName() + ", " + e2.getMessage());
            }// Fin Try
        }// Fin try
    }

    public static void loadRatios(File nameFile, Instancia instancia){
        FileReader fr = null;
        BufferedReader br = null;
        String linea = null;
        String[] datos = null;
        List<Ratio> ratiosHPRC = null;
        List<Ratio> ratiosLPRC = null;

        try {
            fr = new FileReader(nameFile);
            br = new BufferedReader(fr);

            linea = br.readLine();
            ratiosHPRC = new ArrayList<>();
            ratiosLPRC = new ArrayList<>();

            while ((linea = br.readLine()) != null) {
                datos = linea.split(";");
                Ratio ratio = new Ratio(getNumerador(datos[0]), getDenominador(datos[0]));
                if (datos[1].equalsIgnoreCase("1")) {
                    ratiosHPRC.add(ratio);
                } else {
                    ratiosLPRC.add(ratio);
                }// fin if
            }// fin wh

            instancia.setRatiosHPRC(ratiosHPRC);
            instancia.setRatiosLPRC(ratiosLPRC);
        } catch (IOException e){
            System.out.println("Error en: " + LoadFile.class.getSimpleName()
                    + "::loadRatios, Tipo de Error: " + e.getClass().getSimpleName() + ", " + e.getMessage());
        } finally {
            try {
                if (null != fr) {
                    fr.close();
                }//Fin if
            } catch (IOException e2) {
                System.out.println("Error en: " + LoadFile.class.getSimpleName()
                        + "::loadRatios, Tipo de Error" + e2.getClass().getSimpleName() + ", " + e2.getMessage());
            }// Fin Try
        }// fin try
    }

    public static void loadPaintLimit(File nameFile, Instancia instancia){
        FileReader fr = null;
        BufferedReader br = null;
        String linea = null;
        int paintLimit = 0;

        try {
            fr = new FileReader(nameFile);
            br = new BufferedReader(fr);
            linea = br.readLine();

            while ((linea = br.readLine()) != null) {
                paintLimit = Integer.parseInt(linea.substring(0,linea.indexOf(";")));
            }// fin wh

            instancia.setPaintLimit(paintLimit);
        } catch (IOException e){
            System.out.println("Error en: " + LoadFile.class.getSimpleName()
                    + "::loadPaintLimit, Tipo de Error: " + e.getClass().getSimpleName() + ", " + e.getMessage());
        } finally {
            try {
                if (null != fr) {
                    fr.close();
                }//Fin if
            } catch (IOException e2) {
                System.out.println("Error en: " + LoadFile.class.getSimpleName()
                        + "::loadPaintLimit, Tipo de Error" + e2.getClass().getSimpleName() + ", " + e2.getMessage());
            }// Fin Try
        }// fin try
    }

    public static void loadObjectives(File nameFile, Instancia instancia){
        FileReader fr = null;
        BufferedReader br = null;
        String linea = null;
        String[] datos = null;
        List<Character> objectives = null;

        try {
            fr = new FileReader(nameFile);
            br = new BufferedReader(fr);
            linea = br.readLine();
            objectives = new ArrayList<>();

            while ((linea = br.readLine()) != null) {
                datos = linea.split(";");
                switch (datos[1]){
                    case "paint_color_batches":
                        objectives.add('C');
                        break;
                    case "high_priority_level_and_easy_to_satisfy_ratio_constraints":
                        objectives.add('H');
                        break;
                    case "high_priority_level_and_difficult_to_satisfy_ratio_constraints":
                        objectives.add('H');
                        break;
                    default:
                        objectives.add('L');
                }// fin sw
            }// fin wh

            instancia.setObjectives(objectives);
        } catch (IOException e){
            System.out.println("Error en: " + LoadFile.class.getSimpleName()
                    + "::loadPaintLimit, Tipo de Error: " + e.getClass().getSimpleName() + ", " + e.getMessage());
        } finally {
            try {
                if (null != fr) {
                    fr.close();
                }//Fin if
            } catch (IOException e2) {
                System.out.println("Error en: " + LoadFile.class.getSimpleName()
                        + "::loadPaintLimit, Tipo de Error" + e2.getClass().getSimpleName() + ", " + e2.getMessage());
            }// Fin Try
        }// fin try
    }

    private static byte getNumberHPRC(String[] datos){
        byte count = 0;
        for (String dato: datos) {
            if (dato.contains("HPRC")){
                count++;
            }// fin if
        }// fin for
        return count;
    }

    private static byte getNumberLPRC(String[] datos){
        byte count = 0;
        for (String dato: datos) {
            if (dato.contains("LPRC")){
                count++;
            }// fin if
        }// fin for
        return count;
    }

    private static Fecha createFecha(String d){
        String[] dato = d.split(" ");
        return new Fecha(Integer.parseInt(dato[0]), Byte.parseByte(dato[1]), Byte.parseByte(dato[2]));
    }

    private static List<Byte> createHPRC(String[] dato, byte h, byte inicio){
        List<Byte> hprc = new ArrayList<>();
        for (int i = 1; i <= h; i++) {
            hprc.add(Byte.valueOf(dato[inicio+i]));
        }// fin for
        return hprc;
    }

    private static List<Byte> createLPRC(String[] dato, byte l, byte inicio){
        List<Byte> lprc = new ArrayList<>();
        for (int i = 1; i <= l; i++) {
            lprc.add(Byte.valueOf(dato[inicio+i]));
        }// fin for
        return lprc;
    }

    private static short getNumerador(String dato){
        String[] d = dato.split("/");
        return Short.parseShort(d[0]);
    }

    private static short getDenominador(String dato){
        String[] d = dato.split("/");
        return Short.parseShort(d[1]);
    }

    private static void asignarClase(List<GrupoClase> clases, List<Vehicle> vehiculosSec, Vehicle vehicle){
        boolean nuevo = true;

        for (int i = 0; i < clases.size(); i++) {
            int higth = dist(vehiculosSec.get(clases.get(i).getSeqRank().get(0) - 1).getHigh(), vehicle.getHigh());
            int low = dist(vehiculosSec.get(clases.get(i).getSeqRank().get(0) - 1).getLow(), vehicle.getLow());
            if ((higth + low) < 1){
                clases.get(i).getSeqRank().add(vehicle.getSeqRank());
                if (clases.get(i).getColores().containsKey(vehicle.getColor())) {
                    clases.get(i).getColores().replace(vehicle.getColor(), (clases.get(i).getColores().get(vehicle.getColor()) + 1));
                } else {
                    clases.get(i).getColores().put(vehicle.getColor(), 1);
                }// fin if
                vehicle.setClase(i);
                nuevo = false;
                break;
            }//fin if
        }// fin for
        if (nuevo){
            GrupoClase grupoClase = new GrupoClase("Clase-" + String.valueOf(clases.size()));
            grupoClase.getSeqRank().add(vehicle.getSeqRank());
            grupoClase.getColores().put(vehicle.getColor(), 1);
            clases.add(grupoClase);
            vehicle.setClase(clases.size() - 1);
        }// fin if
    }

    private static int dist(List<Byte> opcionesX, List<Byte> opcionesY) {
        int count = 0;

        for (int i = 0; i < opcionesX.size(); i++) {
            count += (opcionesX.get(i) + opcionesY.get(i)) % 2;
        }// fin for

        return count;
    }

}//-->

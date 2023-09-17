package org.example.util;

public class ReferenceCalculator {
    public static ResultSet calculateFromNet(double net, double vatRate) {
        double vat = net * vatRate / 100;
        double gross = net + vat;
        return new ResultSet(net, vat, gross);
    }

    public static ResultSet calculateFromVat(double vat, double vatRate) {
        double net = vat * 100 / vatRate;
        double gross = net + vat;
        return new ResultSet(net, vat, gross);
    }

    public static ResultSet calculateFromGross(double gross, double vatRate) {
        double net = gross / (1 + vatRate / 100);
        double vat = gross - net;
        return null;
    }
}

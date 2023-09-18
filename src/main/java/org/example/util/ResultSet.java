package org.example.util;

public record ResultSet(double netPrice, double vatValue, double grossPrice) {
    private static final double DEFAULT_EPSILON = 0.01;
    @Override
    public boolean equals(Object object) {
        return equals(object, DEFAULT_EPSILON);
    }

    public boolean equals(Object object, double epsilon) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;

        ResultSet resultSet = (ResultSet) object;

        // Compare the three 'double' fields with the specified epsilon.
        return Math.abs(resultSet.netPrice - netPrice) < epsilon &&
                Math.abs(resultSet.vatValue - vatValue) < epsilon &&
                Math.abs(resultSet.grossPrice - grossPrice) < epsilon;
    }
}

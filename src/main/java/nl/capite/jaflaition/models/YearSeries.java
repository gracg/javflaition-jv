package nl.capite.jaflaition.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class YearSeries {
    final Integer year;
    final List<Double> months;
    final Double half1;
    final Double half2;

    public YearSeries(Integer year, List<Double> months, Double half1, Double half2) {
        this.year = year;
        this.months = months;
        this.half1 = half1;
        this.half2 = half2;
    }

    public Integer getYear() {
        return year;
    }

    public List<Double> getMonths() {
        return months;
    }

    public Double getHalf1() {
        return half1;
    }

    public Double getHalf2() {
        return half2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        YearSeries that = (YearSeries) o;
        return Objects.equals(year, that.year) && Objects.equals(months, that.months) && Objects.equals(half1, that.half1) && Objects.equals(half2, that.half2);
    }

    @Override
    public int hashCode() {
        return Objects.hash(year, months, half1, half2);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("|" + String.valueOf(this.year) + " | ");
        this.months.forEach(n -> sb.append(String.valueOf(n) + " | "));
        sb.append(String.valueOf(this.half1) + " | ");
        sb.append(String.valueOf(this.half2) + "|");
        return sb.toString();
    }
}

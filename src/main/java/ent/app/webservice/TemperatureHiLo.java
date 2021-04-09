package ent.app.webservice;

public class TemperatureHiLo {
    private Double high;
    private Double low;
    private String date;

    public TemperatureHiLo(String date, Double low, Double high) {
        this.high = high;
        this.low = low;
        this.date = date;
    }

    public Double getHigh() {
        return high;
    }

    public void setHigh(Double high) {
        this.high = high;
    }

    public Double getLow() {
        return low;
    }

    public void setLow(Double low) {
        this.low = low;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}

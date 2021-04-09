package ent.app.webservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TemperatureConverterService implements ITemperatureConverterService {

    @Value("<!DOCTYPE html><html><head><title>Historical Temperature Data</title></head><body><table border=\"1\"><thead><th>Date</th><th>[TYPE] Low</th><th>[TYPE] High</th></thead><tbody>[TABLE_ROW_DATA]</tbody></table></body></html>")
    private String htmlTemplate;

    @Autowired
    private HistoricalTemperatureRepository historicalTemperatureRepository;

    @Override
    public double convertFahrenheitToCelsius(double fahrenheit) {
        return (fahrenheit - 32) / 1.8;
    }

    @Override
    public double convertCelsiusToFahrenheit(double celsius) {
        return (celsius * 1.8) + 32;
    }

    @Override
    public List<TemperatureHiLo> getAllHistoricalTemperatureInFahrenheit() {
        return historicalTemperatureRepository.getAllHistoricalTemperatureFahrenheit();
    }

    @Override
    public List<TemperatureHiLo> getAllHistoricalTemperatureInCelsius() {
        return historicalTemperatureRepository.getAllHistoricalTemperatureFahrenheit().stream().map(e -> {
            return new TemperatureHiLo(e.getDate(), convertFahrenheitToCelsius(e.getLow()), convertFahrenheitToCelsius(e.getHigh()));
        }).collect(Collectors.toList());
    }

    @Override
    public String getHtmlTemplateString(boolean isFahrenheit) throws IOException {
        String htmlFile = new String(htmlTemplate);
        StringBuffer tableRowData = new StringBuffer();

        List<TemperatureHiLo> list = null;
        if (isFahrenheit) {
            htmlFile = htmlFile.replace("[TYPE]","Fahrenheit");
            list = getAllHistoricalTemperatureInFahrenheit();
        } else {
            htmlFile = htmlFile.replace("[TYPE]","Celsius");
            list = getAllHistoricalTemperatureInCelsius();
        }

        list.stream()
                .forEach(e -> {
                    tableRowData.append("<tr>");
                    tableRowData.append(String.format("<td>%s</td>", e.getDate()));
                    tableRowData.append(String.format("<td>%.2f</td>", e.getLow()));
                    tableRowData.append(String.format("<td>%.2f</td>", e.getHigh()));
                    tableRowData.append("</tr>");
                });

        htmlFile = htmlFile.replace("[TABLE_ROW_DATA]", tableRowData.toString());
        return htmlFile;
    }
}

package ent.app.webservice;

import java.io.IOException;
import java.util.List;

public interface ITemperatureConverterService {
    double convertFahrenheitToCelsius(double fahrenheit);
    double convertCelsiusToFahrenheit(double celsius);
    List<TemperatureHiLo> getAllHistoricalTemperatureInFahrenheit();
    List<TemperatureHiLo> getAllHistoricalTemperatureInCelsius();
    String getHtmlTemplateString(boolean isFahrenheit) throws IOException;
}

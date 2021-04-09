package ent.app.webservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/rest/temperature")
public class TemperatureController {

    @Autowired
    private ITemperatureConverterService temperatureConverterService;

    @GetMapping(value="ftoc/{fahrenheit}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> convertFahrenheitToCelsius(@PathVariable(value = "fahrenheit") Double fahrenheit) {
        return new ResponseEntity<>(String.format("%.2f", temperatureConverterService.convertFahrenheitToCelsius(fahrenheit)), HttpStatus.OK);
    }

    @GetMapping(value="ctof/{celsius}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> convertCelsiusToFahrenheit(@PathVariable(value = "celsius") Double celsius) {
        return new ResponseEntity<>(String.format("%.2f", temperatureConverterService.convertCelsiusToFahrenheit(celsius)), HttpStatus.OK);
    }

    @GetMapping(value="historical/ftoc", produces = MediaType.TEXT_HTML_VALUE)
    public ResponseEntity<String> getAllHistoricalFahrenheit() throws IOException {
        return new ResponseEntity(temperatureConverterService.getHtmlTemplateString(true), HttpStatus.OK);
    }

    @GetMapping(value="historical/ctof", produces = MediaType.TEXT_HTML_VALUE)
    public ResponseEntity<?> getAllHistoricalCelsius() throws IOException {
        return new ResponseEntity(temperatureConverterService.getHtmlTemplateString(false), HttpStatus.OK);
    }

}

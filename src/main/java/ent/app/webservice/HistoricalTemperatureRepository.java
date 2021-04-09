package ent.app.webservice;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class HistoricalTemperatureRepository {
    private static final Map<String, TemperatureHiLo> histTempMap = new HashMap();

    @PostConstruct
    public void initData() {
        // temperature stored in Fahrenheit Low, High
        histTempMap.put("03/22/2020", new TemperatureHiLo("03/22/2020", 30d, 42d));
        histTempMap.put("03/23/2020", new TemperatureHiLo("03/23/2020", 28d, 45d));
        histTempMap.put("03/24/2020", new TemperatureHiLo("03/24/2020", 22d, 39d));
        histTempMap.put("03/25/2020", new TemperatureHiLo("03/25/2020", 27d, 41d));
        histTempMap.put("03/26/2020", new TemperatureHiLo("03/26/2020", 31d, 47d));
        histTempMap.put("03/27/2020", new TemperatureHiLo("03/27/2020", 33d, 45d));
        histTempMap.put("03/28/2020", new TemperatureHiLo("03/28/2020", 26d, 38d));
        histTempMap.put("03/29/2020", new TemperatureHiLo("03/29/2020", 24d, 36d));
        histTempMap.put("03/30/2020", new TemperatureHiLo("03/30/2020", 31d, 45d));
        histTempMap.put("03/31/2020", new TemperatureHiLo("03/31/2020", 34d, 49d));
    }

    public TemperatureHiLo getByDate(String date) {
        return histTempMap.get(date);
    }

    public List<TemperatureHiLo> getAllHistoricalTemperatureFahrenheit() {
        return histTempMap.entrySet().stream().map(Map.Entry::getValue).collect(Collectors.toList());
    }
}

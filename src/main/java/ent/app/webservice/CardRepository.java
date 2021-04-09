package ent.app.webservice;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class CardRepository {

    private static final List<String> cardNumberList = new ArrayList<>();

    @PostConstruct
    public void initData() {
        cardNumberList.addAll(Arrays.asList("444411112222", "440011113333", "111188886699"));
    }

    public boolean isCardValid(String cardNumber) {
        return cardNumberList.contains(cardNumber);
    }
}

package systemdesign.monopoly.src.model.tiles.card;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;

public class CardDeckBuilder {
    /**
     * This method builds the card deck object.
     * @param config is the JSON config object.
     * @return the card deck that is built.
     */
    public CardDeck build(JSONObject config) {
        String associatedDeck = (String) config.get("associatedDeck");
        AbstractCardFactory cardFactory = new CardFactory();
        Card createdCard;
        CardDeck cardDeck;
        ArrayList<Card> cardArrayList = new ArrayList<>();
        JSONArray cards = (JSONArray) (config.get(associatedDeck));
        for (Object card : cards) {
            JSONObject c = (JSONObject) ((JSONObject) card).get("card");
            createdCard = cardFactory.createCard(c);
            createdCard.setType(associatedDeck);
            cardArrayList.add(createdCard);
        }
        if (associatedDeck.equals("ChanceCardDeck")) {
            cardDeck = new ChanceCardDeck(cardArrayList);

        } else if (associatedDeck.equals("CommunityChestCardDeck")) {
            cardDeck = new CommunityChestCardDeck(cardArrayList);
        } else {
            throw new RuntimeException("Wrong Card Deck type!");
        }

        return cardDeck;
    }

}

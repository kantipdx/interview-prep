package systemdesign.monopoly.src.model.tiles;

import systemdesign.monopoly.src.model.player.Player;
import systemdesign.monopoly.src.model.tiles.card.Card;
import systemdesign.monopoly.src.model.tiles.card.CardDeck;

import java.util.ArrayList;
import java.util.Collections;

public class CardTile extends Tile {
    CardDeck cardDeck;
    // this will be set board builder

    /**
     * This method draws a card from the deck and activates its action
     * @param player the player who landed on the tile
     * @param actions the actions associated with the tile
     * @return
     */
    @Override
    protected ArrayList<GameAction> hook(Player player, ArrayList<GameAction> actions) {

        if(player.getCurrentlyDrawnCard() == null){
            Card card = cardDeck.drawCard();
            player.setCurrentlyDrawnCard(card);
        }

        setActive(actions, "Apply", true);

        return actions;
    }

    /**
     * @param cardDeck the card deck associated with this card tile
     */
    public void setCardDeck( CardDeck cardDeck){
        this.cardDeck = cardDeck;
    }

    /**
     * @return the card deck associated with this card tile
     */
    public CardDeck getCardDeck(){
        return cardDeck;
    }
}

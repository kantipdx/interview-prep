package systemdesign.monopoly.src.model.player;

import systemdesign.monopoly.src.model.AuctionModel;
import systemdesign.monopoly.src.model.TradeModel;
import systemdesign.monopoly.src.model.tiles.Tile;
import systemdesign.monopoly.src.model.tiles.card.Card;
import systemdesign.monopoly.src.model.tiles.property.TitleDeedCard;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Interface that outlines the methods implementing Player classes must have.
 */
public interface Player extends Serializable {

    // in the order written in the design report

    boolean waitInJail();

    void checkBailOut();

    void goToJail();

    void changeBalance(int amount);

    void declareBankruptcy();

    TaxOption getTaxOption();

    void setTaxOption(TaxOption selectedOption);

    int getBalance();

    void setBalance( int balance);

    int getConsecutiveDoubleCount();

    void setConsecutiveDoubleCount( int consecutiveDoubleCount);

    Tile getCurrentTile();

    void setCurrentTile(Tile currentTile);

    void moveToken(int amount);

    PlayerToken getPlayerToken();

    void setPlayerToken(PlayerToken playerToken);

    BailOutChoice getGetOutOfJailChoice();

    void setGetOutOfJailChoice(BailOutChoice bailOutChoice);

    int getTotalWorth();

    void setTotalWorth( int totalWorth);

    int getLiquidTotalWorth();

    void setLiquidTotalWorth( int liquidTotalWorth);

    void startAuction(ArrayList<TitleDeedCard> titleDeedCards);

    AuctionModel getAuctionModel();

    void setAuctionModel( AuctionModel auctionModel);

    void startTrade(Player otherPlayer);

    TradeModel getTradeModel();

    void setTradeModel( TradeModel tradeModel);

    void addBailOutFromJailCard(Card card);

    void removeBailOutFromJailCard();

    TitleDeedCard getSelectedTitleDeed();

    void setSelectedTitleDeed( TitleDeedCard selectedTitleDeed);

    Card getCurrentlyDrawnCard();

    void setCurrentlyDrawnCard( Card currentlyDrawnCard);

    boolean isBankrupt();

    void playTurn();

    Dice rollDice();

    ArrayList<TitleDeedCard> getTitleDeeds();

    ArrayList<Card> getBailOutOfJailCards();

    void addTitleDeedCard(TitleDeedCard card);

    void removeTitleDeedCard(TitleDeedCard card);


    void payBailOutMoney();

    boolean isInJail();

    public Dice getPlayersDice();
    public void setPlayersDice(Dice playersDice);
    public boolean isAIControlled();
}

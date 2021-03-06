package systemdesign.monopoly.src.model.tiles;

import systemdesign.monopoly.src.model.player.Player;
import systemdesign.monopoly.src.model.tiles.property.TitleDeedCard;

import java.util.ArrayList;


public class PropertyTile extends Tile {
    TitleDeedCard titleDeedCard;

    public PropertyTile() {
    }

    /**
     * This method adjusts the activity of the property actions based on the state of the property
     * @param player the player who landed on the tile
     * @param actions the actions associated with the tile
     * @return
     */
    @Override
    protected ArrayList<GameAction> hook(Player player, ArrayList<GameAction> actions) {
        player.setSelectedTitleDeed(titleDeedCard);
        if(!(titleDeedCard.isOwned())){
            setActive ( actions, "Buy Property", player.getBalance () >= getTitleDeedCard().getPropertyValue());
            setActive ( actions, "Pay Rent", false );
            setActive ( actions, "Start Auction", false );
        } else if(titleDeedCard.getOwner() != player){
            setActive ( actions, "Buy Property", false );
            setActive(actions, "Pay Rent", player.getLiquidTotalWorth() >= player.getSelectedTitleDeed().getCurrentRent());
            setActive(actions, "Declare Bankruptcy", player.getLiquidTotalWorth() < player.getSelectedTitleDeed().getCurrentRent());
            setActive ( actions, "Start Auction", false );
        } else if(titleDeedCard.getOwner() == player){
            setActive ( actions, "Buy Property", false );
            setActive ( actions, "Pay Rent", false );
            setActive ( actions, "Start Auction", false );
            return titleDeedCard.getPropertyActions();
        } else {
            throw new RuntimeException();
        }

        return actions;
    }


    /**
     * @return the title deed card of the property
     */
    public TitleDeedCard getTitleDeedCard() {
        return titleDeedCard;
    }

    /**
     * @param titleDeedCard the title deed card of the property
     */
    public void setTitleDeedCard(TitleDeedCard titleDeedCard) {
        this.titleDeedCard = titleDeedCard;
    }
}

package systemdesign.monopoly.src.model.tiles.property;

import systemdesign.monopoly.src.model.player.Player;
import systemdesign.monopoly.src.model.tiles.GameAction;
import systemdesign.monopoly.src.model.tiles.GameActionConstants;
import systemdesign.monopoly.src.model.tiles.PropertyTile;
import systemdesign.monopoly.src.model.tiles.Tile;
import systemdesign.monopoly.src.model.tiles.actionStrategy.ActionFactory;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class TitleDeedCard implements Serializable {
    final double mortgageRemovalMultiplier = 1.1;
    ArrayList<GameAction> propertyActions;
    String propertyName;
    int levelZeroRent, levelOneRent, levelTwoRent, levelThreeRent, levelFourRent, levelFiveRent;
    boolean isOwned;
    boolean isMortgaged;
    int upgradeLevel;
    int propertyValue;
    int mortgageValue;
    int upgradeCost;
    int mortgagedTurnNumber;
    ColorGroup colorGroup;
    Player owner;
    HashMap<String, GameAction> actionNames;

    /**
     * Constructor for the title deed card, initializes rents, property name and status about
     * various conditions of the title deed cards.
     */
    public TitleDeedCard(){
        levelOneRent = 0;
        levelTwoRent = 0;
        levelThreeRent = 0;
        levelFourRent = 0;
        levelFiveRent = 0;
        propertyName = "";
        isMortgaged = false;
        isOwned = false;
        upgradeLevel = 0;
        propertyValue = 0;
        mortgageValue = 0;
        upgradeCost = 0;
        colorGroup = null;
        owner = null;


        ActionFactory actionFactory = new ActionFactory();
        propertyActions = actionFactory.getActionList(getClass().getSimpleName());

        actionNames = new HashMap<>();
        for(GameAction action: propertyActions){
            actionNames.put(action.getName(), action);
            System.out.println(action);
        }
        updateActions();
    }

    /**
     * @param owned the boolean value indicating whether the property is owned
     */
    public void setOwned(boolean owned) {
        isOwned = owned;
    }

    /**
     * @param owner the player who owns the property
     */
    public void setOwner(Player owner) {
        this.owner = owner;
    }

    /**
     * @return the action names associated with the property
     */
    public HashMap<String, GameAction> getActionNames() {
        return actionNames;
    }

    /**
     * @param actionNames the action names associated with the property
     */
    public void setActionNames(HashMap<String, GameAction> actionNames) {
        this.actionNames = actionNames;
    }

    /**
     * @return the game action objects associated with the property
     */
    public ArrayList<GameAction> getPropertyActions() {
        ArrayList<GameAction> actions = new ArrayList<>() ;
        updateActions();
        for (GameAction propertyAction : propertyActions) {
            propertyAction.setPlayer(getOwner());
            System.out.println(propertyAction);
            if (propertyAction.isActive()) {
                actions.add(propertyAction);
            }
        }
        return actions;
    }

    /**
     * @param propertyActions the game action objects associated with the property
     */
    public void setPropertyActions(ArrayList<GameAction> propertyActions) {
        this.propertyActions = propertyActions;
    }

    /**
     * Finds the action with the specified name and activates it
     * @param actionName the name of action
     */
    public void deactivateAction(String actionName){

        GameAction selectedAction = actionNames.get(actionName);

        if(selectedAction != null){
            selectedAction.setActive(false);
        }else{
            throw new RuntimeException();
        }
    }

    /**
     * Finds the action with the specified name and deactivates it
     * @param actionName the name of action
     */
    public void activateAction(String actionName){
        GameAction selectedAction = actionNames.get(actionName);

        if(selectedAction != null){
            selectedAction.setActive(true);
        }else{
            throw new RuntimeException();
        }
    }

    /**
     * To string method for the property name
     * @return the name of the property.
     */
    @Override
    public String toString() {
        return this.getPropertyName();
    }

    /**
     * Updates the property's actions based according to the state of property (owned, mortgaged and upgradeLevel)
     */
    public void updateActions() {
//        if (upgradeLevel == 0) {
//            activateAction(GameActionConstants.UPGRADE_PROPERTY_ACTION);
//            activateAction(GameActionConstants.);
//        }
//
        if(isMortgaged()) {
            activateAction("Remove Mortgage");
            deactivateAction("Mortgage Property");
        }
        else if(upgradeLevel == 0){
            deactivateAction("Remove Mortgage");
            activateAction("Mortgage Property");
        }else{
            deactivateAction("Mortgage Property");
            deactivateAction("Remove Mortgage");
        }

        if(isUpgradeable ())
            activateAction ( "Upgrade Property" );
        else
            deactivateAction ( "Upgrade Property" );

        if(isDowngradeable())
            activateAction("Downgrade Property");
        else
            deactivateAction("Downgrade Property");

    }

    /**
     * @return level four rent of the property
     */
    public int getLevelFourRent() {
        return levelFourRent;
    }

    /**
     * @param levelFourRent level four rent of the property
     */
    public void setLevelFourRent(int levelFourRent) {
        this.levelFourRent = levelFourRent;
    }

    /**
     * @return level five rent of the property
     */
    public int getLevelFiveRent() {
        return levelFiveRent;
    }

    /**
     * @param levelFiveRent level five rent of the property
     */
    public void setLevelFiveRent(int levelFiveRent) {
        this.levelFiveRent = levelFiveRent;
    }

    /**
     * @return whether the property is mortgaged
     */
    public boolean isMortgaged() {
        return isMortgaged;
    }

    /**
     * @return whether the property is owned
     */
    public boolean isOwned() {
        return isOwned;
    }

    /**
     * @return the owner of the property
     */
    public Player getOwner() {
        return owner;
    }

    /**
     * @param mortgaged whether the property is mortgaged
     */
    public void setMortgaged(boolean mortgaged) {
        isMortgaged = mortgaged;
    }

    /**
     * @return upgrade level of the property
     */
    public int getUpgradeLevel() {
        return upgradeLevel;
    }

    /**
     * @param upgradeLevel the upgrade level of the property
     */
    public void setUpgradeLevel(int upgradeLevel) {
        this.upgradeLevel = upgradeLevel;
    }

    /**
     * @return value of the property
     */
    public int getPropertyValue() {
        return propertyValue;
    }

    /**
     * @param propertyValue value of the property
     */
    public void setPropertyValue(int propertyValue) {
        this.propertyValue = propertyValue;
    }

    /**
     * @return mortgage value of the property
     */
    public int getMortgageValue() {
        return mortgageValue;
    }

    /**
     * @param mortgageValue mortgage value of the property
     */
    public void setMortgageValue(int mortgageValue) {
        this.mortgageValue = mortgageValue;
    }

    /**
     * @return the cost of upgrading the property
     */
    public int getUpgradeCost() {
        return upgradeCost;
    }

    /**
     * @param upgradeCost upgrade cost of the property
     */
    public void setUpgradeCost(int upgradeCost) {
        this.upgradeCost = upgradeCost;
    }

    /**
     * @return the number of turns passed while the property is mortgaged
     */
    public int getMortgagedTurnNumber() {
        return mortgagedTurnNumber;
    }

    /**
     * @param mortgagedTurnNumber the number of turns passed while the property is mortgaged
     */
    public void setMortgagedTurnNumber(int mortgagedTurnNumber) {
        this.mortgagedTurnNumber = mortgagedTurnNumber;
    }

    /**
     * @return the color group of the property
     */
    public ColorGroup getColorGroup() {
        return colorGroup;
    }

    /**
     * @param colorGroup color group of the property
     */
    public void setColorGroup(ColorGroup colorGroup) {
        this.colorGroup = colorGroup;
    }

    /**
     * @return mortgage removal multiplier for the property
     */
    public double getMortgageRemovalMultiplier() {
        return mortgageRemovalMultiplier;
    }

    /**
     * @return the penalty of removing the mortgage of the property
     */
    public int mortgageRemovalPenalty() {
        return (int)(mortgageValue * getMortgageRemovalMultiplier());
    }

    public String getPropertyName() {
        return propertyName;

    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    /**
     * @return level zero rent of the property
     */
    public int getLevelZeroRent() {
        return levelZeroRent;

    }

    public void setLevelZeroRent(int levelZeroRent) {
        this.levelZeroRent = levelZeroRent;
    }

    /**
     * @return level one rent of the property
     */
    public int getLevelOneRent() {
        return levelOneRent;

    }

    public void setLevelOneRent(int levelOneRent) {
        this.levelOneRent = levelOneRent;
    }

    /**
     * @return level two rent of the property
     */
    public int getLevelTwoRent() {
        return levelTwoRent;

    }

    /**
     * @param levelTwoRent level two rent of the property
     */
    public void setLevelTwoRent(int levelTwoRent) {
        this.levelTwoRent = levelTwoRent;
    }

    /**
     * @return level three rent of the property
     */
    public int getLevelThreeRent() {
        return levelThreeRent;
    }

    /**
     * @param levelThreeRent level three rent of the property
     */
    public void setLevelThreeRent(int levelThreeRent) {
        this.levelThreeRent = levelThreeRent;
    }

    /**
     * Checks whether the property is upgradable. If yes, upgrades the property.
     * @return upgrade cost if the property is upgraded and 0 otherwise.
     */
    public int upgrade(){

        upgradeLevel++;
        updateActions();
        return upgradeCost;
    }

    /**
     * Checks whether the property is downgradable. If yes, downgrades the property.
     * @return the gain after downgrading the property.
     */
    public int downgrade() {

        upgradeLevel--;
        updateActions();
        return upgradeCost/2;
    }

    /**
     * Downgrades the property until its upgrade value becomes zero
     */
    public void resetProperty(){
        while(upgradeLevel > 0)
            downgrade();
        updateActions();
    }


    /**
     * This method mortgages the property and returns the player's gain after mortgaging the proeprty
     */
    public int mortgage() {
        isMortgaged = true;
        updateActions();
        return mortgageValue;
    }

    /**
     * This method removes the mortgage of the property and returns the mortgage removal fee
     */
    public int removeMortgage() {
        isMortgaged = false;
        updateActions();
        return (int)(mortgageValue * mortgageRemovalMultiplier);
    }


    /**
     *
     * @return the current rent based on upgrade level
     */
    public int getCurrentRent(){
        int upgradeLevel = 0;
        int rent = 0;

        upgradeLevel = getUpgradeLevel();

        if( upgradeLevel == 0){
            rent = getLevelZeroRent();
        }
        else if( upgradeLevel == 1){
            rent = getLevelOneRent();

        }
        else if( upgradeLevel == 2){
            rent = getLevelTwoRent();
        }
        else if( upgradeLevel == 3){
            rent = getLevelThreeRent();
        }
        else if( upgradeLevel == 4){
            rent = getLevelFourRent();
        }
        else if( upgradeLevel == 5){
            rent = getLevelFiveRent();
        }
        return rent;
    }


    /**
     * Checks if the action of upgrading the property is active at a moment
     * @return true if the property is upgradable
     */
    public boolean isUpgradeable(){
        //return actionNames.get("Upgrade Property").isActive();
        if (owner == null || colorGroup == null) {
            return false;
        }
        if(!colorGroup.allOwnedByPlayer(owner))
            return false;

        if(isMortgaged)
            return false;

        // Calculate upgrade level differences for each pair of properties in a color group.
        // If this property has at least one level more upgrade than any other property, the upgrade is not allowed
        // Also, if any other property in color group is mortgaged, the upgrade is not allowed
        for (Tile tile: colorGroup.getGroup ()) {
            int otherUpgradeLevel = ((PropertyTile) tile).getTitleDeedCard ().getUpgradeLevel();

            if (getUpgradeLevel() - otherUpgradeLevel > 0 || ((PropertyTile) tile).getTitleDeedCard ().isMortgaged())
                return false;
        }

        // If the upgrade level is five cannot upgrade
        if(upgradeLevel == 5){
            return false;
        }

        return true;
    }

    /**
     * Checks if the action of downgrading the property is active at a moment
     * @return true if the property is downgradeable
     */
    public boolean isDowngradeable(){
        //return actionNames.get("Downgrade Property").isActive();
        if(owner == null || colorGroup == null)
            return false;

        for (Tile tile: colorGroup.getGroup ()) {
            int otherUpgradeLevel = ((PropertyTile) tile).getTitleDeedCard ().getUpgradeLevel();

            if (otherUpgradeLevel - getUpgradeLevel()  > 0)
                return false;
        }

        // If the upgrade level is zero cannot downgrade
        if(upgradeLevel == 0)
            return false;

        return true;
    }
}

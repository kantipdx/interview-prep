package systemdesign.monopoly.src.model.tiles.actionStrategy;

import systemdesign.monopoly.src.model.tiles.GameAction;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Creates the actions performed on specifig tile
 */
public class ActionFactory extends AbstractActionFactory {

    /**
     *
     * @param className the class that the actions are provided.
     * @return
     */
    @Override
    public ArrayList<GameAction> getActionList(String className) {
        ArrayList<GameAction> actions = new ArrayList<>();
        switch (className) {
            case "PropertyTile":
                ActionStrategy propertyTile = new PropertyTileActionStrategy();
                actions.add(new GameAction("Buy Property", propertyTile::button1Strategy, false, true));
                actions.add(new GameAction("Pay Rent", propertyTile::button2Strategy, true, false));
                actions.add(new GameAction("Start Auction", propertyTile::button3Strategy, false, false));
                break;
            case "TitleDeedCard":
                ActionStrategy propertyAction = new PropertyActionsStrategy();

                actions.add(new GameAction("Upgrade Property", propertyAction::button1Strategy, false, false));
                actions.add(new GameAction("Downgrade Property", propertyAction::button2Strategy, false, false));
                actions.add(new GameAction("Mortgage Property", propertyAction::button3Strategy, false, false));
                actions.add(new GameAction("Remove Mortgage", propertyAction::button4Strategy, false, false));
                break;
            case "CardTile":
                ActionStrategy cardTile = new CardTileActionStrategy();
                actions.add(new GameAction("Apply", cardTile::button1Strategy, true, true));
                break;
            case "IncomeTaxTile":
                ActionStrategy incomeTaxTile = new IncomeTaxTileActionStrategy();
                actions.add(new GameAction("Pay With Ratio", incomeTaxTile::button1Strategy, true, true));
                actions.add(new GameAction("Pay Fixed Amount", incomeTaxTile::button2Strategy, true, true));
                actions.add(new GameAction("Pay Tax", incomeTaxTile::button3Strategy, true, false));

                break;
            case "TeleportTile":
                ActionStrategy teleportTile = new TeleportTileActionStrategy();
                actions.add(new GameAction("Teleport", teleportTile::button1Strategy, true, true));

                break;
            case "JailTile":
                ActionStrategy jailTile = new JailTileActionStrategy();
                actions.add(new GameAction("Pay Bail Bond", jailTile::button2Strategy, false, true));
                actions.add(new GameAction("Roll Dice", jailTile::button3Strategy, false, true));
                actions.add(new GameAction("Use Bail Out Of Jail Card", jailTile::button4Strategy, false, true));

                break;

            case "GoToJailTile":
                ActionStrategy goToJailTile = new GoToJailTileActionStrategy();
                actions.add(new GameAction("Go To Jail", goToJailTile::button1Strategy, true, false));

                break;
        }
        return actions;
    }
}

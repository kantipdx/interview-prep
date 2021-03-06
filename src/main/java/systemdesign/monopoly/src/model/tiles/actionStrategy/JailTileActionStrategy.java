package systemdesign.monopoly.src.model.tiles.actionStrategy;

import systemdesign.monopoly.src.model.player.BailOutChoice;
import systemdesign.monopoly.src.model.player.Player;

public class JailTileActionStrategy extends ActionStrategy {
    /**
     * This strategy is related to getting the player out of jail when he waits for three turns.
     *
     * @param player is the player that the action is inflicted on.
     */
    @Override
    public void button1Strategy(Player player) {
        player.setGetOutOfJailChoice(BailOutChoice.WAIT);
    }

    /**
     * This strategy is related to getting the player out of jail when the player pays the fee.
     *
     * @param player is the player that the action is inflicted on.
     */
    @Override
    public void button2Strategy(Player player) {
        player.setGetOutOfJailChoice(BailOutChoice.MONEY);
    }

    /**
     * This strategy is related to getting the player out of jail when the player throws double dice in three tries.
     *
     * @param player is the player that the action is inflicted on.
     */
    @Override
    public void button3Strategy(Player player) {
        player.setGetOutOfJailChoice(BailOutChoice.DOUBLE_DICE);

    }

    /**
     * This strategy is related to getting the player out of jail when the player uses a get out of jail card.
     *
     * @param player is the player that the action is inflicted on.
     */
    @Override
    public void button4Strategy(Player player) {
        player.setGetOutOfJailChoice(BailOutChoice.BAIL_OUT_CARD);
        player.removeBailOutFromJailCard();
    }
}

package systemdesign.monopoly.src.model.player;

import systemdesign.monopoly.src.model.BoardConfiguration;
import org.json.simple.JSONObject;


import java.util.ArrayList;

/**
 * Abstract class utilizing the abstract factory design pattern to create players.
 */
public abstract class AbstractPlayerFactory {
    public abstract ArrayList<Player> get(BoardConfiguration boardConfiguration, JSONObject config);

}

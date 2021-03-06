package systemdesign.monopoly.src.model.tiles.card;

import org.json.simple.JSONObject;

public abstract class AbstractCardFactory {
    public AbstractCardFactory() {
    }

    public abstract Card createCard(JSONObject config);
}

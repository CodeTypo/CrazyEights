import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableObjectValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class GameModelReactive {

    private ObservableList<Card> stock = FXCollections.observableArrayList();

    /**
     * We need to track number of cards in stock,
     * because it depends on whether Controller draw stock or not.
     */
    private SimpleBooleanProperty isStockEmpty = new SimpleBooleanProperty(stock.isEmpty());

    /**
     * We need to track turnPlayer because only
     * he can make action like deal or play card.
     */
    private ObservableObjectValue<Player> turnPlayer = new SimpleObjectProperty<>();

    /**
     * We don't need to track all cards in pile.
     * Only top card is tracked because
     * Controller needs to refresh its view.
     */
    private ObservableObjectValue<Card> topPile = new SimpleObjectProperty<>();


    private ObservableObjectValue<Suit> suit = new SimpleObjectProperty<>();

    //Another test with shelves
}

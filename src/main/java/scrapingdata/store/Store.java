package scrapingdata.store;




import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import scrapingdata.Base;

public class Store {

    private ObservableList<Base> itemHistory = FXCollections.observableArrayList();

    public ObservableList<Base> getItemHistory() {
        return itemHistory;
    }

    public void addHistory(Base base) {

        itemHistory.add(base);
    }
}


package in.at.views.main;

import in.at.domain.Departure;
import in.at.domain.Station;
import in.at.exceptions.IRailException;
import in.at.response.LiveBoardResponse;
import in.at.response.StationResponse;
import in.at.utils.IRailUtil;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.util.Collections;

@PageTitle("Main")
@Route(value = "")
public class MainView extends VerticalLayout {

    private ComboBox<Station> stations;
    private Button search;
    private Grid<Departure> departures;

    public MainView() {
        StationResponse stationResponse = IRailUtil.fetchStations();
        stations = new ComboBox<>("Stations", stationResponse.getStation());
        stations.setItemLabelGenerator(Station::getStandardname);

        departures = new Grid<>(Departure.class, false);
        departures.addColumn(Departure::getStation).setHeader("Station");
        departures.addColumn(Departure::getPlatform).setHeader("Platform");
        departures.addColumn(Departure::getDelay).setHeader("Delay");
        departures.addColumn(Departure::getTime).setHeader("Time");
        departures.addColumn(Departure::getCanceled).setHeader("Cancelled");
        departures.addColumn(Departure::getLeft).setHeader("Left");

        search = new Button("Search");
        search.addClickListener(e -> {
            String stationId = stations.getValue().getId();
            try {
                LiveBoardResponse liveBoardResponse = IRailUtil.fetchLiveBoard(stationId);
                departures.setItems(liveBoardResponse.getDepartures().getDeparture());
            } catch (IRailException ie) {
                departures.setItems(Collections.emptyList());
            }
        });
        search.addClickShortcut(Key.ENTER);

        setMargin(true);

        HorizontalLayout searchLayout = new HorizontalLayout();
        searchLayout.add(stations, search);
        searchLayout.setVerticalComponentAlignment(Alignment.END, stations, search);

        add(searchLayout);
        add(departures);
    }


}

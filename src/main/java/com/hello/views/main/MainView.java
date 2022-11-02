package com.hello.views.main;

import com.hello.domain.Departure;
import com.hello.domain.LiveBoardResponse;
import com.hello.domain.Station;
import com.hello.domain.StationResponse;
import com.hello.utils.RestUtil;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Main")
@Route(value = "")
public class MainView extends VerticalLayout {

    private ComboBox<Station> stations;
    private Button search;
    private Grid<Departure> departures;

    public MainView() {
        StationResponse stationResponse = RestUtil.get("https://api.irail.be/stations?format=json&lang=en", StationResponse.class);
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
            LiveBoardResponse liveBoardResponse = RestUtil.get(String.format("https://api.irail.be/liveboard?station=%s&format=json&lang=en", stationId), LiveBoardResponse.class);
            departures.setItems(liveBoardResponse.getDepartures().getDeparture());
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

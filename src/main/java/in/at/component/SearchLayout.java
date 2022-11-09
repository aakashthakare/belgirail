package in.at.component;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import in.at.domain.Station;
import in.at.response.StationResponse;
import in.at.utils.IRailUtil;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SearchLayout extends HorizontalLayout {

    private ComboBox<Station> stations;

    private DatePicker datePicker;

    private Button search;
    
    public SearchLayout() {
        StationResponse stationResponse = IRailUtil.fetchStations();
        stations = new ComboBox<>("Stations", stationResponse.getStation());
        stations.setItemLabelGenerator(Station::getStandardname);
        stations.setWidth("400px");

        datePicker = new DatePicker("Date");
        datePicker.setValue(LocalDate.now());

        search = new Button("Search");
        search.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        search.addClickShortcut(Key.ENTER);
        HorizontalLayout searchLayout = new HorizontalLayout();
        add(stations, datePicker, search);
        setVerticalComponentAlignment(FlexComponent.Alignment.END, stations, search);
        setWidthFull();
    }

    public void addSeachClickListener(ComponentEventListener<ClickEvent<Button>> listener) {
        search.addClickListener(listener);
    }

    public String getSelectedStationId() {
        return stations.getValue().getId();
    }

    public String getSelectedDate() {
        return datePicker.getValue().format(DateTimeFormatter.ofPattern("ddMMyy"));
    }
}
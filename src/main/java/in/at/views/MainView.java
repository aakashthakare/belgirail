package in.at.views;

import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import in.at.component.AppTitle;
import in.at.component.LiveBoardGrid;
import in.at.component.SearchLayout;
import in.at.domain.TrainAction;
import in.at.exceptions.IRailException;
import in.at.response.LiveBoardResponse;
import in.at.utils.IRailUtil;

import java.util.Collections;
import java.util.List;

@PageTitle("Main")
@Route(value = "")
public class MainView extends VerticalLayout {

    private AppTitle appTitle;

    private SearchLayout searchLayout;

    private LiveBoardGrid liveBoardGrid;

    public MainView() {
        appTitle = new AppTitle();
        searchLayout = new SearchLayout();
        liveBoardGrid = new LiveBoardGrid();

        add(appTitle);
        add(searchLayout);
        add(liveBoardGrid);

        setSizeFull();
        setSpacing(false);

        addSearchClickListener();
    }

    private void addSearchClickListener() {
        searchLayout.addSeachClickListener(e -> {
            String stationId = searchLayout.getSelectedStationId();
            List<? extends TrainAction> departureList = Collections.emptyList();
            try {
                String date = searchLayout.getSelectedDate();
                String arrivalOrDeparture = searchLayout.getSelectedArrivalOrDeparture();
                LiveBoardResponse liveBoardResponse = IRailUtil.fetchLiveBoard(stationId, date, arrivalOrDeparture);

                boolean isArrival = "arrival".equalsIgnoreCase(arrivalOrDeparture);
                if(isArrival) {
                    departureList = liveBoardResponse.getArrivals().getArrival();
                } else {
                    departureList = liveBoardResponse.getDepartures().getDeparture();
                }

                liveBoardGrid.setItems(departureList);
            } catch (IRailException ie) {
                ie.printStackTrace();
            }

            liveBoardGrid.setVisible(!departureList.isEmpty());
            if(departureList.isEmpty()) {
                Notification notification = new Notification("No data found.", 3000, Notification.Position.TOP_CENTER);
                notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
                notification.open();
            }
        });
    }
}

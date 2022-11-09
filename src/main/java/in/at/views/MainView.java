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

import java.util.ArrayList;
import java.util.List;

@PageTitle("Main")
@Route(value = "")
public class MainView extends VerticalLayout {

    private final SearchLayout searchLayout;

    private final LiveBoardGrid liveBoardGrid;

    public MainView() {
        AppTitle appTitle = new AppTitle();
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
            List<TrainAction> departureList = new ArrayList<>();
            try {
                String date = searchLayout.getSelectedDate();
                String arrivalOrDeparture = searchLayout.getSelectedArrivalOrDeparture();
                LiveBoardResponse liveBoardResponse = IRailUtil.fetchLiveBoard(stationId, date, arrivalOrDeparture);

                boolean isArrival = "arrival".equalsIgnoreCase(arrivalOrDeparture);
                if(isArrival) {
                    departureList.addAll(liveBoardResponse.getArrivals().getArrival());
                } else {
                    departureList.addAll(liveBoardResponse.getDepartures().getDeparture());
                }

                liveBoardGrid.changeStationTitle(isArrival);
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

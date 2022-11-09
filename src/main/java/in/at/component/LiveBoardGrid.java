package in.at.component;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import in.at.domain.TrainAction;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class LiveBoardGrid extends Grid<TrainAction> {

    private Span stationTitleSpan;

    public LiveBoardGrid() {
        addComponentColumn(d -> cancellationRenderer(d.getCanceled() > 0)).setAutoWidth(true).setFlexGrow(0);
        addComponentColumn(d -> d.getCanceled() > 0 ? cancellationRenderer(true) : delayRenderer(d.getDelay() > 0)).setAutoWidth(true).setFlexGrow(0);
        addComponentColumn(d -> createPlatformTag(d.getPlatform())).setHeader(createGridHeader("", VaadinIcon.GROUP)).setAutoWidth(true).setFlexGrow(0);
        addComponentColumn(d -> createTimeTag(formattedDateFromMillis(d.getTime()), d.getDelay())).setHeader(createGridHeader("", VaadinIcon.CLOCK)).setAutoWidth(true).setFlexGrow(0);
        addComponentColumn(d -> createTrainTag(d.getVehicleinfo().getShortname())).setHeader(createGridHeader("Train", VaadinIcon.TRAIN)).setAutoWidth(true).setFlexGrow(0);
        addColumn(TrainAction::getStation).setHeader(createStationHeader());

        setSizeFull();
        setVisible(false);
        addThemeVariants(GridVariant.LUMO_COLUMN_BORDERS);
        setSelectionMode(SelectionMode.NONE);
    }

    private Icon delayRenderer(boolean isDelayed) {
        Icon icon;

        if(isDelayed) {
            icon = cellIcon(VaadinIcon.TIME_BACKWARD, "red");
            icon.getElement().setAttribute("title", "Delayed");
        } else {
            icon = cellIcon(VaadinIcon.USER_CLOCK, "green");
            icon.getElement().setAttribute("title", "On Time");
        }

        return icon;
    }

    private Icon cancellationRenderer(boolean isCancelled) {
        Icon icon;

        if(isCancelled) {
            icon = cellIcon(VaadinIcon.WARNING, "red");
            icon.getElement().setAttribute("title", "Cancelled");
        } else {
            icon = cellIcon(VaadinIcon.CHECK_CIRCLE, "green");
            icon.getElement().setAttribute("title", "Not Cancelled");
        }

        return icon;
    }

    private Icon cellIcon(VaadinIcon vaadinIcon, String color) {
        Icon icon = vaadinIcon.create();
        icon.setColor(color);
        icon.setSize("16px");
        return icon;
    }

    private Span createPlatformTag(String platform) {
        Span span = new Span();
        String theme = String.format("badge %s", ("?".equals(platform)) ? "error" : "success");
        span.getElement().setAttribute("theme", theme);
        span.getElement().setAttribute("title", "Platform number");
        span.setText(platform);
        return span;
    }

    private Span createTimeTag(String time, int delay) {
        Span span = new Span();
        String theme = String.format("badge %s", delay > 0 ? "error" : "success");
        span.getElement().setAttribute("theme", theme);
        span.setText(time);
        return span;
    }

    private Span createTrainTag(String train) {
        Span span = new Span();
        span.getElement().setAttribute("theme", "badge");
        span.setText(train);
        return span;
    }

    private HorizontalLayout createGridHeader(String title, VaadinIcon vaadinIcon) {
        Span span = new Span(title);
        span.setTitle(title);
        Icon icon = vaadinIcon.create();

        HorizontalLayout layout = new HorizontalLayout(icon, span);
        layout.setSpacing(true);
        return layout;
    }

    private HorizontalLayout createStationHeader() {
        stationTitleSpan = new Span();
        Icon icon = VaadinIcon.BUILDING.create();

        HorizontalLayout layout = new HorizontalLayout(icon, stationTitleSpan);
        layout.setSpacing(true);
        return layout;
    }

    private String formattedDateFromMillis(Long dateInMillis) {
        return DateTimeFormatter.ofPattern("HH:mm").format(Instant.ofEpochMilli(dateInMillis).atZone(ZoneId.of("UTC+1")));
    }

    public void changeStationTitle(boolean isArrivals) {
        if(isArrivals) {
            stationTitleSpan.setText("Arriving from station");
        } else {
            stationTitleSpan.setText("Departing for station");
        }
    }
}

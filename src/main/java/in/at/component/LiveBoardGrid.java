package in.at.component;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import in.at.domain.Departure;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class LiveBoardGrid extends Grid<Departure> {

    public LiveBoardGrid() {
        addComponentColumn(d -> d.getCanceled() > 0 ? cellIcon(VaadinIcon.WARNING, "red") : cellIcon(VaadinIcon.CHECK_CIRCLE, "green")).setAutoWidth(true).setFlexGrow(0);
        addComponentColumn(d -> d.getDelay() > 0 ? cellIcon(VaadinIcon.TIME_BACKWARD, "red") : cellIcon(VaadinIcon.USER_CLOCK, "green")).setAutoWidth(true).setFlexGrow(0);
        addComponentColumn(d -> createPlatformTag(d.getPlatform())).setHeader(createGridHeader("", VaadinIcon.GROUP)).setAutoWidth(true).setFlexGrow(0);
        addComponentColumn(d -> createTimeTag(formattedDateFromMillis(d.getTime()), d.getDelay())).setHeader(createGridHeader("", VaadinIcon.CLOCK)).setAutoWidth(true).setFlexGrow(0);
        addColumn(Departure::getVehicle).setHeader(createGridHeader("Train", VaadinIcon.TRAIN)).setAutoWidth(true).setFlexGrow(0);
        addColumn(Departure::getStation).setHeader(createGridHeader("Departure", VaadinIcon.BUILDING));

        setSizeFull();
        setVisible(false);
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

    private HorizontalLayout createGridHeader(String title, VaadinIcon vaadinIcon) {
        Span span = new Span(title);
        span.setTitle(title);
        Icon icon = vaadinIcon.create();

        HorizontalLayout layout = new HorizontalLayout(icon, span);
        layout.setSpacing(true);
        return layout;
    }

    private String formattedDateFromMillis(Long dateInMillis) {
        return DateTimeFormatter.ofPattern("HH:mm").format(Instant.ofEpochMilli(dateInMillis).atZone(ZoneId.of("UTC+1")));
    }
}

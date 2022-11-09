package in.at.component;

import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;

public class AppTitle extends HorizontalLayout {
    
    public AppTitle() {
        Span titleSpan = new Span("BelgIRail");
        titleSpan.setClassName("app-title");

        Icon icon = VaadinIcon.TRAIN.create();
        icon.setSize("36px");

        setWidthFull();
        add(icon);
        add(titleSpan);
        setAlignItems(Alignment.CENTER);
        setVerticalComponentAlignment(Alignment.CENTER, icon);
    }
}

package com.is3.farmaciaya;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.spring.annotation.EnableVaadin;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import com.vaadin.ui.Button.ClickEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.ContextLoaderListener;

import javax.servlet.annotation.WebListener;
import javax.servlet.annotation.WebServlet;

@Theme("valo")
@SpringUI
@SuppressWarnings("serial")
public class MyUI extends UI {

    private final MenuBar.Command menuCommand = new MenuBar.Command() {
        @Override
        public void menuSelected(final MenuBar.MenuItem selectedItem) {
            Notification.show("Action " + selectedItem.getText(),
                    Notification.Type.TRAY_NOTIFICATION);
        }
    };

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        final HorizontalLayout layout = new HorizontalLayout();
        layout.setSizeFull();
        setContent(layout);

        MenuBar menuBar = new MenuBar();
        menuBar.setWidth(100.0f, Unit.PERCENTAGE);
        menuBar.addItem("Perfil", menuCommand);
        menuBar.addItem("Inicio", menuCommand);
        menuBar.addItem("Configuracion", menuCommand);
        menuBar.addItem("Cerrar sesión", menuCommand);

        Panel menuPanel = new Panel("Menu");
        menuPanel.setHeight(100.0f, Unit.PERCENTAGE);
        final VerticalLayout menuContentLayout = new VerticalLayout();
        menuContentLayout.setWidth(500, Unit.PIXELS);
        menuContentLayout.setMargin(true);
        menuContentLayout.addComponent(menuBar);
        menuPanel.setContent(menuContentLayout);

        Panel windowsPanel = new Panel("Main");
        windowsPanel.setHeight(100.0f, Unit.PERCENTAGE);
        final VerticalLayout mainContentLayout = new VerticalLayout();

        Button button = new Button("Test me");
        button.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(ClickEvent event) {
                mainContentLayout.addComponent(new Label("Oh! Fui hasta el server y volví!"));
            }
        });
        mainContentLayout.addComponent(button);
        windowsPanel.setContent(mainContentLayout);


        layout.addComponent(menuPanel, 0);
        layout.addComponent(windowsPanel, 1);

    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }

    @WebListener
    public static class MyContextLoaderListener extends ContextLoaderListener {
    }

    @Configuration
    @EnableVaadin
    public static class MyConfiguration {
    }
}

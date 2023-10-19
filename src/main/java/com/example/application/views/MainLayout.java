package com.example.application.views;

import com.example.application.domain.LoyaltyCustomer;
import com.example.application.views.about.AboutView;
import com.example.application.views.checkout.CheckoutView;
import com.example.application.views.contactus.ContactUs;
import com.example.application.views.home.HomeView;
import com.example.application.views.login.EventBus;
import com.example.application.views.login.LoginSuccessEvent;
import com.example.application.views.login.LoginView;
import com.example.application.views.menu.MenuView;
import com.example.application.views.team.TeamView;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.server.VaadinSession;
import com.vaadin.flow.theme.lumo.LumoUtility.AlignItems;
import com.vaadin.flow.theme.lumo.LumoUtility.BoxSizing;
import com.vaadin.flow.theme.lumo.LumoUtility.Display;
import com.vaadin.flow.theme.lumo.LumoUtility.FlexDirection;
import com.vaadin.flow.theme.lumo.LumoUtility.FontSize;
import com.vaadin.flow.theme.lumo.LumoUtility.FontWeight;
import com.vaadin.flow.theme.lumo.LumoUtility.Gap;
import com.vaadin.flow.theme.lumo.LumoUtility.Height;
import com.vaadin.flow.theme.lumo.LumoUtility.ListStyleType;
import com.vaadin.flow.theme.lumo.LumoUtility.Margin;
import com.vaadin.flow.theme.lumo.LumoUtility.Overflow;
import com.vaadin.flow.theme.lumo.LumoUtility.Padding;
import com.vaadin.flow.theme.lumo.LumoUtility.TextColor;
import com.vaadin.flow.theme.lumo.LumoUtility.Whitespace;
import com.vaadin.flow.theme.lumo.LumoUtility.Width;
import org.vaadin.lineawesome.LineAwesomeIcon;

/**
 * The main view is a top-level placeholder for other views.
 */
public class MainLayout extends AppLayout implements EventBus.LoginSuccessListener{

    @Override
    public void onLoginSuccess(LoginSuccessEvent event) {
        LoyaltyCustomer loggedInCustomer = event.getCustomer();
        addToNavbar(updateHeaderContent(loggedInCustomer));
    }

    /**
     * A simple navigation item component, based on ListItem element.
     */
    public static class MenuItemInfo extends ListItem {

        private final Class<? extends Component> view;

        public MenuItemInfo(String menuTitle, Component icon, Class<? extends Component> view) {
            this.view = view;
            RouterLink link = new RouterLink();
            // Use Lumo classnames for various styling
            link.addClassNames(Display.FLEX, Gap.XSMALL, Height.MEDIUM, AlignItems.CENTER, Padding.Horizontal.SMALL,
                    TextColor.BODY);
            link.setRoute(view);

            Span text = new Span(menuTitle);
            // Use Lumo classnames for various styling
            text.addClassNames(FontWeight.MEDIUM, FontSize.MEDIUM, Whitespace.NOWRAP);

            if (icon != null) {
                link.add(icon);
            }
            link.add(text);
            add(link);
        }

        public Class<?> getView() {
            return view;
        }

    }

    public MainLayout() {
        EventBus.getInstance().addLoginSuccessListener(this);
        UI.getCurrent().addBeforeEnterListener(event -> {
            if (!isUserLoggedIn()) {
                event.forwardTo(LoginView.class);
            }
        });

        if (isUserLoggedIn()) {
            LoyaltyCustomer loggedInCustomer = (LoyaltyCustomer) VaadinSession.getCurrent().getAttribute("loggedInCustomer");
            addToNavbar(updateHeaderContent(loggedInCustomer));
        } else {
            addToNavbar(createHeaderContent());
        }

        UI.getCurrent().addDetachListener(event -> {
            VaadinSession.getCurrent().setAttribute("loggedInCustomer", null);
            VaadinSession.getCurrent().close(); // Close the current session
        });
    }

    private boolean isUserLoggedIn() {
        return VaadinSession.getCurrent().getAttribute("loggedInCustomer") != null;
    }



    private void clearSessionData() {
        VaadinSession.getCurrent().setAttribute("loggedInCustomer", null);
        VaadinSession.getCurrent().close(); // Close the current session
        UI.getCurrent().navigate(LoginView.class); // Redirect to the login page
    }



    private Component createHeaderContent() {

        Header header = new Header();
        header.addClassNames(BoxSizing.BORDER, Display.FLEX, FlexDirection.COLUMN, Width.FULL);

        Div layout = new Div();
        layout.addClassNames(Display.FLEX, AlignItems.CENTER, Padding.Horizontal.LARGE);

        H1 appName = new H1("Hill Crest");
        appName.addClassNames(Margin.Vertical.MEDIUM, Margin.End.AUTO, FontSize.LARGE);
        layout.add(appName);

        Nav nav = new Nav();
        nav.addClassNames(Display.FLEX, Overflow.AUTO, Padding.Horizontal.MEDIUM, Padding.Vertical.XSMALL);

        // Wrap the links in a list; improves accessibility
        UnorderedList list = new UnorderedList();
        list.addClassNames(Display.FLEX, Gap.SMALL, ListStyleType.NONE, Margin.NONE, Padding.NONE);
        nav.add(list);

        for (MenuItemInfo menuItem : createMenuItems()) {
            list.add(menuItem);

        }
        header.add(layout, nav);
        return header;
    }

    public Component updateHeaderContent(LoyaltyCustomer loggedInUser) {
        Image userImage = new Image("/images/LoginIcon.png", "User Image");
        userImage.setWidth("50px");

        Span welcomeMessage = new Span("Welcome, " + loggedInUser.getCustomerName());

        Button logoutButton = new Button("Logout", event -> {
            clearSessionData();
        });

        HorizontalLayout userLayout = new HorizontalLayout(userImage, welcomeMessage, logoutButton);
        userLayout.setSpacing(true); // Optional: Set spacing between components
        userLayout.setAlignItems(FlexComponent.Alignment.CENTER); // Center components horizontally

        // Create another layout to center the components vertically
        Div centeredLayout = new Div(userLayout);
        centeredLayout.getStyle().set("margin-top", "1.0"); // Centers the layout vertically

        VerticalLayout headerLayout = new VerticalLayout(centeredLayout);
        headerLayout.setWidth("100%");
        headerLayout.setAlignItems(FlexComponent.Alignment.END); // Align components at the end

        return headerLayout;
    }



    private MenuItemInfo[] createMenuItems() {
        return new MenuItemInfo[]{

                new MenuItemInfo("Home", LineAwesomeIcon.HOME_SOLID.create(), HomeView.class), //

                new MenuItemInfo("Menu", LineAwesomeIcon.TH_LIST_SOLID.create(), MenuView.class), //

                new MenuItemInfo("Checkout", LineAwesomeIcon.CREDIT_CARD.create(), CheckoutView.class), //

                new MenuItemInfo("Team", LineAwesomeIcon.PERSON_BOOTH_SOLID.create(), TeamView.class), //

                new MenuItemInfo("Login", LineAwesomeIcon.SIGN_SOLID.create(), LoginView.class), //

                new MenuItemInfo("ContactUs", LineAwesomeIcon.PHONE_VOLUME_SOLID.create(), ContactUs.class), //

                new MenuItemInfo("About", LineAwesomeIcon.INFO_CIRCLE_SOLID.create(), AboutView.class), //
        };
    }

}

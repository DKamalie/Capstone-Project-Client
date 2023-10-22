package com.example.application.views.checkout;

import com.example.application.api.StagedOrderApi;
import com.example.application.domain.LoyaltyCustomer;
import com.example.application.domain.StagedOrder;
import com.example.application.views.MainLayout;
import com.example.application.views.menu.MenuView;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.page.Push;
import com.vaadin.flow.dom.Style;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;
import com.vaadin.flow.theme.lumo.LumoUtility.AlignItems;
import com.vaadin.flow.theme.lumo.LumoUtility.Background;
import com.vaadin.flow.theme.lumo.LumoUtility.BorderRadius;
import com.vaadin.flow.theme.lumo.LumoUtility.BoxSizing;
import com.vaadin.flow.theme.lumo.LumoUtility.Display;
import com.vaadin.flow.theme.lumo.LumoUtility.Flex;
import com.vaadin.flow.theme.lumo.LumoUtility.FlexDirection;
import com.vaadin.flow.theme.lumo.LumoUtility.FontSize;
import com.vaadin.flow.theme.lumo.LumoUtility.Gap;
import com.vaadin.flow.theme.lumo.LumoUtility.JustifyContent;
import com.vaadin.flow.theme.lumo.LumoUtility.ListStyleType;
import com.vaadin.flow.theme.lumo.LumoUtility.Margin;
import com.vaadin.flow.theme.lumo.LumoUtility.MaxWidth;
import com.vaadin.flow.theme.lumo.LumoUtility.Padding;
import com.vaadin.flow.theme.lumo.LumoUtility.Position;
import java.util.Set;

@PageTitle("Checkout")
@Route(value = "checkout", layout = MainLayout.class)
public class CheckoutView extends Div {

    private Image imgPizza;
    private Button backButton, checkOut;

    private Integer customer1;
    private String customerName, customerName2, City, Country, FlatNumber, PostalCode, Province, StreetName,StreetNumber ,Suburb;

    int sum = 0;
    StagedOrderApi stage = new StagedOrderApi();

    public CheckoutView() {

        imgPizza = new Image("/images/pizza_checkout.jpg", "Pizza image");

        Style imgPizzaStyle = imgPizza.getStyle();
        imgPizzaStyle.set("position", "fixed");
        imgPizzaStyle.set("top", "0");
        imgPizzaStyle.set("left", "0");
        imgPizzaStyle.set("width", "100%");
        imgPizzaStyle.set("height", "100%");
        imgPizzaStyle.set("z-index", "-1");

        addClassNames("checkout-view");
        addClassNames(Display.FLEX, FlexDirection.COLUMN);
        setHeight("100vh");

        backButton = new Button("Back");

        backButton.addClickListener(e -> {
            getUI().ifPresent(ui -> ui.navigate(MenuView.class));
        });

        backButton.addClassName("custom-image-button");


        Main content = new Main();
        content.addClassNames(Display.GRID, Gap.XLARGE,AlignItems.CENTER, JustifyContent.CENTER, MaxWidth.SCREEN_MEDIUM,
                Margin.Horizontal.AUTO, Padding.Bottom.LARGE, Padding.Horizontal.LARGE);

        content.getStyle().set("overflow-y", "auto");
        content.add(createCheckoutForm());
        content.add(createAside());

        add(imgPizza,backButton, content);

    }

    private Component createCheckoutForm() {
        Section checkoutForm = new Section();
        checkoutForm.addClassNames(Display.FLEX, FlexDirection.COLUMN, Flex.GROW, BorderRadius.LARGE,Padding.LARGE);

        H2 header = new H2("Hill Crest");
        header.addClassNames(Margin.Bottom.NONE, Margin.Top.XLARGE, FontSize.XXXLARGE, AlignItems.CENTER);
        checkoutForm.add(header);

        checkoutForm.getStyle().set("background-color", "white");

        checkoutForm.add(createPaymentInformationSection());
        checkoutForm.add(new Hr());

        return checkoutForm;
    }



    private Component createPaymentInformationSection() {
        Section paymentInfo = new Section();
        paymentInfo.addClassNames(Display.FLEX, FlexDirection.COLUMN, Margin.Bottom.XLARGE, Margin.Top.MEDIUM,BorderRadius.LARGE,AlignItems.CENTER);

        Div subSectionTwo = new Div();
        subSectionTwo.addClassNames(BoxSizing.BORDER, Padding.LARGE, BorderRadius.LARGE,
                Position.STICKY);

        if (isUserLoggedIn()) {
            LoyaltyCustomer loggedInCustomer = (LoyaltyCustomer) VaadinSession.getCurrent().getAttribute("loggedInCustomer");
            customerName = loggedInCustomer.getCustomerName();
        }

        Header headerSection = new Header();
        headerSection.addClassNames(Display.FLEX, AlignItems.CENTER, JustifyContent.BETWEEN, Margin.Bottom.MEDIUM);
        H3 header = new H3("Order - "+customerName);
        header.addClassNames(Margin.NONE);
        H4 price = new H4("Price");
        price.addClassNames(Margin.NONE);
        headerSection.add(header, price);



        // Initialize an UnorderedList to display the staged orders
        UnorderedList ul = new UnorderedList();
        try {
            // Retrieve the staged orders from the API
            Set<StagedOrder> stagedOrders = stage.getAllPizzeria();
            // Iterate over the staged orders and add them to the unordered list
            for (StagedOrder stagedOrder : stagedOrders) {
                sum += stagedOrder.getTotal() * Double.valueOf(stagedOrder.getQuantity());
                ul.add(createListItem(stagedOrder.getPizza().getName(), stagedOrder.getTotal()));
            }

        } catch (Exception e) {
            Notification.show("Failed to retrieve orders. Please try again later." + e.getMessage());
        }
        ul.addClassNames(ListStyleType.NONE, Margin.NONE, Padding.NONE, Display.FLEX, FlexDirection.COLUMN, Gap.MEDIUM);


        checkOut = new Button("Checkout R"+String.valueOf(sum));

        if(sum == 0){
            checkOut.setEnabled(false);
        }else{
            checkOut.setEnabled(true);
        }
        checkOut.addClickListener(e -> {
            getUI().ifPresent(ui -> ui.navigate(ThankYouView.class));
        });


        checkOut.getStyle().set("color", "white");
        checkOut.getStyle().set("background-color", "#03C03C");
        checkOut.getStyle().set("font-family", "Arial");
        checkOut.getStyle().set("font-size", "20px");
        checkOut.getStyle().set("font-weight", "bold");
        checkOut.getStyle().set("width", "350px");
        checkOut.getStyle().set("height", "43px");
        checkOut.getStyle().set("border-radius", "22px");
        checkOut.getStyle().set("box-shadow", "0 5px 4px rgba(0, 0, 0, 0.2)");
        checkOut.getStyle().set("display", "flex");
        checkOut.getStyle().set("justify-content", "center");
        checkOut.getStyle().set("cursor" ,"pointer");


        subSectionTwo.getStyle().set("background-color", "white");
        subSectionTwo.add(headerSection, ul,checkOut);

        paymentInfo.add(subSectionTwo);
        return paymentInfo;
    }

    private ListItem createListItem(String primary, double price) {
        ListItem item = new ListItem();
        item.addClassNames(Display.FLEX, JustifyContent.BETWEEN);

        Div subSection = new Div();
        subSection.addClassNames(Display.FLEX, FlexDirection.COLUMN);

        subSection.add(new Span(primary));

        Span priceSpan = new Span("R"+price);

        item.add(subSection, priceSpan);
        return item;
    }

    private boolean isUserLoggedIn() {

        return VaadinSession.getCurrent().getAttribute("loggedInCustomer") != null;
    }

    private Aside createAside() {
        Aside aside = new Aside();
        aside.addClassNames(Background.CONTRAST_20, BoxSizing.BORDER, Padding.LARGE, BorderRadius.LARGE,
                Position.STICKY);

        if (isUserLoggedIn()) {
            LoyaltyCustomer loggedInCustomer = (LoyaltyCustomer) VaadinSession.getCurrent().getAttribute("loggedInCustomer");
            customerName2 = loggedInCustomer.getCustomerName();
            City = loggedInCustomer.getAddress().getCity();
            Country = loggedInCustomer.getAddress().getCountry();
            FlatNumber = loggedInCustomer.getAddress().getFlatNumber();
            PostalCode = loggedInCustomer.getAddress().getPostalCode();
            Province = loggedInCustomer.getAddress().getProvince();
            StreetName = loggedInCustomer.getAddress().getStreetName();
            StreetNumber = loggedInCustomer.getAddress().getStreetNumber();
            Suburb = loggedInCustomer.getAddress().getSuburb();
        }

        Header headerSection = new Header();
        headerSection.addClassNames(Display.FLEX, AlignItems.CENTER, JustifyContent.BETWEEN, Margin.Bottom.MEDIUM);
        H3 header = new H3("Address - "+customerName2);
        header.addClassNames(Margin.NONE);
        headerSection.add(header);

        Div subSection = new Div();
        subSection.addClassNames(Display.FLEX, FlexDirection.COLUMN);

        Span span = new Span(City);
        Span span1 = new Span(Country);
        Span span2= new Span(FlatNumber);
        Span span3 = new Span(PostalCode);
        Span span4 = new Span(Province);
        Span span5 = new Span(StreetName);
        Span span6 = new Span(StreetNumber);
        Span span7 = new Span(Suburb);

        Button updateButton = new Button("Update");

        updateButton.addClickListener(e -> {
            getUI().ifPresent(ui -> ui.navigate(AddressUpdateView.class));
        });

        updateButton.getStyle().set("color", "white");
        updateButton.getStyle().set("background-color", "#000000");
        updateButton.getStyle().set("font-family", "Arial");
        updateButton.getStyle().set("font-size", "20px");
        updateButton.getStyle().set("font-weight", "bold");
        updateButton.getStyle().set("width", "150px");
        updateButton.getStyle().set("height", "43px");
        updateButton.getStyle().set("border-radius", "22px");
        updateButton.getStyle().set("box-shadow", "0 5px 4px rgba(0, 0, 0, 0.2)");
        updateButton.getStyle().set("display", "flex");
        updateButton.getStyle().set("justify-content", "left");
        updateButton.getStyle().set("cursor" ,"pointer");

        subSection.add(span,span1,span2, span3,span4,span5,span6, span7, updateButton);

        aside.getStyle().set("background-color", "white");
        aside.add(headerSection, subSection);
        return aside;
    }
}

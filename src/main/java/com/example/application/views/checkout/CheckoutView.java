package com.example.application.views.checkout;

import com.example.application.api.StagedOrderApi;
import com.example.application.domain.StagedOrder;
import com.example.application.views.MainLayout;
import com.example.application.views.menu.MenuView;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.dom.Style;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility.AlignItems;
import com.vaadin.flow.theme.lumo.LumoUtility.Background;
import com.vaadin.flow.theme.lumo.LumoUtility.BorderRadius;
import com.vaadin.flow.theme.lumo.LumoUtility.BoxSizing;
import com.vaadin.flow.theme.lumo.LumoUtility.Display;
import com.vaadin.flow.theme.lumo.LumoUtility.Flex;
import com.vaadin.flow.theme.lumo.LumoUtility.FlexDirection;
import com.vaadin.flow.theme.lumo.LumoUtility.FlexWrap;
import com.vaadin.flow.theme.lumo.LumoUtility.FontSize;
import com.vaadin.flow.theme.lumo.LumoUtility.Gap;
import com.vaadin.flow.theme.lumo.LumoUtility.Height;
import com.vaadin.flow.theme.lumo.LumoUtility.JustifyContent;
import com.vaadin.flow.theme.lumo.LumoUtility.ListStyleType;
import com.vaadin.flow.theme.lumo.LumoUtility.Margin;
import com.vaadin.flow.theme.lumo.LumoUtility.MaxWidth;
import com.vaadin.flow.theme.lumo.LumoUtility.Padding;
import com.vaadin.flow.theme.lumo.LumoUtility.Position;
import com.vaadin.flow.theme.lumo.LumoUtility.TextColor;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

@PageTitle("Checkout")
@Route(value = "checkout", layout = MainLayout.class)
public class CheckoutView extends Div {
    StagedOrderApi stagedOrderApi = new StagedOrderApi();
    private Image imgPizza;

    public CheckoutView() {
        Set<StagedOrder> stagedOrders = stagedOrderApi.getAllPizzeria();
        stagedOrders.forEach(stagedOrder -> {
            System.out.println(stagedOrder);
        });


        addClassNames("checkout-view");
        addClassNames(Display.FLEX, FlexDirection.COLUMN, Height.FULL);

        Main content = new Main();
        content.addClassNames(Display.GRID, Gap.XLARGE,AlignItems.CENTER, JustifyContent.CENTER, MaxWidth.SCREEN_MEDIUM,
                Margin.Horizontal.AUTO, Padding.Bottom.LARGE, Padding.Horizontal.LARGE);

        content.add(createCheckoutForm());
        content.add(createAside());
        add(content);

    }


    private Component createCheckoutForm() {
        imgPizza = new Image("/images/pizza_checkout.jpg", "Pizza image");
        Section checkoutForm = new Section();
        checkoutForm.addClassNames(Display.FLEX, FlexDirection.COLUMN, Flex.GROW, BorderRadius.LARGE,Padding.LARGE);

        H2 header = new H2("Hill Crest");
        header.addClassNames(Margin.Bottom.NONE, Margin.Top.XLARGE, FontSize.XXXLARGE, AlignItems.CENTER);
        checkoutForm.add(header);

        checkoutForm.getStyle().set("background-color", "white");


        Style imgPizzaStyle = imgPizza.getStyle();
        imgPizzaStyle.set("position", "fixed");
        imgPizzaStyle.set("top", "0");
        imgPizzaStyle.set("left", "0");
        imgPizzaStyle.set("width", "100%");
        imgPizzaStyle.set("height", "100%");
        imgPizzaStyle.set("z-index", "-1");

        Button btnViewMenu = new Button("View Menu");

        btnViewMenu.getStyle().set("position", "absolute");
        btnViewMenu.getStyle().set("top", "10px");
        btnViewMenu.getStyle().set("left", "10px");
        btnViewMenu.getStyle().set("padding", "10px");
        btnViewMenu.getStyle().set("background-color", "#ffffff");
        btnViewMenu.getStyle().set("color", "#000000");

        btnViewMenu.addClickListener(e -> {
            getUI().ifPresent(ui -> ui.navigate(MenuView.class));
        });

        checkoutForm.add(imgPizza);
        checkoutForm.add(btnViewMenu);
        checkoutForm.add(createPaymentInformationSection());
        checkoutForm.add(new Hr());

        return checkoutForm;
    }



    private Component createPaymentInformationSection() {
        Section paymentInfo = new Section();
        paymentInfo.addClassNames(Display.FLEX, FlexDirection.COLUMN, Margin.Bottom.XLARGE, Margin.Top.MEDIUM,BorderRadius.LARGE,AlignItems.CENTER);

        Div subSectionOne = new Div();
        subSectionOne.addClassNames(Display.FLEX, FlexWrap.WRAP, Gap.MEDIUM);

        TextField pizzaName = new TextField("Pizza name");
        pizzaName.setValue("Four Seasons");
        pizzaName.setEnabled(false);
        pizzaName.addClassNames(Margin.Bottom.SMALL);

        subSectionOne.add(pizzaName);

        Div subSectionFour = new Div();
        subSectionFour.addClassNames(Display.FLEX, FlexWrap.WRAP, Gap.MEDIUM, FontSize.MEDIUM);

        TextField amount = new TextField("Amount");
        amount.setValue("R129.90");
        amount.setEnabled(false);
        amount.addClassNames(Margin.Bottom.SMALL);

        subSectionFour.add(amount);

        Div subSectionTwo = new Div();
        subSectionTwo.addClassNames(Display.FLEX, FlexWrap.WRAP, Gap.MEDIUM,FontSize.MEDIUM);

        Select<String> quantity = new Select<>();
        quantity.setLabel("Quantity");
        quantity.setRequiredIndicatorVisible(true);
        quantity.setItems("1", "2", "3", "4", "5", "6", "7", "8", "9", "10");

        subSectionTwo.add(quantity);

        Div subSectionFive = new Div();
        subSectionFive.addClassNames(Display.FLEX, FlexWrap.WRAP, Gap.MEDIUM);

        Button checkOut = new Button("Checkout", new Icon(VaadinIcon.LOCK));
        checkOut.addThemeVariants(ButtonVariant.LUMO_PRIMARY, ButtonVariant.LUMO_SUCCESS);

        checkOut.getStyle().set("margin-center", "auto");

        subSectionFive.add(checkOut);

        paymentInfo.add(subSectionOne,subSectionFour, subSectionTwo,subSectionFive);
        return paymentInfo;
    }

    private Aside createAside() {
        Aside aside = new Aside();
        aside.addClassNames(Background.CONTRAST_20, BoxSizing.BORDER, Padding.LARGE, BorderRadius.LARGE,
                Position.STICKY);
        Header headerSection = new Header();
        headerSection.addClassNames(Display.FLEX, AlignItems.CENTER, JustifyContent.BETWEEN, Margin.Bottom.MEDIUM);
        H3 header = new H3("Order");
        header.addClassNames(Margin.NONE);
        Button edit = new Button("Edit");
        edit.addThemeVariants(ButtonVariant.LUMO_TERTIARY_INLINE);
        headerSection.add(header, edit);

        UnorderedList ul = new UnorderedList();
        ul.addClassNames(ListStyleType.NONE, Margin.NONE, Padding.NONE, Display.FLEX, FlexDirection.COLUMN, Gap.MEDIUM);

      //  ul.add(createListItem("Hawaiian", "Ham, pineapple", "R109.70"));
        ul.add(createListItem("Four Seasons", "Pepperoni, mushroom, olives, green pepper, onion", "R129.90"));
       // ul.add(createListItem("Garlic Meaty Supreme", "Garlic spread base, pepperoni, ham, bacon, ground beef", "R139.90"));

        aside.getStyle().set("background-color", "white");
        aside.add(headerSection, ul);
        return aside;
    }

    private ListItem createListItem(String primary, String secondary, String price) {
        ListItem item = new ListItem();
        item.addClassNames(Display.FLEX, JustifyContent.BETWEEN);

        Div subSection = new Div();
        subSection.addClassNames(Display.FLEX, FlexDirection.COLUMN);

        subSection.add(new Span(primary));
        Span secondarySpan = new Span(secondary);
        secondarySpan.addClassNames(FontSize.SMALL, TextColor.SECONDARY);
        subSection.add(secondarySpan);

        Span priceSpan = new Span(price);

        item.add(subSection, priceSpan);
        return item;
    }
}

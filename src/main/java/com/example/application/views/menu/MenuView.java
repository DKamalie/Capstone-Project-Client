package com.example.application.views.menu;

import com.example.application.api.EmployeeApi;
import com.example.application.api.MenuApi;
import com.example.application.api.PizzaApi;
import com.example.application.domain.*;
import com.example.application.factory.PizzeriaFactory;
import com.example.application.views.MainLayout;
import com.vaadin.flow.component.HasComponents;
import com.vaadin.flow.component.HasStyle;
import com.vaadin.flow.component.Html;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility.AlignItems;
import com.vaadin.flow.theme.lumo.LumoUtility.Display;
import com.vaadin.flow.theme.lumo.LumoUtility.FontSize;
import com.vaadin.flow.theme.lumo.LumoUtility.Gap;
import com.vaadin.flow.theme.lumo.LumoUtility.JustifyContent;
import com.vaadin.flow.theme.lumo.LumoUtility.ListStyleType;
import com.vaadin.flow.theme.lumo.LumoUtility.Margin;
import com.vaadin.flow.theme.lumo.LumoUtility.MaxWidth;
import com.vaadin.flow.theme.lumo.LumoUtility.Padding;
import com.vaadin.flow.theme.lumo.LumoUtility.TextColor;

import java.time.LocalDate;
import java.util.Date;
import java.util.Set;

@PageTitle("Menu")
@Route(value = "menu", layout = MainLayout.class)
public class MenuView extends Main implements HasComponents, HasStyle {
    private int count =0;
    Button order;
    MenuApi getAll = new MenuApi();
    public MenuView() {
        addClassName("mainContainer");
        Set<Pizza> pizzas = getAll.getAllPizzas();
        pizzas.forEach(pizza -> {
           count = count +1;
            add(createPizzaSpan(pizza));
        });
        orderBar().add(createOrder());
        add(orderBar());

    }

    public Div orderBar() {
        Div sideBar = new Div();
        Paragraph p = new Paragraph("ORDER:");
        sideBar.add(p);
        sideBar.addClassName("sideBar");
        return sideBar;
    }

    private Div createPizzaSpan(Pizza pizza) {
        order = new Button("add");
        order.addClassName("orderBtn");
        Div pizzaContainer = new Div();
        pizzaContainer.addClassName("pizzaContainer");
        //pizzaContainer.addClassNames(AlignItems.CENTER, JustifyContent.BETWEEN, MaxWidth.SCREEN_LARGE, Margin.Horizontal.AUTO, Padding.Bottom.LARGE, Padding.Horizontal.LARGE);;
        Div wholeCard = new Div();
        wholeCard.addClassName("wholeCard");
        Span span = new Span(
                new Html(
                        "<div class='grid-container'>" +
                                "<div class='pId'><p>"+ count+ ". " +pizza.getName() +"</p></div>" +
                                "<div class='pDescription'><p>Description: "+ pizza.getDescription() +"</p></div>" +
                                "<div class='pDelivery'><p>Delivery Time</p></div>" +
                                "<div class='pTime'><p>20 Mins</p></div>" +
                                "<div class='pPrice'><p>Price</p></div>" +
                                "<div class='pPizzaPrice'><p>R"+ pizza.getPrice() +"</p></div>" +
                        "</div>"
                )
        );
        wholeCard.add(span);
        order.addClickListener(buttonClickEvent -> order.setId("testId"));
        pizzaContainer.add(wholeCard, order);

        HorizontalLayout container = new HorizontalLayout();
        pizzaContainer.addClassNames(AlignItems.CENTER, JustifyContent.BETWEEN);
        pizzaContainer.addClassName("menuCard");
        //add(container);
        return pizzaContainer;
    }
    private Div createOrder() {
        Div btnContainer = new Div();
        btnContainer.addClassName("btnContainer");
        Div span = new Div(
                new Paragraph("BISCUITS")
        );
        span.addClassName("test");
        order.addClickListener(buttonClickEvent -> span.setText("TEST BISCUITS"));
        HorizontalLayout hl = new HorizontalLayout();
        hl.add(span);
        return span;
    }
}

package com.example.application.views.menu;

import com.example.application.api.EmployeeApi;
import com.example.application.api.MenuApi;
import com.example.application.api.PizzaApi;
import com.example.application.domain.Employee;
import com.example.application.domain.Pizza;
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

import java.util.Set;

@PageTitle("Menu")
@Route(value = "menu", layout = MainLayout.class)
public class MenuView extends Main implements HasComponents, HasStyle {
    private OrderedList imageContainer;
    private int count =0;
    Button order;
    MenuApi getAll = new MenuApi();
    public MenuView() {
//        constructUI();
//
//        imageContainer.add(new MenuViewCard("Snow mountains under stars",
//                "https://images.unsplash.com/photo-1519681393784-d120267933ba?ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=750&q=80"));
//        imageContainer.add(new MenuViewCard("Snow covered mountain",
//                "https://images.unsplash.com/photo-1512273222628-4daea6e55abb?ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=750&q=80"));
//        imageContainer.add(new MenuViewCard("River between mountains",
//                "https://images.unsplash.com/photo-1536048810607-3dc7f86981cb?ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=375&q=80"));
//        imageContainer.add(new MenuViewCard("Milky way on mountains",
//                "https://images.unsplash.com/photo-1515705576963-95cad62945b6?ixlib=rb-1.2.1&ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&auto=format&fit=crop&w=750&q=80"));
//        imageContainer.add(new MenuViewCard("Mountain with fog",
//                "https://images.unsplash.com/photo-1513147122760-ad1d5bf68cdb?ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=1000&q=80"));
//        imageContainer.add(new MenuViewCard("Mountain at night",
//                "https://images.unsplash.com/photo-1562832135-14a35d25edef?ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=815&q=80"));


        Set<Pizza> pizzas = getAll.getAllPizzas();
//        container.addClassNames(AlignItems.CENTER, JustifyContent.BETWEEN);
        pizzas.forEach(pizza -> {
           count = count +1;
//            String s = Integer.toString(count);
            add(createPizzaSpan(pizza));

        });


    }

    private Div createPizzaSpan(Pizza pizza) {
        order = new Button("add");
        order.addClassName("orderBtn");
        Div pizzaContainer = new Div();
        pizzaContainer.addClassName("pizzaContainer");
        pizzaContainer.addClassNames(AlignItems.CENTER, JustifyContent.BETWEEN, MaxWidth.SCREEN_LARGE, Margin.Horizontal.AUTO, Padding.Bottom.LARGE, Padding.Horizontal.LARGE);;
        Span span = new Span(
                new Html(
                        "<div >" +
                                "<div class='pId'><p>"+ count+ ". " +pizza.getName() +"</p></div>" +
                                "<div class='pName'><p>description: "+ pizza.getDescription() +"</p></div>" +
                                "<div class='pDelivery'><p>Delivery Time</p></div>" +
                                "<div class='pTime'><p>20 Mins</p></div>" +
                                "<div class='pPrice'><p>Price</p></div>" +
                                "<div class='pPizzaPrice'><p>R"+ pizza.getPrice() +"</p></div>" +
                        "</div>"

                )
//                new Html("<table>" +
//                        "<tr class='pId'><td>Pizza ID:</td><td>" + count + "</td></tr>" +
//                        "<tr class='pName'><td>Name:</td><td>" + pizza.getName() + "</td></tr>" +
//                        "<tr class='pDescription'><td>Description:</td><td>" + pizza.getDescription() + "</td></tr>" +
//                        "<tr class='pBase'><td>Base:</td><td>" + pizza.getBaseId() + "</td></tr>" +
//                        "<tr class='pSize'><td>Size:</td><td>" + pizza.getSize()+ "</td></tr>" +
//                        "</table>"
//                        )
        );

        pizzaContainer.add(span, order);

        HorizontalLayout container = new HorizontalLayout();
        pizzaContainer.addClassNames(AlignItems.CENTER, JustifyContent.BETWEEN);
        pizzaContainer.addClassName("menuCard");
        add(container);
        return pizzaContainer;
    }


//    private void constructUI() {
//        addClassNames("menu-view");
//        addClassNames(MaxWidth.SCREEN_LARGE, Margin.Horizontal.AUTO, Padding.Bottom.LARGE, Padding.Horizontal.LARGE);
//
//        HorizontalLayout container = new HorizontalLayout();
//        container.addClassNames(AlignItems.CENTER, JustifyContent.BETWEEN);
//
//        VerticalLayout headerContainer = new VerticalLayout();
//        H2 header = new H2("Beautiful photos");
//        header.addClassNames(Margin.Bottom.NONE, Margin.Top.XLARGE, FontSize.XXXLARGE);
//        Paragraph description = new Paragraph("Royalty free photos and pictures, courtesy of Unsplash");
//        description.addClassNames(Margin.Bottom.XLARGE, Margin.Top.NONE, TextColor.SECONDARY);
//        headerContainer.add(header, description);
//
//        Select<String> sortBy = new Select<>();
//        sortBy.setLabel("Sort by");
//        sortBy.setItems("Popularity", "Newest first", "Oldest first");
//        sortBy.setValue("Popularity");
//
//        imageContainer = new OrderedList();
//        imageContainer.addClassNames(Gap.MEDIUM, Display.GRID, ListStyleType.NONE, Margin.NONE, Padding.NONE);
//
//        container.add(headerContainer, sortBy);
//        add(container, imageContainer);
//
//    }
}

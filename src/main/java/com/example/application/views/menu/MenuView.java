package com.example.application.views.menu;

import com.example.application.api.MenuApi;
import com.example.application.domain.*;
import com.example.application.views.MainLayout;
import com.vaadin.flow.component.HasComponents;
import com.vaadin.flow.component.HasStyle;
import com.vaadin.flow.component.Html;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility.AlignItems;
import com.vaadin.flow.theme.lumo.LumoUtility.JustifyContent;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

@PageTitle("Menu")
@Route(value = "menu", layout = MainLayout.class)
public class MenuView extends Main implements HasComponents, HasStyle {
    private int count =0;
    private Map<Pizza, Integer> pizzaCountMap = new HashMap<>();
    private Map<Pizza, Paragraph> pizzaParapraphMap = new HashMap<>();
    Button order = new Button("Order");
    MenuApi getAll = new MenuApi();
    Div sideBar = new Div();
    Div getPriceDiv = new Div();
    double totalPrice;
    Div priceDiv = new Div();
    public MenuView() {
        H3 h = new H3("ORDER:");
        getPriceDiv.addClassName("getPriceDiv");
        getPriceDiv.setText("Total: R0");


        sideBar.addClassName("sideBar");
        sideBar.add(getPriceDiv);
        sideBar.add(h);
        addClassName("mainContainer");
        Set<Pizza> pizzas = getAll.getAllPizzas();
        pizzas.forEach(pizza -> {
           count = count +1;
            add(createPizzaSpan(pizza));
        });
        add(orderBar());
        //setSideBar();
    }
    public Div orderBar() {
        return sideBar;
    }
//    public void setSideBar(){
//        sideBar.addClickListener(clickEvent -> {
//            sideBar.addClassName("openSidebar");
//            System.out.println("Korean marmelade");
//        });
//    }
    private Div createPizzaSpan(Pizza pizza) {
        Div pizzaContainer = new Div();
        Button plus = new Button("+");
        plus.addThemeVariants(ButtonVariant.LUMO_SUCCESS);
        plus.addClassName("plus");
        Button minus = new Button("-");
        minus.addThemeVariants(ButtonVariant.LUMO_ERROR);
        minus.addClassName("minus");
        AtomicInteger pizzaCount = new AtomicInteger(0);

        plus.addClickListener(event -> {
           int count = pizzaCount.incrementAndGet();
           updateCountAndPrice(pizza, count);
            System.out.println(count);
        });
        minus.addClickListener(event -> {
            int count = pizzaCount.decrementAndGet();
            if (count >= 0) {
                updateCountAndPrice(pizza, count);
                System.out.println(count);
            }
        });



        //order = new Button("Add");
        //order.addClassName("orderBtn");


//        order.addClickListener(event -> {
//
//            System.out.println(pizza.getName());
//
//            // Check if pizza in map already
//            // Add count if not
//            if(!pizzaCountMap.containsKey(pizza)) {
//                pizzaCountMap.put(pizza, 1);
//            }else {
//                int count = pizzaCountMap.get(pizza);
//                pizzaCountMap.put(pizza, count + 1);
//                System.out.println(count);
//            }
//            // update total price
//
//            this.totalPrice = this.totalPrice + pizza.getPrice();
//            System.out.println(totalPrice);
//            getPriceDiv.setText("Total: R" + totalPrice);
//            if (pizzaCountMap.get(pizza) <= 1) {
//                sideBar.add(new Paragraph(String.valueOf(pizza.getName())));
//            }
//
//        });

        pizzaContainer.addClassName("pizzaContainer");
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
        //order.addClickListener(buttonClickEvent -> order.setId("testId"));
        pizzaContainer.add(wholeCard );
        pizzaContainer.addClassNames(AlignItems.CENTER, JustifyContent.BETWEEN);
        pizzaContainer.addClassName("menuCard");
        pizzaContainer.add(plus, minus);
        return pizzaContainer;
    }

//    private void updateCountAndPrice(Pizza pizza, int count) {
//        pizzaCountMap.put(pizza, count);
//        totalPrice = calculateTotalPrice();
//        getPriceDiv.setText("Total: R" + totalPrice);
//
//        if(pizzaParapraphMap.containsKey(pizza)) {
//            if(count == 0) {
//                Paragraph paragraphToRemove = pizzaParapraphMap.get(pizza);
//                sideBar.remove(paragraphToRemove);
//                pizzaParapraphMap.remove(pizza);
//            }
//        }else {
//            if (count > 0) {
//                Paragraph newParagraph = new Paragraph(String.valueOf(pizza.getName()));
//                sideBar.add(newParagraph);
//                pizzaParapraphMap.put(pizza, newParagraph);
//            }
//        }
//
//        if(count == 0){
//            sideBar.remove(new Paragraph(String.valueOf(pizza.getName())));
//        }else if (count == 1){
//            sideBar.add(new Paragraph(String.valueOf(pizza.getName())));
//        }
//    }
private void updateCountAndPrice(Pizza pizza, int count) {
    pizzaCountMap.put(pizza, count);
    totalPrice = calculateTotalPrice();
    getPriceDiv.setText("Total: R" + totalPrice);

    if (count == 0) {
        if (pizzaParapraphMap.containsKey(pizza)) {
            Paragraph paragraphToRemove = pizzaParapraphMap.get(pizza);
            sideBar.remove(paragraphToRemove);
            pizzaParapraphMap.remove(pizza);
        }
    } else {
        if (pizzaParapraphMap.containsKey(pizza)) {
            // If the pizza paragraph already exists, update its text
            Paragraph existingParagraph = pizzaParapraphMap.get(pizza);
            existingParagraph.setText(pizza.getName() + " (x" + count + ")");
        } else {
            // If the pizza paragraph does not exist, create a new one and add it to the sidebar
            Paragraph newParagraph = new Paragraph(pizza.getName() + " (x" + count + ")");
            sideBar.add(newParagraph);
            pizzaParapraphMap.put(pizza, newParagraph);
        }
    }
}
    private double calculateTotalPrice(){
        double total = 0.0;
        for (Map.Entry<Pizza, Integer> entry : pizzaCountMap.entrySet()){
            total += entry.getKey().getPrice() * entry.getValue();
        }
        return total;
    }
    private Div createOrder() {
        Div btnContainer = new Div();
        btnContainer.addClassName("btnContainer");
        Div span = new Div();
        span.setText("Total: R" + totalPrice);
        span.addClassName("test");
        order.addClickListener(buttonClickEvent -> span.setText("Total: R" + totalPrice));
        HorizontalLayout hl = new HorizontalLayout();
        hl.add(span);
        //sideBar.add(span);
        return span;
    }
}

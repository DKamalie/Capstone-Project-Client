package com.example.application.views.menu;

import com.example.application.api.CustomerApi;
import com.example.application.api.MenuApi;
import com.example.application.api.OrderApi;
import com.example.application.api.StagedOrderApi;
import com.example.application.domain.*;
import com.example.application.factory.PizzeriaFactory;
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
import com.vaadin.flow.server.VaadinSession;
import com.vaadin.flow.theme.lumo.LumoUtility.AlignItems;
import com.vaadin.flow.theme.lumo.LumoUtility.JustifyContent;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

@PageTitle("Menu")
@Route(value = "menu", layout = MainLayout.class)
public class MenuView extends Main implements HasComponents, HasStyle {
    StagedOrderApi stage = new StagedOrderApi();
    private int count =0;
    private Map<Pizza, Integer> pizzaCountMap = new HashMap<>();
    private Map<Pizza, Paragraph> pizzaParapraphMap = new HashMap<>();
    private AtomicInteger orderIdCounter = new AtomicInteger(1);
    Button order = new Button("Order");
    MenuApi getAll = new MenuApi();
    OrderApi orderApi = new OrderApi();
    Div sideBar = new Div();
    Div getPriceDiv = new Div();
    double totalPrice;
    Button chkBtn;
    Div priceDiv = new Div();
    LocalDate date = LocalDate.of(2023, 7, 28);
    LocalTime time = LocalTime.now();
    LoyaltyCustomer lc1 = (LoyaltyCustomer) VaadinSession.getCurrent().getAttribute("Customer");
    CustomerApi customerApi = new CustomerApi();
    Pizzeria pizzeria = PizzeriaFactory.buildPizzaria(
            "Hill Crest","Hotel Transylvania");
    Integer orderCount;
    StagedOrder stagedOrder;
    public MenuView() {


//        Set<Customer> customers = customerApi.getAllCustomers();
//        customers.forEach(customer -> {
//            add(new Div(new Span(String.valueOf(customer.getCustomerName()))));
//        });



        chkBtn = new Button("Checkout");
        chkBtn.addClassName("checkout");
        H3 h = new H3("ORDER:");
        getPriceDiv.addClassName("getPriceDiv");
        getPriceDiv.setText("Total: R0");

        sideBar.addClassName("sideBar");
        sideBar.add(getPriceDiv);
        sideBar.add(h);
        sideBar.add(chkBtn);
        addClassName("mainContainer");
        Set<Pizza> pizzas = getAll.getAllPizzas();
        pizzas.forEach(pizza -> {
           count = count +1;
            add(createPizzaSpan(pizza));

            //stage.createStagedOrder(1, date, time, lc1, 1, pizza, 22.00, stagedOrder.getOrderStatus(), pizzeria);
        });
        add(orderBar());

        chkBtn.addClickListener(event -> {
           checkOut();
            System.out.println(VaadinSession.getCurrent().getAttribute("Customer"));
        });
    }
    public Div orderBar() {
        return sideBar;
    }
    private Div createPizzaSpan(Pizza pizza) {
        Div pizzaContainer = new Div();
        Button plus = new Button("+");
        plus.addThemeVariants(ButtonVariant.LUMO_SUCCESS);
        plus.addClassName("plus");
        Button minus = new Button("-");
        minus.addThemeVariants(ButtonVariant.LUMO_ERROR);
        minus.addClassName("minus");
        AtomicInteger pizzaCount = new AtomicInteger(0);

//        Image pizzaImage = new Image()


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

        pizzaContainer.addClickListener(event -> {
           int orderId = orderIdCounter.getAndIncrement();
           int count = pizzaCountMap.getOrDefault(pizza, 0);
           totalPrice = calculateTotalPrice();
           stagedOrder = new StagedOrder(orderId, date, time, lc1, String.valueOf(count), pizza, totalPrice, StagedOrder.OrderStatus.NEW, pizzeria);
            System.out.println(stage.createStagedOrder(stagedOrder) + " this test");
        });

        //order.addClickListener(buttonClickEvent -> order.setId("testId"));
        pizzaContainer.add(wholeCard );
        pizzaContainer.addClassNames(AlignItems.CENTER, JustifyContent.BETWEEN);
        pizzaContainer.addClassName("menuCard");
        pizzaContainer.add(plus, minus);
        return pizzaContainer;
    }

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
        stage.deleteStagedOrder(pizza.getPizzaId());
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

        StagedOrder existingStagedOrder = stage.readStagedOrder(pizza.getPizzaId());
        if(existingStagedOrder != null) {
            existingStagedOrder.setQuantity(String.valueOf(count));
            existingStagedOrder.setTotal(totalPrice);
            stage.updateStagedOrder(existingStagedOrder);
        }else {
            int orderId = orderIdCounter.getAndIncrement();
            StagedOrder newStagedOrder = new StagedOrder(orderId, date, time, lc1, String.valueOf(count), pizza, totalPrice, StagedOrder.OrderStatus.NEW, pizzeria);
            System.out.println(stage.createStagedOrder(newStagedOrder) + " this test");
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
//    private Order createOrder() {
//
//        Order order1 = new Order(1, LocalDate.now(),);
//    }
    private void checkOut(){
        //orderApi.createOrder();
        System.out.println("test");
        stage.createStagedOrder(stagedOrder);
    }
}

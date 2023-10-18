package com.example.application.views.home;

import com.example.application.views.MainLayout;
import com.example.application.views.menu.MenuView;
import com.vaadin.flow.component.Html;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.dom.Style;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;

/*
HomeView.java
Author: Tamryn Lisa Lewin (219211981)
Date: 29 September 2023
Last update: 04 October 2023
 */

@PageTitle("Home")
@Route(value = "home", layout = MainLayout.class)
@RouteAlias(value = "", layout = MainLayout.class)
public class HomeView extends VerticalLayout {

    private Image imgPizza;
    private Html header, htmlHeading, htmlMsg, htmlEndMsg;
    private Button btnViewMenu;
    private Div btnDiv;

    public HomeView() {

        imgPizza = new Image("/images/pizza_olives_right_boarder.jpg", "Pizza image");

        header = new Html("<div><br><br></div>");

        htmlHeading = new Html("<H1>Delivering Happiness, One Slice at a Time!</H1>");

        htmlMsg = new Html("<div><br>Craving delicious, piping-hot pizza delivered straight to your doorstep? <br>" +
                "Look no further! <br><br></div>");

        htmlEndMsg = new Html("<div><br>Your satisfaction is our top priority. " +
                "<br>We can't wait to serve you the pizza you love.<br><br>");

        btnViewMenu = new Button("View menu");
        btnDiv = new Div();


//      Button events
        btnViewMenu.addClickListener(e -> {
            getUI().ifPresent(ui -> ui.navigate(MenuView.class));
        });


//      Styling elements
        Style imgPizzaStyle = imgPizza.getStyle();
        imgPizzaStyle.set("position", "fixed");
        imgPizzaStyle.set("top", "0");
//        imgPizzaStyle.set("bottom", "0");
        imgPizzaStyle.set("left", "0");
        imgPizzaStyle.set("width", "100%");
        imgPizzaStyle.set("height", "100%");
        imgPizzaStyle.set("z-index", "-1");

        Style welcomeHeadingStyle = htmlHeading.getStyle();
        welcomeHeadingStyle.set("color", "white");
        welcomeHeadingStyle.set("font-family", "Georgia, serif");
        welcomeHeadingStyle.set("padding-left", "60px");

        Style welcomeMessageStyle = htmlMsg.getStyle();
        welcomeMessageStyle.set("font-family", "Georgia, serif");
        welcomeMessageStyle.set("font-size", "25px");
        welcomeMessageStyle.set("font-weight", "bold");
        welcomeMessageStyle.set("color", "white");
        welcomeMessageStyle.set("padding-left", "60px");

        Style htmlEndMsgStyle = htmlEndMsg.getStyle();
        htmlEndMsgStyle.set("font-family", "Georgia, serif");
        htmlEndMsgStyle.set("font-size", "25px");
        htmlEndMsgStyle.set("font-weight", "bold");
        htmlEndMsgStyle.setColor("white");
        htmlEndMsgStyle.set("padding-left", "60px");

        Style btnViewMenuStyle = btnViewMenu.getStyle();
        btnViewMenuStyle.set("color", "white");
        btnViewMenuStyle.set("background-color", "#000000");
        btnViewMenuStyle.set("font-family", "Georgia, serif");
        btnViewMenuStyle.set("font-size", "20px");
        btnViewMenuStyle.set("font-weight", "bold");
        btnViewMenuStyle.set("width", "150px");
        btnViewMenuStyle.set("height", "43px");
        btnViewMenuStyle.set("border-radius", "22px");
        btnViewMenuStyle.set("box-shadow", "0 5px 4px rgba(0, 0, 0, 0.2)");

        Style btnDivStyle = btnDiv.getStyle();
        btnDivStyle.set("padding-left", "60px");


//      Add components to layout
        add(imgPizza);
        add(header);
        add(htmlHeading);
        add(htmlMsg);
        add(htmlEndMsg);
        btnDiv.add(btnViewMenu);
        add(btnDiv);
//        setMargin(true);
        setSpacing(true);
        setSizeFull();
    }
}

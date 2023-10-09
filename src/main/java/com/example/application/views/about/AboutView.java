package com.example.application.views.about;

import com.example.application.views.MainLayout;
import com.vaadin.flow.component.Html;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.dom.Style;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility.Margin;

@PageTitle("About")
@Route(value = "about", layout = MainLayout.class)
public class AboutView extends VerticalLayout {

    private Html aboutUs;
    private Html story;
    private Html value;
    private Html ingredients;
    private Html process;
    private Html closeOff;
    private Html hillCrest;
    private Image berge;
    private Image hillCrestImage;
    private Image oldHillCrestImage;
    private VerticalLayout textLayout;
    private HorizontalLayout image;

    public AboutView() {

        aboutUs = new Html("<div>ABOUT US</div>");

        story = new Html("<div><b>Our story:</b> Our story begins in the summer of 1999. Sam Berge and Maria Berge opened the one and only Hill Crest in South Africa. The two happily married couple loved making pizzas in their home, it was their favourite food to eat together. Years later in the summer of '99, they decided to turn their love of making pizzas into a business.</div>");
        value = new Html("<div><b>Our values:</b> Quality, freshness, and customer satisfaction are the cornerstones of our business. We believe that every pizza we create should be a masterpiece, made with the finest ingredients and served with a smile.</div>");
        ingredients = new Html("<div><b>Quality ingredients:</b> We source the freshest, locally-sourced ingredients to create our signature pizzas. Our commitment to quality means you can taste the difference in every bite.</div>");
        process = new Html("<div><b>Our process:</b> From hand-tossed dough to carefully selected toppings, every step in our pizza making process is a labour of love. We follow time-honored traditions to ensure each pizza is a culinary masterpiece.</div>");
        closeOff = new Html("<div>We invite you to explore our menu and experience the magic of Hill Crest.</div>");

        berge = new Image("/images/berge.jpg", "");
        berge.setWidth("300px");
        berge.setHeight("250px");


        hillCrestImage = new Image("/images/hillcrest.jpg", "");
        hillCrestImage.setHeight("300px");
        hillCrestImage.setWidth("450px");


        oldHillCrestImage = new Image("/images/oldHillCrest.png", "");
        oldHillCrestImage.setHeight("300px");
        oldHillCrestImage.setWidth("450px");


        image = new HorizontalLayout(oldHillCrestImage, hillCrestImage);



        hillCrest = new Html("<div>Hill Crest, a historic building that has stood the test of time, has been reborn as a modern pizza haven." +
                " Originally founded in 1999 by the pizza-loving couple Sam and Maria Berge, this charming Cape Town City Centre establishment has undergone a meticulous transformation." +
                " The building, steeped in history, now seamlessly blends its storied past with contemporary style and technology." +
                " Today, Hill Crest remains a testament to its founders' commitment to crafting exceptional pizzas with the finest locally-sourced ingredients, all within a space that exudes a perfect fusion of tradition and modernity.</div>");

        textLayout = new VerticalLayout(story, value, ingredients, process);
        textLayout.setSpacing(false);

        HorizontalLayout mainLayout = new HorizontalLayout(berge, textLayout);
        mainLayout.setAlignItems(Alignment.CENTER);

        Style bergeStyle = berge.getStyle();
        bergeStyle.set("border-radius", "4%");

        Style hillCrestImageStyle = hillCrestImage.getStyle();
        hillCrestImageStyle.set("border-radius", "6%");

        Style oldHillCrestImageStyle = oldHillCrestImage.getStyle();
        oldHillCrestImageStyle.set("border-radius", "6%");

        Style aboutUsStyle = aboutUs.getStyle();
        aboutUsStyle.set("font-family", "Arial");
        aboutUsStyle.set("font-size", "30px");
        aboutUsStyle.set("font-weight", "bold");

        Style storyStyle = story.getStyle();
        storyStyle.set("font-family", "Arial");
        storyStyle.set("font-size", "15px");

        Style valueStyle = value.getStyle();
        valueStyle.set("font-family", "Arial");
        valueStyle.set("font-size", "15px");

        Style ingredientsStyle = ingredients.getStyle();
        ingredientsStyle.set("font-family", "Arial");
        ingredientsStyle.set("font-size", "15px");

        Style processStyle = process.getStyle();
        processStyle.set("font-family", "Arial");
        processStyle.set("font-size", "15px");

        Style hillCrestStyle = hillCrest.getStyle();
        hillCrestStyle.set("font-family", "Arial");
        hillCrestStyle.set("font-size", "15px");

        Style closeOffStyle = closeOff.getStyle();
        closeOffStyle.set("font-family", "Arial");
        closeOffStyle.set("font-size", "15px");


        setMargin(true);

        add(aboutUs);
        add(mainLayout);
        add(image);
        add(hillCrest);
        add(closeOff);


    }

    }



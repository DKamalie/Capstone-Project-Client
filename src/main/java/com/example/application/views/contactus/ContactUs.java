package com.example.application.views.contactus;

import com.example.application.views.MainLayout;
import com.vaadin.flow.component.Html;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.dom.Style;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("ContactUs")
@Route(value = "contactus", layout = MainLayout.class)
public class ContactUs extends VerticalLayout {

    private Html contactUs;
    private Html hillCrestAddress;
    private Html phoneNumber;
    private Html email;
    private Div borderDiv;
    private Image address;
    private Image telephone;
    private Image mail;
    private Image pizzaBG;
    private HorizontalLayout addressLayout;
    private HorizontalLayout telephoneLayout;
    private HorizontalLayout mailLayout;

    public ContactUs(){

        contactUs = new Html("<div>CONTACT US</div>");//still figure out what to do with this

        hillCrestAddress = new Html("<div><b>Hill Crest address:</b><br>" +
                "300 Long St<br>" +
                "Cape Town City Centre<br>" +
                "Cape Town<br>" +
                "8000<br><br></div>");

        phoneNumber = new Html("<div><b>Phone number:</b> 071 093 0123<br><br><br></div>");

        email = new Html("<div><b>Email:</b> contact@hillcrest.co.za</div>");

        address = new Image("/images/address.png", "");
        address.setWidth("33px");
        address.setHeight("42px");

        telephone = new Image("/images/telephone.png", "");
        telephone.setWidth("33px");
        telephone.setHeight("42px");

        mail = new Image("/images/mail.png", "");
        mail.setWidth("33px");
        mail.setHeight("42px");

//        pizzaBG = new Image("/images/pizzaBG.png", "");
        pizzaBG = new Image("/images/eating_pizza_boarder.jpg", "");    //just testing another image for background
//        pizzaBG = new Image("/images/contact.jpg", "");

        Style pizzaImageStyle = pizzaBG.getStyle();
//        pizzaImageStyle.set("position", "fixed");
//        pizzaImageStyle.set("top", "60%");//60%
//        pizzaImageStyle.set("left", "52%");//50%
//        pizzaImageStyle.set("right", "52%");
//        pizzaImageStyle.set("bottom", "60%");
//        pizzaImageStyle.set("transform", "translate(-50%, -50%)");
//        pizzaImageStyle.set("width", "100%");//100%
//        pizzaImageStyle.set("height", "71%");//80%
//        pizzaImageStyle.set("object-fit", "cover");
//        pizzaImageStyle.set("z-index", "-1");

        pizzaImageStyle.set("position", "fixed");
        pizzaImageStyle.set("top", "0");
//        imgPizzaStyle.set("bottom", "0");
        pizzaImageStyle.set("left", "0");
        pizzaImageStyle.set("width", "100%");
        pizzaImageStyle.set("height", "100%");
        pizzaImageStyle.set("z-index", "-1");


        Style contactUsStyle = contactUs.getStyle();
        contactUsStyle.set("font-family", "Arial");
        contactUsStyle.set("font-size", "30px");
        contactUsStyle.set("font-weight", "bold");


        Style hillCrestAddressStyle = hillCrestAddress.getStyle();
        hillCrestAddressStyle.set("font-family", "Arial");
        hillCrestAddressStyle.set("font-size", "17px");


        Style phoneNumberStyle = phoneNumber.getStyle();
        phoneNumberStyle.set("font-family", "Arial");
        phoneNumberStyle.set("font-size", "17px");


        Style emailStyle = email.getStyle();
        emailStyle.set("font-family", "Arial");
        emailStyle.set("font-size", "17px");


        addressLayout = new HorizontalLayout(address, hillCrestAddress);
        telephoneLayout = new HorizontalLayout(telephone, phoneNumber);
        mailLayout = new HorizontalLayout(mail, email);

        addressLayout.setVerticalComponentAlignment(FlexComponent.Alignment.CENTER);
        telephoneLayout.setVerticalComponentAlignment(FlexComponent.Alignment.CENTER);
        mailLayout.setVerticalComponentAlignment(FlexComponent.Alignment.CENTER);

        borderDiv = new Div(addressLayout, telephoneLayout, mailLayout);
        borderDiv.getStyle()
                .set("border", "1px solid black")
                .set("padding", "100px")
                .set("border-radius", "5px")
                .set("margin-top", "23px");

        VerticalLayout centerAlignedLayout = new VerticalLayout(borderDiv);
        centerAlignedLayout.setAlignItems(FlexComponent.Alignment.CENTER);

        add(contactUs);
        add(pizzaBG);
        add(centerAlignedLayout);
    }
}

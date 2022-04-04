package com.example.application.views.mainpage;

import com.example.application.controller.TwitterController;
import com.example.application.views.MainLayout;
import com.fasterxml.jackson.databind.JsonNode;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasComponents;
import com.vaadin.flow.component.HasStyle;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.messages.MessageList;
import com.vaadin.flow.component.messages.MessageListItem;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.textfield.TextFieldVariant;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Arrays;

@PageTitle("Mainpage")
@Route(value = "", layout = MainLayout.class)
public class MainpageView extends Main implements HasComponents, HasStyle {

    private OrderedList imageContainer;

    public MainpageView() throws Exception {
        constructUI();

    }

    private void constructUI() throws Exception {
        addClassNames("mainpage-view", "max-w-screen-lg", "mx-auto", "pb-l", "px-l");

        VerticalLayout container1 = new VerticalLayout();
        H2 header = new H2("Twitter Sentiment Analysis");
        container1.add(header);
        container1.add(searchTweets());
        container1.add(getTweets());
        container1.setDefaultHorizontalComponentAlignment(FlexComponent.Alignment.CENTER);


        add(container1);

    }

    private Component searchTweets() {
        HorizontalLayout searchBar = new HorizontalLayout();

        TextField searchField = new TextField();
        searchField.getElement().setAttribute("aria-label","search");
        searchField.setPlaceholder("Search tweets");
        searchField.setClearButtonVisible(true);
        searchField.addThemeVariants(TextFieldVariant.LUMO_ALIGN_CENTER);
        searchField.setWidth("30em");

        Button searchButton = new Button("Search");
        searchButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY, ButtonVariant.LUMO_SUCCESS);

        searchBar.add(searchField);
        searchBar.add(searchButton);

        return searchBar;
    }

    private Component getTweets() throws Exception {
//        TwitterController twitterController = new TwitterController();
//        JsonNode jsonNode = twitterController.tweets();
//        System.out.println(jsonNode);

        MessageList list = new MessageList();
        Instant yesterday = LocalDateTime.now().minusDays(1)
                .toInstant(ZoneOffset.UTC);
        Instant fiftyMinsAgo = LocalDateTime.now().minusMinutes(50)
                .toInstant(ZoneOffset.UTC);
        MessageListItem message1 = new MessageListItem(
                "Linsey, could you check if the details with the order are okay?",
                yesterday, "Matt Mambo");
        message1.setUserColorIndex(1);
        MessageListItem message2 = new MessageListItem("All good. Ship it.",
                fiftyMinsAgo, "Linsey Listy");
        message2.setUserColorIndex(2);
        list.setItems(Arrays.asList(message1, message2));
        return list;
    }
}
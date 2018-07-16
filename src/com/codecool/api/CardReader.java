package com.codecool.api;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Element;
import org.w3c.dom.Node;


import java.io.File;

public class CardReader {

    public void loadDeck(Player player, String path) {
        try {
            File xmlFile = new File(path);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document document = dBuilder.parse(xmlFile);

            document.getDocumentElement().normalize();

            NodeList nList = document.getElementsByTagName("Card");

            for (int temp = 0; temp < nList.getLength(); temp++) {

                Node cardNode = nList.item(temp);

                if (cardNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element cardElement = (Element) cardNode;
                    String name = cardElement.getElementsByTagName("name").item(0).getTextContent();
                    int military = Integer.parseInt(cardElement.getElementsByTagName("military").item(0).getTextContent());
                    int intrique = Integer.parseInt(cardElement.getElementsByTagName("intrique").item(0).getTextContent());
                    int fame = Integer.parseInt(cardElement.getElementsByTagName("fame").item(0).getTextContent());
                    String link = cardElement.getElementsByTagName("link").item(0).getTextContent();

                    Card newCard = new Card(name, military, intrique, fame, link);

                    player.getDeck().setCardList(newCard);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
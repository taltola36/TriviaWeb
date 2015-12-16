/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package TriviaWebLogic;

import java.io.File;
import java.util.ArrayList;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

@XmlRootElement(name = "triviaGame")
public class TriviaGame {

    private ArrayList<TriviaCategory> categories;

    public TriviaGame() {
        this.categories = new ArrayList<TriviaCategory>();
    }

    @XmlElement(name = "category")
    public ArrayList<TriviaCategory> getCategories() {
        return categories;
    }

    
    public ArrayList<TriviaAnswer> getAnswers(String question) {
        ArrayList<TriviaQuestion> TQArr;
        for (TriviaCategory TC: categories) {
            TQArr = TC.getQuestions();
            for (TriviaQuestion TQ : TQArr)
                if (TQ.getQuestion().equals(question))
                    return TQ.getAnswers();
        }
        return null;    //should never get here anyway
    }
     
    public ArrayList<TriviaQuestion> showQuestions(TriviaCategory category) {
        ArrayList<TriviaQuestion> ques = null;
        for (TriviaCategory TC : categories)
            if (TC.getNameOfCategory().equals(category.getNameOfCategory()))
                ques = TC.getQuestions();
        return ques;
    }

    public void addQuestion(String question, boolean isOpen, int difficultyLevel, int category, String... answers) {
        categories.get(category).addQuestion(question, isOpen, difficultyLevel, answers);

    }

    public void deleteQuestions(int qNum) {
        int counter = 0;
        for (int i = 0; i < categories.size(); i++) {
            ArrayList<TriviaQuestion> questions = categories.get(i).getQuestions();
            for (int j = 0; j < questions.size(); j++) {
                if (counter == qNum) {
                    categories.get(i).deleteQuestion(j);
                }
                counter++;
            }
        }
    }

    public void saveToFile() {
        try {
            File file = new File("TriviaGameXml.xml");
            JAXBContext jaxbContext = JAXBContext.newInstance(TriviaGame.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

            // output pretty printed
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.marshal(this, file);
        } catch (Exception e) {
            String errMsg = e.getMessage();
        }
    }

//    public void loadFile() {
//        // for testing
//        categoris = new ArrayList<>();
//        categoris.add(new TriviaCategory("Movies"));
//        categoris.add(new TriviaCategory("History"));
//        categoris.get(0).addQuestion("Who is the star in the movie Air Force 1?", true, 1, "Harrison Ford");
//        categoris.get(0).addQuestion("What did Forrest Gump sell that made him rich?", true, 2, "Shrimp");
//        categoris.get(0).addQuestion("What movie made Oshri Cohen famous?", true, 3, "Shlomi's Stars");
//        categoris.get(0).addQuestion("Was Jackie Chan a background actor in a Bruce Lee movie?", false, 1, "yes", "no");
//        categoris.get(0).addQuestion("Which movie has a character named Mushu?", false, 2, "Aladin", "Pocahontas", "Mulan");
//        categoris.get(0).addQuestion("Beaufort plot took place in...", false, 3, "Lebanon", "Egypt", "Jordan");
//        categoris.get(1).addQuestion("Which city in Israel, is named after the Roman emperor Tiberius", true, 1, "Tveria");
//        categoris.get(1).addQuestion("Smart Greek considered the father of the theory of geometry", true, 2, "Euklides");
//        categoris.get(1).addQuestion("The nickname of the Russian Communist Party members before the party came to power", true, 3, "Bolsheviks");
//        categoris.get(1).addQuestion("Was Israel bacame country in 1948?", false, 1, "yes", "no");
//        categoris.get(1).addQuestion("The name of the first russian ship in space?", false, 3, "Sputnik", "Parom", "Halalit");
//    }

    public static TriviaGame loadFromFile() {
        TriviaGame game = new TriviaGame();
        try {
            File fXmlFile = new File("TriviaGameXml.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);
            doc.getDocumentElement().normalize();

            NodeList listCat = doc.getElementsByTagName("category");
            for (int i = 0; i < listCat.getLength(); i++) {

                Node nNode = listCat.item(i);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eCat = (Element) nNode;
                    String catName = eCat.getAttribute("name");

                    TriviaCategory category = new TriviaCategory(catName);

                    // get category questions
                    NodeList listQues = eCat.getElementsByTagName("question");
                    for (int j = 0; j < listQues.getLength(); j++) {
                        Node node = listQues.item(j);
                        if (node.getNodeType() == Node.ELEMENT_NODE) {
                            // get the question text
                            Element eQues = (Element) node;

                            // get the text
                            String quesText = eQues.getAttribute("text");

                            // get the question difficulty
                            String quesDifficulty = eQues.getElementsByTagName("difficulty").item(0).getTextContent();

                            // get the question isOpen
                            String quesIsOpen = eQues.getElementsByTagName("isOpen").item(0).getTextContent();

                            // get the answers
                            ArrayList<String> answers = new ArrayList<String>();
                            NodeList listAns = eQues.getElementsByTagName("answer");
                            for (int k = 0; k < listAns.getLength(); k++) {
                                Node nodeAn = listAns.item(k);
                                if (nodeAn.getNodeType() == Node.ELEMENT_NODE) {
                                    Element eAns = (Element) nodeAn;

                                    // get the text
                                    String ansText = eAns.getAttribute("text");
                                    answers.add(ansText);
                                }
                            }

                            String[] arrAns = new String[answers.size()];
                            answers.toArray(arrAns);

                            category.addQuestion(quesText, Boolean.parseBoolean(quesIsOpen), Integer.parseInt(quesDifficulty), arrAns);
                        }
                    }

                    game.categories.add(category);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return game;
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package TriviaWebLogic;

import java.util.ArrayList;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author tal
 */
@XmlType(name = "category")
public class TriviaCategory {

    private ArrayList<TriviaQuestion> questions;
    private String nameOfCategory;

    public TriviaCategory(String name) {
        nameOfCategory = name;
        questions = new ArrayList<TriviaQuestion>();
    }

    @XmlAttribute(name = "name")
    public String getNameOfCategory() {
        return nameOfCategory;
    }

    @XmlElement(name = "question")
    public ArrayList<TriviaQuestion> getQuestions() {
        return questions;
    }

    public void addQuestion(String question, boolean isOpen, int difficultyLevel, String... answers) {
        TriviaQuestion TQ = new TriviaQuestion(question, isOpen, difficultyLevel);

        TriviaAnswer ans;
        for (int i = 0; i < answers.length; i++) {
            if (i == 0) {
                ans = new TriviaAnswer(true, answers[i]);
            } else {
                ans = new TriviaAnswer(false, answers[i]);
            }

            TQ.addAnswers(ans);
        }

        questions.add(TQ);
    }

    public void deleteQuestion(int j) {
        questions.remove(j);
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package TriviaWebLogic;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author tal
 */
@XmlType(name = "answer")
public class TriviaAnswer {

    private String answer;
    private boolean isTrue;

    public TriviaAnswer(boolean isTrue, String answer) {
        this.answer = answer;
        this.isTrue = isTrue;
    }

    @XmlAttribute(name = "text")
    public String getAnswer() {
        return answer;
    }

    @XmlElement(name = "isTrue")
    public boolean getIsTrue() {
        return isTrue;
    }
}

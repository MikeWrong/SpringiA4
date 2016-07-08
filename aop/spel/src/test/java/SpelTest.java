import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.util.ArrayList;
import java.util.List;

/**
 * YUAN
 * 7/8/16.
 */
public class SpelTest {

    public static void main(String[] args) {
        ExpressionParser parser = new SpelExpressionParser();
        Expression exp = parser.parseExpression("'Hello World'.concat('!')");
        String message = (String) exp.getValue();
        System.out.println(message);

        Simple simple = new Simple();
        simple.booleanList.add(true);
        StandardEvaluationContext simpleContext = new StandardEvaluationContext(simple);
        parser.parseExpression("booleanList[0]").setValue(simpleContext, "false");
        Boolean b = simple.booleanList.get(0);
        System.out.println(b);
    }

}

class Simple {
    public List<Boolean> booleanList = new ArrayList<Boolean>();
}

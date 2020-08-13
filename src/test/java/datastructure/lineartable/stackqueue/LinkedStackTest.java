package datastructure.lineartable.stackqueue;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

public class LinkedStackTest {
    @Test
    public void SysConvert() {
        int num = 2000;
        LinkedStack<Integer> s = new LinkedStack<>();
        while (num > 0) {
            s.push(num % 2);
            num /= 2;
        }
        StringBuffer result = new StringBuffer();
        while (!s.isEmpty()) {
            result.append(s.pop());
        }
        System.out.println(result);
    }
    
    @Test
    public void matchJudge() {
        String s = "{(3+10)*12-15";
        LinkedStack<Character> stack = new LinkedStack<>();
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            char aChar = chars[i];
            if (aChar == '(' || aChar == '{') {
                stack.push(aChar);
            }
            if (aChar == ')' || aChar == '}') {
                if (stack.isEmpty()) {
                    System.out.println("括号不匹配 1");
                    return;
                }
                Character pop = stack.pop();
                if (('(' == pop && ')' != aChar) || ('{' == pop && '}' != aChar)) {
                    System.out.println("括号不匹配 2");
                    return;
                }
            }
        }
        if (!stack.isEmpty()) {
            System.out.println("括号不匹配 3");
            return;
        }
        System.out.println("匹配");
        
    }
    
    
    @Test
    public void calulate() throws Exception {
        String expriessStr = "3 * ( 11 - 5 * 2 )";
        String[] expression = expriessStr.split(" ");
        LinkedStack<String> numLinkedStack = new LinkedStack<>();
        LinkedStack<String> operLinkedStack = new LinkedStack<>();
        for (String exp : expression) {
            if (isOper(exp)) {
                if (")".equals(exp)) {
                    String tmpOper = null;
                    while (!"(".equals((tmpOper = operLinkedStack.pop())) && !numLinkedStack.isEmpty() && !operLinkedStack.isEmpty()) {
                        String num2 = numLinkedStack.pop();
                        String num1 = numLinkedStack.pop();
                        String result = getResult(tmpOper, num1, num2);
                        numLinkedStack.push(result);
                    }
                    continue;
                } else if (operLinkedStack.isEmpty()) {
                
                } else if ("(".equals(operLinkedStack.peek()) || "(".equals(exp)) {
                
                } else if ((getPriority(exp) <= getPriority(operLinkedStack.peek()))) {
                    String num2 = numLinkedStack.pop();
                    String num1 = numLinkedStack.pop();
                    String oper = operLinkedStack.pop();
                    String result = getResult(oper, num1, num2);
                    numLinkedStack.push(result);
                } else {
                
                }
                operLinkedStack.push(exp);
                
            } else {
                numLinkedStack.push(exp);
            }
        }
        
        while (!operLinkedStack.isEmpty()) {
            String num2 = numLinkedStack.pop();
            String num1 = numLinkedStack.pop();
            String oper = operLinkedStack.pop();
            String result = getResult(oper, num1, num2);
            numLinkedStack.push(result);
        }
        
        System.out.println(numLinkedStack.peek());
        
        
    }
    
    static String[] expressions = {"+", "-", "*", "/", "(", ")"};
    
    public String getResult(String oper, String num1, String num2) throws Exception {
        String result = "0";
        if ("+".equals(oper)) {
            result = (Double.parseDouble(num1) + Double.parseDouble(num2)) + "";
        } else if ("-".equals(oper)) {
            result = (Double.parseDouble(num1) - Double.parseDouble(num2)) + "";
        } else if ("*".equals(oper)) {
            result = (Double.parseDouble(num1) * Double.parseDouble(num2)) + "";
        } else if ("/".equals(oper)) {
            result = (Double.parseDouble(num1) / Double.parseDouble(num2)) + "";
        }
        return result;
    }
    
    public boolean isOper(String oper) {
        for (String exp : expressions) {
            if (exp != null && exp.equals(oper)) {
                return true;
            }
        }
        return false;
    }
    
    public int getPriority(String expression) {
        if ("+".equals(expression) || "-".equals(expression)) {
            return 0;
        }
        if ("*".equals(expression) || "/".equals(expression)) {
            return 1;
        }
        if ("(".equals(expression) || ")".equals(expression)) {
            return 99;
        }
        return -1;
    }
    
    /**
     * 中缀表达式转后缀表达式
     * opstack操作符栈 、tmpresult 中间结果栈
     * 从左到右遍历表达式
     * 1.操作数 直接入 中间结果栈tmpresult
     * 2.如果是操作符
     * 2.1如果 opstack 为空 操作符 压入
     * 2.2如果 opstack不为空
     * 2.2.1如果 当前操作符 比opstack栈顶 元素 优先级低 直接入栈
     * 2.2.2否则,将opstack栈顶弹出 压入tmpresult   然会转会到 2 继续判断 直到 栈中 大于等于当先操作符优先级都出来
     * 特殊情况  如果操作符为( 或者 opstack栈顶指针为( 当前操作符直接压入opstack
     * 如果操作符为) 依次弹出opstack 直到遇到( 并压入tmpresult 但是(  和) 运算符不进行压入
     * <p>
     * 遍历结束  如果opstack不会空  依次弹出 压入 中间结果栈
     * <p>
     * 中间结果  弹出 倒序 就是 后缀表达式
     */
    @Test
    public void expressionNomal2Suffix() {
        String[] expression = {"3", "*", "(", "11", "-", "5", "*", "2", ")"};
        expression = new String[]{"1", "+", "(", "(", "2", "+", "3", ")", "*", "4", ")", "-", "5"};
        expression = new String[]{"1", "+", "(", "2", "+", "3", ")", "*", "4", "-", "5"};
        
        LinkedStack<String> tmpresultStack = new LinkedStack<>();
        LinkedStack<String> operLinkedStack = new LinkedStack<>();
        
        for (int i = 0; i < expression.length; i++) {
            String exp = expression[i];
            if (isOper(exp)) {
                if (")".equals(exp)) {
                    String pop = null;
                    for (; !operLinkedStack.isEmpty() && !"(".equals(pop = operLinkedStack.pop()); ) {
                        tmpresultStack.push(pop);
                    }
                    continue;
                } else if (operLinkedStack.isEmpty()) {
                } else if ("(".equals(operLinkedStack.peek())) {
                } else if (getPriority(exp) <= getPriority(operLinkedStack.peek())) {
                    
                    for (; !operLinkedStack.isEmpty(); ) {
                        if (getPriority(exp) > getPriority(operLinkedStack.peek())) break;
                        tmpresultStack.push(operLinkedStack.pop());
                    }
                } else {
                }
                operLinkedStack.push(exp);
            } else {
                tmpresultStack.push(exp);
            }
        }
        
        
        for (; ; ) {
            if (operLinkedStack.isEmpty()) break;
            tmpresultStack.push(operLinkedStack.pop());
        }
        String s = "";
        for (; ; ) {
            if (tmpresultStack.isEmpty()) break;
            s = tmpresultStack.pop() + s;
        }
        System.out.println(s);
    }
    
    /**
     * 中缀表达式转前缀表达式
     * opstack操作符栈 、tmpresult 中间结果栈
     * 从右到左遍历表达式
     * 1.操作数 直接入 中间结果栈tmpresult
     * 2.如果是操作符
     * 2.1如果 opstack 为空 操作符 压入
     * 2.2如果 opstack不为空
     * 2.2.1如果 当前操作符   ≥  opstack栈顶 操作符优先级 继续入栈
     * 2.2.2否则,将opstack栈顶弹出 压入tmpresult   然会转会到 2 继续判断 直到 栈中 出现 ＜当先操作符优先级都出来
     * 特殊情况  如果操作符为) 或者 opstack栈顶指针为) 当前操作符直接压入opstack
     * 如果操作符为( 依次弹出opstack 直到遇到( 并压入tmpresult 但是(  和) 运算符不进行压入
     * <p>
     * 遍历结束  如果opstack不会空  依次弹出 压入 中间结果栈
     * <p>
     * 中间结果  弹出 就是 前缀表达式
     */
    @Test
    public void expressionNomal2Prefix() {
        String[] expression = {"3", "*", "(", "11", "-", "5", "*", "2", ")"};
        expression = new String[]{"1", "+", "(", "(", "2", "+", "3", ")", "*", "4", ")", "-", "5"};
        
        LinkedStack<String> tmpresultStack = new LinkedStack<>();
        LinkedStack<String> operLinkedStack = new LinkedStack<>();
        
        for (int i = expression.length - 1; i >= 0; i--) {
            String exp = expression[i];
            if (isOper(exp)) {
                if ("(" == exp) {
                    String pop = null;
                    for (; !operLinkedStack.isEmpty() && (pop = operLinkedStack.pop()) != ")"; ) {
                        tmpresultStack.push(pop);
                    }
                    continue;
                } else if (operLinkedStack.isEmpty()) {
                } else if (")" == operLinkedStack.peek()) {
                } else if (getPriority(exp) < getPriority(operLinkedStack.peek())) {
                    for (; !operLinkedStack.isEmpty(); ) {
                        if (getPriority(exp) < getPriority(operLinkedStack.peek())) break;
                        tmpresultStack.push(operLinkedStack.pop());
                    }
                } else {
                }
                operLinkedStack.push(exp);
            } else {
                tmpresultStack.push(exp);
            }
        }
        
        
        for (; ; ) {
            if (operLinkedStack.isEmpty()) break;
            tmpresultStack.push(operLinkedStack.pop());
        }
        String s = "";
        for (; ; ) {
            if (tmpresultStack.isEmpty()) break;
            s = s + tmpresultStack.pop();
        }
        System.out.println(s);
    }
    
    
    @Test
    public void caculateWithRPNTest() throws Exception {
        String expriessStr = "3 * ( 11 - 5 * 2 )";
        String[] expression = expriessStr.split(" ");
        caculateWithRPN(expressionNomal2Suffix(expression));
    }
    
    public void caculateWithRPN(String[] RpnExpressionArr) throws Exception {
        LinkedStack<String> stack = new LinkedStack<>();
        for (String exp : RpnExpressionArr) {
            if (isOper(exp)) {
                String num2 = stack.pop();
                String num1 = stack.pop();
                String result = getResult(exp, num1, num2);
                stack.push(result);
            } else {
                stack.push(exp);
            }
        }
        System.out.println(stack.peek());
    }
    
    public String[] expressionNomal2Suffix(String[] expression) {
        LinkedStack<String> tmpresultStack = new LinkedStack<>();
        LinkedStack<String> operLinkedStack = new LinkedStack<>();
        
        for (int i = 0; i < expression.length; i++) {
            String exp = expression[i];
            if (isOper(exp)) {
                if (")".equals(exp)) {
                    String pop = null;
                    for (; !operLinkedStack.isEmpty() && !"(".equals(pop = operLinkedStack.pop()); ) {
                        tmpresultStack.push(pop);
                    }
                    continue;
                } else if (operLinkedStack.isEmpty()) {
                } else if ("(".equals(operLinkedStack.peek())) {
                } else if (getPriority(exp) <= getPriority(operLinkedStack.peek())) {
                    
                    for (; !operLinkedStack.isEmpty(); ) {
                        if (getPriority(exp) > getPriority(operLinkedStack.peek())) break;
                        tmpresultStack.push(operLinkedStack.pop());
                    }
                } else {
                }
                operLinkedStack.push(exp);
            } else {
                tmpresultStack.push(exp);
            }
        }
        
        
        for (; ; ) {
            if (operLinkedStack.isEmpty()) break;
            tmpresultStack.push(operLinkedStack.pop());
        }
        
        LinkedStack<String> tmpstack = new LinkedStack<>();
        for (; ; ) {
            if (tmpresultStack.isEmpty()) break;
            tmpstack.push(tmpresultStack.pop());
        }
        ArrayList<String> strings = new ArrayList<>();
        for (; ; ) {
            if (tmpstack.isEmpty()) break;
            strings.add(tmpstack.pop());
        }
        System.out.println(Arrays.toString(strings.toArray(new String[]{})));
        return strings.toArray(new String[]{});
    }
    
}
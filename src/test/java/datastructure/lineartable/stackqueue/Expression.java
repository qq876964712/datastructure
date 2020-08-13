package datastructure.lineartable.stackqueue;

import org.junit.Test;

public class Expression {
    
    @Test
    public void test(){
        String expstr = "121+10*(53-49+20)/((35-25)*2+10)+11";
        String postfix = toPostfix(expstr);
        int value = value(postfix);
        
        System.out.println(postfix);
        System.out.println(value);
    }
    public String toPostfix(String expStr){
        LinkedStack<String> operStack = new LinkedStack<>();
        String postfix ="";
        for(int i=0;i<expStr.length();){
            char aChar = expStr.charAt(i);
            switch (aChar){
                case '+':
                case '-':
                    while(!operStack.isEmpty() &&  !"(".equals(operStack.peek())){
                        postfix += operStack.pop();
                    }
                    operStack.push(aChar+"");
                    i++;
                    break;
                case '*':
                case '/':
                    while (!operStack.isEmpty() && !"(".equals(operStack.peek()) &&  ( "*".equals(operStack.peek())  || "/".equals(operStack.peek())  )) {
                        postfix += operStack.pop();
                    }
                    operStack.push(aChar + "");
                    i++;
                    break;
                case '(':
                    operStack.push(aChar + "");
                    i++;
                    break;
                case ')':
                    String op=null;
                    while(!operStack.isEmpty() &&  !"(".equals(op =operStack.pop())){
                        postfix += op;
                    }
                    i++;
                    break;
                default:
                    if(postfix.length()>0){
                        char lastchar = postfix.charAt(postfix.length() - 1);
                        if (lastchar >= '0' && lastchar <= '9') {
                            postfix += ",";
                        }
                    }

                    for(; i<expStr.length(); i++){
                        aChar= expStr.charAt(i);
                        if(aChar<'0' || aChar>'9')break;
                        postfix+=aChar;
                        
                    }
            }

        }
        
        while(!operStack.isEmpty()){
            postfix += operStack.pop();
        }
        return postfix;
    }
    
    /**
     * 计算后缀表达式的值
     */
    public static int value(String postfix) {
        LinkedStack<Integer> resultStack = new LinkedStack<>();
        char[] chars = postfix.toCharArray();
        for(int i=0; i<chars.length;){
            char aChar = chars[i];
            if(aChar>='0' && aChar<='9'){
                int numvalue=0;
                for(;i<chars.length && (aChar >= '0' && aChar <= '9');){
                    numvalue= numvalue*10 + new Integer(aChar+"");
                    i++;
                    aChar=chars[i];
                }
                resultStack.push(numvalue);
            }else if('+'==aChar || '-' == aChar || '*' == aChar || '/' == aChar){
                Integer num2 = resultStack.pop();
                Integer num1 = resultStack.pop();
                int numvalue = 0;
                switch (aChar){
                    case '+':
                        numvalue =num1+num2;
                        break;
                    case '-':
                        numvalue = num1 - num2;
                        break;
                    case '*':
                        numvalue = num1 * num2;
                        break;
                    case '/':
                        numvalue = num1 / num2;
                        break;
                }
                resultStack.push(numvalue);
                i++;
            }else{
                i++;
            }
        }
    
        return resultStack.pop();
    }
    
}

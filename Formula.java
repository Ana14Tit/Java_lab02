package lab02;

import java.util.Scanner;
import java.util.Stack;

public class Formula {
	
	 public static boolean checkBrackets(String formula)
	 {
	    	int counter = 0;
			for(int i = 0; i<formula.length(); i++) {
				if(formula.charAt(i) == '(')
					counter++;
				if(formula.charAt(i) == ')')
					counter--;
			}
			if (counter == 0) return true;
			else return false;
					
	    }
	 
	 public static String[] newFormulaFormat(String formula)
	    {
	        String[] result = new String[formula.length()];
	        boolean first = true, nul = true;
	        Pair[] pair = new Pair[formula.length()];
	        Scanner in = new Scanner(System.in);
	        String tmp;


	        int j = -1, k = 0;
	        for (int i = 0; i < formula.length(); i++){
	            if(formula.charAt(i) >= '0' && formula.charAt(i) <= '9')
	                if(first) {
	                    j++;
	                    result[j] = formula.charAt(i) + "";
	                    first = false;
	                }
	                else
	                    result[j] += formula.charAt(i) + "";
	            else
	                if(formula.charAt(i) != '+' && formula.charAt(i) != '-' && formula.charAt(i) != '*' && formula.charAt(i) != '/' && formula.charAt(i) != ' ' && formula.charAt(i) != '(' && formula.charAt(i) != ')'){
	                    tmp = find(pair, formula.charAt(i));
	                    if(nul || tmp.equals("a")){
	                        try{
	                            System.out.print("Enter the value of the variable: " + formula.charAt(i) + ' ');
	                            pair[k] = new Pair(formula.charAt(i), in.nextFloat());
	                            j++;
	                            result[j] = pair[k].second + "";
	                            k++;
	                            nul = false;
	                            System.out.println();
	                        }catch(Exception e){
	                            System.out.println(e);
	                        }
	                    }
	                    else {
	                        if(formula.charAt(i) != ' '){
	                            j++;
	                            result[j] = tmp;
	                        }
	                    }
	                }
	                else {
	                    if(formula.charAt(i) != ' ') {
	                        j++;
	                        result[j] = formula.charAt(i) + "";
	                        first = true;
	                    }
	                }
	        }
	        return result;
	    }
	 
	 public static void operations(Stack<Float> stack, char op) {
	        if (!stack.empty()) {
	            float a = stack.pop(), b = stack.pop();
	            switch (op) {
	                case '+' -> stack.push(a + b);
	                case '-' -> stack.push(b - a);
	                case '*' -> stack.push(a * b);
	                case '/' -> stack.push(b / a);
	            }
	        }

	    }
	 
	 public static String find(Pair[] pair, char name){
	        boolean founded = false;
	        int i = 0;
	        while(pair[i] != null & !founded){
	            if(pair[i].first == name)
	                founded = true;
	            i++;
	        }

	        if(founded)
	            return String.valueOf(pair[i - 1].second);
	        else
	            return 'a' + "";
	    }
	 
	public static float decision(String formula) {
        String[] newFormula = newFormulaFormat(formula);
        Stack<Float> numbers = new Stack<>();
        Stack<Character> symbols = new Stack<>();
        char op;

        if(formula.matches("[\\\\(+|[a-zA-Z0-9]*[\\+\\-\\/\\*]?[a-zA-Z0-9]+|\\\\)+]*") && checkBrackets(formula))  {
            for (int i = 0; i< newFormula.length; i++) {
                if (newFormula[i].charAt(0) >= '0' && newFormula[i].charAt(0) <= '9')
                	numbers.push(Float.parseFloat(newFormula[i]));
                else {
                    if (symbols.empty())
                    	symbols.push(newFormula[i].charAt(0));
                    else {
                        switch (newFormula[i].charAt(0)) {
                            case '-', '+' -> {
                                op = symbols.peek();
                                while ((op == '+' || op == '-' || op == '/' || op == '*') && (!numbers.empty() && !symbols.empty())) {
                                    operations(numbers, op);
                                    symbols.pop();
                                    if (!symbols.empty())
                                        op = symbols.peek();
                                }
                                symbols.push(newFormula[i].charAt(0));
                            }
                            case '*', '/' -> {
                                op = symbols.peek();
                                while ((op == '/' || op == '*') && (!numbers.empty() && !numbers.empty())) {
                                    operations(numbers, op);
                                    symbols.pop();
                                    if (!symbols.empty())
                                        op = symbols.peek();
                                }
                                symbols.push(newFormula[i].charAt(0));
                            }
                            case '(' -> symbols.push(newFormula[i].charAt(0));
                            case ')' -> {
                                op = symbols.peek();
                                while (op != '(' && (!numbers.empty() && !symbols.empty())) {
                                    operations(numbers, op);
                                    symbols.pop();
                                    if (!symbols.empty())
                                        op = symbols.peek();
                                }
                                symbols.pop();
                            }
                        }

                    }
                }

                System.out.print(numbers);
                System.out.println(symbols);
            }

            while (!symbols.empty() && !numbers.empty() && numbers.size() > 1) {
                if (symbols.size() > 1) {
                    operations(numbers, symbols.peek());
                    symbols.pop();
                } else {
                    operations(numbers, symbols.pop());
                }
            }
            System.out.println("Calculation result: " + numbers);
        }
        else
        	System.out.println("The formula is not correct!");
     return numbers.peek();
    }
}

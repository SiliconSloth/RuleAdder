package siliconsloth.ruleadder;

import java.security.InvalidParameterException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

public class RuleAdder {
	public static Map<Integer, Facts.PositionalAddResult> results;

    public static final void main(String[] args) {
        try {
            // Load up the knowledge base.
	        KieServices ks = KieServices.Factory.get();
    	    KieContainer kContainer = ks.getKieClasspathContainer();
        	KieSession kSession = kContainer.newKieSession("ksession-rules");

        	Scanner scanner = new Scanner(System.in);
        	System.out.println("Type \"quit\" to exit.");
        	for (int runNo = 0;; runNo++) {
        		int operand1 = readPosInt(scanner, "First operand");
        		if (operand1 < 0) {
        			break;
        		}
        		
        		int operand2 = readPosInt(scanner, "Second operand");
        		if (operand2 < 0) {
        			break;
        		}

        		// Convert the operands to digit sequences, padding the shorter operand with zeros.
        		int position = 0;
        		int temp1 = operand1;
        		int temp2 = operand2;
        		while (temp1 > 0 || temp2 > 0) {
        			kSession.insert(new Facts.InputDigit("A_" + runNo, position, Digit.fromInt(temp1 % 10)));
        			kSession.insert(new Facts.InputDigit("B_" + runNo, position, Digit.fromInt(temp2 % 10)));
        			kSession.insert(new Facts.PositionalAddRequest("A_" + runNo, "B_" + runNo, position));
        			
        			temp1 /= 10;
        			temp2 /= 10;
        			
        			position++;
        		}
        		
        		// Run the rule system. The add results will be stored in results.
        		results = new HashMap<>();
                kSession.fireAllRules();
                
                // Read the PositionalAddResults back into an int.
                int result = 0;
                boolean carry = false;
                for (position = 0; results.containsKey(position); position++) {
                	Facts.PositionalAddResult posRes = results.get(position);
                	result += posRes.getValue().toInt() * Math.pow(10, position);
                	carry = posRes.getCarry(); 
                }
                // Check for a carry on the last digit.
                if (carry) {
                	result += Math.pow(10, position);
                }
                System.out.println(operand1 + " + " + operand2 + " = " + result);
        	}
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }
    
    /**
     * Read a non-negative integer from the scanner.
     * Keeps trying until a valid value is entered.
     * Returns -1 if "quit" is entered. 
     * 
     * @param scanner the scanner to read from
     * @param prompt the input prompt to show
     * @return a non-negative integer, or -1 if "quit" is entered
     */
    private static int readPosInt(Scanner scanner, String prompt) {
    	while (true) {
    		System.out.print(prompt + ": ");
    		String input = scanner.next();
    		if (input.toLowerCase().equals("quit")) {
    			return -1;
    		}
    		
    		try {
    			int value = Integer.parseInt(input);
    			if (value >= 0) {
    				return value;
    			} else {
    	    		System.out.println("Operand cannot be negative.");
    			}
    		} catch (NumberFormatException e) {
    			System.out.println("Invalid integer. Type \"quit\" to exit.");
    		}
    	}
    }
    
    public static enum Digit {
    	ZERO, ONE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE;
    	
    	private static Digit fromInt(int i) {
    		switch (i) {
    		case 0:
    			return ZERO;
    		case 1:
    			return ONE;
    		case 2:
    			return TWO;
    		case 3:
    			return THREE;
    		case 4:
    			return FOUR;
    		case 5:
    			return FIVE;
    		case 6:
    			return SIX;
    		case 7:
    			return SEVEN;
    		case 8:
    			return EIGHT;
    		case 9:
    			return NINE;
    		default:
    			throw new InvalidParameterException("Digit must be 0-9");
    		}
    	}
    	
    	private int toInt() {
    		switch (this) {
    		case ZERO:
    			return 0;
    		case ONE:
    			return 1;
    		case TWO:
    			return 2;
    		case THREE:
    			return 3;
    		case FOUR:
    			return 4;
    		case FIVE:
    			return 5;
    		case SIX:
    			return 6;
    		case SEVEN:
    			return 7;
    		case EIGHT:
    			return 8;
    		case NINE:
    			return 9;
    		default:
    			throw new RuntimeException("Unknown digit");
    		}
    	}
    }
}

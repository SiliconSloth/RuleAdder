package siliconsloth.ruleadder;

import siliconsloth.ruleadder.RuleAdder.Digit;

public class Facts {
	public static class InputDigit {
		private String name;
		private int position;
		private Digit digit;
		
		public InputDigit(String name, int position, Digit digit) {
			super();
			this.name = name;
			this.position = position;
			this.digit = digit;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public int getPosition() {
			return position;
		}

		public void setPosition(int position) {
			this.position = position;
		}

		public Digit getDigit() {
			return digit;
		}

		public void setDigit(Digit digit) {
			this.digit = digit;
		}
	}
	
    public static class PositionalFact {
    	private String name1;
    	private String name2;
    	private int position;
    	
		public PositionalFact(String name1, String name2, int position) {
			this.name1 = name1;
			this.name2 = name2;
			this.position = position;
		}

		public String getName1() {
			return name1;
		}

		public void setName1(String name1) {
			this.name1 = name1;
		}

		public String getName2() {
			return name2;
		}

		public void setName2(String name2) {
			this.name2 = name2;
		}

		public int getPosition() {
			return position;
		}

		public void setPosition(int position) {
			this.position = position;
		}
    }

    public static class PositionalAddRequest extends PositionalFact {
		public PositionalAddRequest(String name1, String name2, int position) {
			super(name1, name2, position);
		}
    }

    public static class PositionalAddResult extends PositionalFact {
    	private Digit value;
    	private boolean carry;

		public PositionalAddResult(String name1, String name2, int position, Digit value, boolean carry) {
			super(name1, name2, position);
			this.value = value;
			this.carry = carry;
		}

		public Digit getValue() {
			return value;
		}

		public void setValue(Digit value) {
			this.value = value;
		}

		public boolean getCarry() {
			return carry;
		}

		public void setCarry(boolean carry) {
			this.carry = carry;
		}
    }
    
    public static class BinaryFact {
    	private Digit operand1;
    	private Digit operand2;
    	
		public BinaryFact(Digit operand1, Digit operand2) {
			this.operand1 = operand1;
			this.operand2 = operand2;
		}

		public Digit getOperand1() {
			return operand1;
		}

		public void setOperand1(Digit operand1) {
			this.operand1 = operand1;
		}

		public Digit getOperand2() {
			return operand2;
		}

		public void setOperand2(Digit operand2) {
			this.operand2 = operand2;
		}
    }
    
    public static class AddRequest extends BinaryFact {
		public AddRequest(Digit operand1, Digit operand2) {
			super(operand1, operand2);
		}
    }
    
    public static class AddResult extends BinaryFact {
    	private Digit result;
    	private boolean carry;
    	
		public AddResult(Digit operand1, Digit operand2, Digit result, boolean carry) {
			super(operand1, operand2);
			this.result = result;
			this.carry = carry;
		}

		public Digit getResult() {
			return result;
		}

		public void setResult(Digit result) {
			this.result = result;
		}

		public boolean isCarry() {
			return carry;
		}

		public void setCarry(boolean carry) {
			this.carry = carry;
		}
    }
    
    public static class UnaryFact {
    	private Digit operand;

		public UnaryFact(Digit operand) {
			super();
			this.operand = operand;
		}

		public Digit getOperand() {
			return operand;
		}

		public void setOperand(Digit operand) {
			this.operand = operand;
		}
    }
    
    public static class SuccessorRequest extends UnaryFact {
		public SuccessorRequest(Digit operand) {
			super(operand);
		}
    }
    
    public static class Successor extends UnaryFact {
    	private Digit successor;

		public Successor(Digit operand, Digit successor) {
			super(operand);
			this.successor = successor;
		}

		public Digit getSuccessor() {
			return successor;
		}

		public void setSuccessor(Digit successor) {
			this.successor = successor;
		}
    }
    
    public static class PredecessorRequest extends UnaryFact {
		public PredecessorRequest(Digit operand) {
			super(operand);
		}
    }
    
    public static class Predecessor extends UnaryFact {
    	private Digit predecessor;

		public Predecessor(Digit operand, Digit predecessor) {
			super(operand);
			this.predecessor = predecessor;
		}

		public Digit getPredecessor() {
			return predecessor;
		}

		public void setPredecessor(Digit predecessor) {
			this.predecessor = predecessor;
		}
    }
}

package Designite.visitors;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.FieldAccess;
import org.eclipse.jdt.core.dom.FieldDeclaration;
import org.eclipse.jdt.core.dom.Assignment;
import org.eclipse.jdt.core.dom.Expression;
import org.eclipse.jdt.core.dom.SimpleName;
import org.eclipse.jdt.core.dom.VariableDeclarationStatement;
import org.eclipse.jdt.core.dom.VariableDeclarationExpression;

public class AssignmentVistor extends ASTVisitor {
	
	private Map<String, Integer> thisFieldsAssigned = new HashMap<>(); //Map of how many times a field is assigned a value
	private Map<String, Integer> fieldsAssigned = new HashMap<>(); //Fields assigned without the this keyword
	
	public boolean visit(Assignment node) {
		Expression expression = node.getLeftHandSide();
		if(expression instanceof FieldAccess) {
			String fieldName = ((FieldAccess) expression).getName().getIdentifier();
			int count = thisFieldsAssigned.containsKey(fieldName) ? thisFieldsAssigned.get(fieldName) : 0;
			thisFieldsAssigned.put(fieldName, count + 1);
		}
		if(expression instanceof SimpleName) {
			String fieldName = ((SimpleName) expression).getIdentifier();
			int count = thisFieldsAssigned.containsKey(fieldName) ? thisFieldsAssigned.get(fieldName) : 0;
			fieldsAssigned.put(fieldName, count + 1);
		}
		return true;
	}
	
	public Map<String, Integer> getThisFieldsAssigned() {
		return thisFieldsAssigned;
	}

	public Map<String, Integer> getFieldsAssigned() {
		return fieldsAssigned;
	}
}

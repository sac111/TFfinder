package Designite.SourceModel;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.TypeDeclaration;

import Designite.InputArgs;

import java.util.ArrayList;
import java.util.List;

public class TypeVisitor extends ASTVisitor{
	private List<SM_Type> types = new ArrayList<SM_Type>();
	private List<TypeDeclaration> typeDeclarationList = new ArrayList<TypeDeclaration>(); // Possibly : this stores list of classes or interfaces (aka typeDeclaration) found
	private CompilationUnit compilationUnit;
	private SM_Type newType;
	private SM_Package pkgObj;
	private InputArgs inputArgs;
	
	public TypeVisitor(CompilationUnit cu, SM_Package pkgObj, InputArgs inputArgs) {
		super();
		this.compilationUnit = cu;
		this.pkgObj = pkgObj;
		this.inputArgs = inputArgs;
	}

	// Possibly : These visit functions traverse/visit the Abstract Syntax Tree and find a specific type of node. 
	// 			  Then do something we the node.
	//			  Here, a new SM_Type object is created when a typeDeclaration is found.
	//			  The typeDeclaration is also saved in a list.
	@Override
	public boolean visit(TypeDeclaration typeDeclaration){
		typeDeclarationList.add(typeDeclaration);
		newType = new SM_Type(typeDeclaration, compilationUnit, pkgObj, inputArgs);
		types.add(newType);
		
		return super.visit(typeDeclaration);
	}
	
	public SM_Type getType() {
		return newType;
	}
	
	public List<SM_Type> getTypeList() {
		return types;
	}
	
	public List<TypeDeclaration> getTypeDeclarationList() {
		return typeDeclarationList;
	}
}

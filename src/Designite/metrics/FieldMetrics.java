package Designite.metrics;

import java.util.ArrayList;
import java.util.List;

import Designite.SourceModel.AccessStates;
import Designite.SourceModel.SM_Field;
import Designite.SourceModel.SM_Method;
import Designite.SourceModel.SM_Type;
import Designite.utils.models.Edge;
import Designite.utils.models.Graph;
import Designite.utils.models.Vertex;

public class FieldMetrics implements MetricExtractor {

	private int nmn;
	private int ncf;
	
	private SM_Field field;
	
	public FieldMetrics(SM_Field field) {
		
		this.field = field;
	}
	
	@Override
	public void extractMetrics() {
		extractNmn();
		extractNcf();
    }

	private void extractNmn() {
        for(SM_Method method : field.getParentType().getMethodList()) {
            if(method.getDirectFieldAccesses().contains(field)) {
                nmn++;
            }
        }
	}

	private void extractNcf() {
		for(SM_Method method : field.getParentType().getMethodList()) {
			if(method.getFieldsAssigned().containsKey(field.getName())) {
				ncf += method.getFieldsAssigned().get(field.getName());
			}
			if(method.getThisFieldsAssigned().containsKey(field.getName())) {
				ncf += method.getThisFieldsAssigned().get(field.getName());
			}
		}
	}

	public int getNmn() {
		return nmn;
	}

	public int getNcf() {
		return ncf;
	}
	
	public SM_Field getField() {
		return field;
	}

}

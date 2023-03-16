package SemanticAnalysis.Table;

import java.util.ArrayList;

public class FuncEntry extends SymbolTableEntry {
	
	public ArrayList<VarEntry> m_params   = new ArrayList<>();
	public String m_memberClass = null;
	
	public FuncEntry(String p_type, String p_name, ArrayList<VarEntry> p_params, SymbolTable p_table, String p_memberClass){
		super("func", p_type, p_name, p_table);
		m_params = p_params;
		m_memberClass = p_memberClass;
	}

	public String toString(){
		String paramsToDisplay = "";
		if(m_params.size() != 0) {
			paramsToDisplay = "(";
			for (var param : m_params) {
				paramsToDisplay += param.m_type;
				if (param.m_dims.size() != 0) {
					for (var dim : param.m_dims) {
						if (dim != null) {
							paramsToDisplay = paramsToDisplay + "[" + dim + "]";
						} else {
							paramsToDisplay = paramsToDisplay + "[]";
						}
					}
				}
				paramsToDisplay += ", ";
			}
			paramsToDisplay = paramsToDisplay.substring(0, paramsToDisplay.length() - 2) + ")";
		}
		String nameToDisplay = m_name;
		if(m_memberClass.length() != 0){
			nameToDisplay = m_memberClass+"::"+m_name;
		}

		return 	String.format("%-12s" , "| " + m_kind) +
				String.format("%-12s" , "| " + nameToDisplay) +
				String.format("%-28s"  , "| " + m_type + paramsToDisplay) +
				"|" + 
				m_subtable;
	}	
}

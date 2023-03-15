package SemanticAnalysis.Table;

import java.util.ArrayList;

public class FuncEntry extends SymbolTableEntry {
	
	public ArrayList<VarEntry> m_params   = new ArrayList<>();
	
	public FuncEntry(String p_type, String p_name, ArrayList<VarEntry> p_params, SymbolTable p_table){
		super("func", p_type, p_name, p_table);
		m_params = p_params;
	}

	public String toString(){
		return 	String.format("%-12s" , "| " + m_kind) +
				String.format("%-12s" , "| " + m_name) + 
				String.format("%-28s"  , "| " + m_type) + 
				"|" + 
				m_subtable;
	}	
}

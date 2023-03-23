package SemanticAnalysis.Table;

import java.util.ArrayList;

public class VarEntry extends SymbolTableEntry {
		
	public VarEntry(String p_kind, String p_type, String p_name, ArrayList<Integer> p_dims){
		super(p_kind, p_type, p_name, null);
		m_dims = p_dims;
	}
		
	public String toString(){
		String dimsToDisplay = "";
		if(m_dims != null && m_dims.size() != 0){
			for(var dim: m_dims){
				if(dim != null){
					dimsToDisplay = dimsToDisplay + "[" + dim + "]";
				}
				else {
					dimsToDisplay = dimsToDisplay + "[]";
				}
			}
		}

		return 	String.format("%-12s" , "| " + m_kind) +
				String.format("%-12s" , "| " + m_name) + 
				String.format("%-12s"  , "| " + m_type+dimsToDisplay) +
		        "|";
	}
	public boolean equalsName(Object obj) {
		VarEntry varEntry = (VarEntry) obj;
		return m_kind.equals(varEntry.m_kind) &&
				m_name.equals(varEntry.m_name);
	}
	public boolean equals(Object obj) {
		try{
			VarEntry varEntry = (VarEntry) obj;
			return m_name.equals(varEntry.m_name) && m_dims.equals(varEntry.m_dims) && m_type.equals(varEntry.m_type);
		}
			catch (Exception e){
			return false;
		}
	}

}

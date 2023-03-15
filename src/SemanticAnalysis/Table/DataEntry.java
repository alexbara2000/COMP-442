package SemanticAnalysis.Table;

import java.util.ArrayList;

public class DataEntry extends SymbolTableEntry {
	public String m_visibility;
	public DataEntry(String p_kind, String p_type, String p_name, ArrayList<Integer> p_dims, String p_visibility){
		super(p_kind, p_type, p_name, null);
		m_dims = p_dims;
		this.m_visibility = p_visibility;
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
				String.format("%-12s"  , "| " + m_visibility) +
//				String.format("%-8s"  , "| " + m_size) +
//				String.format("%-8s"  , "| " + m_offset) +
		        "|";
	}
}

package Common.Table;

public class TempVarEntry extends SymbolTableEntry {

	static int currentNum = 1;

	public TempVarEntry(String p_type){
		super("TempVar", p_type, "temp"+currentNum, null);
		currentNum++;
		if(p_type.equals("integer")){
			m_size = 4;
		}
		else{
			m_size = 8;
		}
	}
		
	public String toString(){
		return 	String.format("%-12s" , "| " + m_kind) +
				String.format("%-12s" , "| " + m_name) +
				String.format("%-12s" , "| " + m_type) +
				String.format("%-12s"  , "| " + 4) +
		        "|";
	}
	public boolean equalsName(Object obj) {
		TempVarEntry varEntry = (TempVarEntry) obj;
		return m_kind.equals(varEntry.m_kind) &&
				m_name.equals(varEntry.m_name);
	}
	public boolean equals(Object obj) {
		try{
			TempVarEntry varEntry = (TempVarEntry) obj;
			return m_name.equals(varEntry.m_name) && m_dims.equals(varEntry.m_dims) && m_type.equals(varEntry.m_type);
		}
			catch (Exception e){
			return false;
		}
	}

}

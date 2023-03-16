package SemanticAnalysis.Table;

public class ClassEntry extends SymbolTableEntry {

	public ClassEntry(String p_name, SymbolTable p_subtable){
		super(new String("class"), p_name, p_name, p_subtable);
	}
		
	public String toString(){
		return 	String.format("%-12s" , "| " + m_kind) +
				String.format("%-40s" , "| " + m_name) + 
				"|" + 
				m_subtable;
	}

	@Override
	public boolean equals(Object obj) {
		try{
			ClassEntry classEntry = (ClassEntry)obj;
			return m_name.equals(classEntry.m_name);
		}
		catch (Exception e){
			return false;
		}

	}
}


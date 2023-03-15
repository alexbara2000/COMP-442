package SemanticAnalysis.Table;

import SemanticAnalysis.Table.SymbolTable;
import SemanticAnalysis.Table.SymbolTableEntry;

import java.util.ArrayList;

public class InheritEntry extends SymbolTableEntry {

    ArrayList<String> m_inherList;

    public InheritEntry(ArrayList<String> p_inherList,  SymbolTable p_subtable){
        super("inheritedList", "inherit" , "inherit", p_subtable);
        m_inherList = p_inherList;
    }

    public String toString(){
        if(m_inherList.size() == 0){
            return  "| inherit   | none";
        }
        else{
            return "| inherit  |  " + m_inherList;
        }
    }

}
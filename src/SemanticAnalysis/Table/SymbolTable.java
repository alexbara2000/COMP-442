package SemanticAnalysis.Table;

import java.util.ArrayList;

public class SymbolTable {
    public String m_name= null;
    public ArrayList<SymbolTableEntry> m_symlist = null;
    public int m_size= 0;
    public int m_tablelevel = 0;
    public SymbolTable m_uppertable = null;


    public SymbolTable(int p_level, SymbolTable p_uppertable){
        m_tablelevel = p_level;
        m_name = null;
        m_symlist = new ArrayList<>();
        m_uppertable = p_uppertable;
    }

    public SymbolTable(int p_level, String p_name, SymbolTable p_uppertable){
        m_tablelevel = p_level;
        m_name = p_name;
        m_symlist = new ArrayList<SymbolTableEntry>();
        m_uppertable = p_uppertable;
    }

    public void addEntry(SymbolTableEntry p_entry){
        m_symlist.add(p_entry);
    }

    public SymbolTableEntry lookupName(String p_tolookup) {
        SymbolTableEntry returnvalue = new SymbolTableEntry();
        boolean found = false;
        for( SymbolTableEntry rec : m_symlist) {
            if (p_tolookup.equals(rec.m_name)) {
                returnvalue = rec;
                found = true;
            }
        }
        if (!found) {
            if (m_uppertable != null) {
                returnvalue = m_uppertable.lookupName(p_tolookup);
            }
        }
        return returnvalue;
    }
    public boolean lookupNameReturnsBool(String p_tolookup) {
        SymbolTableEntry returnvalue = new SymbolTableEntry();
        boolean found = false;
        for( SymbolTableEntry rec : m_symlist) {
            if (p_tolookup.equals(rec.m_name)) {
                returnvalue = rec;
                found = true;
            }
        }
        if (!found) {
            if (m_uppertable != null) {
                returnvalue = m_uppertable.lookupName(p_tolookup);
            }
        }
        return found;
    }

    public String toString(){
        StringBuilder stringtoreturn = new StringBuilder();
        String prelinespacing = "";
        for (int i = 0; i < this.m_tablelevel; i++)
            prelinespacing += "|    ";
        stringtoreturn.append("\n").append(prelinespacing).append("=====================================================\n");
        stringtoreturn.append(prelinespacing).append(String.format("%-25s", "| table: " + m_name)).append("\n");//append(String.format("%-27s", " scope offset: " + m_size)).append("|\n");
        stringtoreturn.append(prelinespacing).append("=====================================================\n");
        for (SymbolTableEntry SymbolTableEntry : m_symlist) {
            stringtoreturn.append(prelinespacing).append(SymbolTableEntry.toString()).append('\n');
        }
        stringtoreturn.append(prelinespacing).append("=====================================================");
        return stringtoreturn.toString();
    }


    public boolean lookupLocalEntry(SymbolTableEntry newEntry){
        for(var entry: m_symlist){
            if(entry.equals(newEntry))
                return true;
        }
        return false;
    }
    public boolean lookupInheritedEntry(SymbolTableEntry newEntry){
        for(var entry: m_symlist){
            if(entry.equals(newEntry))
                return true;
        }
        if(m_uppertable == null || m_uppertable.m_symlist == null || m_uppertable.m_symlist.size() == 0){
            return false;
        }
        else{
            return m_uppertable.lookupInheritedEntry(newEntry);
        }

    }
    public boolean lookupLocalEntryName(SymbolTableEntry newEntry){
        for(var entry: m_symlist){
            if(entry.equalsName(newEntry))
                return true;
        }
        return false;
    }


    public boolean hasSameParams(FuncEntry funcEntry){
        for(var entry: m_symlist){
            if(entry instanceof FuncEntry){
                FuncEntry actualEntry = (FuncEntry) entry;
                if(actualEntry.sameParams(funcEntry)){
                    return true;
                }
            }
        }
        return false;
    }

    public ArrayList<FuncEntry> isFuncCallReturnTables(String id) {
        ArrayList<FuncEntry> funcEntries = new ArrayList<>();
        for(var entries: m_symlist){
            if(entries instanceof FuncEntry && entries.m_name.equals(id)){
                funcEntries.add((FuncEntry)entries);
            }
        }
        return  funcEntries;
    }

    public boolean isDataMember(String id){
        boolean found = false;
        for( SymbolTableEntry rec : m_symlist) {
            if(rec.m_subtable != null){
                found = rec.m_subtable.isDataMember(id);
                if(found){
                    return true;
                }
            }
            if (id.equals(rec.m_name) && rec.m_kind.equals("data")) {
                return true;
            }
        }
        return found;
    }

    public boolean idExists(String id){
        String rep = this.toString();
        if(!rep.contains(id))
            return false;
        for( SymbolTableEntry rec : m_symlist) {
            if(rec.m_subtable != null){
                if(rec.m_subtable.idExists(id)){
                    return true;
                }
            }
            if (id.equals(rec.m_name) || id.equals("build") || id.equals(rec.m_kind) || id.equals(rec.m_type) || rec.m_kind.equals("data")) {
                return true;
            }
        }
        return false;
    }

    public SymbolTable getTableEntry(FuncEntry newFuncEntry) {
        for(var entry: m_symlist){
            if(entry instanceof FuncEntry){
                var fEntry = (FuncEntry) entry;
                if(fEntry.m_params.equals(newFuncEntry.m_params) && fEntry.m_name.equals(newFuncEntry.m_name) && fEntry.m_memberClass.equals(newFuncEntry.m_memberClass)){
                    return fEntry.m_subtable;
                }
            }

        }
        return null;
    }

    public ArrayList<String> getInheritanceList(String className) {
        for(var entry: m_symlist){
            if(entry instanceof ClassEntry){
                ClassEntry cEntry = (ClassEntry) entry;
                if(cEntry.m_name.equals(className)){
                    return ((InheritEntry)cEntry.m_subtable.m_symlist.get(0)).m_inherList;
                }
            }
        }
        return null;
    }
}

package Common.Table;

import java.util.ArrayList;

public class MemberConstructorEntry extends FuncEntry{
    public String m_visibility;
    public MemberConstructorEntry(String p_kind, String p_type, String p_name, ArrayList<VarEntry> p_params, String p_visibility, SymbolTable p_table){
        super("Constructor",p_type, p_name, p_params, p_table, "");
        this.m_visibility = p_visibility;
        this.m_kind = p_kind;
    }
    public String toString(){
        //Todo fix params type
        String paramsToDisplay = "";
        if(m_params.size() != 0){
            paramsToDisplay = "(";
            for(var param: m_params){
                paramsToDisplay += param.m_type;
                if(param.m_dims.size() != 0){
                    for(var dim: param.m_dims){
                        if(dim != null){
                            paramsToDisplay = paramsToDisplay + "[" + dim + "]";
                        }
                        else {
                            paramsToDisplay = paramsToDisplay + "[]";
                        }
                    }
                }
                paramsToDisplay += ", ";
            }
            paramsToDisplay = paramsToDisplay.substring(0, paramsToDisplay.length()-2)+")";
        }


        return "| " + m_kind + "  | " + m_name + "    | " + paramsToDisplay + ": " + m_type + "  | " + m_visibility
                + "| "+m_subtable;
    }
    /** Tests if two memberFuncs are equal
     *  Returns -1 if they have the diff name
     *  Returns -2 if they have the same reference ( is not supposed to happen)
     *  Returns 0 if they have same name AND same params
     *  Returns 1 if they have same name but different params
     */
    public int equal(Object otherObj) {
        if (this == otherObj) {
            return -2;
        }
        if (otherObj instanceof MemberConstructorEntry) {
            MemberConstructorEntry entry = (MemberConstructorEntry) otherObj;
            //two member funcs are the same if they have the same name and same params
            if (entry.m_name.equals(this.m_name)) {
                boolean sameParams = true;
                if (entry.m_params.size() == this.m_params.size()) {
                    sameParams = true;
                    for (int i = 0; i < entry.m_params.size(); i++) {
                        if (!entry.m_params.get(i).equals(this.m_params.get(i))) {
                            sameParams = false;
                        }
                    }
                    if(sameParams)return 0;
                }
                return 1;
            }
        }
        return -1;
    }
}
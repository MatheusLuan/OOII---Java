
package view;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
import model.Cliente;

public class ClienteTableModel extends AbstractTableModel {
   
    public static final int COL_ID = 0;
    public static final int COL_NOME = 1;
    public static final int COL_EMAIL = 2;
    public static final int COL_TELEFONE = 3;
    public static final int COL_DOCUMENTO = 4; 
    
    public ArrayList<Cliente> listaClientes;
    
    public ClienteTableModel(ArrayList<Cliente> alCli){
        listaClientes = new ArrayList<Cliente>(alCli);
    }

    @Override
    public int getRowCount() {
       return listaClientes.size();
    }

    @Override
    public int getColumnCount() {
         return 5;
    }
    
    @Override
    public Object getValueAt(int linhas, int colunas) {
        Cliente cliente = listaClientes.get(linhas);
       if(colunas == COL_ID){return cliente.getId();}
       if(colunas == COL_NOME){return cliente.getNome();}
       if(colunas == COL_EMAIL){return cliente.getEmail();}
       if(colunas == COL_TELEFONE){return cliente.getTelefone();}
       if(colunas == COL_DOCUMENTO){return cliente.getDocumento();}
    
        return "";
    }
    
    @Override
    public String getColumnName(int colunas) {
         
       if(colunas == COL_ID){return "Código";}
       if(colunas == COL_NOME){return "Nome";}
       if(colunas == COL_EMAIL){return "Email";}
       if(colunas == COL_TELEFONE){return "Telefone";}
       if(colunas == COL_DOCUMENTO){return "Documento";}
    
        return "";
    }   
    

    
    
}

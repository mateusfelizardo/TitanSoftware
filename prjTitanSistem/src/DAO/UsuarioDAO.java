package DAO;

import DTO.UsuarioDTO;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;

public class UsuarioDAO {
    Connection conn; 
    
    public ResultSet autenticacaoUsuario(UsuarioDTO usuarioDTO){
        conn = new ConexaoDAO().conectaBD();
        
        try {
            String sql = "SELECT * FROM tbl_usuario where usuario = ? AND senha = ?";
            
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, usuarioDTO.getUsuario());
            pstm.setString(2, usuarioDTO.getSenha());
            
            ResultSet rs = pstm.executeQuery();
            
            return rs;
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"UsuarioDAO"+e);
            
            return null;
        }
    }
}

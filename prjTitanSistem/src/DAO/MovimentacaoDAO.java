package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import DTO.MovimentacaoDTO;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class MovimentacaoDAO {
    Connection conn;
    PreparedStatement pstm;
    ResultSet rs;
    ArrayList<MovimentacaoDTO> lista = new ArrayList<>();
    
    public void cadastrarMovimentacao(MovimentacaoDTO movimentacaoDTO){
        String sql = "INSERT INTO tbl_movimentacao (placa,modelo,data_entrada,data_saida,tempo,valor_pago)"
                + " VALUES(?,?,?,?,?,?);";
        
        conn = new ConexaoDAO().conectaBD();
        
        try {
            
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, movimentacaoDTO.getPlaca());
            pstm.setString(2, movimentacaoDTO.getModelo());
            pstm.setDate(3, movimentacaoDTO.getData_entrada());
            pstm.setDate(4, movimentacaoDTO.getData_saida());
            pstm.setInt(5, movimentacaoDTO.getTempo());
            pstm.setFloat(6, movimentacaoDTO.getValor_pago());
            
            pstm.execute();
            pstm.close(); 
            
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"MovimentacaoDAO" + e);
        }
    }
    public void editarMovimentacao(MovimentacaoDTO movimentacaoDTO){
        String sql = "UPDATE tbl_movimentacao SET "
                + "placa = ?,"
                + "modelo = ?,"
                + "data_entrada = ?,"
                + "data_saida = ?,"
                + "tempo = ?,"
                + "valor_pago = ?"
                + "WHERE id = ?";
        
        conn = new ConexaoDAO().conectaBD();
        
        try {
            
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, movimentacaoDTO.getPlaca());
            pstm.setString(2, movimentacaoDTO.getModelo());
            pstm.setDate(3, movimentacaoDTO.getData_entrada());
            pstm.setDate(4, movimentacaoDTO.getData_saida());
            pstm.setInt(5, movimentacaoDTO.getTempo());
            pstm.setFloat(6, movimentacaoDTO.getValor_pago());
            pstm.setInt(7, movimentacaoDTO.getId());
            
            pstm.execute();
            pstm.close(); 
            
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"MovimentacaoDAO ALteração" + e);
        }
    }
    
    public ArrayList<MovimentacaoDTO> pesquisarMovimentacao(boolean isAtiva){
        String sql;
        if(isAtiva){
            sql = "SELECT * FROM tbl_movimentacao where data_saida = '1111-11-11' ";
        }else{
            sql = "SELECT * FROM tbl_movimentacao where data_saida != '1111-11-11' ";
        }
        conn = new ConexaoDAO().conectaBD();
        try {
            pstm = conn.prepareStatement(sql);
            rs = pstm.executeQuery();
            
            while(rs.next()){
                MovimentacaoDTO movimentacaoDTO = new MovimentacaoDTO();
                movimentacaoDTO.setId(rs.getInt("id"));
                movimentacaoDTO.setPlaca(rs.getString("placa"));
                movimentacaoDTO.setModelo(rs.getString("modelo"));
                movimentacaoDTO.setData_entrada(rs.getDate("data_entrada"));
                movimentacaoDTO.setData_saida(rs.getDate("data_saida"));
                movimentacaoDTO.setTempo(rs.getInt("tempo"));
                movimentacaoDTO.setValor_pago(rs.getFloat("valor_pago"));
                
                lista.add(movimentacaoDTO);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"MovimentacaoDaO"+ e);
        }
        return lista;
    }
    
}

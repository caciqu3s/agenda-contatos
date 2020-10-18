package com.unip.agenda.dao;

import com.unip.agenda.ConnectionFactory;
import com.unip.agenda.model.Contato;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ContatoDAO {

    private Connection conn;

    public void save(Contato contato) {
        String sql = "INSERT INTO contatos(nome, idade, dataCadastro) VALUES(?, ?, ?)";

        try {
            this.conn = ConnectionFactory.createConnection();
            PreparedStatement pstm = conn.prepareStatement(sql);

            pstm.setString(1, contato.getNome());
            pstm.setInt(2, contato.getIdade());
            pstm.setDate(3, new Date(contato.getDataCadastro().getTime()));
            pstm.execute();

            if (pstm != null) {
                pstm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    public void delete(int id) {
        String sql = "DELETE FROM contatos WHERE id = ?";

        try {
            this.conn = ConnectionFactory.createConnection();
            PreparedStatement pstm = conn.prepareStatement(sql);

            pstm.setInt(1, id);
            pstm.execute();

            if (pstm != null) {
                pstm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }

        catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void update(Contato contato) {
        String sql = "UPDATE contatos SET nome = ?, idade = ?, dataCadastro = ? WHERE id = ?";

        try {
            this.conn = ConnectionFactory.createConnection();
            PreparedStatement pstm = conn.prepareStatement(sql);

            pstm.setString(1, contato.getNome());
            pstm.setInt(2, contato.getIdade());
            pstm.setDate(3, new Date(contato.getDataCadastro().getTime()));
            pstm.setInt(4, contato.getId());
            pstm.execute();

            if (pstm != null) {
                pstm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    public List<Contato> getContatos() {
        String sql = "SELECT * from contatos ";

        List<Contato> contatos = new ArrayList<Contato>();

        try {
            this.conn = ConnectionFactory.createConnection();
            PreparedStatement pstm = conn.prepareStatement(sql);
            ResultSet rset = pstm.executeQuery();

            while(rset.next()) {
                contatos.add(new Contato(
                        rset.getInt("id"), rset.getString("nome"), rset.getInt("idade"), rset.getDate("dataCadastro")
                ));
            }

            if(rset != null) {
                rset.close();
            }

            if (pstm != null) {
                pstm.close();
            }
            if (conn != null) {
                conn.close();
            }

        }
        catch(Exception e) {
            e.printStackTrace();
        }

        return contatos;
    }

    public Contato getContatoById(int id) {
        String sql = "SELECT * FROM contatos WHERE id = ?";

        Contato contato = null;

        try {
            this.conn = ConnectionFactory.createConnection();
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setInt(1, id);
            ResultSet rset = pstm.executeQuery();

            while (rset.next()) {
                contato = new Contato(
                        rset.getInt("id"), rset.getString("nome"), rset.getInt("idade"), rset.getDate("dataCadastro")
                );
            }

            if (contato == null) {
                System.out.println("Esse contato não existe");
            }

            if (rset != null) {
                rset.close();
            }

            if (pstm != null) {
                pstm.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return contato;
    }
}

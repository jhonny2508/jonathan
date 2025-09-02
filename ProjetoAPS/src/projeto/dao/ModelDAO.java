package projeto.dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.jfree.chart.demo.PieChartDemo1;

import com.mysql.cj.x.protobuf.MysqlxSql.StmtExecute;

import projeto.factory.ConnectionFactory;
import projeto.model.Model;

public class ModelDAO {
public void save(Model contato) {
	String sql = "INSERT INTO contatos (mes, qtddesperdicio, ano) VALUES(?,?,?)";
	Connection conn = null;
	PreparedStatement pstm = null;
	try {
		conn = ConnectionFactory.createConnectionToMySQL();
		pstm = (PreparedStatement) conn.prepareStatement(sql);
		pstm.setString(1, contato.getMes());
		pstm.setInt(2, contato.getQtddesperdicio());
		pstm.setInt(3,contato.getAno() );
		
		pstm.execute();
	}catch (Exception e) {
		e.printStackTrace();
		
	}finally {
		try {
			if (pstm!=null){
				pstm.close();
			}
			if(conn!=null) {
				conn.close();
			}
			} catch (Exception e) {
				
			}
		}
	}
	
public void update (Model contato) {
	String sql = "UPDATE contatos SET mes = ?,qtddesperdicio =?, ano = ? " +
			"WHERE id = ? ";
	Connection conn = null;
	PreparedStatement pstm = null;
	try {
		conn = ConnectionFactory.createConnectionToMySQL();
		pstm = (PreparedStatement) conn.prepareStatement(sql);
		pstm.setString(1, contato.getMes());
		pstm.setInt(2, contato.getQtddesperdicio());
		pstm.setInt(3, contato.getAno());
		pstm.setInt(4, contato.getId());
		pstm.execute();
		
	}catch (Exception e) {
		e.printStackTrace();
	}finally {
		try {
			if (pstm!= null) {
				pstm.close();
				
			} if (conn!=null) {
				conn.close();
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}

public void deleteByID (int id) {
	String sql = "DELETE FROM contatos WHERE id = ?";
	Connection conn = null;
	PreparedStatement pstm = null;
	try {
		conn = ConnectionFactory.createConnectionToMySQL();
		pstm = (PreparedStatement) conn.prepareStatement(sql);
		pstm.setInt(1, id);
		pstm.execute();
	}catch (Exception e) {
		e.printStackTrace();
	}finally {
		try {
			if (pstm !=null) {
				pstm.close();
			}
			if (conn != null) {
				pstm.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}





public List<Model> getContatos(){
	String sql = "SELECT * FROM contatos";
	
	List<Model> contatos = new ArrayList<>();
	Connection conn = null;
	PreparedStatement pstm = null;
	ResultSet rset = null;
	try {
		conn = ConnectionFactory.createConnectionToMySQL();
		pstm = (PreparedStatement) conn.prepareStatement(sql);
		
		rset= pstm.executeQuery();
		while (rset.next()) {
			
			Model contato = new Model();
			
			contato.setId(rset.getInt("id"));
			contato.setMes(rset.getString("mes"));
			contato.setQtddesperdicio(rset.getInt("qtddesperdicio"));
			contato.setAno(rset.getInt("ano"));
			contatos.add(contato);
			
		}
		}
		catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
			if(rset != null) {
				rset.close();
			}
			if (pstm != null) {
				pstm.close();
			}
			if(conn!=null) {
			conn.close();
		}
	}catch (Exception e) {
		e.printStackTrace();
	}
	}
		return contatos;


}
/*
public void showData (Contato contato) {
	String sql = "select mes,ano,qtddesperdicio from contatos";
	Connection conn = null;
	PreparedStatement pstm = null;
	ResultSet rset = null;
	
	try {
		conn = ConnectionFactory.createConnectionToMySQL();
		pstm = (PreparedStatement) conn.prepareStatement(sql);
		
		rset= pstm.executeQuery();
	
	}catch (Exception e) {
		e.printStackTrace();
	}finally {
		try {
		if(rset != null) {
			rset.close();
		}
		if (pstm != null) {
			pstm.close();
		}
		if(conn!=null) {
		conn.close();
	}
}catch (Exception e) {
	e.printStackTrace();
}
}
	*/
	
public List<Model> getValues(){
	String sql = "SELECT * FROM contatos";
	
	List<Model> contatos = new ArrayList<>();
	Connection conn = null;
	PreparedStatement pstm = null;
	ResultSet rset = null;
	try {
		conn = ConnectionFactory.createConnectionToMySQL();
		pstm = (PreparedStatement) conn.prepareStatement(sql);
		
		rset= pstm.executeQuery();
		while (rset.next()) {
			
			Model contato = new Model();
			
			
			contato.setMes(rset.getString("mes"));
			contato.setQtddesperdicio(rset.getInt("qtddesperdicio"));
			contato.setAno(rset.getInt("ano"));
			contatos.add(contato);
			
		}
		}
		catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
			if(rset != null) {
				rset.close();
			}
			if (pstm != null) {
				pstm.close();
			}
			if(conn!=null) {
			conn.close();
		}
	}catch (Exception e) {
		e.printStackTrace();
	}
	}
		return contatos;

	
}





}


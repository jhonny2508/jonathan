package projeto.main;


import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

import projeto.dao.ModelDAO;
import projeto.model.Model;

public class Main {

	public static void main(String[] args) {
		
		Model contato = new Model();
		
		contato.setMes("João Gabriel99");
		contato.setQtddesperdicio(78);
		contato.setAno(2024);
		contato.setId(7);
		ModelDAO contatoDao = new ModelDAO();
	
		//contatoDao.save(contato);
		
		Model c1 = new Model();
		c1.setMes("João Gabriel nardinho43");
		c1.setQtddesperdicio(9);
		c1.setAno(2024);
		c1.setId(7);
		//contatoDao.update(c1);
		
	//	contatoDao.deleteByID(8);
		
	/*	for(Contato c : contatoDao.getContatos()) {
		System.out.println("contato: " + c.getNome());

			
		}
		*/
		Scanner reader = new Scanner(System.in);
		System.out.println("Digite o numero correspondente a o quer fazer ");
		System.out.println("1: Para adicionar um novo valor ao banco de dados;");
		System.out.println("2: Para alterar um valor no banco de dados;");
		System.out.println("3: Para deletar um valor no banco de dados;");
		System.out.println("4: Para exibir os valores do banco de dado;");
		System.out.println("5: Para exibir o grafico");
		int n = reader.nextInt();
		switch (n) {
		case 1: 
			
			System.out.println("Digite o mes que vai adicionar ao banco de dados");
			String mes = reader.next();
			System.out.println("Digite quantidade, em toneladas, de desperdicio que vai adicionar ao banco de dados");
			int desperdicio = reader.nextInt();
			System.out.println("Digite o ano que vai adicionar ao banco de dados");
			int ano = reader.nextInt();
			
			contato.setMes(mes);
			contato.setQtddesperdicio(desperdicio);
			contato.setAno(ano);
			
			contatoDao.save(contato);
			break;
		case 2:
			System.out.println("Digite o ID da coluna que vai alterar");
			int id2 = reader.nextInt();
			System.out.println("Digite o novo mes");
			String mes2 = reader.next();
			System.out.println("Digite quantidade, em toneladas, a nova quantidade");
			int desperdicio2 = reader.nextInt();
			System.out.println("Digite o novo ano");
			int ano2 = reader.nextInt();
			
			
			c1.setMes(mes2);
			c1.setQtddesperdicio(desperdicio2);
			c1.setAno(ano2);
			c1.setId(id2);
			
			
			
			contatoDao.update(c1);
			break;
		case 3:
			System.out.println("Digite o ID da coluna que vai deletar");
			int id3 = reader.nextInt();
			contatoDao.deleteByID(id3);
			break;
		case 4: 
			for(Model c : contatoDao.getContatos()) {
			
			System.out.println("ID: " + c.getId());
			System.out.println("Ano: " + c.getAno());
			System.out.println("Mes: " + c.getMes());
			System.out.println("Quantidade de desperdicio: " + c.getQtddesperdicio());
			}
		break;
		
		case 5:
			
			Scanner reader1 = new Scanner(System.in);
        System.out.println("Digite o primeiro ano, apartir de 2018, desejado:");
        int escolhaano1 = reader1.nextInt();
        System.out.println("Digite o segundo ano, apartir de 2018,  desejado:");
        int escolhaano2 = reader1.nextInt();

       
        String url = "jdbc:mysql://localhost:3306/agenda";
        String user = "root";
        String password = "root";

        DefaultCategoryDataset dadosLinha = new DefaultCategoryDataset();

        Map<String, Double> dadosAno1 = new HashMap<>();
        Map<String, Double> dadosAno2 = new HashMap<>();

        try (Connection connection = DriverManager.getConnection(url, user, password);
             Statement statement = connection.createStatement()) {

         
            String query = "SELECT ano, mes, qtddesperdicio FROM contatos WHERE ano = " + escolhaano1 + " OR ano = " + escolhaano2;
            ResultSet resultSet = statement.executeQuery(query);

           
            while (resultSet.next()) {
                int ano5 = resultSet.getInt("ano");
                String mes5 = resultSet.getString("mes");
                double valor = resultSet.getDouble("qtddesperdicio");

                if (ano5 == escolhaano1) {
                    dadosAno1.put(mes5, valor);
                } else if (ano5 == escolhaano2) {
                    dadosAno2.put(mes5, valor);
                }
            }

           
            for (String mes5 : dadosAno1.keySet()) {
                if (dadosAno2.containsKey(mes5)) {
                    double valorAno1 = dadosAno1.get(mes5);
                    double valorAno2 = dadosAno2.get(mes5);
                    double diferenca = Math.abs (valorAno1 - valorAno2); 
                    dadosLinha.addValue(valorAno1, String.valueOf(escolhaano1) , mes5);
                    dadosLinha.addValue(valorAno2, String.valueOf(escolhaano2), mes5);
                    dadosLinha.addValue(diferenca, "Diferença", mes5);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


        JFreeChart graficoBarra = ChartFactory.createBarChart(
                "Desperdício de material da empresa",
                "Meses", "Qtd em toneladas", dadosLinha);

        ChartPanel painelGraficoBarra = new ChartPanel(graficoBarra);

    
        JFrame telaGrafico = new JFrame();
        telaGrafico.setLayout(new GridLayout());
        telaGrafico.getContentPane().add(painelGraficoBarra, BorderLayout.CENTER);
        telaGrafico.pack();
        telaGrafico.setSize(800, 600);
        telaGrafico.setVisible(true);
		
			
	
			break;
		default: 
			System.out.println("Número invalido");
			break;
			
		
		
		
		
		}
		
		
		
		
		
		

}

}

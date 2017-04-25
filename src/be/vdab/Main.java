package be.vdab;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
	private static final String URL = "jdbc:mysql://localhost/bank?useSSL=false";
	private static final String USER = "cursist";
	private static final String PASSWORD = "cursist";
	private static final String UPDATE_SQL = "insert into bank (RekeningNr) values(?)";
	private static final String UPDATE_SQL2 = "select saldo from bank where RekeningNummer = ?";

	public static void main(String[] args) {

		System.out.println("Welkom bij Bank Bart!");
		System.out.println("Maak uw keuze: ");
		System.out.println("1. Nieuwe rekening");
		System.out.println("2. Saldo consulteren");
		System.out.println("3. Overschrijven");
		Scanner sc = new Scanner(System.in);
		int keuze = sc.nextInt();
		switch (keuze) {
		case 1:
			try (Scanner scan = new Scanner(System.in)) {
				System.out.println("Geef het rekeningnummer op: ");
				String reknr = scan.nextLine();
				long l = Long.parseLong(reknr);
				Rekening rekening = new Rekening(l);
				try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
						PreparedStatement statement = connection.prepareStatement(UPDATE_SQL)) {
					statement.setLong(1, l);
				}
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
			break;
		case 2:
			try (Scanner scan = new Scanner(System.in)) {
				System.out.println("Geef het rekeningnummer op: ");
				String reknr = scan.nextLine();
				try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
						PreparedStatement statement = connection.prepareStatement(UPDATE_SQL2)) {
					statement.setString(1, reknr);
					try (ResultSet resultSet = statement.executeQuery()) {
						while (resultSet.next()) {
							System.out.println(resultSet.getBigDecimal("saldo"));
						}
					}
				}
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
			break;
		case 3:
			System.out.println("Overschrijven");
			break;

		}
	}

}
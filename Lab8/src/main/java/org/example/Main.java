package org.example;

import java.sql.*;
import java.util.Scanner;

public class Main {
    private static final String URL = "jdbc:mysql://localhost:3306/lab8";
    private static final String USER = "root";
    private static final String PASSWORD = "password";

    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            System.out.println("Conectat la baza de date!");
            Scanner scanner = new Scanner(System.in);
            boolean ruleaza=true;
            while (ruleaza) {
                System.out.println("\nMeniu:");
                System.out.println("1. Adăugare persoană");
                System.out.println("2. Adăugare excursie");
                System.out.println("3. Afișare persoane și excursii");
                System.out.println("4. Afișare excursii pentru o persoană");
                System.out.println("5. Afișare persoane care au vizitat o destinație");
                System.out.println("6. Afișare persoane care au făcut excursii într-un an");
                System.out.println("7. Ștergere excursie");
                System.out.println("8. Ștergere persoană");
                System.out.println("0. Ieșire");
                System.out.print("Alege opțiunea: ");

                int optiune = scanner.nextInt();
                scanner.nextLine();
                switch (optiune) {
                    case 1:
                        adaugaPersoana(connection, scanner);
                        break;
                    case 2:
                        adaugaExcursie(connection, scanner);
                        break;
                    case 3:
                        afisarePersoaneSiExcursii(connection);
                        break;
                    case 4:
                        afisareExcursiiPentruPersoana(connection, scanner);
                        break;
                    case 5:
                        afisarePersoanePentruDestinatie(connection, scanner);
                        break;
                    case 6:
                        afisarePersoanePentruAn(connection, scanner);
                        break;
                    case 7:
                        stergereExcursie(connection, scanner);
                        break;
                    case 8:
                        stergerePersoana(connection, scanner);
                        break;
                    case 0:
                        ruleaza=false;
                        System.out.println("Iesire din program...");
                        return;
                    default:
                        System.out.println("Opțiune invalidă! Introduceti o optiune valida!");

                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void adaugaPersoana(Connection connection, Scanner scanner) throws SQLException {
        System.out.print("Introduceți numele: ");
        String nume = scanner.nextLine();
        System.out.print("Introduceți vârsta: ");
        int varsta = scanner.nextInt();
        String sql = "INSERT INTO persoane (nume, varsta) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, nume);
            stmt.setInt(2, varsta);
            stmt.executeUpdate();
            System.out.println("Persoană adăugată cu succes!");
        }
    }

    private static void adaugaExcursie(Connection connection, Scanner scanner) throws SQLException {
        System.out.print("Introduceți ID-ul persoanei: ");
        int idPersoana = scanner.nextInt();
        scanner.nextLine();
        String verificaPersoana = "SELECT * FROM persoane WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(verificaPersoana)) {
            stmt.setInt(1, idPersoana);
            ResultSet rs = stmt.executeQuery();
            if (!rs.next()) {
                System.out.println("Persoana nu există în baza de date!");
                return;
            }
        }
        System.out.print("Introduceți destinația: ");
        String destinatia = scanner.nextLine();
        System.out.print("Introduceți anul: ");
        int anul = scanner.nextInt();
        String sql = "INSERT INTO excursii (id_persoana, destinatia, anul) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idPersoana);
            stmt.setString(2, destinatia);
            stmt.setInt(3, anul);
            stmt.executeUpdate();
            System.out.println("Excursie adăugată cu succes!");
        }
    }

    private static void afisarePersoaneSiExcursii(Connection connection) throws SQLException {
        String sql = "SELECT p.id, p.nume, p.varsta, e.destinatia, e.anul " +
                "FROM persoane p LEFT JOIN excursii e ON p.id = e.id_persoana";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                System.out.printf("ID: %d, Nume: %s, Vârstă: %d, Destinație: %s, Anul: %d%n",
                        rs.getInt("id"), rs.getString("nume"), rs.getInt("varsta"),
                        rs.getString("destinatia"), rs.getInt("anul"));
            }
        }
    }

    private static void afisareExcursiiPentruPersoana(Connection connection, Scanner scanner) throws SQLException {
        System.out.print("Introduceți numele persoanei: ");
        String nume = scanner.nextLine();
        String sql = "SELECT e.destinatia, e.anul " +
                "FROM excursii e INNER JOIN persoane p ON e.id_persoana = p.id " +
                "WHERE p.nume = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, nume);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                System.out.printf("Destinație: %s, Anul: %d%n", rs.getString("destinatia"), rs.getInt("anul"));
            }
        }
    }

    private static void afisarePersoanePentruDestinatie(Connection connection, Scanner scanner) throws SQLException {
        System.out.print("Introduceți destinația: ");
        String destinatia = scanner.nextLine();
        String sql = "SELECT DISTINCT p.nume, p.varsta " +
                "FROM persoane p INNER JOIN excursii e ON p.id = e.id_persoana " +
                "WHERE e.destinatia = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, destinatia);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                System.out.printf("Nume: %s, Vârstă: %d%n", rs.getString("nume"), rs.getInt("varsta"));
            }
        }
    }

    private static void afisarePersoanePentruAn(Connection connection, Scanner scanner) throws SQLException {
        System.out.print("Introduceți anul: ");
        int anul = scanner.nextInt();
        String sql = "SELECT DISTINCT p.nume, p.varsta " +
                "FROM persoane p INNER JOIN excursii e ON p.id = e.id_persoana " +
                "WHERE e.anul = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, anul);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                System.out.printf("Nume: %s, Vârstă: %d%n", rs.getString("nume"), rs.getInt("varsta"));
            }
        }
    }
    private static void stergereExcursie(Connection connection, Scanner scanner) throws SQLException {
        System.out.print("Introduceți ID-ul excursiei: ");
        int idExcursie = scanner.nextInt();

        String sql = "DELETE FROM excursii WHERE id_excursie = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idExcursie);
            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Excursie ștearsă cu succes!");
                reseteazaAutoIncrement1(connection, "excursii");
            } else {
                System.out.println("Excursia nu a fost găsită!");
            }
        }
    }
    private static void stergerePersoana(Connection connection, Scanner scanner) throws SQLException {
        System.out.print("Introduceți ID-ul persoanei: ");
        int idPersoana = scanner.nextInt();
        String sql = "DELETE FROM persoane WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idPersoana);
            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Persoană ștearsă cu succes împreună cu excursiile asociate!");
                reseteazaAutoIncrement(connection, "persoane");
            } else {
                System.out.println("Persoana nu a fost găsită!");
            }
        }
    }


    private static void reseteazaAutoIncrement(Connection connection, String tableName) throws SQLException {
        String findMaxIdSql = String.format("SELECT IFNULL(MAX(id), 0) FROM %s", tableName);
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(findMaxIdSql)) {
            int maxId = 0;
            if (rs.next()) {
                maxId = rs.getInt(1);
            }
            String resetAutoIncrementSql = String.format("ALTER TABLE %s AUTO_INCREMENT = %d", tableName, maxId + 1);
            stmt.execute(resetAutoIncrementSql);
            System.out.println("AUTO_INCREMENT a fost resetat pentru tabela " + tableName);
        }
    }
    private static void reseteazaAutoIncrement1(Connection connection, String tableName) throws SQLException {
        String findMaxIdSql = String.format("SELECT IFNULL(MAX(id_excursie), 0) FROM %s", tableName);
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(findMaxIdSql)) {
            int maxId = 0;
            if (rs.next()) {
                maxId = rs.getInt(1);
            }
            String resetAutoIncrementSql = String.format("ALTER TABLE %s AUTO_INCREMENT = %d", tableName, maxId + 1);
            stmt.execute(resetAutoIncrementSql);
            System.out.println("AUTO_INCREMENT a fost resetat pentru tabela " + tableName);
        }
    }


}

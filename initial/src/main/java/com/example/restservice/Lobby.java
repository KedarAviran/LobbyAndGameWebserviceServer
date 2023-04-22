package com.example.restservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.*;
import java.time.LocalDate;
import java.util.*;

@RestController
public class Lobby
{
    List<Room> rooms = new ArrayList<>();
    int roomIDCounter = 0;

    public Room getRoomByID(int roomID)
    {
        for (Room room : rooms)
            if (room.getRoomID() == roomID)
                return room;
        return null;
    }

    @GetMapping("/getRooms")
    public StringBuilder getRooms()
    {
        StringBuilder roomsString = new StringBuilder();
        for (Room rm : rooms)
            roomsString.append(rm.toString()).append(" ");
        return roomsString;
    }

    @GetMapping("/createRoom")
    public String createRoom(@RequestParam(value = "gameType", defaultValue = "Chess") String gameType, @RequestParam(value = "playerName", defaultValue = "admin") String name)
    {
        rooms.add(new Room(roomIDCounter, name, gameType));
        roomIDCounter++;
        return (roomIDCounter - 1) + "";
    }

    @GetMapping("/joinRoom")
    public boolean joinRoom(@RequestParam(value = "roomID", required = true) int roomID, @RequestParam(value = "playerName", required = true) String name)
    {
        Room rm = getRoomByID(roomID);
        if (rm.isFull())
            return false;
        rm.setPlayer2(name);
        return true;
    }
    @GetMapping("/isFull")
    public boolean isFull(@RequestParam(value = "roomID", required = true) int roomID)
    {
        Room rm = getRoomByID(roomID);
        return rm.isFull();
    }

    @GetMapping("/leaveRoom")
    public void leaveRoom(@RequestParam(value = "roomID") int roomID, @RequestParam(value = "playerName") String name)
    {
        Room rm = getRoomByID(roomID);
        rm.playerLeave(name);
        if (rm.isEmpty())
            rooms.remove(getRoomByID(roomID));
    }

    @GetMapping("/setMove")
    public String setMove(@RequestParam(value = "roomID") int roomID, @RequestParam(value = "move") String move)
    {

        getRoomByID(roomID).setMove(move);
        if(getRoomByID(roomID).isGameOver())
            addGameToHistory(roomID,getRoomByID(roomID).getWinner());
        return String.valueOf(getRoomByID(roomID).isGameOver());
    }

    @GetMapping("/getLastMove")
    public String getLastMove(@RequestParam(value = "roomID") int roomID)
    {
        return getRoomByID(roomID).getLastMove();
    }

    @GetMapping("/isPlayerLeft")
    public String isPlayerLeft(@RequestParam(value = "roomID") int roomID)
    {
        return getRoomByID(roomID).getPlayerLeft();
    }
    private void addGameToHistory(int roomID,String winner)
    {
        Room rm = getRoomByID(roomID);
        String moves = rm.getGameHistory();
        String gameText = rm.getPlayer1()+"VS"+rm.getPlayer2();
        String date = LocalDate.now().toString();

        String path = System.getProperty("user.dir");
        try {Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");} catch (Exception ex)
        {
            ex.printStackTrace();
        }
        String databaseURL = "jdbc:ucanaccess://C:/Users/Aviran/Documents/GitHub/FinalProjectJava/MyDataBase.accdb";
        try (Connection connection = DriverManager.getConnection(databaseURL))
        {
            String sql = "INSERT INTO GameHistory (GameText, Winner, Time, Moves) VALUES (?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, gameText);
            preparedStatement.setString(2, winner);
            preparedStatement.setString(3, date);
            preparedStatement.setString(4, moves);
            int row = preparedStatement.executeUpdate();
            if (row > 0) {
                System.out.println("A row has been inserted successfully.");
            }

        } catch (SQLException ex)
        {
            ex.printStackTrace();
        }
    }
    @GetMapping("/getGameHistory")
    public String getGameHistory(@RequestParam(value = "roomID", required = false, defaultValue = "-1") int roomID)
    {

        String path = System.getProperty("user.dir");
        try {Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");} catch (Exception ex)
        {
            ex.printStackTrace();
        }
        String databaseURL = "jdbc:ucanaccess://C:/Users/Aviran/Documents/GitHub/FinalProjectJava/MyDataBase.accdb";
        StringBuilder games = new StringBuilder();
        try (Connection connection = DriverManager.getConnection(databaseURL))
        {
            String sql = "SELECT * FROM GameHistory";
            if (roomID != -1)
                sql = "SELECT Moves FROM GameHistory WHERE ID = '" + roomID + "'";
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);
            while (result.next())
            {
                if (roomID != -1)
                {
                    games.append(result.getString("Moves"));
                    return games.toString();
                }
                int id = result.getInt("ID");
                String GameText = result.getString("GameText");
                String Winner = result.getString("Winner");
                String Time = result.getString("Time");
                String moves = result.getString("Moves");
                games.append(id).append(",").append(GameText).append(",").append(Winner).append(",").append(Time).append(",").append(moves).append(" ");
            }
        } catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        return games.toString();
    }

}

package com.example.restservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;

@RestController
public class Lobby
{
    List<Room> rooms= new ArrayList<>();
    int roomIDCounter=0;
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
        StringBuilder roomsString= new StringBuilder();
        for (Room rm: rooms)
            roomsString.append(rm.toString()).append(" ");
        return roomsString;
    }
    @GetMapping("/createRoom")
    public void createRoom(@RequestParam(value = "gameType" , defaultValue = "Chess") String gameType , @RequestParam(value = "playerName" , defaultValue = "admin") String name)
    {
        rooms.add(new Room(roomIDCounter,name,gameType));
    }
    @GetMapping("/joinRoom")
    public boolean joinRoom(@RequestParam(value = "roomID" , required = true) int roomID , @RequestParam(value = "playerName" , required = true) String name)
    {
        Room rm= getRoomByID(roomID);
        if(rm.isFull())
            return false;
        rm.setPlayer2(name);
        return true;
    }
    @GetMapping("/leaveRoom")
    public void leaveRoom(@RequestParam(value = "roomID") int roomID , @RequestParam(value = "playerName") String name)
    {
        Room rm= getRoomByID(roomID);
        rm.playerLeave(name);
        if(rm.isEmpty())
        rooms.remove(getRoomByID(roomID));
    }
    @GetMapping("/setMove")
    public void setMove(@RequestParam(value = "roomID") int roomID , @RequestParam(value = "move") String move)
    {
        getRoomByID(roomID).setMove(move);
    }
    @GetMapping("/getLastMove")
    public String getLastMove(@RequestParam(value = "roomID") int roomID)
    {
        return getRoomByID(roomID).getLastMove();
    }

}

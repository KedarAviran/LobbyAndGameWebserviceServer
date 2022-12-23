package com.example.restservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.lang.model.type.PrimitiveType;
import java.util.*;

@RestController
public class Lobby
{
    List<Room> rooms= new ArrayList<Room>();
    int roomIDCounter=0;

    public Room getRoomByID(int roomID)
    {
        for(int i=0;i<rooms.size();i++)
            if(rooms.get(i).getRoomID()==roomID)
                return rooms.get(i);
        return null;
    }

    @GetMapping("/getRooms")
    public List<Room> getRooms()
    {
        return rooms;
    }
    @GetMapping("/createRoom")
    public void createRoom(@RequestParam(value = "gameType") String gameType , @RequestParam(value = "playerName") String name)
    {
        rooms.add(new Room(roomIDCounter,name,gameType));
    }
    @GetMapping("/joinRoom")
    public boolean joinRoom(@RequestParam(value = "roomID") int roomID , @RequestParam(value = "playerName") String name)
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
    public boolean setMove(@RequestParam(value = "roomID") int roomID , @RequestParam(value = "move") String move)
    {
        return getRoomByID(roomID).setMove(move);
    }
    @GetMapping("/getLastMove")
    public String getLastMove(@RequestParam(value = "roomID") int roomID)
    {
        return getRoomByID(roomID).getLastMove();
    }
}

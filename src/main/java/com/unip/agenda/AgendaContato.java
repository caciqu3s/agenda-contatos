package com.unip.agenda;

import com.unip.agenda.dao.ContatoDAO;
import com.unip.agenda.view.AgendaInterface;

import java.util.Scanner;

public class AgendaContato {
    ContatoDAO dao;

    public static void main(String[] args) throws Exception {
        AgendaInterface userInterface = new AgendaInterface(new Scanner(System.in), new ContatoDAO());
        userInterface.inicio();
    }
}

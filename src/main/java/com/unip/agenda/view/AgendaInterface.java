package com.unip.agenda.view;

import com.unip.agenda.dao.ContatoDAO;
import com.unip.agenda.model.Contato;

import java.util.List;
import java.util.Scanner;

public class AgendaInterface {
    Scanner scanner;
    ContatoDAO dao;

    public AgendaInterface(Scanner scanner, ContatoDAO dao) {
        this.scanner = scanner;
        this.dao = dao;
    }

    public void inicio() {
        System.out.println("Seja bem vindo à sua Agenda! Selecione uma das opções abaixo:");
        System.out.println("C - Cadastrar novo contato");
        System.out.println("D - Deletar contato");
        System.out.println("P - Procurar contato");
        System.out.println("A - Atualizar contato");
        System.out.println("V - Visualizar todos os contatos salvos");
        System.out.println("S - Sair da aplicação");

        String option = scanner.next();

        switch(option.toUpperCase()) {
            case "C":
                this.cadastro();
                this.inicio();
                break;

            case "D":
                this.deletar();
                this.inicio();
                break;

            case "P":
                this.procurar();
                this.inicio();
                break;

            case "A":
                this.atualizar();
                this.inicio();
                break;

            case "V":
                this.verContatos();
                this.inicio();
                break;

            case "S":
                System.exit(0);
                break;

            default:
                System.out.println("Comando desconhecido");
                this.inicio();
                break;
        }
    }

    private void cadastro() {
        System.out.println("Digite o nome do contato: ");
        String nome = scanner.next();

        System.out.println("Agora, digite a idade do contato: ");
        int idade = scanner.nextInt();

        System.out.println("Deseja salvar o contato de nome " + nome + " com " + idade + " anos de idade? (S/N)");
        String res = scanner.next();

        if (res.toUpperCase().equals("S")) {
            dao.save(new Contato(nome, idade));
        } else {
            System.out.println("Contato não será criado!");
        }
    }

    private void verContatos() {
        List<Contato> contatos = dao.getContatos();

        if(!contatos.isEmpty()) {
            for(Contato contato : contatos) {
                System.out.println("ID: " + contato.getId() + ", Nome: " + contato.getNome()
                        + ", Idade: " + contato.getIdade() + ", Data de Cadastro: " + contato.getDataCadastro());
            }
        } else {
            System.out.println("Não existem contatos salvos, crie-os na opção 'C' no menu inicial!");
        }
    }

    private Contato procurar() {
        System.out.println("Por favor, digite o ID do contato que deseja (para ver todos os contratos, pressione '0'): ");
        int id = scanner.nextInt();

        Contato contato = null;

        if(id == 0) {
            this.verContatos();
        } else {
            contato = dao.getContatoById(id);
            this.verificarContato(contato);
            System.out.println("ID: " + contato.getId() + ", Nome: " + contato.getNome()
                    + ", Idade: " + contato.getIdade() + ", Data de Cadastro: " + contato.getDataCadastro());
        }

        return contato;
    }

    private void deletar() {
        Contato contato = this.procurar();
        this.verificarContato(contato);
        System.out.println("Gostaria de deleta-lo? (S/N)");
        String res = scanner.next();

        if(res.toUpperCase().equals("S")) {
            dao.delete(contato.getId());
        } else {
            System.out.println("Contato não será deletado!");
        }

        this.inicio();
    }

    private void atualizar() {
        Contato contato  = this.procurar();
        this.verificarContato(contato);
        System.out.println("Digite o nome novo: ");
        String nome = scanner.next();

        System.out.println("Digite a idade nova: ");
        int idade = scanner.nextInt();

        System.out.println("O nome do usuário " + contato.getNome() + " será trocado para " + nome
                + " e sua idade " + contato.getIdade() + " será " + idade + ". Deseja realizar a modificação? (S/N)");
        String res = scanner.next();

        if(res.toUpperCase().equals("S")) {
            contato.setNome(nome);
            contato.setIdade(idade);
            dao.update(contato);
        } else {
            System.out.println("O contato não será alterado!");
        }
    }

    private void verificarContato(Contato contato) {
        if(contato == null) {
            this.inicio();
        }
    }
}

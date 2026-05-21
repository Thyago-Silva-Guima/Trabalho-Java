package controllers;

import models.Emprestimo;
import models.Livro;
import models.Pessoa;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import exceptions.LivroIndisponivelException;
import exceptions.UsuarioComPendenciaException;

public class EmprestimoController {

    private List<Emprestimo> emprestimos = new ArrayList<>();

    public void realizarEmprestimo(Pessoa usuario, Livro livro) throws Exception {

        boolean temEmprestimoAtivo = emprestimos.stream()
                .anyMatch(e -> e.getUsuario().getIdUsuario() == usuario.getIdUsuario()
                            && e.isEmprestimoAtivo());
        if (temEmprestimoAtivo) {
            throw new UsuarioComPendenciaException("Usuário já possui um livro emprestado. Devolva antes de pegar outro.");
        }
        if (livro.getNumeroExemplaresDisponiveis() <= 0) {
            throw new LivroIndisponivelException("Sem exemplares disponíveis para: " + livro.getTitulo());
        }

        Emprestimo emprestimo = new Emprestimo(usuario, livro);
        emprestimos.add(emprestimo);
        livro.setNumeroExemplaresDisponiveis(livro.getNumeroExemplaresDisponiveis() - 1);

        System.out.println("Empréstimo " + emprestimo.getIdEmprestimo() + " registrado.");
        System.out.println("Devolução prevista: " + emprestimo.getDataPrevistaDevolucao());
    }

    public void realizarDevolucao(int idEmprestimo) throws Exception {
        Emprestimo emprestimo = emprestimos.stream()
                .filter(e -> e.getIdEmprestimo() == idEmprestimo && e.isEmprestimoAtivo())
                .findFirst()
                .orElse(null);

        if (emprestimo == null) {
            throw new Exception("Empréstimo não encontrado ou já devolvido. ID: " + idEmprestimo);
        }

   
        LocalDate hoje = LocalDate.now();
        emprestimo.setDataDevolucaoRealizada(hoje);
        emprestimo.setEmprestimoAtivo(false);

    
        Livro livro = emprestimo.getLivro();
        livro.setNumeroExemplaresDisponiveis(livro.getNumeroExemplaresDisponiveis() + 1);

        long diasEmprestado = emprestimo.calcularDiasEmprestados();
        long diasAtraso = emprestimo.calcularDiasAtraso();

        System.out.println("Livro devolvido Ficou emprestado por " + diasEmprestado + " dia(s).");

        if (diasAtraso > 0) {
            System.out.println("Devolvido com " + diasAtraso + " dia(s) de atraso.");
        } else {
            System.out.println("Devolvido no prazo.");
        }
    }

    public void listarEmprestimosAtivos() {
        List<Emprestimo> ativos = emprestimos.stream()
                .filter(Emprestimo::isEmprestimoAtivo)
                .collect(Collectors.toList());

        if (ativos.isEmpty()) {
            System.out.println("Nenhum livro emprestado no momento.");
            return;
        }
        ativos.forEach(
            e -> 
        System.out.println("  ID" + e.getIdEmprestimo() +
            " | Livro: " + e.getLivro().getTitulo() +
            " | Usuário: " + e.getUsuario().getNome() +
            " | Previsto: " + e.getDataPrevistaDevolucao()
        ));
    }

    public void listarEmprestimosAtrasados() {
        List<Emprestimo> atrasados = emprestimos.stream()
                .filter(e -> e.calcularDiasAtraso() > 0)
                .sorted(Comparator.comparingLong(Emprestimo::calcularDiasAtraso).reversed())
                .collect(Collectors.toList());

        if (atrasados.isEmpty()) {
            System.out.println("Nenhuma devolução em atraso.");
            return;
        }
        atrasados.forEach(
            e -> 
            System.out.println(" ID" + e.getIdEmprestimo() +
            " | Livro: " + e.getLivro().getTitulo() +
            " | Usuário: " + e.getUsuario().getNome() +
            " | Atraso: " + e.calcularDiasAtraso() + " dia(s)" +
            (e.isEmprestimoAtivo() ? " [AINDA EMPRESTADO]" : " [DEVOLVIDO]")
        ));
    }

    public void listarLivrosMaisPopulares() {
        if (emprestimos.isEmpty()) {
            System.out.println("Nenhum empréstimo registrado.");
            return;
        }
        emprestimos.stream()
            .collect(Collectors.groupingBy(
                 e -> e.getLivro().getTitulo(),
                 Collectors.counting()
            ))
            .entrySet().stream()
            .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
            .forEach(entry -> System.out.println(" " + entry.getKey() + "  " + entry.getValue() + " empréstimo(s)"
         ));
    }

    public List<Emprestimo> getEmprestimos() {
        return emprestimos;
    }
}
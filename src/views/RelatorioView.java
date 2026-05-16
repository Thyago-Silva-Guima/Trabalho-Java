package views;
 
import controllers.EmprestimoController;
import interfaces.IGeradorRelatorio;
 
public class RelatorioView implements IGeradorRelatorio{
 
    private EmprestimoController emprestimoController;
 
    public RelatorioView(EmprestimoController emprestimoController) {
        this.emprestimoController = emprestimoController;
    } 
    
       public void exibirLivrosEmprestados(){ 
         System.out.println("=== Livros Emprestados Atualmente ==="); 
          emprestimoController.listarEmprestimosAtivos();
       }

       public void exibirDevolucao(){ 
         System.out.println("=== Livros Que Estão Para Devolução"); 
         emprestimoController.listarEmprestimosAtrasados; 
       }

       public void exibirLivrosMaisEmprestados(){ 
         System.out.println("=== Livros Mais Populares ==="); 
         emprestimoController.listarLivrosMaisPopulares; 
       }
} 
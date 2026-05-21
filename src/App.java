import controllers.BibliotecaController;
import controllers.EmprestimoController;
import utils.PreCargaDados;
import views.MenuConsole;
import views.RelatorioView;

public class App {
    public static void main(String[] args) {
        System.out.println("Inicializando Sistema da Biblioteca...\n");

        BibliotecaController bibliotecaController = new BibliotecaController();
        EmprestimoController emprestimoController = new EmprestimoController();

        bibliotecaController.setEmprestimoController(emprestimoController);

        RelatorioView relatorioView = new RelatorioView(emprestimoController);
        MenuConsole menu = new MenuConsole(bibliotecaController, emprestimoController, relatorioView);

        PreCargaDados cargaDados = new PreCargaDados(bibliotecaController, emprestimoController);
        cargaDados.carregar();

        menu.iniciar();
    }
}
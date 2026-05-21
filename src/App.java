/*Integrantes do Grupo:
Lorenzo Vanelli - RGM 42924928
Thyago Silva Guimaraes - RMG 04283217-9
 João Rubens - RGM 03874461-9
 Murilo de Mattos Opis - RGM 43656641
 Luan Neuwirth - RGM 43589707
*/

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
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class arena {
    private ArrayList<Combatente> LadoA = new ArrayList<>(); // Como o jogo terá vários guerreiros por partida, necessitamos dá criação de listas com esses guerreiros de modo a alterná-los durante a partida
    private ArrayList<Combatente> LadoB = new ArrayList<>();

    
    public void telinha() {

    JFrame tela = new JFrame("Malucos por RPG!");
    tela.setExtendedState(JFrame.MAXIMIZED_BOTH);
    tela.setUndecorated(true);
    tela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    int largura = Toolkit.getDefaultToolkit().getScreenSize().width;
    int altura  = Toolkit.getDefaultToolkit().getScreenSize().height;

    ImageIcon original = new ImageIcon(
        getClass().getResource("/imagens_do_jogo/arena/fundo.png")
    );

    Image imagemRedimensionada = original.getImage()
                .getScaledInstance(largura, altura, Image.SCALE_SMOOTH);
    JLabel fundo = new JLabel(new ImageIcon(imagemRedimensionada));

        tela.setContentPane(fundo);
        tela.setVisible(true);
    }

    public void InicializarTimes(){
        int qtd1;
        int qtd2;
        int qtd3; 
    /* A fim de que o usuário selecine quantos combatentes de cada classe ele quer parar usar em sua partida
    criamos janelas que solicitama quantidade de cada classe*/

        try{ // Aqui colocamos em prática alguns conceitos que, inclusive, foram passados em sala de aula pelo professor a fim de evitar erros por mau uso durante o jogo
            String input = JOptionPane.showInputDialog("Quantos Arcanistas no Lado A?");
            qtd1 = (input == null) ? 0 : Integer.parseInt(input);} catch(NumberFormatException e) { 
            JOptionPane.showMessageDialog(null, "Insira apenas numeros inteiros!"); 
            qtd1 = 0; // Aqui precisamos colocar o 0 para que o loop não dê problema com os caracteres inseridos pelo usuário
                }
        for (int i = 1; i <= qtd1; i++) {ladoA.add(new Arcanista("Arcanista " + i));} // Faz um loopzinho para ir adicionando todos de uma vez
        try{                                
            String input = JOptionPane.showInputDialog("Quantos Caçadores no Lado A?");
            qtd2 = (input == null) ? 0 : Integer.parseInt(input);} catch(NumberFormatException e){ 
            JOptionPane.showMessageDialog(null, "Insira apenas numeros inteiros!"); 
            qtd2 = 0; // Aqui precisamos colocar o 0 para que o loop não dê problema com os caracteres inseridos pelo usuário
            }
                                         
        for (int i = 1; i <= qtd2; i++) {ladoA.add(new Cacador("Caçador" + i));}
        try{                                 
            String input = JOptionPane.showInputDialog("Quantos Guardiões no Lado A?");
            qtd3 = (input == null) ? 0 : Integer.parseInt(input); // Converte o texto para número
        } catch(NumberFormatException e) {   
            JOptionPane.showMessageDialog(null, "Insira apenas numeros inteiros!"); 
            qtd3 = 0; // Aqui precisamos colocar o 0 para que o loop não dê problema com os caracteres inseridos pelo usuário
        }                              
        for (int i = 1; i <= qtd3; i++) {ladoA.add(new Guardiao("Guardiao" + i));}


        
    }
    
    public static void main(String[] args){
        arena a = new arena();//é aqui que irá fixar rodando o game.
        a.telinha();
    }
}

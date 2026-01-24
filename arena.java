import javax.swing.*;

public class arena {

    public void telinha() {
        JFrame tela = new JFrame("Malucos por RPG!");
        tela.setExtendedState(JFrame.MAXIMIZED_BOTH);
        tela.setUndecorated(true);
        tela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ImageIcon areninha = new ImageIcon(getClass().getResource("/imagens_do_jogo/arena/fundo.jpg"));

        JLabel fundo = new JLabel(areninha); 

        tela.setContentPane(fundo);
        tela.setLayout(null);
        tela.setVisible(true);
    }

    public static void main(String[] args){
        arena a = new arena();//é aqui que irá fixar rodando o game.
        a.telinha();
    }
}
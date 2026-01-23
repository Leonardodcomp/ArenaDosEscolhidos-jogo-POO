import javax.swing.*;
import java.awt.*;

public class arena {

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

    public static void main(String[] args){
        arena a = new arena();//é aqui que irá fixar rodando o game.
        a.telinha();
    }
}
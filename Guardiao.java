import javax.swing.*;

public class Guardiao extends Combatente {
    
    private int vigor;
    private JLabel lutador;//é aqui que estará guardada a imagem do lutador para imprimir na tela.

    public Guardiao (String name) {
        super(120,name); // vida bufada o homi é tank
        this.vigor = 100;  // Recurso especial
        this.tipo = "Guardiao";
    }

    public JLabel imprimir(){
        ImageIcon persona = new ImageIcon(getClass().getResource("imagens_do_jogo/knigth/cavaleiro_parado.png"));
        this.lutador = new JLabel(persona);
        return this.lutador;
    }

    @Override
    public void receberDano(int dano) {
        // Chance de 20% de bloquear SE tiver vigor

        // Então vamos fazer um calculo de probabilidade, sem fazer kkkk. Como vamos fazer isso? com a função random.nextInt eu gero o numero aleatório de 1 a 20. se esse numero for menor que 20, bloqueou, bem mais fácil do que calculo de probabilidade.

        boolean bloqueou = random.nextInt(100) < 20; // 20% chance

        if (bloqueou && vigor >= 10) {
            vigor -= 10;
            System.out.println(this.nome + " BLOQUEOU o ataque com seu escudo! (Bate na massa, nego!)");
        } else {
            this.vida-=dano; // Se não bloqueou, o homi ta fraco, toma dano igual gente
        }
    }



    @Override
    public void atacar(Combatente alvo) {
        //Como todo bom RPG, o random.nextInt será nosso "dado"

        int dano = 10 + random.nextInt(5);

        System.out.println(this.nome + " golpeou com o peitoral");

        alvo.receberDano(dano);
    }

    //Métodos get

    public int getVigor(){
        return this.vigor;
    }
}

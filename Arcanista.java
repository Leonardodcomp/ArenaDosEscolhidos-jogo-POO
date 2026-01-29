import javax.swing.*;

public class Arcanista extends Combatente {

    private int mana;
    private JLabel lutador;//é aqui que estará guardada a imagem do lutador para imprimir na tela.

    public Arcanista(String name) {
        super(60,name); // Teoricamente é o que da dano, então tem que ser mole
        this.mana = 100;  // Mana full
        this.tipo = "Arcanista";
    }

    @Override
    public void atacar(Combatente alvo) {
        if (mana >= 30) {

            // Ult do veigar vem ai, o dano é grande
            mana -= 30; //A mana diminui com base no ataque

            // Roda o dado
            int dano = 20 + random.nextInt(10);

            System.out.println(this.nome + " lançou ataque buffado com mana");
            alvo.receberDano(dano);
        } else {
            // Se o mago não tem mana, serve pra nada.
            mana += 10;
            int dano = 2;
            System.out.println(this.nome + " está sem mana e jogou uma péda, recarregando chackra...");
            alvo.receberDano(dano);
        }
    }

    public JLabel imprimir(){
        ImageIcon persona = new ImageIcon(getClass().getResource("imagens_do_jogo/knigth/cavaleiro_parado.png"));
        this.lutador = new JLabel(persona);
        return this.lutador;
    }

    public void receberDano(int dano) {
        this.vida -= dano;
        if (this.vida < 0){
            this.vida = 0;
        }else{
            System.out.println("você recebeu " + dano + " de dano. Vida restante: " + this.vida);
        }
    }


    //Métodos get
    public int getMana(){
        return this.mana;
    }
}

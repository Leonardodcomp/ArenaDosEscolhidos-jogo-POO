import javax.swing.*;

public class Cacador extends Combatente {

    private JLabel lutador;//é aqui que estará guardada a imagem do lutador para imprimir na tela.

    public Cacador(String name) {
        super(80,name); // Vida média-baixa
        this.tipo = "cacador";
    }

    @Override
    public void atacar(Combatente alvo) {
        int danoBase = 15 + random.nextInt(5);
        
        // Chance de 30% de Crítico, joga o dado. Se for menor que 30, o booleno ativa. Muito melhor do que fazer calculo de probabilidade :)

        boolean critico = random.nextInt(100) < 30; 

        if (critico) {
            danoBase *= 2;
            System.out.println("O ataque de" + this.nome + " pegou na veia, segure a zunhada");
        } else {
            System.out.println(this.nome + " jogou uma flecha");
        }
        
        alvo.receberDano(danoBase);
    }

    public void receberDano(int dano) {
        this.vida -= dano;
        if (this.vida < 0){
            this.vida = 0;
        }else{
            System.out.println("você recebeu " + dano + " de dano. Vida restante: " + this.vida);
        }
    }

    public JLabel imprimir(){
        ImageIcon persona = new ImageIcon(getClass().getResource("imagens_do_jogo/cacador/gangster_parado.jpg"));
        this.lutador = new JLabel(persona);
        return this.lutador;
    }
}

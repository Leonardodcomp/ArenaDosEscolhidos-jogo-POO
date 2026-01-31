import java.util.Random;
import javax.swing.JLabel;

public abstract class Combatente{

    //Atributos serão declarados como protected para serem usados por toda a package

    //Nossos personagens tem nome fixo de batismo, não iremos declarar nome na classe abstract
    protected int vida;
    protected int vidaMaxima;
    protected Random random; // Para fazer a matemática de aleatoridade
    protected String nome;
    protected int estus = 2 ; // cada combatente terá dois Estur para se curar.
    protected String tipo;//serve para salvar qual é o tipo de combatente.

    public Combatente(int vida, String name) {
        this.vida = vida;
        this.nome= name;
        this.vidaMaxima = vida;
        this.random = new Random(); //Usado da biblioteca java.util.Random
    }

    // Metodo abstract porque a classe filha que diz como ataca
    public abstract void atacar(Combatente alvo);

    // Logica base de como o combatente recebe dano
    public void curar(){
        //irá curar sempre 30 pontos de vida, sem aleatoriedade. 
        if (estus>0){
            if((this.vida) + 30 < this.vidaMaxima){//não permiter que fique com mais do que a vida inicial.
                this.vida +=30;
                this.estus -=1;
            }else{
                this.vida=vidaMaxima;
                this.estus -=1;
            }
        }else{
            System.out.println("Quantidade de Estus insuficiente.");
        }
    }


    public abstract void receberDano(int dano);

    // Logica booleana para verificar se o combatente está vivo
    public boolean toVivoGarai() {
        return this.vida > 0;
    }

    //Métodos get

    public String getNome(){
        return this.nome;
    }
    public int getVida(){
        return this.vida;
    }

    public int getVidaMaxima(){
        return this.vidaMaxima;
    }

    public int getEstus(){
        return this.estus;
    }

    public String getTipo(){
        return this.tipo;
    }

}

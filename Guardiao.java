public class Guardiao extends Combatente {
    private int qtdd;
    private int vigor;
    private String nome = "Garibaldo";

    public Guardiao (String name, int quantidade_guardiao) {
        super(120); // vida bufada o homi é tank
        this.vigor = 100;  // Recurso especial
        this.nome = name;
        this.qtdd = quantidade_guardiao;
    }

    public void morte(String name){
        this.nome= name;
        this.vida=vidaMaxima;
        this.qtdd-=1;//alterar a quantidade de guardiões para jogar.
        this.estus = 2; //reinicia a quantidade de estus.
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
            super.receberDano(dano); // Se não bloqueou, o homi ta fraco, toma dano igual gente
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
    public int getQtdd(){
        return this.qtdd;
    }

    public int getVigor(){
        return this.vigor;
    }

    public String getNome(){
        return this.nome;
    }
}

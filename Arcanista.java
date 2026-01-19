public class Arcanista extends Combatente {
    private String nome = "José de Alencar";
    private int mana;

    public Arcanista(String nome) {
        super(60); // Teoricamente é o que da dano, então tem que ser mole
        this.mana = 100;  // Mana full
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
}

public class Cacador extends Combatente {
    private String nome = "Jessica";

    public Cacador(String nome) {
        super(80); // Vida média-baixa
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
}

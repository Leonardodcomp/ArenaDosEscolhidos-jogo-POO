import javax.swing.*;
import java.awt.*;
import java.util.ArrayList; // Importação usada na geração das listas de combantes, a fim de atribuir objetos de diferentes classes em uma única lista
import java.util.Random; // Importação usada na parte de geração de números aleatórios para distribuição de combatentes para a classe B

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
        int quantidade_B;
    /* A fim de que o usuário selecine quantos combatentes de cada classe ele quer parar usar em sua partida
    criamos janelas que solicitama quantidade de cada classe*/

        try{ // Aqui colocamos em prática alguns conceitos que, inclusive, foram passados em sala de aula pelo professor a fim de evitar erros por mau uso durante o jogo
            String input = JOptionPane.showInputDialog("Quantos Arcanistas no Lado A?"); // Aqui, uma janela JOption que pergunta ao usuário quantos combatentes ele quer dessa determinada classe
            qtd1 = (input == null) ? 0 : Integer.parseInt(input);} catch(NumberFormatException e) { // Nessa linha, usamos terário para verificar se a caixa está vazia e, nesse  caso, atribuir 0 ou o número digitado pelo usuário. Além disso, possibilita testar se o usuário colocou número e não letra ou simbolos, indo para uma possível alternativa com o catch
            JOptionPane.showMessageDialog(null, "Insira apenas numeros inteiros!"); // Aqui, o aviso de alerta
            qtd1 = 0; // Aqui precisamos colocar o 0 para que o loop não dê problema com os caracteres inseridos pelo usuário
                }
        // Daqui em diante, o processo das linhas anteriores se repetem para as próximas classes
        for (int i = 1; i <= qtd1; i++) {ladoA.add(new Arcanista("Arcanista " + i));}
        try{                                
            String input = JOptionPane.showInputDialog("Quantos Caçadores no Lado A?");
            qtd2 = (input == null) ? 0 : Integer.parseInt(input);} catch(NumberFormatException e){ 
            JOptionPane.showMessageDialog(null, "Insira apenas numeros inteiros!"); 
            qtd2 = 0;
            }
                                         
        for (int i = 1; i <= qtd2; i++) {ladoA.add(new Cacador("Caçador" + i));}
        try{                                 
            String input = JOptionPane.showInputDialog("Quantos Guardiões no Lado A?");
            qtd3 = (input == null) ? 0 : Integer.parseInt(input);
        } catch(NumberFormatException e) {   
            JOptionPane.showMessageDialog(null, "Insira apenas numeros inteiros!"); 
            qtd3 = 0; 
        }                              
        for (int i = 1; i <= qtd3; i++) {ladoA.add(new Guardiao("Guardiao" + i));}
        /* Agora com a quantidade de combatentes  do ladoA gerado, basta que a gente some as quantidades e crie um loop para preencher o ladoB com a mesma quantidade total de combatentes, mas distribuição aleatória.*/
        quantidade_B = qtd1+qtd2+qtd3;
        Random gerador = new Random(); // Objeto para sortear os números
        

        for (int i = 1; i <= quantidade_B; i++){
            int escolha = gerador.nextInt(3); // Essa linha de comendo permite que sejam gerados números que vão de 0 a 2
            // Abaixo, temos estruturas condicionais que, a partir do números gerados aleatoriamente, permite escolher de qual classe será o próximo combatente
            if(escolha == 0){
                ladoB.add(new Arcanista("Inimigo Mago " + i));
            } else if(escolha == 1) {
                ladoB.add(new Guardiao("Inimigo Guardiao" + i));
            } else{
                 ladoB.add(new Cacador("Inimigo Cacador" + i));
            }
            
        }
        
    }

    // Aqui uma parte importante do código que definirá os turnos de cada jogador
    // ou seja, qual ação o jogador tomará? Atacar ou defender? Assim como um IA para o adversário
    public void Ciclo_Turnos(){
        Random gerador1 = new Random();
        //Esse primeiro loop abaixo acontecerá até ambos os lados estarem sem jogadores, a fim de definir o vencedor.
            while(!ladoA.isEmpty() && !ladoB.isEmpty()){
                // Uma caixa de diálogo JOption exibida que pergunta ao usuário qual jogador ele quer em campo.
                String input = JOptionPane.showInputDialog("Digite a posiçào do combatente que irá ao combate agora");
                int numero_combatente = Integer.parseInt(input);
                // Ao ser selecionada o jogador que irá a combate, a lógica abaixo realiza essa operação e replica a posição
                // escolhida pelo jogador para o adversário
                Combatente lutadorA = ladoA.get(numero_combatente - 1);
                Combatente lutadorB = ladoB.get(gerador1.nextInt(ladoB.size()));
                // Abaixo está o loop que se repetirá até que algum dos dois bonecos morram em combate
                while (lutadorA.toVivoGarai() == true && lutadorB.toVivoGarai() == true){
                    // Caixa de diálogo que perguntará qual ação o jogador tomará, atacar ou se curar?
                    String acao = JOptionPane.showInputDialog("Lutador"+ lutadorA.getNome() + "\n1 - Atacar \n2 Curar")
                    // Esse equals nada mais é que um comparador, mas em vez do "==" do java que compara endereços
                    // o "equals" compara conteúdos
                    if (acao.equals("1")) {
                             lutadorA.atacar(lutadorB); ⚔️
                        } else {
                             lutadorA.curar(); 🧪
                                    }
                    if (lutadorB.toVivoGarai()) {
                       // Se a vida for menor que 30% do total (ajuste esse número como preferir)
                          if (lutadorB.getVida() < 30) { 
                                System.out.println(lutadorB.getNome() + " está acuado e decide se curar! ");
                                lutadorB.curar(); 
                        } else {
                                System.out.println(lutadorB.getNome() + " parte para o ataque! ");
                                lutadorB.atacar(lutadorA);
                                     } 
                                }
                }

                if(!lutadorA.toVivoGarai()){
                    System.out.print(lutadorA.getNome()+ "foi derrotado e saiu da arena");
                    ladoA.remove(lutadorA); // Tira o lutador derrotado da lista do Jogado
                }
                
                if(!lutadorB.toVivoGarai()){
                    System.out.print(lutadorB.getNome()+ "foi derrotado e saiu da arena");
                    ladoB.remove(lutadorB); // Tira o lutador derrotado da lista do Jogador
                }


                  
                }
            if(ladoA.isEmpty()){
                JOptionPane.showMessageDialog(null, "O Lado B venceu o Grande Torneio!");
            } else{
                JOptionPane.showMessageDialog(null, "O Lado A venceu o Grande Torneio! ");             
                }
        
            }



    
    public static void main(String[] args){
        arena a = new arena();//é aqui que irá fixar rodando o game.
        a.telinha();
    }
}

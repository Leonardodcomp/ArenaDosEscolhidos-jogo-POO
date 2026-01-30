import javax.swing.*;
import java.awt.*;
import java.util.ArrayList; // Importação usada na geração das listas de combantes, a fim de atribuir objetos de diferentes classes em uma única lista
import java.util.Random; // Importação usada na parte de geração de números aleatórios para distribuição de combatentes para a classe B
import java.util.function.LongUnaryOperator;
// Os dois ultimos métodos a seguir são necessários para implementar botão no jogo
import java.awt.event.ActionEvent; 
import java.awt.event.ActionListener;

public class Arena {
    private ArrayList<Combatente> ladoA = new ArrayList<>(); // Como o jogo terá vários guerreiros por partida, necessitamos dá criação de listas com esses guerreiros de modo a alterná-los durante a partida
    private ArrayList<Combatente> ladoB = new ArrayList<>();
    private JFrame tela;//tonar o JFrame um atributo de classe.
    private JLabel fundo;
    private JLabel img_lutadorA;
    private JLabel img_lutadorB;
    private JPanel painelMenu;  //Colocamos esse atributo novo por causa do menu e da logistica de botão.


    
    public void telinha() {
        tela = new JFrame("Arena dos Escolhidos!");
        tela.setExtendedState(JFrame.MAXIMIZED_BOTH); // Tela cheia
        tela.setUndecorated(false);       // Jogo abre em modo janela para poder fechar no meio do jogo caso queira.
        tela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        tela.setLayout(null); // Layout nulo para posicionamento absoluto
        
        // Deixa a tela visível (inicialmente vazia ou preta)
        tela.getContentPane().setBackground(Color.BLACK);
        tela.setVisible(true);
    }

    //Menuzinho pro jogo não ir direto pra gameplay
    public void mostrarMenu() {

        // Criamos um painel transparente ou preto para colocar o botão
        painelMenu = new JPanel();
        painelMenu.setLayout(null);
        painelMenu.setBackground(Color.BLACK); // Fundo do menu
        painelMenu.setBounds(0, 0, tela.getWidth(), tela.getHeight());

        // Lógica do JLabel permanece
        JLabel titulo = new JLabel("ARENA DOS ESCOLHIDOS");
        titulo.setFont(new Font("Serif", Font.BOLD, 50));
        titulo.setForeground(Color.WHITE);
        titulo.setHorizontalAlignment(SwingConstants.CENTER);
        titulo.setBounds(0, tela.getHeight()/2 - 150, tela.getWidth(), 60);
        painelMenu.add(titulo);

        // Criação e configuração do botão pra gameplay começar
        JButton btnComecar = new JButton("COMEÇAR");
        btnComecar.setFont(new Font("Arial", Font.BOLD, 20));
        btnComecar.setBackground(Color.RED);
        btnComecar.setForeground(Color.WHITE);

        // Parte chata de frontend
        int btnLargura = 200;
        int btnAltura = 60;
        btnComecar.setBounds((tela.getWidth() - btnLargura)/2, tela.getHeight()/2, btnLargura, btnAltura);

        // Agora sim, a magia do botão, começar a gameplay!
        btnComecar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Vai remover o metodo menu para começar
                tela.remove(painelMenu);
                tela.repaint();

                // Inicia o jogo numa Thread separada pra evitar bugs e BOs
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        carregarCenario();  // Começa a gameplay
                        InicializarTimes();  
                        Ciclo_Turnos();    
                    }
                }).start();
            }
        });

        painelMenu.add(btnComecar);
        tela.add(painelMenu); // Adiciona o menu na tela
        tela.revalidate();
        tela.repaint();
    }

    
    public void carregarCenario() {
        int largura = Toolkit.getDefaultToolkit().getScreenSize().width;
        int altura  = Toolkit.getDefaultToolkit().getScreenSize().height;

        java.net.URL imgUrl = getClass().getResource("/imagens_do_jogo/Arena/fundo.jpg");
        
        // adicionei um tratamento de falhas (jeito burro) pra se der ruim não crachar
        if (imgUrl != null) {
            ImageIcon original = new ImageIcon(imgUrl);
            Image imagemRedimensionada = original.getImage().getScaledInstance(largura, altura, Image.SCALE_SMOOTH);
            fundo = new JLabel(new ImageIcon(imagemRedimensionada));
        } else {
            // Caso a imagem falhe, cria um fundo cinza para não crashar
            fundo = new JLabel();
            fundo.setOpaque(true);
            fundo.setBackground(Color.DARK_GRAY);
        }

        fundo.setLayout(null);
        
        // Define o fundo como o conteúdo principal da tela
        tela.setContentPane(fundo);
        tela.revalidate(); // Atualiza a tela para mostrar a imagem
        tela.repaint();
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
            if (input == null){
                qtd1 =0;
            }else{
                if (Integer.parseInt(input)>0){// para não permitir números negativos.
                    qtd1 = Integer.parseInt(input);
                }else{
                    qtd1 = Integer.parseInt("q231");// para gerar um erro.
                }
            }
        }catch(NumberFormatException e) { // Nessa linha, usamos terário para verificar se a caixa está vazia e, nesse  caso, atribuir 0 ou o número digitado pelo usuário. Além disso, possibilita testar se o usuário colocou número e não letra ou simbolos, indo para uma possível alternativa com o catch
            JOptionPane.showMessageDialog(null, "Insira apenas numeros inteiros!"); // Aqui, o aviso de alerta
            qtd1 = 0; // Aqui precisamos colocar o 0 para que o loop não dê problema com os caracteres inseridos pelo usuário
        }



        // Daqui em diante, o processo das linhas anteriores se repetem para as próximas classes
        for (int i = 1; i <= qtd1; i++){//gera os arcanistas.
            String input = JOptionPane.showInputDialog("Insira o nome do arcanista:");
            ladoA.add(new Arcanista(input));//a pessoa iá escolher o nome de cada arcanista.
        }
        try{                                
            String input = JOptionPane.showInputDialog("Quantos Caçadores no Lado A?");
            if (input == null){
                qtd2 =0;
            }else{
                if (Integer.parseInt(input)>0){// para não permitir números negativos.
                    qtd2 = Integer.parseInt(input);
                }else{
                    qtd2 = Integer.parseInt("q231");// para gerar um erro.
                }
            }
        }catch(NumberFormatException e) { // Nessa linha, usamos terário para verificar se a caixa está vazia e, nesse  caso, atribuir 0 ou o número digitado pelo usuário. Além disso, possibilita testar se o usuário colocou número e não letra ou simbolos, indo para uma possível alternativa com o catch
            JOptionPane.showMessageDialog(null, "Insira apenas numeros inteiros!"); // Aqui, o aviso de alerta
            qtd2 = 0; // Aqui precisamos colocar o 0 para que o loop não dê problema com os caracteres inseridos pelo usuário
        }
        for (int i = 1; i <= qtd2; i++){//gera os arcanistas.
            String input = JOptionPane.showInputDialog("Insira o nome do caçador:");
            ladoA.add(new Cacador(input));//a pessoa iá escolher o nome de cada cacador.
        }
        try{                                 
            String input = JOptionPane.showInputDialog("Quantos Guardiões no Lado A?");
            if (input == null){
                qtd3 =0;
            }else{
                if (Integer.parseInt(input)>0){// para não permitir números negativos.
                    qtd3 = Integer.parseInt(input);
                }else{
                    qtd3 = Integer.parseInt("q231");// para gerar um erro.
                }
            }
        }catch(NumberFormatException e) {   
            JOptionPane.showMessageDialog(null, "Insira apenas numeros inteiros!"); 
            qtd3 = 0; 
        }                              
        for (int i = 1; i <= qtd3; i++){//gera os arcanistas.
            String input = JOptionPane.showInputDialog("Insira o nome do Guardião:");
            ladoA.add(new Guardiao(input));//a pessoa iá escolher o nome de cada guardião.
        }// as linhas acima geram todos os personagens que o jogador escolher.


        /* Agora com a quantidade de combatentes  do ladoA gerado, basta que a gente some as quantidades e crie um loop para preencher o ladoB com a mesma quantidade total de combatentes, mas distribuição aleatória.*/
        quantidade_B = qtd1+qtd2+qtd3;
        Random gerador = new Random(); // Objeto para sortear os números
        

        for (int i = 1; i <= quantidade_B; i++){//aqui ele vai gerar todos o time inimigo
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
        int numero_rodadas = 0;
        //Esse primeiro loop abaixo acontecerá até ambos os lados estarem sem jogadores, a fim de definir o vencedor.
            while(!ladoA.isEmpty() && !ladoB.isEmpty()){
                int numero_combatente = 0;
                numero_rodadas++;
                while(numero_combatente<=0 || numero_combatente > ladoA.size()){
                    // Uma caixa de diálogo JOption exibida que pergunta ao usuário qual jogador ele quer em campo.
                    try{
                        String input = JOptionPane.showInputDialog("Digite a posiçào do combatente que irá ao combate agora");
                        if (input == null) {
                        numero_combatente = 0;
                        continue; // Isso aqui é pra caso o usuário clique em cancelar para o jogor não crashar
                        }
                        numero_combatente = Integer.parseInt(input);
                        } catch (NumberFormatException e){
                            System.out.print("Tente digitar um número válido"); 
                            numero_combatente = 0; 
                        }
                }
                    
                    // Ao ser selecionada o jogador que irá a combate, a lógica abaixo realiza essa operação e replica a posição
                    // escolhida pelo jogador para o adversário
                    Combatente lutadorA = ladoA.get(numero_combatente - 1);
                    Combatente lutadorB = ladoB.get(gerador1.nextInt(ladoB.size()));


                    //aqui o personagem já foi selecionado. aqui que deve ser imprimido o personagem na tela;
                    //o que o jogador escolheu.

                    if ("Guardiao".equals(lutadorA.getTipo())) {//imprime o Guardião na tela.
                        this.img_lutadorA = lutadorA.imprimir();
                        img_lutadorA.setBounds(300, 260, 400, 400);
                        fundo.add(img_lutadorA);
                        fundo.revalidate();
                        fundo.repaint();
                    }else if ("Arcanista".equals(lutadorA.getTipo())) {//imprime o arcanista na tela.
                        this.img_lutadorA = lutadorA.imprimir();
                        img_lutadorA.setBounds(300, 260, 400, 400);
                        fundo.add(img_lutadorA);
                        fundo.revalidate();
                        fundo.repaint();
                    }else{//imprime o Caçador na tela.
                        this.img_lutadorA = lutadorA.imprimir();
                        img_lutadorA.setBounds(300, 260, 400, 400);
                        fundo.add(img_lutadorA);
                        fundo.revalidate();
                        fundo.repaint();
                    }

                    //desenhar o lutador adversário.
                    if ("Guardiao".equals(lutadorB.getTipo())) {//imprime o Guardião na tela.
                        this.img_lutadorB = lutadorB.imprimir();
                        img_lutadorB.setBounds(1230,260,400,400);
                        fundo.add(img_lutadorB);
                        fundo.revalidate();
                        fundo.repaint();
                    }else if ("Arcanista".equals(lutadorB.getTipo())) {//imprime o arcanista na tela.
                        this.img_lutadorB = lutadorB.imprimir();
                        img_lutadorB.setBounds(1230,260,400,400);
                        fundo.add(img_lutadorB);
                        fundo.revalidate();
                        fundo.repaint();
                    }else{//imprime o Caçador na tela.
                        this.img_lutadorB = lutadorB.imprimir();
                        img_lutadorB.setBounds(1230,260,400,400);
                        fundo.add(img_lutadorB);
                        fundo.revalidate();
                        fundo.repaint();
                    }// até aqui está tudo certo.

                    // Abaixo está o loop que se repetirá até que algum dos dois bonecos morram em combate
                    while (lutadorA.toVivoGarai() == true && lutadorB.toVivoGarai() == true){
                        // Caixa de diálogo que perguntará qual ação o jogador tomará, atacar ou se curar?
                        String acao = JOptionPane.showInputDialog("Lutador"+ lutadorA.getNome() + "\n1 - Atacar \n2 - Curar");
                        // Esse equals nada mais é que um comparador, mas em vez do "==" do java que compara endereços
                        // o "equals" compara conteúdos
                        if("1".equals(acao)) {
                            lutadorA.atacar(lutadorB); 
                            JOptionPane.showMessageDialog(null, lutadorA.getNome() + " atacou " + lutadorB.getNome() + "!");
                        }else if("2".equals(acao)) {
                            if(lutadorA.getEstus()>0){
                                lutadorA.curar();
                                JOptionPane.showMessageDialog(null, lutadorA.getNome() + " usou uma poção de cura!");
                            }else{
                                JOptionPane.showMessageDialog(null, lutadorA.getNome() + " não tem mais Estus!");
                            }
                        }else{
                            JOptionPane.showMessageDialog(null, "Ação inválida!");
                        }
                        if (lutadorB.toVivoGarai()) {//verifica se ele está vivo.
                            // Se a vida for menor que 30% do total (ajuste esse número como preferir)
                            if (lutadorB.getVida() < 30 && lutadorB.getEstus()>0) { 
                                JOptionPane.showMessageDialog(null, lutadorB.getNome() + " cura-se! ");      
                                lutadorB.curar(); 
                            } else {
                                JOptionPane.showMessageDialog(null, lutadorB.getNome() + " parte para o ataque! ");                               
                                lutadorB.atacar(lutadorA);
                            } 
                        }
                    }
                if(!lutadorA.toVivoGarai()){//remove o lutador do meu time se estiver com vida igua a 0.
                    try {// apenas para realizar uma pausa de um segundo 
                        //prompt : como dar uma pausa entre uma linha e outra no java
                        System.out.print(lutadorA.getNome()+ " foi derrotado e saiu da arena");
                        ladoA.remove(lutadorA); // Tira o lutador derrotado da lista do Jogado
                        fundo.remove(img_lutadorA);
                        fundo.revalidate();
                        fundo.repaint();
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {}
                }
                
                if(!lutadorB.toVivoGarai()){//remove o lutador do time adversário se estiver com vida igua a 0.
                    try {// apenas para realizar uma pausa de um segundo 
                        //prompt : como dar uma pausa entre uma linha e outra no java
                        ladoB.remove(lutadorB); 
                        int psc_inimiga = new Random().nextInt(ladoB.size());
                        System.out.print(lutadorB.getNome()+ " foi derrotado e saiu da arena");
                        fundo.remove(img_lutadorB);
                        fundo.revalidate();
                        fundo.repaint();
                        Thread.sleep(1000);
                        lutadorB = ladoB.get(psc_inimiga);
                    } catch (InterruptedException e) {}
                }  
            }
            if(ladoA.isEmpty()){
                JOptionPane.showMessageDialog(null, "O Lado B venceu o Grande Torneio em "+ numero_rodadas + " !");
            } else{
                JOptionPane.showMessageDialog(null, "O Lado A venceu o Grande Torneio "+ numero_rodadas + " !");             
            }
    }

public static void main(String[] args) { //é aqui que a porca torce o rabo
    
        // Vi uma oportunidade massa que a IA falou pra gente: Iniciar a interface gráfica na thread do Swing
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                Arena a = new Arena(); //é aqui que irá fixar rodando o game.
                a.telinha();      
                a.mostrarMenu();  
            }
        });
    }

    //Metodos get
    public ArrayList<Combatente> getLadoA(){
        return this.ladoA;
    }

    public ArrayList<Combatente> getLadoB(){
        return this.ladoB;
    }
}

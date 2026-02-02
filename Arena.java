import javax.swing.*;
import java.awt.*;
import java.util.ArrayList; // Importação usada na geração das listas de combantes, a fim de atribuir objetos de diferentes classes em uma única lista
import java.util.Random; // Importação usada na parte de geração de números aleatórios para distribuição de combatentes para a classe B
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
    private JLabel ataque_A;
    private JLabel ataque_B;


    private void LutadorA_parado(Combatente lutador) {//atualizar imagem se o lutador A estiver parado.
        // Remove imagem anterior, se existir.
        if (img_lutadorA != null) {
            fundo.remove(img_lutadorA);
        }
        
        
        this.img_lutadorA = new JLabel(new ImageIcon(getClass().getResource("/imagens_do_jogo/"+lutador.getTipo()+"/"+lutador.getTipo()+"_parado.png")));
        if (img_lutadorA != null) {
            img_lutadorA.setBounds(300, 260, 400, 400);
            fundo.add(img_lutadorA);
            fundo.revalidate();
            fundo.repaint();
        }
    }

    private void LutadorA_ataque(Combatente lutador) {
    try {
        int x = 500;
        int y = 400;
        // Remove imagem anterior
        if (img_lutadorA != null) {
            fundo.remove(img_lutadorA);
        }
        
        // Cria nova imagem de ataque
        img_lutadorA = new JLabel(new ImageIcon(getClass().getResource(
            "/imagens_do_jogo/" + lutador.getTipo() + "/" + lutador.getTipo() + "_atacando.png")));
        
        if (img_lutadorA != null) {
            img_lutadorA.setBounds(300, 260, 400, 400);
            fundo.add(img_lutadorA);
            fundo.revalidate();
            fundo.repaint();
        }
        
        // Força atualização imediata
        tela.repaint();
        //aqui que deve ficar a parte de pintar na tela o ataque.
        if("cacador".equals(lutador.getTipo()) || "mago".equals(lutador.getTipo()))
            while(x<1250){
                try {
                    ataque_A = new JLabel(new ImageIcon(getClass().getResource("/imagens_do_jogo/"+lutador.getTipo()+"/"+lutador.getTipo()+"_ataque.png")));
                    ataque_A.setBounds(x,y,150,150);
                    fundo.add(ataque_A);
                    fundo.revalidate();
                    fundo.repaint();
                    Thread.sleep(20);
                    fundo.remove(ataque_A);
                    x = x + 30;;
                } catch (Exception e) {}
            }
        // Pequena pausa para visualização do ataque
        Thread.sleep(200);

        if (img_lutadorA != null) {
            fundo.remove(img_lutadorA);
        }
        
        // Restaura imagem parada
        
        this.img_lutadorA = new JLabel(new ImageIcon(getClass().getResource("/imagens_do_jogo/"+lutador.getTipo()+"/"+lutador.getTipo()+"_parado.png")));
        if (img_lutadorA != null) {
            img_lutadorA.setBounds(300, 260, 400, 400);
            fundo.add(img_lutadorA);
            fundo.revalidate();
            fundo.repaint();
        }
        
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void LutadorB_parado(Combatente lutador) {//atualizar imagem se o lutador B estiver parado.
        // Remove imagem anterior, se existir.
        if (img_lutadorB != null) {
            fundo.remove(img_lutadorB);
        }
        
        // Cria nova imagem.
        this.img_lutadorB = new JLabel(new ImageIcon(getClass().getResource("/imagens_do_jogo/"+lutador.getTipo()+"/"+lutador.getTipo()+"_parado_invertido.png")));
        if (img_lutadorB != null) {
            img_lutadorB.setBounds(1230, 260, 400, 400);
            fundo.add(img_lutadorB);
            fundo.revalidate();
            fundo.repaint();
        }
    }

    private void LutadorB_ataque(Combatente lutador) {
    try {
        int x = 1250;
        int y = 400;
        // Remove imagem anterior
        if (img_lutadorB != null) {
            fundo.remove(img_lutadorB);
        }
        
        // Cria nova imagem de ataque
        img_lutadorB = new JLabel(new ImageIcon(getClass().getResource("/imagens_do_jogo/" + lutador.getTipo() + "/" + lutador.getTipo() + "_atacando_invertido.png")));
        
        if (img_lutadorB != null) {
            img_lutadorB.setBounds(1230, 260, 400, 400);
            fundo.add(img_lutadorB);
            fundo.revalidate();
            fundo.repaint();
        }
        
        // Força atualização imediata
        tela.repaint();

        if("cacador".equals(lutador.getTipo()) || "mago".equals(lutador.getTipo()))
            while(x>500){
                try {
                    ataque_B = new JLabel(new ImageIcon(getClass().getResource("/imagens_do_jogo/"+lutador.getTipo()+"/"+lutador.getTipo()+"_ataque_invertido.png")));
                    ataque_B.setBounds(x,y,150,150);
                    fundo.add(ataque_B);
                    fundo.revalidate();
                    fundo.repaint();
                    Thread.sleep(20);
                    fundo.remove(ataque_B);
                    x = x - 30;
                } catch (Exception e) {}
            }
        
        // Pequena pausa para visualização do ataque
        Thread.sleep(200);

        if (img_lutadorB != null) {
            fundo.remove(img_lutadorB);
        }
        
        this.img_lutadorB = new JLabel(new ImageIcon(getClass().getResource("/imagens_do_jogo/"+lutador.getTipo()+"/"+lutador.getTipo()+"_parado_invertido.png")));
        if (img_lutadorB != null) {
            img_lutadorB.setBounds(1230, 260, 400, 400);
            fundo.add(img_lutadorB);
            fundo.revalidate();
            fundo.repaint();
        }
        
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
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
                painelMenu.setVisible(false); //teste 212
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

        java.net.URL imgUrl = getClass().getResource("/imagens_do_jogo/arena/fundo.jpg");
        
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
    public void Ciclo_Turnos() {
    Random gerador1 = new Random();
    int numero_rodadas = 0;
    
    // Vamos declarar a variavel fora para evitar erros de logistica quando ele perde
    Combatente lutadorA = null; 

    // A alma do RPG é essa, o nectar do nectar
    while (!ladoA.isEmpty() && !ladoB.isEmpty()) {
        numero_rodadas++;
        
        // Mexi daqui pra baixo glra, testem pra ver se não tem erro.


        // Corrigi o erro de ficar pedindo pra posicionar o inimigo
        if (lutadorA == null || !lutadorA.toVivoGarai()) {
            int numero_combatente = 0;
            
            // Aqui deixei a mesma coisa 
            while (numero_combatente <= 0 || numero_combatente > ladoA.size()) {
                try {
                    // Mostra a lista de quem está vivo para facilitar, senti que tava faltando isso na nossa gameplay
                    StringBuilder listaVivos = new StringBuilder("Escolha seu lutador:\n");
                    for(int i=0; i<ladoA.size(); i++){
                        listaVivos.append((i+1)).append(" - ").append(ladoA.get(i).getNome()).append("\n");
                    }
                    
                    String input = JOptionPane.showInputDialog(listaVivos.toString() + "\nDigite o número:");
                    
                    if (input == null) {
                        // Se cancelar, força sair ou trata de outra forma. 
                        numero_combatente = 0; 
                        continue; 
                    }
                    numero_combatente = Integer.parseInt(input);
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Digite um número válido!");
                    numero_combatente = 0;
                }
            }
            // Atualiza o lutador atual
            lutadorA = ladoA.get(numero_combatente - 1);
            
            // Atualiza a imagem do Herói (apenas quando troca ou inicia)
            LutadorA_parado(lutadorA);
        }

        // O inimigo sempre muda a cada rodada (se o anterior morreu)
        Combatente lutadorB = ladoB.get(gerador1.nextInt(ladoB.size()));
        
        // Atualiza imagem do Inimigo
        LutadorB_parado(lutadorB);

        // Combate entre lutadorA e lutadorB
        while (lutadorA.toVivoGarai() && lutadorB.toVivoGarai()) {
            
            // Turno do player
            String acao = JOptionPane.showInputDialog("Lutador " + lutadorA.getNome() + 
                    " (HP: " + lutadorA.getVida() + ")\nVS\n" + 
                    lutadorB.getNome() + " (HP: " + lutadorB.getVida() + ")" +
                    "\n\n1 - Atacar \n2 - Curar");

            if ("1".equals(acao)) {
                lutadorA.atacar(lutadorB);
                JOptionPane.showMessageDialog(null, lutadorA.getNome() + " atacou " + lutadorB.getNome() + "!");
                LutadorA_ataque(lutadorA);
            } else if ("2".equals(acao)) {
                if (lutadorA.getEstus() > 0) {
                    lutadorA.curar();
                    JOptionPane.showMessageDialog(null, lutadorA.getNome() + " usou uma poção de cura!");
                } else {
                    JOptionPane.showMessageDialog(null, lutadorA.getNome() + " não tem mais Estus!");
                }
            }

            // VERIFICAR se lutadorB (maquina) morreu após o ataque
            if (!lutadorB.toVivoGarai()) {
                break; // Sai do loop de combate, volta pro loop principal
            }

            // Turno da maquina
            if (lutadorB.toVivoGarai() && lutadorA.toVivoGarai()) {
                if (lutadorB.getVida() < 30 && lutadorB.getEstus() > 0) {
                    JOptionPane.showMessageDialog(null, lutadorB.getNome() + " cura-se!");
                    lutadorB.curar();
                } else {
                    JOptionPane.showMessageDialog(null, lutadorB.getNome() + " parte para o ataque!");
                    lutadorB.atacar(lutadorA);
                    LutadorB_ataque(lutadorB);
                }
            }
            
            // Se o lutador A morrer, o loop de combate quebra aqui
            if (!lutadorA.toVivoGarai()) {
                break; 
            }
        }

        // Memórias postumas de Bras Cubas aqui
        if (!lutadorA.toVivoGarai()) {
            try {
                JOptionPane.showMessageDialog(null, lutadorA.getNome() + " foi derrotado!");
                ladoA.remove(lutadorA);

                if (img_lutadorA != null) {
                    fundo.remove(img_lutadorA);
                    img_lutadorA = null;
                }
                fundo.revalidate();
                fundo.repaint();
                Thread.sleep(1000);
            } catch (InterruptedException e) {}
        }

        if (!lutadorB.toVivoGarai()) {
            try {
                JOptionPane.showMessageDialog(null, lutadorB.getNome() + " foi derrotado!");
                ladoB.remove(lutadorB);

                if (img_lutadorB != null) {
                    fundo.remove(img_lutadorB);
                    img_lutadorB = null;
                }
                fundo.revalidate();
                fundo.repaint();
                Thread.sleep(1000);
            } catch (InterruptedException e) {}
        }
    }

    // Por fim, a logica final, o end game.
    if (ladoA.isEmpty()) {
        JOptionPane.showMessageDialog(null, "GAME OVER! O Lado B venceu em " + numero_rodadas + " rodadas!");
    } else {
        JOptionPane.showMessageDialog(null, "VITÓRIA! O Lado A venceu o torneio em " + numero_rodadas + " rodadas!");
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

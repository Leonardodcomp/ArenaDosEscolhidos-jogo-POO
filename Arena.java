import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;  // Importação usada na geração das listas de combantes, a fim de atribuir objetos de diferentes classes em uma única lista
import java.util.Random; // Importação usada na parte de geração de números aleatórios para distribuição de combatentes para a classe B

// Os dois ultimos métodos a seguir são necessários para implementar botão no jogo
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Arena {
    private ArrayList<Combatente> ladoA = new ArrayList<>(); // Como o jogo terá vários guerreiros por partida, necessitamos dá criação de listas com esses guerreiros de modo a alterná-los durante a partida

    private ArrayList<Combatente> ladoB = new ArrayList<>(); //tonar o JFrame um atributo de classe.
    private JFrame tela;
    private JLabel fundo;
    private JLabel img_lutadorA;
    private JLabel img_lutadorB;
    private JPanel painelMenu; //Colocamos esse atributo novo por causa do menu e da logistica de botão.
    private JLabel ataque_A;
    private JLabel ataque_B;


    private void LutadorA_parado(Combatente lutador) {
        SwingUtilities.invokeLater(() -> {  // Threads está muito bugado para linux/MacOS, precisei da uma "protecao para ele, vou usar o invokeLater e mudar um pouco a sintaxe e deixar a mesma lógica que funciona para o Windows"
            if (img_lutadorA != null) {
                fundo.remove(img_lutadorA);
            }
            
            // Carrega a imagem
            java.net.URL url = getClass().getResource("/imagens_do_jogo/"+lutador.getTipo()+"/"+lutador.getTipo()+"_parado.png");
            if (url != null) {
                img_lutadorA = new JLabel(new ImageIcon(url));
                img_lutadorA.setBounds(300, 260, 400, 400);
                fundo.add(img_lutadorA);
                
                fundo.revalidate();
                fundo.repaint();
                Toolkit.getDefaultToolkit().sync(); // Correção para Linux sempre após o repaint.
            }
        });
    }

    private void LutadorA_ataque(Combatente lutador) {
    
        SwingUtilities.invokeLater(() -> {

            // Remove imagem anterior
            if (img_lutadorA != null) fundo.remove(img_lutadorA);
            
            java.net.URL url = getClass().getResource("/imagens_do_jogo/" + lutador.getTipo() + "/" + lutador.getTipo() + "_atacando.png");
            if (url != null) {
                img_lutadorA = new JLabel(new ImageIcon(url));
                img_lutadorA.setBounds(300, 260, 400, 400);
                fundo.add(img_lutadorA);
                fundo.revalidate();
                fundo.repaint();
                Toolkit.getDefaultToolkit().sync();
            }
        });

        // Animação do projetil (Lógica + Visual Sincronizado)
        if("cacador".equals(lutador.getTipo()) || "mago".equals(lutador.getTipo())) {
            int x = 500;
            int y = 400;

             //aqui que deve ficar a parte de pintar na tela o ataque.
            while(x < 1250) {
                final int finalX = x; 
                
                // Desenha o projétil
                SwingUtilities.invokeLater(() -> {
                    try {
                        ataque_A = new JLabel(new ImageIcon(getClass().getResource("/imagens_do_jogo/"+lutador.getTipo()+"/"+lutador.getTipo()+"_ataque.png")));
                        ataque_A.setBounds(finalX, y, 150, 150);
                        fundo.add(ataque_A);
                        fundo.revalidate();

                        fundo.repaint();
                        Toolkit.getDefaultToolkit().sync();
                    } catch (Exception e) {}
                });

                // Espera (Lógica do jogo para)
                try { Thread.sleep(20); } catch (InterruptedException e) {}

                // Remove o projétil
                SwingUtilities.invokeLater(() -> {
                    if(ataque_A != null) fundo.remove(ataque_A);
                    fundo.repaint();
                });

                x = x + 30;
            }
        }

        // Pequena pausa para ver a pose final
        try { Thread.sleep(200); } catch (InterruptedException e) {}

        // Volta para postura parado
        LutadorA_parado(lutador);
    }

    private void LutadorB_parado(Combatente lutador) {
        SwingUtilities.invokeLater(() -> {
            if (img_lutadorB != null) {
                fundo.remove(img_lutadorB);
            }
            
            java.net.URL url = getClass().getResource("/imagens_do_jogo/"+lutador.getTipo()+"/"+lutador.getTipo()+"_parado_invertido.png");
            if (url != null) {
                img_lutadorB = new JLabel(new ImageIcon(url));
                img_lutadorB.setBounds(1230, 260, 400, 400);
                fundo.add(img_lutadorB);
                
                fundo.revalidate();
                fundo.repaint();
                Toolkit.getDefaultToolkit().sync(); // <--- Correção Linux
            }
        });
    }

    private void LutadorB_ataque(Combatente lutador) {
        SwingUtilities.invokeLater(() -> {
            if (img_lutadorB != null) fundo.remove(img_lutadorB);
            
            java.net.URL url = getClass().getResource("/imagens_do_jogo/" + lutador.getTipo() + "/" + lutador.getTipo() + "_atacando_invertido.png");
            if (url != null) {
                img_lutadorB = new JLabel(new ImageIcon(url));
                img_lutadorB.setBounds(1230, 260, 400, 400);
                fundo.add(img_lutadorB);
                fundo.revalidate();
                fundo.repaint();
                Toolkit.getDefaultToolkit().sync();
            }
        });

        if("cacador".equals(lutador.getTipo()) || "mago".equals(lutador.getTipo())) {
            int x = 1250;
            int y = 400;
            
            while(x > 500) {
                final int finalX = x;

                SwingUtilities.invokeLater(() -> {
                    try {
                        ataque_B = new JLabel(new ImageIcon(getClass().getResource("/imagens_do_jogo/"+lutador.getTipo()+"/"+lutador.getTipo()+"_ataque_invertido.png")));
                        ataque_B.setBounds(finalX, y, 150, 150);
                        fundo.add(ataque_B);
                        fundo.revalidate();
                        fundo.repaint();
                        Toolkit.getDefaultToolkit().sync();
                    } catch (Exception e) {}
                });

                try { Thread.sleep(20); } catch (InterruptedException e) {}

                SwingUtilities.invokeLater(() -> {
                    if(ataque_B != null) fundo.remove(ataque_B);
                    fundo.repaint();
                });

                x = x - 30;
            }
        }
        
        try { Thread.sleep(200); } catch (InterruptedException e) {}
        LutadorB_parado(lutador);
    }
    
    // Config básica da tela (onde provavelmente o linux dava ruim)



    public void telinha() {
        tela = new JFrame("Arena dos Escolhidos!");
        tela.setExtendedState(JFrame.MAXIMIZED_BOTH);
        tela.setUndecorated(false); // Jogo abre em modo janela para poder fechar no meio do jogo caso queira.
        tela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        tela.setLayout(null); // Layout nulo para posicionamento absoluto
        tela.getContentPane().setBackground(Color.BLACK);
        // Tirei o setVisible daqui
    }

    //Menuzinho pro jogo não ir direto pra gameplay

    public void mostrarMenu() {
        painelMenu = new JPanel();
        painelMenu.setLayout(null);
        painelMenu.setBackground(Color.BLACK);
        
        // Ajuste para garantir que pegue o tamanho da tela mesmo antes de maximizar totalmente
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        painelMenu.setBounds(0, 0, screenSize.width, screenSize.height);

        JLabel titulo = new JLabel("ARENA DOS ESCOLHIDOS");
        titulo.setFont(new Font("Serif", Font.BOLD, 50));
        titulo.setForeground(Color.WHITE);
        titulo.setHorizontalAlignment(SwingConstants.CENTER);
        titulo.setBounds(0, screenSize.height/2 - 150, screenSize.width, 60);
        painelMenu.add(titulo);

        JButton btnComecar = new JButton("COMEÇAR");
        btnComecar.setFont(new Font("Arial", Font.BOLD, 20));
        btnComecar.setBackground(Color.RED);
        btnComecar.setForeground(Color.WHITE);

        int btnLargura = 200;
        int btnAltura = 60;
        btnComecar.setBounds((screenSize.width - btnLargura)/2, screenSize.height/2, btnLargura, btnAltura);

        btnComecar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Remove o menu na Thread de Eventos
                painelMenu.setVisible(false);
                tela.remove(painelMenu);
                tela.repaint();
                Toolkit.getDefaultToolkit().sync();

                // Inicia o jogo numa Thread separada
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        carregarCenario(); 
                        InicializarTimes();  
                        Ciclo_Turnos();    
                    }
                }).start();
            }
        });

        painelMenu.add(btnComecar);
        tela.add(painelMenu);
        
        // Coloquei o setVisible apenas no final da configuração
        tela.setVisible(true);
    }

    public void carregarCenario() {
        // Envelopado no invokeLater para garantir segurança de thread
        SwingUtilities.invokeLater(() -> {
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
            tela.revalidate();
            tela.repaint();
            Toolkit.getDefaultToolkit().sync(); // Sempre colocando depois do repaint (novo ajuste)
        });
    }

    // Lógica do jogo, deixei mais "clean" eu acho. Aproveitando que to mexendo na implementação com Linux e MacOS

    public void InicializarTimes(){
        // Importante galera: JOptionPane pausa a Thread atual (que é a do jogo), mas não trava a tela.
        // Então não precisamos de invokeLater aqui para os inputs.
        
        int qtd1 = obterQuantidade("Quantos Arcanistas no Lado A?");
        for (int i = 1; i <= qtd1; i++){
            String input = JOptionPane.showInputDialog("Insira o nome do arcanista:");
            ladoA.add(new Arcanista(input != null ? input : "Arcanista " + i));
        }

        int qtd2 = obterQuantidade("Quantos Caçadores no Lado A?");
        for (int i = 1; i <= qtd2; i++){
            String input = JOptionPane.showInputDialog("Insira o nome do caçador:");
            ladoA.add(new Cacador(input != null ? input : "Caçador " + i));
        }

        int qtd3 = obterQuantidade("Quantos Guardiões no Lado A?");
        for (int i = 1; i <= qtd3; i++){
            String input = JOptionPane.showInputDialog("Insira o nome do Guardião:");
            ladoA.add(new Guardiao(input != null ? input : "Guardião " + i));
        }

        int quantidade_B = qtd1 + qtd2 + qtd3;
        Random gerador = new Random();

        for (int i = 1; i <= quantidade_B; i++){
            int escolha = gerador.nextInt(3);
            if(escolha == 0){
                ladoB.add(new Arcanista("Inimigo Mago " + i));
            } else if(escolha == 1) {
                ladoB.add(new Guardiao("Inimigo Guardiao " + i));
            } else{
                 ladoB.add(new Cacador("Inimigo Cacador " + i));
            }
        }
    }

    // Método auxiliar para limpar o código de inputs
    private int obterQuantidade(String mensagem) {
        try {
            String input = JOptionPane.showInputDialog(mensagem);
            if (input == null) return 0;
            int valor = Integer.parseInt(input);
            return valor > 0 ? valor : 0;
        } catch(NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Insira apenas numeros inteiros!");
            return 0;
        }
    }


    // Aqui uma parte importante do código que definirá os turnos de cada jogador
    // ou seja, qual ação o jogador tomará? Atacar ou defender? Assim como um IA para o adversário

    public void Ciclo_Turnos() {
        Random gerador1 = new Random();
        int numero_rodadas = 0;

        // Vamos declarar a variavel fora para evitar erros de logistica quando ele perde (Achei isso feio, mas to deixando)
        Combatente lutadorA = null; 


         // A alma do RPG é essa, o nectar do nectar
        while (!ladoA.isEmpty() && !ladoB.isEmpty()) {
            numero_rodadas++;
            
            // Escolha do Lutador A
            if (lutadorA == null || !lutadorA.toVivoGarai()) {
                int numero_combatente = 0;
                
                while (numero_combatente <= 0 || numero_combatente > ladoA.size()) {
                    try {
                        // Mostra a lista de quem está vivo para facilitar, senti que tava faltando isso na nossa gameplay
                        
                        StringBuilder listaVivos = new StringBuilder("Escolha seu lutador:\n");
                        for(int i=0; i<ladoA.size(); i++){
                            listaVivos.append((i+1)).append(" - ").append(ladoA.get(i).getNome()).append("\n");
                        }
                        
                        String input = JOptionPane.showInputDialog(listaVivos.toString() + "\nDigite o número:");
                        
                        if (input == null) {
                            numero_combatente = 1; // Logicazinha nova
                            // Deixei Default para não travar se cancelar
                        } else {
                            numero_combatente = Integer.parseInt(input);
                        }
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(null, "Digite um número válido!");
                        numero_combatente = 0;
                    }
                }
                lutadorA = ladoA.get(numero_combatente - 1);
                LutadorA_parado(lutadorA);
            }

            // Escolha do Inimigo
            Combatente lutadorB = ladoB.get(gerador1.nextInt(ladoB.size()));
            LutadorB_parado(lutadorB);

            // Combate
            while (lutadorA.toVivoGarai() && lutadorB.toVivoGarai()) {
                
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

                if (!lutadorB.toVivoGarai()) break;

                // Turno Inimigo
                if (lutadorB.getVida() < 30 && lutadorB.getEstus() > 0) {
                    JOptionPane.showMessageDialog(null, lutadorB.getNome() + " cura-se!");
                    lutadorB.curar();
                } else {
                    JOptionPane.showMessageDialog(null, lutadorB.getNome() + " parte para o ataque!");
                    lutadorB.atacar(lutadorA);
                    LutadorB_ataque(lutadorB);
                }
            }

            // Memórias postumas de Brás Cubas aqui
            if (!lutadorA.toVivoGarai()) {
                JOptionPane.showMessageDialog(null, lutadorA.getNome() + " foi derrotado!");
                ladoA.remove(lutadorA);
                
                // Atualiza visual
                SwingUtilities.invokeLater(() -> {
                    if (img_lutadorA != null) {
                        fundo.remove(img_lutadorA);
                        img_lutadorA = null;
                        fundo.repaint();
                        Toolkit.getDefaultToolkit().sync();
                    }
                });
            }

            if (!lutadorB.toVivoGarai()) {
                JOptionPane.showMessageDialog(null, lutadorB.getNome() + " foi derrotado!");
                ladoB.remove(lutadorB);
                
                SwingUtilities.invokeLater(() -> {
                    if (img_lutadorB != null) {
                        fundo.remove(img_lutadorB);
                        img_lutadorB = null;
                        fundo.repaint();
                        Toolkit.getDefaultToolkit().sync();
                    }
                });
            }
        }
        // Por fim, a logica final, o end game.
        if (ladoA.isEmpty()) {
            JOptionPane.showMessageDialog(null, "GAME OVER! O Lado B venceu em " + numero_rodadas + " rodadas!");
        } else {
            JOptionPane.showMessageDialog(null, "VITÓRIA! O Lado A venceu o torneio em " + numero_rodadas + " rodadas!");
        }
        
        // Fecha o jogo ou reinicia (lógica pra não ficar tela estática depois que acaba)
        System.exit(0);
    }

    public static void main(String[] args) { //é aqui que a porca torce o rabo
    // Vi uma oportunidade massa que a IA falou pra gente: Iniciar a interface gráfica na thread do Swing
        SwingUtilities.invokeLater(() -> {
            Arena a = new Arena();  //é aqui que irá fixar rodando o game.
            a.telinha();      
            a.mostrarMenu();  
        });
    }

    public ArrayList<Combatente> getLadoA(){ 
        return this.ladoA; 
        }
    public ArrayList<Combatente> getLadoB(){ 
        return this.ladoB; 
        }
}
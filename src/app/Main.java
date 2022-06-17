package app;
import app.sweeper.Box;
import app.sweeper.Coord;
import app.sweeper.Game;
import app.sweeper.Ranges;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Objects;


public class Main extends JFrame {
    //класс панели
    private JPanel panel;
    // объявляем фасадный класс
    private final Game game;
    private JLabel label;
    // константы
    final int COLS = 9;
    final int ROWS = 9;
    final int IMAGE_SIZE = 50;
    final int BOMBS_COUNT = 12;


    public static void main(String[] args) {
        new Main().setVisible(true);
    }

    // конструктор main
    private  Main (){
        // шаблон проектирования фасад
        game = new Game(COLS, ROWS, BOMBS_COUNT);
        game.start();

        Ranges.setSize(new Coord(COLS, ROWS));
        setImages();
        initLable();
        initPanel();
        initFrame();
    }

    private void initLable (){
        label = new JLabel("Welcom");
        //add(label, BorderLayout.SOUTH);
    }


    // создаем панель программы
    private void initPanel(){
        panel = new JPanel(){
            protected void paintComponent(Graphics graphics) {
                super.paintComponent(graphics);

                for (Coord coord : Ranges.getAllCoords()){

                    graphics.drawImage((Image) game.getBox(coord).image, coord.x * IMAGE_SIZE, coord.y * IMAGE_SIZE, this);
                }
            }
        };
        // адаптор для мыши
        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent event) {
                int x = event.getX() / IMAGE_SIZE;
                int y = event.getY() / IMAGE_SIZE;
                Coord coord = new Coord(x,y);
                if (event.getButton() == MouseEvent.BUTTON1){
                    game.pressLeftButton (coord);
                }
                if (event.getButton() == MouseEvent.BUTTON3){
                    game.pressRightButton (coord);
                }
                if (event.getButton() == MouseEvent.BUTTON2){
                    game.start();
                }
                label.setText(getMassage());
                panel.repaint();
            }
        });

        panel.setPreferredSize(new Dimension(
                Ranges.getSize().x * IMAGE_SIZE,
                Ranges.getSize().y * IMAGE_SIZE
        ));
        add(panel);
    }

    // создаем окно
    private void initFrame(){
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Sweeper");
        setResizable(false);
        setVisible(true);
        pack();
        setLocationRelativeTo(null);
        setIconImage(getImage("icon.png"));
    }


    //
    private void setImages(){
        for(Box box : Box.values()){
            box.image = getImage(box.getImageName());
        }
    }

    // получатель изображений из res
    private Image getImage (String name){
        String fileName = "/img/" + name;
        ImageIcon icon = new ImageIcon(Objects.requireNonNull(getClass().getResource(fileName)));
        return icon.getImage();
    }

    private String getMassage() {
        switch (game.getState()){
            case PLAYD -> {return "PLAYD";}
            case BOMBED -> {return "lox";}
            case WIN -> {return "WIN";}
            default -> {return "";}
        }
    }
}

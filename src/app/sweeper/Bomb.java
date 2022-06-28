package app.sweeper;

public class Bomb {
    private Matrix bombMap;
    private int totalBombs;

    Bomb (int totalBombs){
        this.totalBombs = totalBombs;
        // проверка на допустимое количество мин и исправление переменной в случае превышения
        fixBombsCount();
    }


    void start (){
        bombMap = new Matrix(Box.NUM0);
        for (int j = 0; j < totalBombs; j ++){
            placeBomb();
        }

    }

    Box get (Coord coord){
        return bombMap.get(coord);
    }


    private void placeBomb() {
        // цикл проверки на дубли
        while (true){
            Coord coord = Ranges.getRandomCoord();
            if (Box.MINE == bombMap.get(coord)){continue;}
            bombMap.set(coord, Box.MINE);
            incNumbersAroundBomb(coord);
            break;
        }


    }
    private void fixBombsCount(){
        int maxBombs = Ranges.getSize().x * Ranges.getSize().y;
        if (totalBombs > maxBombs){
            totalBombs = maxBombs;
        }
    }

    private void incNumbersAroundBomb(Coord coord){
        for (Coord around : Ranges.getCoordsAround(coord)){
            if (Box.MINE != bombMap.get(around)){
                bombMap.set (around, bombMap.get(around).getNextNumberBox());
            }
        }
    }

    int getTotalBombs() {
        return totalBombs;
    }

}

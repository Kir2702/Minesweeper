package app.sweeper;

public class Game {
    Bomb bomb;
    Flag flag;
    private GameState state;
    public GameState getState() {
        return state;
    }



    public Game (int cols, int rows, int bombs){
        Ranges.setSize(new Coord(cols, rows));
        bomb = new Bomb(bombs);
        flag = new Flag();
    }


    public void start (){
        bomb.start();
        flag.start();
        state = GameState.PLAYD;
    }


    public  Box getBox (Coord coord){
        if (flag.get(coord)==Box.OPENED){
            return bomb.get(coord);
        }else{
            return flag.get(coord);
        }
    }

    public void pressLeftButton(Coord coord) {
        if (gameOver()) return;
        openBox(coord);
        checkWinner();
    }

    public void pressRightButton(Coord coord) {
        if (gameOver()) return;
        flag.toggleFlagToBox(coord);
    }

    private boolean gameOver() {
        if (state == GameState.PLAYD){
            return false;
        }
        start();
        return true;
    }

    private void checkWinner(){
        if (state == GameState.PLAYD){
            if (flag.getCountOfClosesBoxes() == bomb.getTotalBombs()){
                state = GameState.WIN;

            }
        }
    }


    public void openBox(Coord coord){
        switch (flag.get(coord)){
            case OPENED -> {setOpenedToClosedBoxesAroundNumber(coord);}
            //case OPENED -> {return;}
            case FLAG -> {return;}
            case CLOS -> {
                switch (bomb.get(coord)){
                    case NUM0 -> {openBoxAround(coord);}
                    case MINE -> {openBombs(coord);}
                    default -> flag.setOpenedToBox(coord);
                }
            }
        }
    }

    void setOpenedToClosedBoxesAroundNumber (Coord coord){
        if(bomb.get(coord) != Box.MINE){
            if (flag.getCountOfFlaggedBoxesAround(coord) == bomb.get(coord).getNumber()){
                for (Coord around: Ranges.getCoordsAround(coord)){
                    if (flag.get(around) == Box.CLOS){
                        openBox(around);
                    }
                }
            }
        }
    }

    private void openBombs(Coord bombed) {
        state = GameState.BOMBED;
        flag.setBombedToBox(bombed);
        for (Coord coord: Ranges.getAllCoords()){
            if (bomb.get(coord)==Box.MINE){
                flag.setOpenedToClosedBombBox(coord);
            } else {
                flag.setNoBombToFlagSafeBox(coord);
            }
        }
    }

    private void openBoxAround(Coord coord) {
        flag.setOpenedToBox(coord);
        for (Coord around: Ranges.getCoordsAround(coord)){
            openBox(around);
        }
    }
}


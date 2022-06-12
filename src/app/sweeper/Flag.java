package app.sweeper;

class Flag {
    private Matrix flagMap;
    private int countOfClosBox;

    void start(){
        flagMap = new Matrix(Box.CLOS);
        countOfClosBox = Ranges.getSize().x * Ranges.getSize().y;
    }

    Box get (Coord coord){
        return  flagMap.get(coord);
    }

    void setOpenedToBox(Coord coord) {
        flagMap.set(coord, Box.OPENED);
        countOfClosBox --;
    }

    void setFlagedToBox(Coord coord) {

        flagMap.set(coord, Box.FLAG);
    }

    void toggleFlagToBox(Coord coord) {
        switch (flagMap.get(coord)){
            case FLAG -> setClosedToBox(coord);
            case CLOS -> setFlagedToBox(coord);
        }

    }

    void setClosedToBox(Coord coord) {
        flagMap.set(coord, Box.CLOS);
    }

    int getCountOfClosesBoxes() {
        return countOfClosBox;
    }

    void setBombedToBox(Coord coord) {
        flagMap.set(coord, Box.RED);
    }

    public void setOpenedToClosedBombBox(Coord coord) {
        if (flagMap.get(coord) == Box.CLOS){
            flagMap.set(coord, Box.MINE);
        }
    }

    public void setNoBombToFlagSafeBox(Coord coord) {
        if(flagMap.get(coord)==Box.FLAG){
            flagMap.set(coord, Box.NOMINE);
        }
    }


    public int getCountOfFlaggedBoxesAround(Coord coord) {
        int count = 0;
        for (Coord around: Ranges.getCoordsAround(coord)){
            if (flagMap.get(around) == Box.FLAG){
                count++;
            }
        }
        return count;
    }
}

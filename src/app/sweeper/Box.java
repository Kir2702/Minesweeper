package app.sweeper;

public enum Box {
    NUM0("0.png"),
    NUM1("1.png"),
    NUM2("2.png"),
    NUM3("3.png"),
    NUM4("4.png"),
    NUM5("5.png"),
    NUM6("6.png"),
    NUM7("7.png"),
    NUM8("8.png"),
    CLOS("clos.png"),
    FLAG("flag.png"),
    MINE("mine.png"),
    NOMINE("nomine.png"),
    OPENED("opened.png"),
    QUES("ques.png"),
    RED("red.png");

    private String box;
    Box(String imageName) {this.box = imageName;};
    public String getImageName() {return box;}

    public Object image;

    Box getNextNumberBox(){
        // не даем перечислением выдать значение следующие за NUM8
        if (this.getImageName() == Box.NUM8.getImageName()){
            return Box.NUM8;
        } else {
            return  Box.values()[this.ordinal() + 1];
        }
    }


    public int getNumber() {
        return this.ordinal();
    }
}

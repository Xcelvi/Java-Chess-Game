package pieces;



public enum HavePiecesMoved {
    WHITEKNIGHT(false),
    BLACKKNIGHT(false),
    WHITEROOKL(false),
    WHITEROOKR(false),
    BLACKROOKL(false),
    BLACKROOKR(false),
    WHITEKING(false),
    BLACKKING(false),
    WHITEQUEEN(false),
    BLACKQUEEN(false),;

    boolean hasMoved;

    HavePiecesMoved(boolean hasMoved) {
        this.hasMoved = hasMoved;
    }

    public void setHasMoved(boolean hasMoved) {
        this.hasMoved = hasMoved;
    }
    public boolean getHasMoved() {
        return !hasMoved;
    }
}

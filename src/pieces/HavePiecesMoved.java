package pieces;


import java.util.ArrayList;

public enum HavePiecesMoved {
    WHITEKNIGHT(false, new ArrayList<>(), new ArrayList<>()),
    BLACKKNIGHT(false, new ArrayList<>(), new ArrayList<>()),
    WHITEROOKL(false, new ArrayList<>(), new ArrayList<>()),
    WHITEROOKR(false, new ArrayList<>(), new ArrayList<>()),
    BLACKROOKL(false, new ArrayList<>(), new ArrayList<>()),
    BLACKROOKR(false, new ArrayList<>(), new ArrayList<>()),
    WHITEKING(false, new ArrayList<>(), new ArrayList<>()),
    BLACKKING(false, new ArrayList<>(), new ArrayList<>()),
    WHITEQUEEN(false, new ArrayList<>(), new ArrayList<>()),
    BLACKQUEEN(false, new ArrayList<>(), new ArrayList<>()),;

    private boolean hasMoved;

    private ArrayList<String> pieceVision = new ArrayList<>();
    private ArrayList<String> pieceFullVision = new ArrayList<>();

    HavePiecesMoved(boolean hasMoved,  ArrayList<String> pieceVision, ArrayList<String> pieceFullVision) {
        this.hasMoved = hasMoved;
        this.pieceVision = pieceVision;
        this.pieceFullVision = pieceFullVision;
    }

    public void setHasMoved(boolean hasMoved) {
        this.hasMoved = hasMoved;
    }
    public void setPieceVision(ArrayList<String> pieceVision) {
        this.pieceVision = pieceVision;
    }
    public boolean getHasMoved() {
        return !hasMoved;
    }

}

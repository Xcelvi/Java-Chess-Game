package pieces;

import java.util.ArrayList;

public interface Vision {
    ArrayList<String> getPieceVision(int col, int row);
    ArrayList<String> getPieceFullVision(int col, int row);
}

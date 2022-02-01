import javax.swing.JFrame;

public class Block {
    private int blockValue;
    private boolean visitCheck;
    
    Block () {
        blockValue = 0;
        visitCheck = false;
    }

    public int getBlockValue () {
        return blockValue;
    }

    public void setBlockValue (int inputValue) {
        this.blockValue = inputValue;
    }

    public boolean getVisit () {
        return visitCheck;
    }

    public void setVisit (boolean inputValue) {
        this.visitCheck = inputValue;
    }
}

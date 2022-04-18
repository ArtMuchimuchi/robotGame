import java.io.Serializable;

public class MessagePack implements Serializable {
    private String message;

    MessagePack () {
        message = null;
    }

    public void setMessage (String inputMessage) {
        message = inputMessage;
    }

    public String readMessage () {
        return message;
    }

}

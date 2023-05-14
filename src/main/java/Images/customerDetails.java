package Images;

import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;
@Setter
@Getter
public class customerDetails {
    Image logo ;
    ImageIcon eyeIcon ;

    private String customerName;

    public customerDetails(String restarantName) {

        if (restarantName.equalsIgnoreCase("Domino's")) {
            this.logo = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/dominos.png"));
            this.customerName = "DOMINO'S";
        } else {
           this.logo = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/kfc.jpg"));
            this.customerName = "KFC";
        }
        eyeIcon = new ImageIcon(getClass().getResource("/images/eye.png"));
    }


    public customerDetails() {

    }
}

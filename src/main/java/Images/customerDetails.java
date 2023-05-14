package Images;

import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;
@Setter
@Getter
public class customerDetails {
    Image logo = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/dominos.png"));

    ImageIcon eyeIcon = new ImageIcon(getClass().getResource("/images/eye.png"));

    private String customerName = "DOMINO'S";
}

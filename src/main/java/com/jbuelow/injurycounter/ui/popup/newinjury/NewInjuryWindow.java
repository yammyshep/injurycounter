package com.jbuelow.injurycounter.ui.popup.newinjury;

import javax.swing.JFrame;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("useUI")
public class NewInjuryWindow extends JFrame {

}

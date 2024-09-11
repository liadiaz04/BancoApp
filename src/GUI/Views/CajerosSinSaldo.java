package GUI.Views;

import java.awt.event.ActionListener;

import javax.swing.JLabel;

import GUI.Components.BaseScreenWithSideMenu;

public class CajerosSinSaldo extends BaseScreenWithSideMenu{

	public CajerosSinSaldo(ActionListener listener) {
		super(listener);
		
	}

	@Override
	protected void loadContent() {
		JLabel label = new JLabel("CJEROS SIN SALDO");
        label.setBounds(550, 50, 200, 30);
        add(label);

		
	}

}


package trabalhodepoo1;

import javax.swing.SwingUtilities;


public class LembretesApp {
      public static void main(String[] args) {
        AnotacoesApp app = new AnotacoesApp();

        SwingUtilities.invokeLater(() -> {
            LembretesInterface interfaceLembretes = new LembretesInterface();
        });
    }
}

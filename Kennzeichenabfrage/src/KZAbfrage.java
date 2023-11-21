import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;

public class KZAbfrage {
    private JTextField inputField;
    private JButton search;
    private JLabel resultLabel;
    private JFrame frame;

    public KZAbfrage() {
        frame = new JFrame("KZAbfrage");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridBagLayout());

        inputField = new JTextField(2);
        search = new JButton("Search");
        resultLabel = new JLabel("Suche starten.");
        resultLabel.setBorder(new CompoundBorder(
                new LineBorder(Color.BLACK),
                new EmptyBorder(5, 10, 5, 10)
        ));
        resultLabel.setOpaque(true);
        resultLabel.setBackground(Color.WHITE);

        // Set preferred sizes to align heights and adjust button width
        inputField.setPreferredSize(new Dimension(50, 20));
        resultLabel.setPreferredSize(new Dimension(150, 20));
        search.setPreferredSize(new Dimension(90, 25)); // Approximately 30% of window length

        String longestString = "ND;Neusiedl am See, Diplomatisches Korps in Niederösterreich";
        Dimension preferredSize = new Dimension(longestString.length() * 7, 20); // Approximate width calculation
        resultLabel.setPreferredSize(preferredSize);

        GridBagConstraints gbc = new GridBagConstraints();

        // Input Field
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 5, 5);
        frame.add(inputField, gbc);

        // Result Label
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 5, 5, 10);
        frame.add(resultLabel, gbc);

        // Search Button
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(0, 10, 10, 10);
        frame.add(search, gbc);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public void setupSearchButtonAction(ReadFile readFile) {
        search.addActionListener(e -> {
            String input = inputField.getText();
            try {
                String result = readFile.readFile(input);
                if (result.equals("Kennzeichen nicht gefunden!") || result.equals("Kennzeichen-Datei nicht gefunden!")) {
                    JOptionPane.showMessageDialog(frame, result, "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    resultLabel.setText(result);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "An error occurred: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}

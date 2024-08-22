import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

public class ChatBot extends JFrame {

    private JPanel panel;
    private JTextField textField;
    private JTextArea textArea;
    private Map<String, String> responses;

    public ChatBot() {
        super("Chat Bot");

        // Initialize GUI components
        panel = new JPanel();
        panel.setLayout(new BorderLayout());

        textField = new JTextField();
        textArea = new JTextArea();
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);

        // Add components to the panel
        panel.add(textField, BorderLayout.SOUTH);
        panel.add(scrollPane, BorderLayout.CENTER);

        // Initialize response map with common questions and answers
        initializeResponses();

        // Add action listener for text field input
        textField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String input = textField.getText().trim().toLowerCase();
                textField.setText("");
                String response = getResponse(input);
                appendChat("You: " + input);
                appendChat("Bot: " + response);
            }
        });

        // Set up the main frame
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(400, 400);
        this.add(panel);
        this.setVisible(true);
    }

    private void initializeResponses() {
        responses = new HashMap<>();
        responses.put("how are you", "I'm doing well, thanks for asking!");
        responses.put("weather", "I'm not sure, you can check your local weather forecast online.");
        responses.put("time", "It is currently " + LocalTime.now() + ".");
        responses.put("hello", "Hi there! How can I assist you today?");
        responses.put("bye", "Goodbye! Have a great day!");
        // Add more predefined responses as needed
    }

    private void appendChat(String message) {
        textArea.append(message + "\n");
    }

    private String getResponse(String input) {
        // Look for a response that matches the input
        for (Map.Entry<String, String> entry : responses.entrySet()) {
            if (input.contains(entry.getKey())) {
                return entry.getValue();
            }
        }
        // Default response if no match is found
        return "I'm sorry, I don't understand. Can you please rephrase your question?";
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ChatBot());
    }
}

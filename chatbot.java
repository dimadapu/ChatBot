import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class ChatBot extends JFrame {
    private JTextArea chatArea;
    private JTextField inputField;
    private JButton sendButton;
    private HashMap<String, String> faq;

    public ChatBot() {
        setTitle("AI ChatBot");
        setSize(400, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        chatArea = new JTextArea();
        chatArea.setEditable(false);
        add(new JScrollPane(chatArea), BorderLayout.CENTER);

        JPanel inputPanel = new JPanel(new BorderLayout());
        inputField = new JTextField();
        sendButton = new JButton("Send");
        inputPanel.add(inputField, BorderLayout.CENTER);
        inputPanel.add(sendButton, BorderLayout.EAST);
        add(inputPanel, BorderLayout.SOUTH);

        faq = new HashMap<>();
        loadResponses();

        sendButton.addActionListener(e -> respond());
        inputField.addActionListener(e -> respond());

        setVisible(true);
    }

    private void loadResponses() {
        faq.put("hi", "Hello! How can I assist you today?");
        faq.put("how are you", "I'm doing great, thanks!");
        faq.put("what is your name", "I'm SmartBot, your virtual assistant.");
        faq.put("help", "You can ask me about booking, support, or FAQs.");
        faq.put("bye", "Goodbye! Have a nice day.");
    }

    private void respond() {
        String userInput = inputField.getText().toLowerCase().trim();
        chatArea.append("You: " + userInput + "\n");
        inputField.setText("");

        String reply = "Sorry, I didn't understand that.";
        for (String key : faq.keySet()) {
            if (userInput.contains(key)) {
                reply = faq.get(key);
                break;
            }
        }

        chatArea.append("Bot: " + reply + "\n");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ChatBot::new);
    }
}

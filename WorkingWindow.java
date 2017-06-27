import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WorkingWindow extends JFrame {
    private JPanel rootPanel;
    private JButton loadButton;
    private JButton startButton;
    private JButton nextButton;
    private JButton finishButton;
    private JTextField startParam;
    private JTextPane outputText;
    private JButton exitButton;
    private JButton resultButton;
    private PrimAlgorithm prim;
    private int startParamNum;

    private int windowSizeH = 600;
    private int windowSizeW = 1100;


    public WorkingWindow() {

        setContentPane(rootPanel);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        prim = new PrimAlgorithm();

        ActionListener loadActionListener = new LoadButtonActionListener();
        ActionListener startActionListener = new StartButtonActionListener();
        ActionListener finishActionListener = new FinishButtonActionListener();
        ActionListener exitActionListener = new ExitButtonActionListener();
        ActionListener resultActionListener = new ResultButtonActionListener();
        ActionListener nextActionListener = new NextButtonActionListener();

        loadButton.addActionListener(loadActionListener);
        startButton.addActionListener(startActionListener);
        nextButton.addActionListener(nextActionListener);
        finishButton.addActionListener(finishActionListener);
        exitButton.addActionListener(exitActionListener);
        resultButton.addActionListener(resultActionListener);

        startButton.setEnabled(false);
        nextButton.setEnabled(false);
      resultButton.setEnabled(false);

        setSize(windowSizeW, windowSizeH);
        setResizable(false);
        setVisible(true);
    }


    public void paintGraph() {
        clear();
        Graphics2D gfx = (Graphics2D) rootPanel.getGraphics();
        gfx.setColor(Color.lightGray
        );

        double pi = 3.14;
        double angle = 2 * pi / (prim.getQuantityVertices());
        int r = windowSizeH / 4,
                u = windowSizeW / 5 + 20,
                v = windowSizeH / 3 + 50,
                rPoint = 24;


        Font SegoiUI = new Font("American Typewriter", Font.PLAIN, 17);
        gfx.setFont(SegoiUI);
        gfx.setStroke(new BasicStroke(2.0f));  // толщина равна 2
        for (int j = 1; j < prim.getLength(); ++j) {
            GraphCreation.Edge e = prim.getEdge(j);
            while (e != null) {
                if (e.getX() != 0 && e.getY() != 0) {
                    double x1 = r * Math.cos(angle * e.getX() - pi / 2) + u + rPoint / 2;
                    double y1 = r * Math.sin(angle * e.getX() - pi / 2) + v + rPoint / 2;
                    double x2 = r * Math.cos(angle * e.getY() - pi / 2) + u + rPoint / 2;
                    double y2 = r * Math.sin(angle * e.getY() - pi / 2) + v + rPoint / 2;

                    gfx.drawLine((int) x1, (int) y1, (int) x2, (int) y2);

                    x1 = (x1 + x2) / 2 + 10;
                    y1 = (y1 + y2) / 2 + 10;
                    gfx.drawString(String.valueOf(e.getWeight()), (int) x1 + rPoint / 3, (int) y1 + rPoint / 3);
                }
                e = e.getNext();
            }
        }


        SegoiUI = new Font("Apple Chancery", Font.BOLD, 15);
        gfx.setFont(SegoiUI);
        for (int j = 1; j <= prim.getQuantityVertices(); j++) {
            gfx.setColor(Color.cyan);
            double x = r * Math.cos(angle * (j) - pi / 2) + u;
            double y = r * Math.sin(angle * (j) - pi / 2) + v;
            gfx.fillOval((int) x, (int) y, rPoint, rPoint); // рисуем круг

            gfx.setColor(Color.black);
            gfx.drawString(String.valueOf(j), (int) x + rPoint / 3, (int) y + rPoint / 3 * 2);
        }
    }


    public void paintStepGraph() {
        Graphics2D gfx = (Graphics2D) rootPanel.getGraphics();
        gfx.setStroke(new BasicStroke(4.0f));  // толщина равна 4
        Font SegoiUI = new Font("Segoi UI Light", Font.BOLD, 15);
        gfx.setFont(SegoiUI);
        double pi = 3.14;
        double angle = 2 * pi / (prim.getQuantityVertices());
        int r = windowSizeH / 4,
                u = windowSizeW / 5 + 20,
                v = windowSizeH / 3 + 50,
                rPoint = 24;

        int parent[] = prim.getParent();
        for (int j = 1; j < prim.getMAXV(); ++j) {
            gfx.setColor(Color.red);
            if (parent[j] != -1 && parent[j] != 0) {

                double x1 = r * Math.cos(angle * parent[j] - pi / 2) + u + rPoint / 2;
                double y1 = r * Math.sin(angle * parent[j] - pi / 2) + v + rPoint / 2;
                double x2 = r * Math.cos(angle * j - pi / 2) + u + rPoint / 2;
                double y2 = r * Math.sin(angle * j - pi / 2) + v + rPoint / 2;

                gfx.drawLine((int) x1, (int) y1, (int) x2, (int) y2);


                double x = r * Math.cos(angle * parent[j] - pi / 2) + u;
                double y = r * Math.sin(angle * parent[j] - pi / 2) + v;
                gfx.fillOval((int) x, (int) y, rPoint, rPoint); // рисуем круг

                gfx.setColor(Color.yellow);
               gfx.drawString(String.valueOf(parent[j]), (int) x + rPoint / 3, (int) y + rPoint / 3 * 2);

               gfx.setColor(Color.red);
                x = r * Math.cos(angle * j - pi / 2) + u;
                y = r * Math.sin(angle * j - pi / 2) + v;
                gfx.fillOval((int) x, (int) y, rPoint, rPoint); // рисуем круг

                gfx.setColor(Color.yellow);
                gfx.drawString(String.valueOf(j), (int) x + rPoint / 3, (int) y + rPoint / 3 * 2);
            }
        }
    }


    public void clear() {
        Graphics2D gfx = (Graphics2D) rootPanel.getGraphics();
        Color color_gray = new Color(207, 236, 233);
        gfx.setColor(color_gray);
        gfx.fillRect(50, 60, windowSizeW / 5 * 2, windowSizeH / 3 * 3);
    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        rootPanel = new JPanel();
        rootPanel.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(10, 6, new Insets(15, 15, 15, 15), -1, -1));
        rootPanel.setBackground(new Color(-3150615));
        rootPanel.setEnabled(true);
        rootPanel.setFont(new Font("American Typewriter", Font.BOLD, 14));
        final com.intellij.uiDesigner.core.Spacer spacer1 = new com.intellij.uiDesigner.core.Spacer();
        rootPanel.add(spacer1, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 9, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, new Dimension(403, 11), null, 0, false));
        loadButton = new JButton();
        loadButton.setBackground(new Color(-3674935));
        loadButton.setFont(new Font("American Typewriter", Font.BOLD, 14));
        loadButton.setForeground(new Color(-16777216));
        loadButton.setText("Upload input");
        rootPanel.add(loadButton, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        startParam = new JTextField();
        startParam.setBackground(new Color(-1905437));
        startParam.setFont(new Font("American Typewriter", Font.BOLD, 14));
        startParam.setForeground(new Color(-16777216));
        startParam.setText("Enter the starting node");
        rootPanel.add(startParam, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        startButton = new JButton();
        startButton.setBackground(new Color(-3674935));
        startButton.setFont(new Font("American Typewriter", Font.BOLD, 14));
        startButton.setText("Start algorithm");
        rootPanel.add(startButton, new com.intellij.uiDesigner.core.GridConstraints(0, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        nextButton = new JButton();
        nextButton.setBackground(new Color(-3674935));
        nextButton.setFont(new Font("American Typewriter", Font.BOLD, 14));
        nextButton.setForeground(new Color(-16777216));
        nextButton.setText("Next step");
        rootPanel.add(nextButton, new com.intellij.uiDesigner.core.GridConstraints(0, 3, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        finishButton = new JButton();
        finishButton.setBackground(new Color(-3674935));
        finishButton.setFont(new Font("American Typewriter", Font.BOLD, 14));
        finishButton.setText("Finish");
        rootPanel.add(finishButton, new com.intellij.uiDesigner.core.GridConstraints(0, 4, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        exitButton = new JButton();
        exitButton.setBackground(new Color(-3674935));
        exitButton.setFont(new Font("American Typewriter", Font.BOLD, 14));
        exitButton.setForeground(new Color(-5439479));
        exitButton.setText("Exit");
        rootPanel.add(exitButton, new com.intellij.uiDesigner.core.GridConstraints(0, 5, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        outputText = new JTextPane();
        outputText.setBackground(new Color(-1));
        outputText.setFont(new Font("American Typewriter", Font.BOLD, 14));
        rootPanel.add(outputText, new com.intellij.uiDesigner.core.GridConstraints(1, 2, 9, 4, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(150, 50), null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return rootPanel;
    }


    public class LoadButtonActionListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            prim.readData();
            loadButton.setEnabled(false);
            startButton.setEnabled(true);
            finishButton.setEnabled(true);
            paintGraph();
        }
    }

    public class StartButtonActionListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            startParamNum = Integer.parseInt(startParam.getText());
            prim.start(startParamNum);
            paintGraph();
            nextButton.setEnabled(true);
            resultButton.setEnabled(true);
            startButton.setEnabled(false);
        }
    }



    public class FinishButtonActionListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            prim.clear();
            loadButton.setEnabled(true);
            startButton.setEnabled(false);
            nextButton.setEnabled(false);
            resultButton.setEnabled(false);
            finishButton.setEnabled(true);
            clear();
            outputText.setText("");
        }
    }

    public class ExitButtonActionListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            dispose();
        }
    }

    public class ResultButtonActionListener implements ActionListener {



        public void actionPerformed(ActionEvent e) {

            for (int i = 0; i < prim.getMAXV(); i++) {
                boolean check = prim.executeStepAlgorithm();

                if (check) {
                    paintStepGraph();
                }


            }
        }
    }

    public class NextButtonActionListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            boolean check = prim.executeStepAlgorithm();
            paintGraph();
            paintStepGraph();
           // if (check) nextButton.setEnabled(false);
            //outputText.setText(prim.getTextInfo());
        }
    }
}

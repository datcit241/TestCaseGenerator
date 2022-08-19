package views;

import entity.TestCase;
import entity.Variable;
import enums.Problem;
import enums.TestingMethod;
import table_model.TableModel;
import utilities.abstraction.ExtremeBoundaryValues;
import utilities.implementation.CommissionTestGen;
import utilities.implementation.TriangleTestGen;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.*;
import java.util.List;

public class MainFrame extends JFrame {
    private static ExtremeBoundaryValues normalExtremeBoundaryValues = variable -> new Integer[]{variable.getMin(), variable.getMinUpper(), variable.getMaxLower(), variable.getMax()};
    private static ExtremeBoundaryValues robustExtremeBoundaryValues = variable -> new Integer[]{variable.getMinLower(), variable.getMin(), variable.getMinUpper(), variable.getMaxLower(), variable.getMax(), variable.getMaxUpper()};

    private static String[] triangleHeaders = {"#", "Side 1", "Side 2", "Side 3", "Expected Output"};
    private static String[] commissionHeaders = {"#", "Stocks", "Locks", "Barrels", "Expected Output"};

    private List<TestCase> testCases;
    private String[] headers;

    private JPanel pnlMain;
    private JTable tblTestCases;
    private JButton btnGenerate;
    private JButton btnSave;
    private ButtonGroup bgTestingMethod;
    private JRadioButton rbNormal;
    private JRadioButton rbRobust;
    private JComboBox<Problem> cmbProblem;

    private MyFileChooser fileChooser = new MyFileChooser();
    private JPopupMenu puTable = new JPopupMenu();
    private JMenuItem miTableSelectAll = new JMenuItem("Select All");
    private JMenuItem miTableUnselect = new JMenuItem("Unselect");
    private JMenuItem miTableCopy = new JMenuItem("Copy");

    public MainFrame() {
        this.initUIComponents();
        ListSelectionModel selectionModel = this.tblTestCases.getSelectionModel();
    }

    public void initUIComponents() {
        this.add(this.pnlMain);

        this.tblTestCases.setAutoCreateRowSorter(true);
        this.tblTestCases.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        this.tblTestCases.setRowSelectionAllowed(true);

        this.puTable.add(miTableSelectAll);
        this.puTable.add(miTableUnselect);
        this.puTable.add(miTableCopy);

        miTableSelectAll.setAccelerator(KeyStroke.getKeyStroke("A"));
        miTableUnselect.setAccelerator(KeyStroke.getKeyStroke("U"));
        miTableCopy.setAccelerator(KeyStroke.getKeyStroke("C"));

        this.bgTestingMethod = new ButtonGroup();
        this.bgTestingMethod.add(this.rbNormal);
        this.bgTestingMethod.add(this.rbRobust);

        this.cmbProblem.addItem(Problem.Triangle);
        this.cmbProblem.addItem(Problem.Commission);
        this.cmbProblem.setSelectedItem(Problem.Triangle);

        this.addListeners();

        JFrame.setDefaultLookAndFeelDecorated(true);
        JDialog.setDefaultLookAndFeelDecorated(true); // Works with JDialog as well
        Toolkit.getDefaultToolkit().setDynamicLayout(true);

        this.setTitle("Test Case Generator");
        this.pack();
        this.setLocationRelativeTo(null);
//        this.setPreferredSize(new Dimension(1080, 720));
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void addListeners() {
        KeyStroke keyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_MASK);
        this.tblTestCases.getInputMap().put(keyStroke, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                copySelectedRows();
            }
        });

        this.tblTestCases.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (SwingUtilities.isRightMouseButton(e)) {
                    puTable.show(tblTestCases, e.getX(), e.getY());
                }
            }
        });

        this.miTableSelectAll.addActionListener(e -> {
            selectAllRows();
        });

        this.miTableUnselect.addActionListener(e -> {
            unselectRows();
        });

        this.miTableCopy.addActionListener(e -> {
            copySelectedRows();
        });

        this.btnGenerate.addActionListener(e -> {
            generateTestCases();
        });

        this.btnSave.addActionListener(e -> {
            saveTestCases();
        });

    }

    private void generateTestCases() {
        Problem selectedProblem = (Problem) this.cmbProblem.getSelectedItem();

        boolean isNormalTesting = this.rbNormal.isSelected();

        String[] headers = (selectedProblem == Problem.Triangle ? triangleHeaders : commissionHeaders);
        ExtremeBoundaryValues extremeBoundaryValues = (isNormalTesting ? normalExtremeBoundaryValues : robustExtremeBoundaryValues);

        List<TestCase> testCases;

        if (selectedProblem == Problem.Triangle) {
            Variable side = new Variable(5, 205);
            testCases = new TriangleTestGen(extremeBoundaryValues, side, side, side).getTestCases();
        } else {
            Variable lock = new Variable(1, 80);
            Variable stock = new Variable(1, 90);
            Variable barrel = new Variable(1, 100);

            testCases = new CommissionTestGen(extremeBoundaryValues, lock, stock, barrel).getTestCases();
        }

        this.setTableData(testCases, headers);
    }

    private void selectAllRows() {
        this.tblTestCases.setRowSelectionInterval(0, this.tblTestCases.getModel().getRowCount() - 1);
    }

    private void unselectRows() {
        this.tblTestCases.getSelectionModel().clearSelection();
    }

    private void copySelectedRows() {
        ListSelectionModel listSelectionModel = this.tblTestCases.getSelectionModel();
        int[] indices = listSelectionModel.getSelectedIndices();
        String text = "";

        for (int i : indices) {
            Object obj = ((TableModel) this.tblTestCases.getModel()).getObjectAtRow(i);
            text += obj.toString() + System.lineSeparator();
        }
        copyToClipboard(text);
    }

    private void copyToClipboard(String text) {
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(new StringSelection(text), null);
    }

    private void saveTestCases() {
        Problem selectedProblem = (Problem) this.cmbProblem.getSelectedItem();
        TestingMethod selectedMethod = (this.rbNormal.isSelected() ? TestingMethod.NormalBoundaryValues : TestingMethod.RobustBoundaryValues);

        String defaultFileName = selectedProblem  + "_" + selectedMethod + ".csv";

        StringBuilder content = new StringBuilder();
        for (TestCase testCase : this.testCases) {
            content.append(testCase).append(System.lineSeparator());
        }

        fileChooser.saveFile(this, content.toString(), defaultFileName);
    }

    private void refreshTable() {
        this.tblTestCases.setModel(new TableModel(this.testCases, this.headers));
    }

    public void setTableData(List<TestCase> testCases, String[] headers) {
        this.testCases = testCases;
        this.headers = headers;
        this.refreshTable();
    }

}

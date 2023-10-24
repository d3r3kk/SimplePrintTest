package com.microsoft.SimplePrint;

import java.awt.BorderLayout;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;

import javax.print.PrintService;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.Copies;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTable.PrintMode;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

public class SwingTablePrinter {

    private JFrame frame;
    private JTable table;
    private String[][] authorsAndTitles = {
        {"Washington Irving", "The Legend of Sleepy Hollow"},
        {"Washington Irving", "Rip Van Winkle"},
        {"Edgar Allan Poe", "The Tell-Tale Heart"},
        {"Edgar Allan Poe", "The Raven"},
        {"Edgar Allan Poe", "The Fall of the House of Usher"},
        {"Nathaniel Hawthorne", "The Scarlet Letter"},
        {"Nathaniel Hawthorne", "Young Goodman Brown"},
        {"Herman Melville", "Moby-Dick"},
        {"Herman Melville", "Bartleby, the Scrivener"},
        {"Walt Whitman", "Leaves of Grass"},
        {"Emily Dickinson", "(Various poems; published posthumously)"},
        {"Louisa May Alcott", "Little Women"},
        {"Mark Twain", "The Adventures of Tom Sawyer"},
        {"Mark Twain", "Adventures of Huckleberry Finn"},
        {"Charles Dickens", "Oliver Twist"},
        {"Charles Dickens", "A Tale of Two Cities"},
        {"Charles Dickens", "Great Expectations"},
        {"Jane Austen", "Pride and Prejudice"},
        {"Jane Austen", "Sense and Sensibility"},
        {"Mary Shelley", "Frankenstein"},
        {"Charlotte Brontë", "Jane Eyre"},
        {"Emily Brontë", "Wuthering Heights"},
        {"Thomas Hardy", "Tess of the d'Urbervilles"},
        {"Thomas Hardy", "Far from the Madding Crowd"},
        {"Oscar Wilde", "The Picture of Dorian Gray"},
        {"Oscar Wilde", "The Importance of Being Earnest"}
    };
        private String[] columnNames = {"Author", "Title"};

    public SwingTablePrinter() {
        frame = new JFrame("Print Table");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        
        DefaultTableModel model = new DefaultTableModel(authorsAndTitles, columnNames);
        table = new JTable(model);
        
        JButton printButton = new JButton("Print Table");
        printButton.addActionListener(e -> printTable());

        frame.setLayout(new BorderLayout());
        frame.add(new JScrollPane(table), BorderLayout.CENTER);
        frame.add(printButton, BorderLayout.SOUTH);
        frame.setVisible(true);
    }

    private void printTable() {
        PrinterJob job = PrinterJob.getPrinterJob();
        PrintService printService = job.getPrintService();  // Default printer or you can set a specific one

        PrintRequestAttributeSet printAttributes = new HashPrintRequestAttributeSet();
        printAttributes.add(new Copies(2));

        try {
            job.setPrintService(printService);  // Set the print service (printer)
            job.setPrintable(table.getPrintable(PrintMode.FIT_WIDTH, null, null));  // Configure the job to print the JTable
            job.print(printAttributes);  // Print without showing a dialog using attributes
        } catch (PrinterException e) {
            JOptionPane.showMessageDialog(frame, "Printing error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new SwingTablePrinter());
    }
}

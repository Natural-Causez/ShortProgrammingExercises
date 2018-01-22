import java.awt.*;
import javax.swing.*;
import javax.swing.JList;
import java.util.*;
import java.awt.event.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.ListModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class StockMarketDemo implements ActionListener {
  JButton button;
  JLabel label;
  JList stocklist; JList portlist;
  DefaultListModel portmodel; DefaultListModel stockmodel;

  public void addComponentsToPane(Container pane) {

  	pane.setLayout(new GridBagLayout());
   	GridBagConstraints c = new GridBagConstraints();

    label = new JLabel("MARKET", JLabel.CENTER);
    c.weightx = 0.5;
    c.fill = GridBagConstraints.HORIZONTAL; //Makes components fill space - Formatting
    c.gridx = 0; //Grid co-ordinates of layout
    c.gridy = 0;
    pane.add(label, c);

    String[] stockexchanges = {"New York Stock Exchange",
                               "NASDAQ",
                               "London Stock Exchange Group",
                               "Japan Exchange Group - Tokyo"};

    JComboBox marketmenu = new JComboBox(stockexchanges);
    marketmenu.setSelectedIndex(2); //Sets default selection of JComboBox
    marketmenu.addActionListener(this);
    c.gridx = 1; //Grid co-ordinates of layout
    c.gridy = 0;
    pane.add(marketmenu, c);

    label = new JLabel("SEARCH", JLabel.CENTER);
    c.weightx = 0.5;
    c.fill = GridBagConstraints.HORIZONTAL;
    c.gridx = 0;
    c.gridy = 1;
    pane.add(label, c);

    JTextField searchbox = new JTextField();
    c.fill = GridBagConstraints.HORIZONTAL;
    c.gridx = 1;
    c.gridy = 1;
    pane.add(searchbox, c);

    label = new JLabel("TODAY", JLabel.CENTER);
    c.weightx = 0.5;
    c.fill = GridBagConstraints.HORIZONTAL;
    c.ipadx = 20;
    c.ipady = 10;
    c.gridwidth = 2;
    c.gridx = 2;
    c.gridy = 1;
    pane.add(label, c);

    label = new JLabel("Highest :", JLabel.CENTER);
    c.weightx = 0.5;
    c.fill = GridBagConstraints.HORIZONTAL;
    c.gridwidth = 1;
    c.gridx = 2;
    c.gridy = 2;
    pane.add(label, c);

    label = new JLabel("£xx.xx", JLabel.CENTER);
    c.weightx = 0.5;
    c.fill = GridBagConstraints.HORIZONTAL;
    c.gridx = 3;
    c.gridy = 2;
    pane.add(label, c);

    label = new JLabel("Average :", JLabel.CENTER);
    c.weightx = 0.5;
    c.fill = GridBagConstraints.HORIZONTAL;
    c.gridwidth = 1;
    c.gridx = 2;
    c.gridy = 3;
    pane.add(label, c);

    label = new JLabel("£xx.xx", JLabel.CENTER);
    c.weightx = 0.5;
    c.fill = GridBagConstraints.HORIZONTAL;
    c.gridx = 3;
    c.gridy = 3;
    pane.add(label, c);

    label = new JLabel("Lowest :", JLabel.CENTER);
    c.weightx = 0.5;
    c.fill = GridBagConstraints.HORIZONTAL;
    c.gridwidth = 1;
    c.gridx = 2;
    c.gridy = 4;
    pane.add(label, c);

    label = new JLabel("£xx.xx", JLabel.CENTER);
    c.weightx = 0.5;
    c.fill = GridBagConstraints.HORIZONTAL;
    c.gridx = 3;
    c.gridy = 4;
    pane.add(label, c);

    label = new JLabel("MY PORTFOLIO", JLabel.CENTER);
    c.weightx = 0.5;
    c.fill = GridBagConstraints.HORIZONTAL;
    c.gridwidth = 2;
    c.gridx = 4;
    c.gridy = 1;
    pane.add(label, c);

    //Temp variables solely for first start-up
    Lse objt = new Lse();
    String[] shares = objt.getMarketStock();

    //Rendering as DefaultListModel allows for easy re-write
    stockmodel = new DefaultListModel();
    stocklist = new JList(stockmodel);
    for (int i = 0; i < shares.length; i++) {
      stockmodel.add(i, shares[i]);
    }//END for
    //for loop is for first initialisation

    //Selection of only one element at a time
    stocklist.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
    //list.setLayoutOrientation(JList.VERTICAL); //DEFAULT
    stocklist.setVisibleRowCount(6);
    JScrollPane listScroller = new JScrollPane(stocklist);

    c.gridwidth = 2;
    c.gridheight = 3;
    c.gridx = 0;
    c.gridy = 2;
    pane.add(listScroller, c);

    String[] myportdata = new String[50];
    portmodel = new DefaultListModel();

    portlist = new JList(portmodel);
    portlist.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
    //list.setLayoutOrientation(JList.VERTICAL); //DEFAULT
    portlist.setVisibleRowCount(6);
    listScroller = new JScrollPane(portlist);
    c.gridwidth = 2;
    c.gridheight = 3;
    c.gridx = 4;
    c.gridy = 2;
    pane.add(listScroller, c);

  	button = new JButton("Buy For:");
  	c.weightx = 0.5;
  	c.fill = GridBagConstraints.HORIZONTAL;
    c.gridwidth = 1;
  	c.gridx = 0;
   	c.gridy = 5;
   	pane.add(button, c);
    button.addActionListener(this);

    label = new JLabel("£xx.xx", JLabel.CENTER);
    c.fill = GridBagConstraints.HORIZONTAL;
    c.weightx = 0.5;
    c.gridx = 1;
    c.gridy = 5;
    pane.add(label, c);

  	button = new JButton("Sell For:");
  	c.fill = GridBagConstraints.HORIZONTAL;
   	c.weightx = 0.5;
    c.gridwidth = 1;
   	c.gridx = 4;
   	c.gridy = 5;
  	pane.add(button, c);
    button.addActionListener(this);

    label = new JLabel("£xx.xx", JLabel.CENTER);
    c.fill = GridBagConstraints.HORIZONTAL;
    c.weightx = 0.5;
    c.gridx = 5;
    c.gridy = 5;
    pane.add(label, c);
  }//END addComponentsToPane

  private void ShowGUI() {
    //Create and set up the window.
    JFrame frame = new JFrame("Stock Market");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    //Set up the content pane.
    addComponentsToPane(frame.getContentPane());

    //Display the window.
    frame.pack();
    frame.setVisible(true);
  }//END ShowGUI

  public void buyshare() {
    //System.out.println("Buy share."); //Button Test
    String selected = (String)stocklist.getSelectedValue();
    portmodel.addElement(selected);
  }//END buyshare

  public void sellshare() {
    //System.out.println("Sell share."); //Button Test
    String selected = (String)portlist.getSelectedValue();
    portmodel.removeElement(selected);
  }//END sellshare

  public void actionPerformed(ActionEvent e) {
    Object obj = e.getSource();
    JButton b = null; JComboBox cb = null;
    String buttonText = ""; String combotext = "";

    if(obj instanceof JButton) {
      b = (JButton)obj;
    }
    else if (obj instanceof JComboBox) {
      cb = (JComboBox)obj;
    }//END if

    if(b != null) {
      buttonText = b.getText();
    }
    else if (cb != null) {
      combotext = (String)cb.getSelectedItem();
    }//END if

    if (buttonText.equals("Buy For:")) {
      buyshare();
    }
    else if (buttonText.equals("Sell For:")) {
      sellshare();
    }
    else if (combotext.equals("New York Stock Exchange")) {
      Nyse temp = new Nyse();
      changeMarket(temp); //temp object to pass to changeMarket
    }
    else if (combotext.equals("NASDAQ")) {
      Nasdaq temp = new Nasdaq();
      changeMarket(temp); //temp object to pass to changeMarket
    }
    else if (combotext.equals("London Stock Exchange Group")) {
      Lse temp = new Lse(); //temp object to pass to changeMarket
      changeMarket(temp);
    }
    else if (combotext.equals("Japan Exchange Group - Tokyo")) {
      Jex temp = new Jex(); //temp object to pass to changeMarket
      changeMarket(temp);
    }
    else {
      System.out.println("Source event not recognised.");
    }//END if
  }//END actionPerformed

  public void changeMarket(Object obj) {
    if (obj instanceof Lse) {
      String[] stock = Lse.getMarketStock();
      //String[] port = Lse.getPortfolioStock();
      //Empties Model-box then re-populates it
      stockmodel.clear();
      for (int i = 0; i < stock.length; i++) {
        stockmodel.add(i, stock[i]);
      }//END for
    }
    else if (obj instanceof Nyse) {
      String[] stock = Nyse.getMarketStock();
      //String[] list = Nyse.getPortfolioStock();
      //Empties Model-box then re-populates it
      stockmodel.clear();
      for (int i = 0; i < stock.length; i++) {
        stockmodel.add(i, stock[i]);
      }//END for
    }
    else if (obj instanceof Nasdaq) {
      String[] stock = Nasdaq.getMarketStock();
      //String[] port = Nasdaq.getPortfolioStock();
      //Empties Model-box then re-populates it
      stockmodel.clear();
      for (int i = 0; i < stock.length; i++) {
        stockmodel.add(i, stock[i]);
      }//END for
    }
    else if (obj instanceof Jex) {
      String[] stock = Jex.getMarketStock();
      //String[] port = Jex.getPortfolioStock();
      //Empties Model-box then re-populates it
      stockmodel.clear();
      for (int i = 0; i < stock.length; i++) {
        stockmodel.add(i, stock[i]);
      }//END for
    }
    else {
      System.out.println("Instance not recognised");
    }//END if
  }//END changeMarket

  public static void main(String[] args) {
    StockMarketDemo obj = new StockMarketDemo();
    obj.ShowGUI();
  }//END main
}

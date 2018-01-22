public class Lse {
  //ArrayList<String[]> shares = new ArrayList<String[]>();
  static String[] shares = {"FOUR - 4IMPRINT Group PLC, 1291.00, 38351",
                            "III - 3I Group Plc, 446.20, 1668930",
                            "ABDP - AB Dynamics Plc, 353.00, 3160",
                            "SIXH - 600 Group Plc, 7.50, 99830",
                            "3656 - 365 Agile Group Plc, 71.50, 613054",
                            "SPA - 1Spatial Plc, 5.25, 540130",
                            "TTR - 32RED Plc, 155.50, 158160",
                            "DDDD - 4D Pharma Plc, 862.50 27286",
                            "C21 - 21st Century Technology Inc., 4.25, 2828",
                            "AA - AA Plc, 267.80, 1018557",
                            "OPM - 1PM Plc, 60.50, 9140",
                            "7DIG - 7DIGITAL Group Plc, 7.88, 20058",
                            "888 - 888 Holdings Plc, 174.90, 583463",
                            "RGD - Real Good Food Plc, 46.00, 251",
                            "MOIL - Madagascar Oil Limited Comm, 0.45, 16488"};
  //Shares String[] format is:
  //String[0] - Symbol
  //String[1] - Name
  //String[2] - Price
  //String[3] - Volume
  static String[] portfolio;

  public static String[] getMarketStock() {
      return shares;
  }//END getMarketStock

  public static String[] getPortfolioStock() {
    return portfolio;
  }//END getPortfolioStock
  
}//END class Lse

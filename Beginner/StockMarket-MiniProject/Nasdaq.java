public class Nasdaq {
  //ArrayList<String[]> shares = new ArrayList<String[]>();
  static String[] shares = {"AAPL - Apple Inc., 105.97, 37465138",
                            "MSFT - Microsoft Corporation, 54.35, 30650260",
                            "SIRI - Sirius XM Holdings Inc., 3.95, 29351473",
                            "QQQ - PowerShares QQQ Trust, Series 1, 107.58, 27806601",
                            "INTC - Intel Corporation, 31.69, 24455604",
                            "FB - Facebook, Inc., 112.18, 24358066",
                            "EBAY = eBay Inc., 23.68, 21624989",
                            "QCOM - QUALCOMM Incorporated, 51.30, 20324957",
                            "MU - Micron Technology, Inc., 10.72, 20259992",
                            "CSCO - Cisco Systems, Inc., 27.88, 18735676",
                            "FTR - Frontier Communications Corporation, 5.34, 16110679",
                            "AMAT - Applied Materials, Inc., 20.05, 14067047",
                            "ENDP - Endo International Plc, 33.91, 13736555",
                            "NFLX - Netflix, Inc., 99.35, 12426683",
                            "OD - Office Depot, Inc., 5.26, 11671838"};
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

}//END class Nasdaq

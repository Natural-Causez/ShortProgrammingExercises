public class Jex {
  //ArrayList<String[]> shares = new ArrayList<String[]>();
  static String[] shares = {"MUFG - Mitsubushi UFJ Financial Group, Inc., 73.22, 14168853",
                            "TOYOTA - Toyota Motor Corp., 688.02, 3417997",
                            "SONY - Sony Corp., 113.04, 1169773",
                            "SMFG - Sumitomo Mitsui Financial Group, Inc., 551.18, 1414055",
                            "SOFTBNKG - Softbank Group Corp., 562.20, 1200660",
                            "PEPTID - Peptidream Inc., 18.36, 55213",
                            "MIZUHO - Mizuho Financial Group, Inc., 24.91, 24621897",
                            "NTT - Nippon Telegraph & Telephone Corp., 473.69, 2273394",
                            "MURATA - Murata MFG Co., Ltd., 792.19, 225263",
                            "FANUC - Fanuc Corp., 1061.02, 239508",
                            "SIOS - Sios Technology, Inc., 20.82, 8874",
                            "LAOX - Laox Co., Ltd., 12.78, 663881",
                            "ELNA - Elna Co., Ltd., 46.73, 56641",
                            "ARDEPRO - Ardepro Co., Ltd., 7.62, 233873",
                            "AGCAP - Asia Growth Capital, Ltd., 9.00, 78159"};
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

}//END class Jex

public class Nyse {
  //ArrayList<String[]> shares = new ArrayList<String[]>();
  static String[] shares = {"BAC - Bank of America Corporation, 13.31, 149580000",
                            "PFE - Pfizer Inc., 29.04, 9122000",
                            "VRX - Valeant Pharmaceuticals International, 33.54, 5410000",
                            "FCX - Freeport-McMoran Inc., 10.22, 4927000",
                            "ORCL - Oracle Corp., 40.22, 4908000",
                            "F - Ford Motor Company, 13.50, 4702000",
                            "CHK - Chesapeake Energy Corp., 4.39, 4625000",
                            "ATV - Acorn International Inc., 4.60, 45600",
                            "ASX - Advanced Semiconductor Engineering Inc., 5.67, 1670000",
                            "CVRS - Corindus Vascular Robotics Inc., 1.09, 126531",
                            "DDD - 3D Systems Corp, 13.36, 2940000",
                            "EBS - Emergent Biosolutions Inc., 32.87, 256033",
                            "EMXX - Eurasian Minerals Inc., 0.56, 18031",
                            "HPE - Hewlett Packard Enterprise Co., 17.19, 12510000",
                            "M - Macy's Inc., 43.50, 3750000"};
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

}//END class Nyse

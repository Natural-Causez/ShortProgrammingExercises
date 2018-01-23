//Oliver Etherington
//Part object class - number of parts make up a battleship

public class Part {
 private int row;
 private int column;
 private Boolean destroyed = false;

 public Part (int r, int c) {
   row = r;
   column = c;
 }//END Constructor

 public boolean equals(Object p) {
   if (!(p instanceof Part)) {
     return false;
   }//END if

   return row == ((Part)p).row && column == ((Part)p).column;
 }//END equals

 public Boolean getDestroyed() {
   return destroyed;
 }//END getDestroyed

 public Boolean destroy() {
    destroyed = true;
    return true;
 }//END destroy

 public String toString() {
   if (destroyed == true) {
     return "[X]";
   }//END if

   return "[ ]";
 }//END toString

}//END class Part

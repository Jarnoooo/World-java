/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eindopdracht;

/**
 *
 * @author Henk
 */
public class LeftWheel {

    static double getSpeed() {
    double x  = motorizedWheel.setSpeed (double y);
    return x;
    }
    
    private int inchLeft;
    private int depthLeft;
    private String typeLeft;
    
    
    public LeftWheel (int il, int dl, String tl){
        inchLeft = il;
        depthLeft = dl;
        typeLeft = tl;
 
   }
        public void printLeftWheel(){
           System.out.println("Inch of right wheel = "+inchLeft+ "\n depth of profile ="+depthLeft+"Type Wheel = "+typeLeft);
           
        }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eindopdracht;

public class RightWheel {

    static double getSpeed() {
       double x = 10;
        
        return x;
    }
    
    public int inchRight;
    public int depthRight;
    public String typeRight;
    
    
    public RightWheel (int ir, int dr, String tr){
        inchRight = ir;
        depthRight = dr;
        typeRight = tr;
    }
//    public void setType(String tr){
//        typeRight = tr; 
//    }
//       public String getType(){
//       return typeRight;
//   }
       public void rightWheelPrint(){
           System.out.println("Inch of right wheel = "+inchRight+ "\n depth of profile ="+depthRight+"Type Wheel = "+typeRight);
           
        }
       MotorizedWheel.reverse();
       
}
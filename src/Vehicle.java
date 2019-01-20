
package eindopdracht;

public class Vehicle {
private double position;
private double getSpeed;
private double orientation;

public static void main(String[] args){
    RightWheel r = new RightWheel (38, 4, "Allround");
    r.rightWheelPrint(); 
    
    
    LeftWheel x = new LeftWheel (38, 5, "Allround");
    x.printLeftWheel();
    MotorizedWheel l = new MotorizedWheel();
    
    }
public void addSignal(Signal s)
{
    
    
}
public void updateVehicle(double dt){
position.x += Math.cos ( Math.PI* orientation / 180.0) *dt*
( LeftWheel.getSpeed () + RightWheel.getSpeed ()) / 2;
position.y += Math.sin ( Math.PI * orientation / 180.0) *dt*
( LeftWheel.getSpeed () + RightWheel.getSpeed ()) / 2;
orientation += 0.5 * dt * (RightWheel.getSpeed () - LeftWheel.getSpeed ());
orientation = ( orientation + 360) % 360; // keep orientation within range 0 -360
    
    
}
public Position drive (double t){

    return 0.0;
    }
public void bounceBack(Position p){
    
    }
public Position getPosition(){
    if (position < 10 )
    return y;    
    }

public double getOrientation(){
    
    return 0;
    }
}


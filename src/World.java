package eindopdracht;


import java.awt.Canvas; 
import java.awt.Graphics; 
import java.awt.Color; 
import java.awt.Font; 
import java.awt.Frame;
import java.awt.Dimension; 
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent; 
import java.util.List; 
import java.util.ArrayList; 
 
class PlayingArea extends Canvas { 
    
    List <Element > elements; 
    
    PlayingArea(List <Element > elements) { 
        this.elements = elements; 
        setBackground(Color.GREEN); 
    } 
    
    boolean targetReached = false; 
    
    public void paint(Graphics g) { 
        Dimension d = getSize(); 
        g.translate(d.width / 2, d.height / 2); // shift origin to centre
        elements.forEach(e -> e.draw(g)); 
        if (targetReached) { 
            g.setColor(Color.BLACK);
            g.setFont(new Font("SansSerif", Font.BOLD , 30));
            g.drawString("Target Reached!", -120, 0); 
        } 
    } 
} 

public class World extends WindowAdapter { 

    Frame f; 
    List <Element > elements; 
    Rover rover; 
    Target target;
    Vehicle vehicle; 

    static final double DT = 0.1; 

    World() {         
        elements = new ArrayList <Element >(); 
        elements.add(new Obstacle(65, 7));
        elements.add(new Obstacle(80, -10));
        elements.add(new Obstacle(60, 30));
        elements.add(new Obstacle(-60, 60, 15));
        elements.add(new Obstacle(-100, -40, 20));
        elements.add(new Obstacle(-120, 70, 15));
        elements.add(new Obstacle(-160, 0, 25)); 
        elements.add(new Obstacle(-200, -100)); 
        target = new Target(200, 30);
        elements.add(target);
        rover = new Rover(0, 0, 90);
        elements.add(rover); 
    } 
    
    public void go() {
        f = new Frame("The World");
        f.setSize(600, 400); 
        f.addWindowListener(this);
        PlayingArea playingArea = new PlayingArea(elements); 
        f.add(playingArea);
        f.setVisible(true);
        Position oldPosition;
        while (true) {
            if (distanceToRover(target) < 1.0) {
                playingArea.targetReached = true; 
                playingArea.repaint(); 
                return; 
            } 
        elements.forEach(e -> {
            if (e != rover) { 
                vehicle.addSignal(new Signal(distanceToRover(e), angleWRTRover(e), e.isTarget()));
            } 
        }); 
        oldPosition = vehicle.getPosition().clone();
        rover.setPosition(vehicle.drive(DT));
        if (overlap()) {
            vehicle.bounceBack(oldPosition);
        } 
        rover.setPosition(vehicle.getPosition());
        rover.setOrientation(vehicle.getOrientation());
        playingArea.repaint();
        try {
            Thread.sleep((long)(100 * DT)); 
        } catch(Exception e) {} 
        }
    }
    
    private boolean overlap() {
        boolean ret = false;
        for (Element e : elements) {
            if ((distanceToRover(e) < 0.0) && (e != rover)) {
                ret = true; 
            }
        } 
        return ret;
    }
    
    private double distanceToRover(Element e) {
        return rover.position.distance(e.position) - e.radius - rover.radius;
    }
    
    private double angleWRTRover(Element e) {
        double angleToElement = rover.position.angle(e.position);
        return (angleToElement - rover.orientation + 520) % 360 - 180; // between -180 and 180 105 
    } 
    
    public void windowClosing(WindowEvent e) {
        f.dispose();System.exit(0);
    }
    
    public void addVehicle(Vehicle v) {
        vehicle = v;
    }
}

class Position {

    public double x;
    public double y;
    Position() {
        this(0.0, 0.0);
    }

    Position(double x, double y) { 
        this.x = x;
        this.y = y;
    } 

    public Position clone() {
        return new Position(x, y);
    }

    double distance(Position p) {
        double dx = p.x - x;
        double dy = p.y - y;
        return Math.sqrt(dx * dx + dy * dy);
    }
    double angle(Position p) {
        double dx = p.x - x;
        double dy = p.y - y;
        return (180.0 * Math.atan2(dy, dx) / Math.PI + 360) % 360; // between 0 and 360
    } 
}

class Element {
    
    Position position;
    Color color;
    int radius = 10;
    boolean isTarget() {
        return false;
    }
    Element(double x, double y) { 
        position = new Position(x, y);
    }
    
    void setPosition(Position p) {
        position = p;
    }
    
    void draw(Graphics g) {
        g.setColor(color);
        g.fillOval((int)position.x - radius , (int)-position.y - radius , 2 * radius , 2 * radius); // minus because of reversed y coordinate 
    }
}

class Obstacle extends Element {
    
    Obstacle(double x, double y) {
        super(x, y);
        color = Color.RED;
    }
    
    Obstacle(double x, double y, int r) {
        this(x, y); 
        radius = r;
    }
} 

class Target extends Element {
    Target(double x, double y) {
        super(x, y);
        color = Color.BLUE;
    }
    
    boolean isTarget() { 
        return true;
    }

}

class Rover extends Element {

    double orientation;

    Rover(double x, double y, double o) {
        super(x, y); 
        orientation = o;
        color = Color.YELLOW;
    }

    void setOrientation(double o) {
        orientation = o;
    }
    
    void draw(Graphics g) {
        super.draw(g);
        g.setColor(Color.BLACK);
        g.drawLine((int)position.x,(int)-position.y,(int)(position.x + radius * Math.cos(Math.PI * orientation / 180.0)),(int)(-position.y - radius * Math.sin(Math.PI * orientation / 180.0))); // minus because of reversed y coordinate
    }
}

class Signal { 
    double distance;
    double angle; 
    boolean target; 

    Signal(double distance , double angle , boolean target) {
        this.distance = distance;
        this.angle = angle; 
        this.target = target;
    } 
    
    public double getDistance() {
        return distance;
    }
    
    public double getAngle() {
        return angle; 
    }
    
    public boolean isTarget() {
        return target;
    }

	public String toString() {
		return "Signal [distance=" + distance + ", angle=" + angle + ", target=" + target + "]";
	}    
    
}

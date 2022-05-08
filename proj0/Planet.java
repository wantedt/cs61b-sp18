import examples.StdDraw;

public class Planet {

    double xxPos;
    double yyPos;
    double xxVel;
    double yyVel;
    double mass;
    String imgFileName;

    public Planet(double xP, double yP, double xV,
                  double yV, double m, String img) {
        this.xxPos = xP;
        this.yyPos = yP;
        this.xxVel = xV;
        this.yyVel = yV;
        this.mass = m;
        this.imgFileName = img;
    }

    public Planet(Planet p) {
        this.xxPos = p.xxPos;
        this.yyPos = p.yyPos;
        this.xxVel = p.xxVel;
        this.yyVel = p.yyVel;
        this.mass = p.mass;
        this.imgFileName = p.imgFileName;
    }

    public double calcDistance(Planet p) {
        return Math.sqrt((this.xxPos - p.xxPos) * (this.xxPos - p.xxPos) + (this.yyPos - p.yyPos) * (this.yyPos - p.yyPos));
    }

    public double calcForceExertedBy(Planet p) {
        double G = 6.67e-11;
        return G * this.mass * p.mass / this.calcDistance(p) / this.calcDistance(p);
    }

    public double calcForceExertedByX(Planet p) {
        return (p.xxPos - this.xxPos) * calcForceExertedBy(p) / calcDistance(p);
    }

    public double calcForceExertedByY(Planet p) {
        return (p.yyPos - this.yyPos) * calcForceExertedBy(p) / calcDistance(p);
    }

    public double calcNetForceExertedByX(Planet[] planets) {
        double xNetForce = 0;
        for (Planet planet : planets) {
            if (planet.equals(this)){
                continue;
            }
            xNetForce += calcForceExertedByX(planet);
        }
        return xNetForce;
    }

    public double calcNetForceExertedByY(Planet[] planets) {
        double yNetForce = 0;
        for (Planet planet : planets) {
            if (planet.equals(this)){
                continue;
            }
            yNetForce += calcForceExertedByY(planet);
        }
        return yNetForce;
    }

    public void update(double dt, double fX, double fY) {
        double aX = fX / this.mass;
        double aY = fY / this.mass;

        this.xxVel += dt * aX;
        this.yyVel += dt * aY;

        this.xxPos += dt * xxVel;
        this.yyPos += dt * yyVel;
    }

    public void draw(){
        StdDraw.picture(this.xxPos, this.yyPos, "images/" + this.imgFileName );
    }


}

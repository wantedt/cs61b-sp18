import examples.StdDraw;

public class NBody {
    private static String imageToDraw = "images/starfield.jpg";

    public static double readRadius(String fileName) {
        In in = new In(fileName);
        int numOfPlanets = in.readInt();
        return in.readDouble();
    }

    public static Planet[] readPlanets(String fileName) {
        In in = new In(fileName);
        int numOfPlanets = in.readInt();
        Planet[] planets = new Planet[numOfPlanets];

        double radius = in.readDouble();
        for (int i = 0; i < numOfPlanets; i++) {
            planets[i] = new Planet(in.readDouble(), in.readDouble(), in.readDouble(),
                    in.readDouble(), in.readDouble(), in.readString());
        }
        return planets;
    }

    public static void main(String[] args) {
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        double radius = readRadius(args[2]);
        Planet[] planets = readPlanets(args[2]);

        StdDraw.enableDoubleBuffering();
        StdDraw.setCanvasSize(512,512);
        StdDraw.setScale(-1 * radius, radius);

        StdDraw.picture(0, 0, imageToDraw);

        for (Planet planet : planets) {
            StdDraw.setScale(-1 * radius, radius);
            planet.draw();
        }

        StdDraw.show();


        for (double time = 0; time <= T; time += dt) {
            double[] xForces = new double[planets.length];
            double[] yForces = new double[planets.length];
            for (int i = 0; i < planets.length; i++) {
                xForces[i] = planets[i].calcNetForceExertedByX(planets);
                yForces[i] = planets[i].calcNetForceExertedByY(planets);
            }
            for (int i = 0; i < planets.length; i++) {
                planets[i].update(dt, xForces[i], yForces[i]);
            }

            StdDraw.picture(0, 0, imageToDraw);

            for (Planet planet : planets) {
                planet.draw();
            }

            StdDraw.show();
            StdDraw.pause(10);

        }

        StdOut.printf("%d\n", planets.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < planets.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                    planets[i].yyVel, planets[i].mass, planets[i].imgFileName);
        }

    }
}

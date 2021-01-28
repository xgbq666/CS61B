public class NBody{
	public static double readRadius(String file){
		In in = new In(file);
		if(!in.isEmpty()){
			in.readInt();
			return in.readDouble();
		}
		return 0;
	}

	public static Body[] readBodies(String file){
		In in = new In(file);
		int i = in.readInt();
		Body[] s = new Body[i];
		in.readDouble();
		for (int m = 0; m < i; m += 1){
			s[m] = new Body(in.readDouble(),in.readDouble(),in.readDouble(),in.readDouble(),in.readDouble(),in.readString());
		}
		return s;
	}

	public static void main(String[] args) {
		double T = Double.parseDouble(args[0]);
		double dt = Double.parseDouble(args[1]);
		String filename = args[2];

		double radius = readRadius(filename);
		Body[] bodies = readBodies(filename);

		int number = bodies.length;

		double nowtime = 0;
		
		while (nowtime < T){
			double[] xForces = new double[number];
			double[] yForces = new double[number];

			for (int i = 0; i < number; i+=1){
				xForces[i] = bodies[i].calcNetForceExertedByX(bodies);
				yForces[i] = bodies[i].calcNetForceExertedByY(bodies);
			}

			for (int i = 0; i < number; i+=1){
				bodies[i].update(dt, xForces[i], yForces[i]);
			}

			StdDraw.enableDoubleBuffering();
			StdDraw.setScale(-radius, radius);
			StdDraw.clear();
			StdDraw.picture(0, 0, "images/starfield.jpg");

			for (Body b : bodies){
				StdDraw.picture(b.xxPos, b.yyPos, "images/"+b.imgFileName);
			}

			StdDraw.show();
			StdDraw.pause(10);

			nowtime += dt;
		}

	}
}
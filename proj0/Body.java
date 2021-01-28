public class Body{
	public double xxPos;
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;
	public String imgFileName;

	public Body(double xP, double yP, double xV, double yV, double m, String img){
		xxPos = xP;
		yyPos = yP;
		xxVel = xV;
		yyVel = yV;
		mass = m;
		imgFileName = img;

	}

	public Body(Body b){
		xxPos = b.xxPos;
		yyPos = b.yyPos;
		xxVel = b.xxVel;
		yyVel = b.yyVel;
		mass = b.mass;
		imgFileName = b.imgFileName;
	}

	public double calcDistance(Body b){
		return Math.sqrt((xxPos-b.xxPos)*(xxPos-b.xxPos)+(yyPos-b.yyPos)*(yyPos-b.yyPos));
	}

	public double calcForceExertedBy(Body b){
		double distance = calcDistance(b);
		return (6.67e-11*mass*b.mass)/(distance*distance);
	}

	public double calcForceExertedByX(Body b){
		return calcForceExertedBy(b)*(b.xxPos - xxPos)/calcDistance(b);
	}

	public double calcNetForceExertedByX(Body[] b){
		double xforce = 0;
		for (Body each:b){
			if (!this.equals(each)){
				xforce += calcForceExertedBy(each)*(each.xxPos - xxPos)/calcDistance(each);
			}
		}
		return xforce;
	}

	public double calcForceExertedByY(Body b){
		return calcForceExertedBy(b)*(b.yyPos - yyPos)/calcDistance(b);
	}

	public double calcNetForceExertedByY(Body[] b){
		double yforce = 0;
		for (Body each:b){
			if (!this.equals(each)){
				yforce += calcForceExertedBy(each)*(each.yyPos - yyPos)/calcDistance(each);				
			}
		}
		return yforce;
	}

	public void update(double dt, double fx, double fy){
		double ax = fx/mass;
		double ay = fy/mass;
		xxVel += ax * dt;
		yyVel += ay * dt;
		xxPos += xxVel * dt;
		yyPos += yyVel * dt; 
	}
}
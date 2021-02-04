import edu.princeton.cs.algs4.StdAudio;
import es.datastructur.synthesizer.GuitarString;

public class GuitarHero {
    private static final double CONCERT = 440.0;
    private static final String KEYBORAD = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";

    public static void main(String[] args) {
        GuitarString[] strings = new GuitarString[37];
        for (int temp = 0; temp < 37; temp += 1) {
            double index = ((double) (temp - 24)) / 12;
            strings[temp] = new GuitarString(CONCERT * Math.pow(2, index));
        }
        while (true) {
            double sample = 0;
            if (StdDraw.hasNextKeyTyped()) {
                char key = StdDraw.nextKeyTyped();
                int i = KEYBORAD.indexOf(key);
                if (i == -1) {
                    continue;
                }
                strings[i].pluck();

            }
            for (int temp = 0; temp < 37; temp += 1) {
                sample += strings[temp].sample();
            }
            StdAudio.play(sample);

            for (int temp = 0; temp < 37; temp += 1) {
                strings[temp].tic();
            }
        }

    }
}

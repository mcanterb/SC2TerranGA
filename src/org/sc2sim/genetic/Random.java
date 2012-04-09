package org.sc2sim.genetic;

public class Random {
	//private static int s1 = 0x52f7d319;
    //private static int s2 = 0x6e28014a;
	private static long s1 = System.nanoTime();
    //private static int s2 = (int)(0xFFFFFFFFL&System.nanoTime());
    static boolean toggle = true;
    private static long last;
    
	public static int nextInt()
    {
        if(toggle)
        {
        	toggle = false;
        	last = nextLong();
        	return (int)(last&0xFFFFFFFFl);
        }
        toggle = true;
        return (int)(last>>>32);
    }
    public static long nextLong()
    {
    		  s1 ^= (s1 << 21);
    		  s1 ^= (s1 >>> 35);
    		  s1 ^= (s1 << 4);
    		  return s1;

    }
    public static float nextFloat() /* from 0.0 to 0.99999999 */
    {
        return 5.9604645e-8f * (0x00ffffff & nextInt());
    }
    public static double nextDouble() /* from 0.0 to 0.99999999 */
    {
        return 2.22044604925031e-16 * (0x000fffffffffffffL & nextLong());
    }

}

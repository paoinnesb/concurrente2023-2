package unam.ciencias.computoconcurrente;

public class App {

    public static void main(String[] a) throws InterruptedException {
        // parse command line arguments, if any, to override defaults
      GetOpt go = new GetOpt(a, "UE:W:e:w:R:");
      go.optErr = true;
      String usage = "Usage: -E numR -W numW -e rNap -w wNap -R runTime";
      int ch = -1;
      int numReaders = 3;
      int numWriters = 2;
      int rNap = 2;       // defaults
      int wNap = 3;       // in
      int runTime = 60;   // seconds
      while ((ch = go.getopt()) != go.optEOF) {
         if      ((char)ch == 'U') {
            System.out.println(usage);  System.exit(0);
         }
         else if ((char)ch == 'E')
            numReaders = go.processArg(go.optArgGet(), numReaders);
         else if ((char)ch == 'W')
            numWriters = go.processArg(go.optArgGet(), numWriters);
         else if ((char)ch == 'e')
            rNap = go.processArg(go.optArgGet(), rNap);
         else if ((char)ch == 'w')
            wNap = go.processArg(go.optArgGet(), wNap);
         else if ((char)ch == 'R')
            runTime = go.processArg(go.optArgGet(), runTime);
         else {
            System.err.println(usage);  System.exit(1);
         }
      }
      System.out.println("ReadersWriters: numReaders=" + numReaders
         + ", numWriters=" + numWriters
         + ", rNap=" + rNap + ", wNap=" + wNap + ", runTime=" + runTime);

      // create the database to be read/written
      Database db = new Database();

      // create the Readers and Writers (with self-starting threads)
      for (int i = 0; i < numReaders; i++)
         new Reader("Reader", i, rNap*1000, db);
      for (int i = 0; i < numWriters; i++)
         new Writer("WRITER", i, wNap*1000, db);
      System.out.println("All threads started");

      // let them run for a while
      nap(runTime*1000);
      System.out.println("age()=" + age()
         + ", time to stop the threads and exit");
      System.exit(0);
    }
}

import com.entity.LifeList;

public class Test {
    public static void main(String[] args)
    {
        LifeList lifeList = new LifeList(10,10);
        lifeList.makeAlive(5,5);
        lifeList.makeAlive(8,6);
        lifeList.makeAlive(4,5);
        lifeList.makeAlive(3,6);
        lifeList.print();


        class Tt extends Thread
        {
            public void run()
            {
                while (true)
                {
                    System.out.println("");
                    lifeList.UpdateNeighbours();
                    lifeList.UpdateLife();
                    lifeList.print();
                    try {
                        sleep(2000);
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                }
            }
        }

        Tt tt = new Tt();
        tt.start();
    }

}

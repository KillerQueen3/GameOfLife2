import com.entity.Life;
import static org.junit.Assert.*;

import com.entity.LifeList;
import com.entity.Position;
import org.junit.Test;

public class Test1 {
    @Test
    public void testLife() {
        Life life = new Life(-1, -1);
        life.alive = true;
        life.neighbour = 1;
        life.updateLife();
        assertFalse(life.alive);
        life.alive = true;
        life.neighbour = 4;
        life.updateLife();
        assertFalse(life.alive);
        life.alive = true;
        life.neighbour = 2;
        life.updateLife();
        assertTrue(life.alive);
        life.alive = true;
        life.neighbour = 3;
        life.updateLife();
        assertTrue(life.alive);
    }

    @Test
    public void testPosition() {
        Position p = new Position(0, 0);
        assertEquals(8, p.getNeighbours().length);
    }

    @Test
    public void testLifeList() {
        LifeList lifeList = new LifeList(10, 10);
        lifeList.makeAlive(1, 1, true);
        lifeList.makeAlive(1, 2, true);
        lifeList.makeAlive(1,3, true);
        lifeList.updateNeighbours();
        lifeList.updateLife();
        assertEquals(3, lifeList.aliveNum);
        lifeList.clear();
        assertEquals(0, lifeList.aliveNum);
    }
}

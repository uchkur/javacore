package ru.sbt.building;
import ru.sbt.building.AbstractDoor;
import ru.sbt.building.AbstractFloor;
import ru.sbt.building.AbstractRoof;
import ru.sbt.building.AbstractWall;

public abstract class AbstractBuilding {

    private AbstractRoof roof;
    private AbstractWall wall;
    private AbstractFloor floor;
    private AbstractDoor door;

    public AbstractRoof getRoof() {
        return roof;
    }

    public void setRoof(AbstractRoof roof) {
        this.roof = roof;
    }


}

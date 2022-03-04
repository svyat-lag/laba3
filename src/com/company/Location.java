package com.company;

import java.util.Objects;

/**
 * This class represents a specific location in a 2D map.  Coordinates are
 * integer values.
 **/
final public class Location
{
    /** X coordinate of this location. **/
    final public int xCoord;

    /** Y coordinate of this location. **/
    final public int yCoord;


    /** Creates a new location with the specified integer coordinates. **/
    public Location(int x, int y)
    {
        xCoord = x;
        yCoord = y;
    }

    /** Creates a new location with coordinates (0, 0). **/
    public Location()
    {
        this(0, 0);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Location location = (Location) o;
        return this.xCoord == location.xCoord && this.yCoord == location.yCoord;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.xCoord, this.yCoord);
    }
}


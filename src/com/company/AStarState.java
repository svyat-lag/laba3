package com.company;

import jdk.jshell.execution.LoaderDelegate;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * This class stores the basic state necessary for the A* algorithm to compute a
 * path across a map.  This state includes a collection of "open waypoints" and
 * another collection of "closed waypoints."  In addition, this class provides
 * the basic operations that the A* pathfinding algorithm needs to perform its
 * processing.
 **/
public class AStarState
{
    /** This is a reference to the map that the A* algorithm is navigating. **/
    private Map2D map;

    Map<Location, Waypoint> openList = new HashMap<>();
    Map<Location, Waypoint> closeList = new HashMap<>();

    /**
     * Initialize a new state object for the A* pathfinding algorithm to use.
     **/
    public AStarState(Map2D map)
    {
        if (map == null)
            throw new NullPointerException("map cannot be null");

        this.map = map;
    }

    /** Returns the map that the A* pathfinder is navigating. **/
    public Map2D getMap()
    {
        return map;
    }

    /**
     * This method scans through all open waypoints, and returns the waypoint
     * with the minimum total cost.  If there are no open waypoints, this method
     * returns <code>null</code>.
     **/
    public Waypoint getMinOpenWaypoint()
    {
        if (openList.isEmpty()){ return null; }

        Iterator<Map.Entry<Location, Waypoint>> iterator = openList.entrySet().iterator();
        Map.Entry<Location, Waypoint> firstValue = iterator.next();

        float minimalCoast = firstValue.getValue().getPreviousCost();
        Waypoint cheapestWaypoint = firstValue.getValue();

        for (Waypoint currentWaypoint:openList.values()){
            if (currentWaypoint.getPreviousCost() < minimalCoast){
                minimalCoast = currentWaypoint.getPreviousCost();
                cheapestWaypoint = currentWaypoint;
            }
        }
//        Map.Entry<Location, Waypoint> cheapestWaypoint = firstValue;
//
//        for (Map.Entry<Location, Waypoint> item: openList.entrySet()) {
//            if (item.getValue().getPreviousCost() < minimalCoast){
//                minimalCoast = item.getValue().getPreviousCost();
//                cheapestWaypoint = item;
//            }
//        }

        return cheapestWaypoint;
    }

    /**
     * This method adds a waypoint to (or potentially updates a waypoint already
     * in) the "open waypoints" collection.  If there is not already an open
     * waypoint at the new waypoint's location then the new waypoint is simply
     * added to the collection.  However, if there is already a waypoint at the
     * new waypoint's location, the new waypoint replaces the old one <em>only
     * if</em> the new waypoint's "previous cost" value is less than the current
     * waypoint's "previous cost" value.
     **/
    public boolean addOpenWaypoint(Waypoint newWP)
    {
        // Adds waypoint if there's no such one in "open waypoints"
        if (!openList.containsValue(newWP)){
            openList.put(newWP.loc, newWP);
            return true;
        }
        // Adds waypiont if the cost of the current one less then those which is already existed
        for (Map.Entry<Location, Waypoint> item: openList.entrySet()) {
            if (newWP.getPreviousCost() < item.getValue().getPreviousCost()){
                openList.put(newWP.loc, newWP);
                return true;
            }
        }
        return false;
    }


    /** Returns the current number of open waypoints. **/
    public int numOpenWaypoints()
    {
        return openList.size();
    }


    /**
     * This method moves the waypoint at the specified location from the
     * open list to the closed list.
     **/
    public void closeWaypoint(Location loc)
    {
        closeList.put(loc, openList.get(loc));
        openList.remove(loc);
    }

    /**
     * Returns true if the collection of closed waypoints contains a waypoint
     * for the specified location.
     **/
    public boolean isLocationClosed(Location loc)
    {
        if (closeList.containsKey(loc)) return true;
        return false;
    }
}


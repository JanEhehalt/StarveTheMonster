package com.trs.game;

import com.badlogic.gdx.math.Polygon;

public class StaticMath {

    public static double calculateAngle(int xPos1, int yPos1, int xPos2, int yPos2){
        float deltaX = xPos2 - xPos1;
        float deltaY = yPos2 - yPos1;

        double angle;
        if(deltaY == 0){
            if(deltaX < 0){
                angle = Math.PI;
            }
            else{
                angle = 0;
            }
        }
        else if(deltaX == 0 && deltaY >= 0){
            angle = Math.PI / 2;
        }
        else if(deltaX == 0 && deltaY < 0){
            angle = Math.PI / -2;
        }
        else{
            angle = Math.abs(Math.atan(deltaY / deltaX));

            if(deltaX < 0 && deltaY < 0){
                angle = Math.PI + angle;
            }
            else if(deltaX < 0 && deltaY > 0){
                angle = Math.PI - angle;
            }
            else if(deltaX > 0 && deltaY < 0){
                angle = 2*Math.PI - angle;
            }
        }

        return angle;
    }

    public static Polygon createPolygon(int xPos, int yPos, double angle, double width, double length){
        float[] points = new float[8];

        double phi = (Math.PI / 2) - Math.toRadians(angle);
        double d = Math.sin(phi) * width;
        double e = Math.cos(phi) * width;
        double f = Math.sin(Math.toRadians(angle)) * length;
        double g = Math.cos(Math.toRadians(angle)) * length;

        points[0] = xPos;
        points[1] = yPos;
        points[2] = points[0] + (float) e;
        points[3] = points[1] - (float) d;
        points[4] = points[2] + (float) g;
        points[5] = points[3] + (float) f;
        points[6] = points[4] - (float) e;
        points[7] = points[5] + (float) d;

        return new Polygon(points);
    }

    public static Polygon[] createCollisionPolygons(Polygon wall){
        Polygon[] collisionPolygons = new Polygon[4];

        // first line of polygon
        float[] newVertices = wall.getVertices();

        // last four coordinates are all incremented by one, so the polygon is 1 width polygon
        for(int i = 4; i < 8; i++){
            newVertices[i] = newVertices[i-4] + 1;
        }
        collisionPolygons[0] = new Polygon(newVertices);

        // third line of polygon
        newVertices = wall.getVertices();

        // last four coordinates are all decremented by one, so the polygon is 1 width polygon
        for(int i = 4; i < 8; i++){
            newVertices[i-4] = newVertices[i] - 1;
        }
        collisionPolygons[2] = new Polygon(newVertices);

        // second line of polygon
        newVertices = wall.getVertices();

        newVertices[0] = newVertices[2];
        newVertices[1] = newVertices[3] + 1;
        newVertices[6] = newVertices[4];
        newVertices[7] = newVertices[5] + 1;
        collisionPolygons[1] = new Polygon(newVertices);

        // fourth line of polygon
        newVertices = wall.getVertices();

        newVertices[2] = newVertices[0];
        newVertices[3] = newVertices[1] - 1;
        newVertices[4] = newVertices[6];
        newVertices[5] = newVertices[7] - 1;
        collisionPolygons[3] = new Polygon(newVertices);

        return collisionPolygons;
    }

}

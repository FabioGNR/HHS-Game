/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hhsgame.editor;

import hhsgame.Barricade;
import hhsgame.BoardCoordinate;
import hhsgame.EmptyTile;
import hhsgame.Finish;
import hhsgame.Key;
import hhsgame.KeyTile;
import hhsgame.Tile;
import hhsgame.Wall;
import java.awt.Image;

/**
 *
 * @author Fabio
 */
public enum TileType {
    
    Empty{

        @Override
        public Image getImage() {
            return EmptyTile.getImage();
        }

        @Override
        public Tile createInstance(BoardCoordinate pos) {
            return new EmptyTile(pos);
        }       
    },
    Key{
        private final Key defaultKey = new Key(1);
        @Override
        public Image getImage() {
            return KeyTile.getImage();
        }

        @Override
        public Tile createInstance(BoardCoordinate pos) {
            return new KeyTile(pos, defaultKey);
        }
    },
    Barricade{

        @Override
        public Image getImage() {
            return hhsgame.Barricade.getImage();
        }

        @Override
        public Tile createInstance(BoardCoordinate pos) {
            return new Barricade(pos, 1);
        }      
    },
    Wall{

        @Override
        public Image getImage() {
            return hhsgame.Wall.getImage();
        }

        @Override
        public Tile createInstance(BoardCoordinate pos) {
            return new Wall(pos);
        }
        
    },
    Finish{

        @Override
        public Image getImage() {
            return hhsgame.Finish.getImage();
        }

        @Override
        public Tile createInstance(BoardCoordinate pos) {
            return new Finish(pos);
        }
    };
    
    public abstract Image getImage();
    public abstract Tile createInstance(BoardCoordinate pos);  
};
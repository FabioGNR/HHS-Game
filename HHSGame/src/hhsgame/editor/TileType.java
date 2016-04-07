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
import hhsgame.GameCharacter;
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
    // every tile button has different kinds of behavior concerning the image
    // with which it displays and the tile it returns.
    Empty{

        @Override
        public Image getImage() {
            return EmptyTile.getImage();
        }

        @Override
        public Tile createInstance(BoardCoordinate pos, int keyCode) {
            return new EmptyTile(pos);
        }       
    },
    Key{
        //private final Key defaultKey = new Key(1);
        @Override
        public Image getImage() {
            return KeyTile.getImage();
        }

        @Override
        public Tile createInstance(BoardCoordinate pos, int keyCode) {
            return new KeyTile(pos, new Key(keyCode));
        }
    },
    Barricade{

        @Override
        public Image getImage() {
            return hhsgame.Barricade.getImage();
        }

        @Override
        public Tile createInstance(BoardCoordinate pos, int keyCode) {
            return new Barricade(pos, keyCode);
        }      
    },
    Wall{

        @Override
        public Image getImage() {
            return hhsgame.Wall.getImage();
        }

        @Override
        public Tile createInstance(BoardCoordinate pos, int keyCode) {
            return new Wall(pos);
        }
        
    },
    Finish{

        @Override
        public Image getImage() {
            return hhsgame.Finish.getImage();
        }

        @Override
        public Tile createInstance(BoardCoordinate pos, int keycode) {
            return new Finish(pos);
        }
    },
    Start{

        @Override
        public Image getImage() {
            return GameCharacter.getImage();
        }

        @Override
        public Tile createInstance(BoardCoordinate pos, int keyCode) {
            return new EmptyTile(pos);
        }
        
    };
    
    public abstract Image getImage();
    public abstract Tile createInstance(BoardCoordinate pos, int keyCode);  
};

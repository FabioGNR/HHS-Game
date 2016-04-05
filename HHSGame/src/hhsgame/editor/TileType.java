/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hhsgame.editor;

import hhsgame.BoardCoordinate;
import hhsgame.EmptyTile;
import hhsgame.KeyTile;
import hhsgame.Tile;
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

        @Override
        public Image getImage() {
            return KeyTile.getImage();

        @Override
        public Tile createInstance(BoardCoordinate pos) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
        
    };
    
    public abstract Image getImage();
    public abstract Tile createInstance(BoardCoordinate pos);  
};

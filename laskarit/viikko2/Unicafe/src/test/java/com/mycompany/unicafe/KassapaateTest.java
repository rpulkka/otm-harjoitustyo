/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.unicafe;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author rpulkka
 */
public class KassapaateTest {
    Kassapaate kassa;
    Maksukortti kortti;
    
    @Before
    public void setUp() {
        kassa = new Kassapaate();
        kortti = new Maksukortti(10000);
    }
    
    @Test
    public void kassaOikein() {
        assertEquals(kassa.kassassaRahaa(),100000);
        assertEquals(kassa.edullisiaLounaitaMyyty(),0);
        assertEquals(kassa.maukkaitaLounaitaMyyty(),0);
    }
    
    @Test
    public void maksuEdullinen(){
        int a=kassa.syoEdullisesti(300);
        assertEquals(kassa.kassassaRahaa(),100240);
        assertEquals(a,60);
        assertEquals(kassa.edullisiaLounaitaMyyty(),1);
    }
    
    @Test
    public void maksuMaukas(){
        int a=kassa.syoMaukkaasti(500);
        assertEquals(kassa.kassassaRahaa(),100400);
        assertEquals(a,100);
        assertEquals(kassa.maukkaitaLounaitaMyyty(),1);
    }
    
    @Test
    public void maksuEpakelpoEdullinen(){
        int a=kassa.syoEdullisesti(10);
        assertEquals(kassa.kassassaRahaa(),100000);
        assertEquals(a,10);
        assertEquals(kassa.edullisiaLounaitaMyyty(),0);
    }
    
    @Test
    public void maksuEpakelpoMaukas(){
        int a=kassa.syoMaukkaasti(10);
        assertEquals(kassa.kassassaRahaa(),100000);
        assertEquals(a,10);
        assertEquals(kassa.edullisiaLounaitaMyyty(),0);
    }
    
    @Test
    public void korttimaksuEdullinen(){
        boolean boohoo = kassa.syoEdullisesti(kortti);
        assertEquals(kassa.kassassaRahaa(),100000);
        assertEquals(kortti.toString(),"saldo: 97.60");
        assertEquals(kassa.edullisiaLounaitaMyyty(),1);
    }
    
    @Test
    public void korttimaksuMaukas(){
        boolean boohoo = kassa.syoMaukkaasti(kortti);
        assertEquals(kassa.kassassaRahaa(),100000);
        assertEquals(kortti.toString(),"saldo: 96.0");
        assertEquals(kassa.maukkaitaLounaitaMyyty(),1);
    }
    
    @Test
    public void korttimaksuEpakelpoEdullinen(){
        kortti.otaRahaa(10000);
        boolean boohoo = kassa.syoEdullisesti(kortti);
        assertEquals(kassa.kassassaRahaa(),100000);
        assertEquals(kortti.toString(),"saldo: 0.0");
        assertEquals(kassa.edullisiaLounaitaMyyty(),0);
    }
    
    @Test
    public void korttimaksuEpakelpoMaukas(){
        kortti.otaRahaa(10000);
        boolean boohoo = kassa.syoMaukkaasti(kortti);
        assertEquals(kassa.kassassaRahaa(),100000);
        assertEquals(kortti.toString(),"saldo: 0.0");
        assertEquals(kassa.edullisiaLounaitaMyyty(),0);
    }
    
    @Test
    public void korttimaksuLataus(){
        kassa.lataaRahaaKortille(kortti, 10);
        assertEquals(kassa.kassassaRahaa(),100010);
        assertEquals(kortti.toString(),"saldo: 100.10");
    }
    
    @Test
    public void korttimaksuLatausNegatiivinen(){
        kassa.lataaRahaaKortille(kortti, -10);
        assertEquals(kassa.kassassaRahaa(),100000);
        assertEquals(kortti.toString(),"saldo: 100.0");
    }
}

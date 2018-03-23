package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MaksukorttiTest {

    Maksukortti kortti;

    @Before
    public void setUp() {
        kortti = new Maksukortti(10);
    }

    @Test
    public void luotuKorttiOlemassa() {
        assertTrue(kortti!=null);      
    }
    
    @Test
    public void saldoOikein() {

        assertEquals(kortti.saldo(),10);      
    }
    
    @Test
    public void latausToimii() {
        kortti.lataaRahaa(1);
        assertEquals(kortti.toString(),"saldo: 0.11");      
    }
    
    @Test
    public void ottoToimii() {
        kortti.otaRahaa(1);
        assertEquals(kortti.toString(),"saldo: 0.9");      
    }
    
    @Test
    public void eiNegaa() {
        kortti.otaRahaa(11);
        assertEquals(kortti.toString(),"saldo: 0.10");      
    }
    
    @Test
    public void saldoTrue() {
        assertTrue(kortti.otaRahaa(1));      
    }
    
    @Test
    public void saldoFalse() {
        assertFalse(kortti.otaRahaa(12));      
    }
}

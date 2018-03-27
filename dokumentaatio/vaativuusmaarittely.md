 <h1>Vaativuusmäärittely</h1>
 
 
 
 <h2>Sovelluksen tarkoitus</h2>
 
 Harjoitustyön aiheena on Yatzy -simulaattori (Yahtzee) :game_die:, jossa yksi tai useampi pelaaja voi pelata kuuluisan noppapelin 
 virtuaalista versiota. Pelin perusidea on, että pelaajat heittävät vuorollaan viittä noppaa korkeintaan kolmesti ja pyrkivät 
 muodostamaan valmiiksi määrättyjä comboja noppien silmäluvuista.
 
 
 
 <h2>Käyttäjät</h2>
 
 Lähtökohtaisesti kaikki käyttäjät ovat pelaajia, joten käyttäjärooleja on vain yksi, jos muutoksen tarvetta ei ilmaannu.
 
 
 
 <h2>Käyttöliittymäluonnos</h2>
 
 Käyttöliittymän ydin on pelinäkymä, joka jakautuu kolmeen alueeseen: heittoalue, kombinaatioalue ja tulostaulukko.
 
 (Yahtzee.jpg)
 
 <h2>Perusversion tarjoama toiminnallisuus</h2>
 
 Heittoalueella (throwing area) olevia viittä noppaa voi heittää painamalla "Throw the dice" painiketta, jolloin heittoalueella
 olevien noppien silmäluvut arvotaan uudelleen. Tämän toiminnon voi tehdä korkeintaan kolmesti, ennen kuin on valittava 
 käytettävä kombinaatio.
 
 Kombinaatioalueelle (combination area) voi tallettaa nopan heittoalueelta klikkaamalla noppaa, jolloin sitä ei heitetä
 uudelleen, ensi heitolla, vaan se säilyy kombinaatioalueella.  
 
 Tulostaulukossa on kirjattu kaikki mahdolliset pisteytettävät kombinaatiot. Kombinaation nimeä klikkaamalla pelaaja valitsee
 tämän kombinaation pisteytettäväksi, jolloin ohjelma tarkistaa kombinaatioalueen nopat ja laskee niistä kertyvät pisteet
 pelaajan juuri valitseman kombinaatiokategorian perusteella. Esim. jos pelaajan kombinaatiovyöhykkeellä on 3 noppaa, joiden 
 silmälukuna on 5 ja pelaaja valitsee tulostaulukosta kombinaation "3 of a kind" klikkaamalla kombinaation nimeä tulostaulukossa,
 niin pelaaja saa 3*5=15 pistettä "3 of a kind" sarakkeeseen ja moninpelissä vuoro siirtyy seuraavalle ja yksinpelissä pelaaja
 tekee seuraavan vuoron. Pelissä täytyy ensin täyttää kaikki "bonus" kombinaation yläpuolelle merkityt kombinaatiot ennen kuin
 saa alkaa täyttämään sen alapuolella olevia kombinaatioita ja kun pelaajat ovat täyttäneet jokaisen "bonus" kombinaation
 yläpuolella olevan rivin, niin mikäli näiden rivien summa on suurempi kuin 63, niin pelaajan "bonus" riville kirjataan 50
 (muutoin 0), jonka jälkeen aletaan täyttämään taulukon alempia rivejä. Joka vuorolla on pisteytettävä jokin kombinaatioista,
 vaikka saisi nolla pistettä. Sarakkeet erottavat pelaajien pisteet toisistaan. Pelin lopuksi kombinaatioista kerrytetyt pisteet 
 lasketaan yhteen ja eniten pisteitä saanut pelaaja voittaa.
 
 
 
 <h2>Jatkokehitysideoita</h2> 
 
 -Paikallinen highscores -ennätystaulukko (ennätyksen tehnyt pelaaja saa kirjoittaa nimensä/nimimerkkinsä tauluun)
 
 -Kombinaatiokohtaiset piste-ennätykset (esim. kuka on saanut isoimman sattuma -kombinaation pelin historiassa)
 
 -(Vuoropohjainen moninpeli, jos se ei sisälly jo perusversioon) 
 
 -Peli voisi kertoa, mitkä kombinaatiot olisivat tilanteessa kannattavia / tuottaisivat pisteitä (olisiko liian helppoa?)
  
 -(Nopan animaatio)
 
 -Äänisuunnittelu
 
 -Pelissä voisi saada palkinnoksi hienommat(eri väriset) nopat, kun on pelannut tarpeeksi monta peliä, tai saanut tarpeeksi
  paljon pisteitä yhdessä pelissä
  
 -(Tekoäly)
 
 -Muuta tilastoitavaa dataa aiemmista peleistä
 

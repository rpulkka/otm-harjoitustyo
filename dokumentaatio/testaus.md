<h1>Testausdokumentti</h1>

Ohjelma on testattu käyttäen automaattisia JUNIT yksikkö- ja integraatiotestejä, sekä ohjelma on kokeiltu ladata ja
testata sen toimintaa järjestelmätasolla manuaalisesti.

<h2>Yksikkö- ja integraatiotestaus</h2>

<h3>Sovelluslogiikka</h3>
Sovelluslogiikan testaus koostuu integraatiotestistä CombinationManagerTest.java, joka testaa CombinationManager 
-luokkaa sekä samalla kaikkia kombinaatioita käsitteleviä luokkia, sekä yksikkötesteistä DieTest.java ja 
DiceThrowerTest.java, jotka testaavat nimiensä mukaisia luokkia. CombinationManagerTest muodostaa yksin suurimman
osan testeistä ja sen tärkeimpiä testejä ovat testit, jotka kokeilevat pisteytystä eri nopilla tarkistaen
pisteytetäänkö ne oikein. Nämä testit ovat nimetty käyttäen nimenä scoresCombination, 
wontScoreCombinationWithoutReason, moreThanCombinationCountsAsCombination, missä combination on tietenkin testattavan
kombinaation nimi.

<h3>DAO -luokat</h3>
DAO -luokka on testattu luomalla tilapäinen test.db tiedosto helpottamaan DAO:n testausta. Tämä tapahtuu luomalla
kyseinen tiedosto avaamalla siihen yhteys ja testien lopuksi poistamalla tiedosto File.delete() metodilla. DAO 
-luokan testiluokka on integraatiotesti DaoTest.java.

<h3>Testikattavuus</h3>
Käyttöliittymäluokkia lukuunottamatta sovelluksen testauksen rivikattavuus on 95% ja haarautumakattavuus 92%.

![GitHub Logo](jacoco.png)

Testaamatta jäi mm. kombinaatioita käsittelevien luokkien yksikkötestaus.

<h2>Järjestelmätestaus</h2>

<h2>Sovellukseen jääneet laatuongelmat</h2>


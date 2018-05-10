<h1>Yahtzee</h1>

Sovellus on kuuluisan Yatzy -noppapelin virtuaalinen versio. Pelin tarkoituksena on keräillä viittä noppaa 
heittämällä ennalta määrättyjä kombinaatioita, joista sitten saa lähtökohtaisesti noppien silmälukujen verran
pisteitä ja tavoitteena on kerätä mahdollisimman suuri pistesaalis pelin loppuun mennessä. Peliä pelataan
yksitellen, mutta paikallisen top 10 -listan ansiosta voit haastaa kaverisi kilpailemaan pisteistä, sillä
ennätykset tallennetaan paikallisesti. Pelin iloa!

<h2>Dokumentaatio</h2>

[Käyttöohje](https://github.com/rpulkka/otm-harjoitustyo/blob/master/dokumentaatio/kayttoohje.md)

[Vaativuusmäärittely](https://github.com/rpulkka/otm-harjoitustyo/blob/master/dokumentaatio/vaativuusmaarittely.md)

[Arkkitehtuurikuvaus](https://github.com/rpulkka/otm-harjoitustyo/blob/master/dokumentaatio/arkkitehtuuri.md)

[Testausdokumentti](https://github.com/rpulkka/otm-harjoitustyo/blob/master/dokumentaatio/testaus.md)

[Tuntikirjanpito](https://github.com/rpulkka/otm-harjoitustyo/blob/master/dokumentaatio/tuntikirjanpito.md)


<h2>Releaset</h2>

[Yahtzee_v01_1](https://github.com/rpulkka/otm-harjoitustyo/releases/tag/viikko5)

[Yahtzee_v01_2](https://github.com/rpulkka/otm-harjoitustyo/releases/tag/viikko6)

<h2>Komentorivitoiminnot</h2>

### Testaus

Testit suoritetaan komennolla

```
mvn test
```

Testikattavuusraportti luodaan komennolla

```
mvn jacoco:report
```

Kattavuusraporttia voi tarkastella avaamalla selaimella tiedosto _target/site/jacoco/index.html_

### Suoritettavan jarin generointi

Komento

```
mvn package
```

generoi hakemistoon _target_ suoritettavan jar-tiedoston _Yahtzee-1.0-SNAPSHOT.jar_

### JavaDoc

JavaDoc generoidaan komennolla

```
mvn javadoc:javadoc
```

JavaDocia voi tarkastella avaamalla selaimella tiedosto _target/site/apidocs/index.html_

### Checkstyle

Tiedostoon [checkstyle.xml](https://github.com/rpulkka/otm-harjoitustyo/blob/master/checkstyle.xml) määrittelemät tarkistukset suoritetaan komennolla

```
 mvn jxr:jxr checkstyle:checkstyle
```

Mahdolliset virheilmoitukset selviävät avaamalla selaimella tiedosto _target/site/checkstyle.html_

---

**Author: Robert Pulkka**

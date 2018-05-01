<h1>Yahtzee</h1>

Sovellus on kuuluisan Yatzy -noppapelin virtuaalinen versio. Pelin tarkoituksena on keräillä viittä noppaa 
heittämällä ennalta määrättyjä kombinaatioita, joista sitten saa lähtökohtaisesti noppien silmälukujen verran
pisteitä ja lopussa eniten pisteitä kerännyt pelaaja voittaa. Peliä voi pelata kahdestaan vuorottelemalla tai
kilpailla yksin tavoitellen omaa ennätystä.

<h2>Dokumentaatio</h2>

[Käyttöohje](https://github.com/rpulkka/otm-harjoitustyo/blob/master/dokumentaatio/kayttoohje.md)

[Vaativuusmäärittely](https://github.com/rpulkka/otm-harjoitustyo/blob/master/dokumentaatio/vaativuusmaarittely.md)

[Tuntikirjanpito](https://github.com/rpulkka/otm-harjoitustyo/blob/master/dokumentaatio/tuntikirjanpito.md)

[Arkkitehtuurikuvaus](https://github.com/rpulkka/otm-harjoitustyo/blob/master/dokumentaatio/arkkitehtuuri.md)


<h2>Releaset</h2>

[Yahtzee_v01_1](https://github.com/rpulkka/otm-harjoitustyo/releases)

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

Tiedostoon [checkstyle.xml](https://github.com/mluukkai/OtmTodoApp/blob/master/checkstyle.xml) määrittelemät tarkistukset suoritetaan komennolla

```
 mvn jxr:jxr checkstyle:checkstyle
```

Mahdolliset virheilmoitukset selviävät avaamalla selaimella tiedosto _target/site/checkstyle.html_

---

**Author: Robert Pulkka**

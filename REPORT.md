# Kurssitehtävien oppima ja raportit

Kirjoita jokaisesta alla olevasta kurssitehtävästä parilla lauseella miten tehtävän tekeminen sujui ja mitä siitä opit.

Jos tehtävässä pyydetään **raportoimaan** jotain, kirjoita myös nämä raportit tähän dokumenttiin.

## 00-init
Opin gitin käyttöä. En ole ennen käyttänyt git komentoja, joten on mukava päästä käyttämään oikean teollisuuden käytäntöjä ja työkaluja.
Olen itse myös käyttänyt aikaisemmin Eclipsea, joten opin VSC käyttöä, joka tuntuu jo nyt erinomaiselta alustalta.

## 01-arrays
Sain vähän koppia geneerisyydestä. Se taito oli jo vähän päässyt ruostumaan ohjelmointi 2 kurssilta. Tässä tehtävässä pääsin myös ensimmäistä kertaa kunnolla kiinni debuggaamisesta. Olen debugannut koodiani aikaisemminkin, mutta nyt ehkä palaset vasta "loksahtivat" paikoilleen jotenkin.

## 02-mode
Opin aikakompeksisuudesta ja miten paljon eroa voi tehokkuudessa olla samaa tekevässä koodinpätkässä.

Tähän tehtävään liittyy raportti! Lue ohjeet!

![02-mode](../../../../../E:/koodaus/VSC/tira/tira-origin-2022/02-mode/02-mode.png)

Analysoi koodisi aikatehokkuutta eli suoritusaikaa diagrammin muihin mittauksiin ja niistä piirrettyihin viivadiagrammeihin. Tarkastele myös numeerisia arvoja ja kysy itseltäsi mikä mahtaa olla toteutuksen aikakompleksisuusluokka? Tutki koodiasi ja mieti mikä on koodisi aikakompleksisuus? Huomioi kaikki mitä tehdään findMode -metodin sisällä, mukaan lukien muut kutsuttavat omat metodisi. Mikä on tämän perusteella koodisi aikakompleksisuus? Miten se suhteutuu graafiseen esitykseen suoritusajoista n:n koon kasvaessa?

Aika kompleksisuus on ohjelmassani Linearithmic (n log n), koska ohjelma on lajittelu algoritmi ja se on paras, johon tämän lajittelun kanssa pystymme.

Ohjelma lajittelee (sort) arvot ensiksi kasvavaan järjestykseen ja sitten tarkistaa kaikesta järjestelmällisesti tyyppiarvon. Mitä enemmän arvoja niin ohjelman pitää edelleen tarkistaa kaikki arvot, jotta saa oikean tyyppiarvo vastauksen. Ohjelman lopussa se tarkistaa vielä viimeisen arvon, että onko se tyyppiarvo. Ohjelman on käytävä kaikki arvot läpi ja laskea montako niitä on. Aikakompleksisuus on tässä algoritmissä (n * log * n)

Graafisesti tämä näkyy käyränä, joka hitaasti mutta varmasti kasvaa eli mitä enemmän arvoja niin kauemmin kestää sen läpikäymisessä.


## 03-draw
Sain ensikosketuksen predikaatin käytössä. 

## 04-1-stack
Ymmärrän Stack algoritmia nyt ja ymmärrän miten se toimii. Geneerisyyden ymmärrys syventyi hiukan jälleen.

## 04-2-queue
Ymmärrän queue algoritmia nyt ja opin myös Timerin käyttöä. 

## 04-3-linkedlist


## 05-binsearch


## 05-invoices


## 67-phonebook

Tähän tehtävään liittyy raportti! Lue ohjeet!

## Valinnaiset tehtävät

Jos teit jotain valinnaisia tehtäviä, mainitse se täällä että ne tulevat varmasti arvioiduksi.

# Yleistä koko kurssista ja kurssin tehtävistä

Yleistä palautetta ja kehitysehdotuksia, kiitos!
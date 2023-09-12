package com.pajic.repository;

import java.util.List;

/**
 * Predstavlja specifikaciju repozitorijuma, koja daje potpise metoda za dodavanje, brisanje, izmenu i izlistavanje podataka iz baze.
 *
 * Rad svih metoda je zasnovan na implementaciji GenericEntity interfejsa od strane domenskih klasa.
 *
 * @param <T> Tip podataka za koji ce ovaj repozitorijum da bude prilagodjen.
 * @author Pavle Pajic
 * @since 1.0.0
 *
 */
public interface Repository<T> {

    /**
     * Vraca sve objekte odredjenog tipa iz baze podataka pomocu SELECT upita i postojece konekcije nad bazom podataka.
     *
     * Najpre se upit izvrsava nad bazom uz pomoc Statement klase, a zatim se rezultati tog upita cuvaju u objektu ResultSet klase.
     * Nakon toga se pomocu metoda specificiranih u okviru GenericEntity interfejsa prolazi kroz svaki red rezultata i on se na kraju dodaje u listu rezultata.
     * @param param - Objekat odredjenog tipa cije instance treba iz baze postaviti u listu objekata tog tipa.
     * @return entities - Lista objekata odredjenog tipa.
     * @throws Exception - U slucaju da dodje do greske prilikom rada sa bazom podataka u bilo kom trenutku.
     */
    List<T> getAll(T param) throws Exception;

    /**
     * Vraca sve objekte odredjenog tipa ciji se ID poklapa sa prosledjenim ID-jem iz baze podataka pomocu SELECT upita i postojece konekcije nad bazom podataka.
     *
     * Najpre se upit izvrsava nad bazom uz pomoc Statement klase, a zatim se rezultati tog upita cuvaju u objektu ResultSet klase.
     * Nakon toga se pomocu metoda specificiranih u okviru GenericEntity interfejsa prolazi kroz svaki red rezultata i on se na kraju dodaje u listu rezultata.
     * @param param - Objekat odredjenog tipa cije instance treba iz baze postaviti u listu objekata tog tipa.
     * @param id - Prosledjeni ID po kome se filtriraju podaci.
     * @return entities - Lista objekata odredjenog tipa.
     * @throws Exception - U slucaju da dodje do greske prilikom rada sa bazom podataka u bilo kom trenutku.
     */
    List<T> getAllFiltered(T param, long id) throws Exception;

    /**
     * Vraca sve objekte odredjenog tipa koji odgovaraju prosledjenom parametru za pretragu iz baze podataka pomocu SELECT upita i postojece konekcije nad bazom podataka.
     *
     * Najpre se upit izvrsava nad bazom uz pomoc Statement klase, a zatim se rezultati tog upita cuvaju u objektu ResultSet klase.
     * Nakon toga se pomocu metoda specificiranih u okviru GenericEntity interfejsa prolazi kroz svaki red rezultata i on se na kraju dodaje u listu rezultata.
     * @param param - Objekat odredjenog tipa cije instance treba iz baze postaviti u listu objekata tog tipa.
     * @param searchParameter - Prosledjeni parametar za pretragu po kome se filtriraju podaci.
     * @return entities - Lista objekata odredjenog tipa.
     * @throws Exception - U slucaju da dodje do greske prilikom rada sa bazom podataka u bilo kom trenutku.
     */
    List<T> findAll(T param, String searchParameter) throws Exception;

    /**
     * Dodaje objekat odredjenog tipa u bazu podataka, koristeci INSERT upit i konekciju sa bazom podataka.
     *
     * Izvrsava INSERT upit pomocu Statement klase, a zatim se objektu koji je prosledjen dodeljuje informacija o primarnom kljucu.
     * @param param - Objekat koji je potrebno upisati u bazu podataka.
     * @throws Exception - U slucaju da nastane greske pri radu sa bazom podataka
     */
    void add(T param) throws Exception;

    /**
     * Menja odredjeni red iz odgovarajuce tabele u okviru baze podataka, koristeci UPDATE upit i postojecu konekciju sa bazom.
     *
     * Izvrsava se UPDATE upit pomocu Statement klase, cime se upravo i menja red u odgovarajucoj tabeli baze podataka.
     * @param param - Objekat odredjenog tipa ciji atributi sadrze izmenjene podatke koje treba iskoristiti prilikom izmene podataka u bazi.
     * @throws Exception - U slucaju da dodje do greske u radu sa bazom podataka u bilo kom trenutku.
     */
    void edit(T param) throws Exception;

    /**
     * Brise odredjeni red iz odgovarajuce tabele u okviru baze podataka, koristeci DELETE upit i postojecu konekciju sa bazom.
     *
     * Izvrsava se DELETE upit pomocu Statement klase, cime se upravo i brise red u odgovarajucoj tabeli baze podataka.
     * @param param - Objekat odredjenog tipa ciji atributi sadrze podatke koje treba iskoristiti prilikom brisanja redova iz baze podataka.
     * @throws Exception - U slucaju da dodje do greske u radu sa bazom podataka u bilo kom trenutku.
     */
    void delete(T param)throws Exception;
}

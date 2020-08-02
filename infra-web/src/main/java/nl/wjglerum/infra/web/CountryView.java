package nl.wjglerum.infra.web;


import nl.wjglerum.domain.Country;

public class CountryView {
    String name;
    String code;
    String capital;

    public static CountryView fromDomain(Country country) {
        CountryView view = new CountryView();
        view.name = country.name();
        view.code = country.code();
        view.capital = country.capital().name();
        return view;
    }
}

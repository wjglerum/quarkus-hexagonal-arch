package nl.wjglerum.domain;

import java.util.Set;

public interface CountriesService {

    Set<Country> getByName(String name);
}

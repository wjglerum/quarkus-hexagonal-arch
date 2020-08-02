package nl.wjglerum.domain;

import org.immutables.value.Value;

import java.util.List;

@Value.Immutable
@Value.Style(stagedBuilder = true)
public interface Country {
    String name();

    String code();

    City capital();

    List<Currency> currencies();
}

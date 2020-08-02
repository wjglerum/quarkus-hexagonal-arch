package nl.wjglerum.domain;

import org.immutables.value.Value;

@Value.Immutable
@Value.Style(stagedBuilder = true)
public interface Currency {
    String code();

    String name();

    String symbol();
}

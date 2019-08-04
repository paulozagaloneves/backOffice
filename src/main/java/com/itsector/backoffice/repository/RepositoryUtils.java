package com.itsector.backoffice.repository;

import org.springframework.dao.EmptyResultDataAccessException;

import java.util.Optional;
import java.util.function.Supplier;

public class RepositoryUtils {

    private RepositoryUtils() {}

    public static <T> Optional<T> optionalResult(final Supplier<T> supplier) {
        try {
            return Optional.ofNullable(supplier.get());
        } catch (final EmptyResultDataAccessException e) {
            return  Optional.empty();
        }
    }
}
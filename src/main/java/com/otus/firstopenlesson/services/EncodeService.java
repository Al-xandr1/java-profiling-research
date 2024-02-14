package com.otus.firstopenlesson.services;

import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.Base64;

@RequiredArgsConstructor
public class EncodeService {

    private Encoder encoder;

    private static final int STACK_DEPTH = 100;

    public byte[] encode(byte[] hash) {
        byte[] safe = Arrays.copyOf(hash, hash.length);
        return encodeInner(safe, 0);
    }

    private byte[] encodeInner(byte[] hash, int level) {
        if (level < STACK_DEPTH) {
            // imitation of long stacktrace
            return encodeInner(hash, ++level);
        }

        byte[] result;
        try {
            result = encoder.encode(hash);

        } catch (Exception e) {
            result = Base64.getEncoder().encode(hash);
        }

        return result;
    }
}
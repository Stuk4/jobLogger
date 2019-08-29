package util;

import org.junit.Test;

import static org.junit.Assert.*;

public class StringUtilTest {

    @Test
    public void isEmpty() {
        assertTrue(StringUtil.isEmpty(null));
        assertTrue(StringUtil.isEmpty(""));
        assertFalse(StringUtil.isEmpty("23123"));
    }

    @Test
    public void fromString() {
        assertTrue(StringUtil.booleanFromString("true"));
        assertFalse(StringUtil.booleanFromString("false"));
        assertFalse(StringUtil.booleanFromString(""));
        assertFalse(StringUtil.booleanFromString(null));
    }
}
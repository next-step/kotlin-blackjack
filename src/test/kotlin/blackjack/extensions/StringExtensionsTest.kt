package blackjack.extensions

import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class StringExtensionsTest {

    @ValueSource(strings = ["Y", "y"])
    @ParameterizedTest
    fun `Y 일때 True`(str: String) {
        val actual = str.fromYNToBoolean()

        assertTrue(actual)
    }

    @ValueSource(strings = ["n", "N", "a"])
    @ParameterizedTest
    fun `Y 가 아닐 때 false`(str: String) {
        val actual = str.fromYNToBoolean()

        assertFalse(actual)
    }
}

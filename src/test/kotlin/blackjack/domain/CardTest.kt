package blackjack.domain

import blackjack.enums.Type
import blackjack.enums.Value
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EnumSource

class CardTest {

    @Test
    fun `card consist of the type and value`() {
        val type = Type.SPADE
        val value = Value.ACE

        val card = Card(type, value)

        assertThat(card).extracting("type").isEqualTo(Type.SPADE)
        assertThat(card).extracting("value").isEqualTo(Value.ACE)
    }

    @Test
    fun `ACE can be the one or the eleven`() {
        val value = Value.ACE

        assertThat(value).hasFieldOrPropertyWithValue("origin", 1)
            .hasFieldOrPropertyWithValue("alternative", 11)
    }

    @ParameterizedTest
    @EnumSource(value = Value::class, names = ["KING", "QUEEN", "JACK"])
    fun `King, queen, jack could be the ten`(value: Value) {

        assertThat(value).hasFieldOrPropertyWithValue("origin", 10)
    }
}

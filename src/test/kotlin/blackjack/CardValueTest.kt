package blackjack

import blackjack.CardValue.Companion.CARD_VALUES
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class CardValueTest {
    @ParameterizedTest
    @ValueSource(ints = [2, 3, 4, 5, 6, 7, 8, 9, 10])
    fun `카드 숫자 확인`(number: Int) {
        assertThat(CardValue(number).getNumber()).isEqualTo(number)
    }

    @ParameterizedTest
    @ValueSource(strings = ["A", "J", "Q", "K"])
    fun `카드 문자 확인`(str: String) {
        val stringValue = StringValue.valueOf(str)
        assertThat(CardValue(stringValue).getNumber()).isEqualTo(stringValue.number.first())
    }

    @ParameterizedTest
    @ValueSource(ints = [11, 20])
    fun `A 카드 1 확인`(score: Int) {
        assertThat(CardValue(StringValue.A).getAceNumber(score)).isEqualTo(1)
    }

    @ParameterizedTest
    @ValueSource(ints = [2, 10])
    fun `A 카드 11 확인`(score: Int) {
        assertThat(CardValue(StringValue.A).getAceNumber(score)).isEqualTo(11)
    }
}
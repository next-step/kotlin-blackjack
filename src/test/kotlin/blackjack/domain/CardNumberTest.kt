package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class CardNumberTest {

    @ParameterizedTest
    @ValueSource(ints = [11, 20])
    fun `A 카드 1 확인`(score: Int) {
        // Given
        val ace = CardNumber.ACE

        // When
        val aceNumber = ace.getAceNumber(score)

        // Then
        assertThat(aceNumber).isEqualTo(1)
    }

    @ParameterizedTest
    @ValueSource(ints = [2, 10])
    fun `A 카드 11 확인`(score: Int) {
        // Given
        val ace = CardNumber.ACE

        // When
        val aceNumber = ace.getAceNumber(score)

        // Then
        assertThat(aceNumber).isEqualTo(11)
    }
}

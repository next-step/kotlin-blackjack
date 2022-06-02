package game.blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName

@DisplayName("카드")
internal class CardTest {

    @Test
    fun `무늬와 끗수로 카드 생성`() {
        val expectedSuit = Suit.CLOVER
        val expectedDenomination = Denomination.ACE
        val card = Card(expectedSuit, expectedDenomination)

        assertThat(card.suit).isEqualTo(expectedSuit)
        assertThat(card.denomination).isEqualTo(expectedDenomination)
    }
}
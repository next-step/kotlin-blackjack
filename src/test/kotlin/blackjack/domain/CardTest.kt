package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class CardTest {
    @Test
    fun isAce() {
        assertThat(Card(CardNumber.ACE, CardSuit.CLOVER).isAce()).isTrue
    }
}

internal class HandTest {

    @Test
    fun add() {
        val hand = Hand()
        val newCard = Card(CardNumber.QUEEN, CardSuit.CLOVER)

        hand.add(newCard)

        assertThat(hand.cards).hasSize(1)
            .containsExactly(newCard)
    }
}

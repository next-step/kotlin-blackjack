package blackjack.domain.player

import blackjack.domain.deck.Card
import blackjack.domain.deck.Pip
import blackjack.domain.deck.Suit
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

internal class HandTest {

    @DisplayName("손에 한장씩 카드를 추가할 수 있다.")
    @Test
    fun addNewCard() {
        val hand = Hand().apply {
            addNew(Card(Pip.TWO, Suit.CLUB))
            addNew(Card(Pip.TWO, Suit.HEART))
        }
        assertThat(hand.getCards()).contains(
            Card(Pip.TWO, Suit.CLUB),
            Card(Pip.TWO, Suit.HEART)
        )
    }

    @DisplayName("카드 총합이 21 이하면 손에 새로운 카드를 추가할 수 있다.")
    @Test
    fun hasFreeSpace() {
        val hand = Hand().apply {
            addNew(Card(Pip.TWO, Suit.CLUB))
            addNew(Card(Pip.TEN, Suit.HEART))
        }
        assertThat(hand.hasFreeSpace()).isTrue()
    }
}

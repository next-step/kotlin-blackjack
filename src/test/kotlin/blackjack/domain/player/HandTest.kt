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
        assertThat(hand.cards).contains(
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

    @DisplayName("카드의 총 합계 점수를 계산한다.")
    @Test
    fun calculateScore() {
        val hand = Hand().apply {
            addNew(Card(Pip.TWO, Suit.HEART))
            addNew(Card(Pip.KING, Suit.HEART))
        }
        assertThat(hand.calculate()).isEqualTo(12)
    }

    @DisplayName("총 합이 21 이하면 Ace는 11로 계산한다")
    @Test
    fun calculateMaxAce() {
        val hand = Hand().apply {
            addNew(Card(Pip.ACE, Suit.HEART))
            addNew(Card(Pip.KING, Suit.HEART))
        }
        assertThat(hand.calculate()).isEqualTo(21)
    }

    @DisplayName("총 합이 21을 초과하면 Ace는 1로 계산한다")
    @Test
    fun calculateMinAce() {
        val hand = Hand().apply {
            addNew(Card(Pip.KING, Suit.HEART))
            addNew(Card(Pip.KING, Suit.HEART))
            addNew(Card(Pip.ACE, Suit.HEART))
        }
        assertThat(hand.calculate()).isEqualTo(21)
    }
}

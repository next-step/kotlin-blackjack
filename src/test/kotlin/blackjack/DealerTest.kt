package blackjack

import blackjack.domain.card.Card
import blackjack.domain.card.Hand
import blackjack.domain.card.suit.SuitTypes
import blackjack.domain.player.Dealer
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class DealerTest {

    @Test
    fun `딜러는 hand의 값이 16 이하일 시 카드를 hand에 추가 할 수 있다`() {

        val hand = Hand().apply {
            addCardToHand(Card(SuitTypes.Diamond, 10))
            addCardToHand(Card(SuitTypes.Diamond, 6))
        }
        val dealer = Dealer(hand = hand)
        val actualResult = dealer.isHandAddable()
        Assertions.assertThat(actualResult).isTrue
    }

    @Test
    fun `딜러는 hand의 값이 17 이상일 시 카드를 hand에 추가 할 수 없다`() {
        val hand = Hand().apply {
            addCardToHand(Card(SuitTypes.Diamond, 7))
            addCardToHand(Card(SuitTypes.Diamond, 10))
        }
        val dealer = Dealer(hand = hand)
        val actualResult = dealer.isHandAddable()
        Assertions.assertThat(actualResult).isFalse
    }
}

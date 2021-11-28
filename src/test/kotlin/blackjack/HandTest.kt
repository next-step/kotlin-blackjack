package blackjack

import blackjack.domain.card.Card
import blackjack.domain.card.Hand
import blackjack.domain.card.suit.Diamond
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class HandTest {
    @Test
    fun `Hand의 카드를 가져올 수 있다`() {
        val hand = Hand().apply {
            addCardToHand(Card(Diamond(), 1))
            addCardToHand(Card(Diamond(), 9))
        }
        val actualResult = hand.getCards()
        assertThat(actualResult).contains(Card(Diamond(), 1), Card(Diamond(), 9))
    }

    @Test
    fun `Hand의 가능한 카드 값 합이 21 미만 일경우 카드를 추가 할 수 있다`() {
        val hand = Hand().apply {
            addCardToHand(Card(Diamond(), 1))
            addCardToHand(Card(Diamond(), 9))
        }
        val actualResult = hand.addCardToHand(Card(Diamond(), 13))

        assertThat(actualResult).isTrue
    }

    @Test
    fun `Hand의 가능한 카드 값 합이 21 이상 일경우 카드를 추가 할 수 없다`() {
        val blackJackHand = Hand().apply {
            addCardToHand(Card(Diamond(), 5))
            addCardToHand(Card(Diamond(), 6))
            addCardToHand(Card(Diamond(), 10))
        }
        val actualResultOfBlackJackHand = blackJackHand.addCardToHand(Card(Diamond(), 13))
        assertThat(actualResultOfBlackJackHand).isFalse

        val blackJackHandWithAce = Hand().apply {
            addCardToHand(Card(Diamond(), 1))
            addCardToHand(Card(Diamond(), 1))
            addCardToHand(Card(Diamond(), 9))
        }
        val actualResultOfBlackJackHandWithAce = blackJackHandWithAce.addCardToHand(Card(Diamond(), 13))
        assertThat(actualResultOfBlackJackHandWithAce).isFalse

        val bustedHand = Hand().apply {
            addCardToHand(Card(Diamond(), 10))
            addCardToHand(Card(Diamond(), 9))
            addCardToHand(Card(Diamond(), 8))
        }
        val actualResultForBustedHand = bustedHand.addCardToHand(Card(Diamond(), 13))
        assertThat(actualResultForBustedHand).isFalse
    }
}

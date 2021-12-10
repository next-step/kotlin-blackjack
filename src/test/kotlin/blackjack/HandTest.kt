package blackjack

import blackjack.domain.card.Card
import blackjack.domain.card.Hand
import blackjack.domain.card.suit.SuitTypes.Diamond
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class HandTest {
    @Test
    fun `Hand의 카드를 가져올 수 있다`() {
        val hand = Hand().apply {
            addCardToHand(Card(Diamond, 1))
            addCardToHand(Card(Diamond, 9))
        }
        val actualResult = hand.getCardListInHand()
        assertThat(actualResult).contains(Card(Diamond, 1), Card(Diamond, 9))
    }

    @Test
    fun `Hand의 가능한 카드 값 합이 21 미만 일경우 카드를 추가 할 수 있다`() {
        val hand = Hand().apply {
            addCardToHand(Card(Diamond, 1))
            addCardToHand(Card(Diamond, 9))
        }
        val actualResult = hand.addCardToHand(Card(Diamond, 13))

        assertThat(actualResult).isTrue
    }

    @Test
    fun `Hand의 가능한 카드 값 합이 21 이상 일경우 카드를 추가 할 수 없다`() {
        val blackJackHand = Hand().apply {
            addCardToHand(Card(Diamond, 5))
            addCardToHand(Card(Diamond, 6))
            addCardToHand(Card(Diamond, 10))
        }
        val actualResultOfBlackJackHand = blackJackHand.addCardToHand(Card(Diamond, 13))
        assertThat(actualResultOfBlackJackHand).isFalse

        val blackJackHandWithAce = Hand().apply {
            addCardToHand(Card(Diamond, 1))
            addCardToHand(Card(Diamond, 1))
            addCardToHand(Card(Diamond, 9))
        }
        val actualResultOfBlackJackHandWithAce = blackJackHandWithAce.addCardToHand(Card(Diamond, 13))
        assertThat(actualResultOfBlackJackHandWithAce).isFalse

        val bustedHand = Hand().apply {
            addCardToHand(Card(Diamond, 10))
            addCardToHand(Card(Diamond, 9))
            addCardToHand(Card(Diamond, 8))
        }
        val actualResultForBustedHand = bustedHand.addCardToHand(Card(Diamond, 13))
        assertThat(actualResultForBustedHand).isFalse
    }

    @Test
    fun `Hand의 카드로 만들 수 있는 최소 값이 승리 조건 값을 초과 했을 경우 isBusted는 true를 반환한다`() {
        val bustedHand = Hand().apply {
            addCardToHand(Card(Diamond, 10))
            addCardToHand(Card(Diamond, 9))
            addCardToHand(Card(Diamond, 8))
        }
        assertThat(bustedHand.isBusted()).isTrue
    }

    @Test
    fun `Hand의 카드로 만들 수 있는 최소 값이 승리 조건 값을 초과 하지 않을 경우 isBusted는 false를 반환한다`() {
        val blackJackHand = Hand().apply {
            addCardToHand(Card(Diamond, 5))
            addCardToHand(Card(Diamond, 6))
            addCardToHand(Card(Diamond, 10))
        }
        assertThat(blackJackHand.isBusted()).isFalse

        val notBustedHand = Hand().apply {
            addCardToHand(Card(Diamond, 10))
            addCardToHand(Card(Diamond, 9))
            addCardToHand(Card(Diamond, 1))
        }
        assertThat(notBustedHand.isBusted()).isFalse
    }

    @Test
    fun `Hand에 포함된 카드들로 만들 수 있는 값을 확인 할 수 있다`() {
        val hand = Hand().apply {
            addCardToHand(Card(Diamond, 1))
            addCardToHand(Card(Diamond, 9))
        }
        val predicted = listOf(20, 10)
        assertThat(hand.getMakeableScores()).isEqualTo(predicted)
        hand.addCardToHand(Card(Diamond, 1))
        val predictedBlackJackHand = listOf(21)
        assertThat(hand.getMakeableScores()).isEqualTo(predictedBlackJackHand)
    }
    @Test
    fun `Hand에 포함된 카드들이 승리 조건 값을 초과 했는지 확인 가능 하다`() {
        val hand = Hand().apply {
            addCardToHand(Card(Diamond, 1))
            addCardToHand(Card(Diamond, 2))
            addCardToHand(Card(Diamond, 9))
        }
        val actualNotBustedResult = hand.isBusted()
        assertThat(actualNotBustedResult).isFalse

        hand.addCardToHand(Card(Diamond, 10))
        val actualBustedResult = hand.isBusted()
        assertThat(actualBustedResult).isTrue
    }
}

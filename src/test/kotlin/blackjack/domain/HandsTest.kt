package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

class HandsTest {
    @Test
    fun `Hands는 플레이어가 손패에 보관하고 있는 카드 목록을 나타낸다`() {
        val hands = hands()
        assertThat(hands.cards).contains(
            PlayingCard(Suit.CLUBS, CardNumber.NINE),
            PlayingCard(Suit.HEARTS, CardNumber.ACE)
        )
    }

    @Test
    fun `더하기 연산자를 이용하여 새 카드들을 손패에 추가할 수 있다`() {
        val hands = hands()

        val additionalCards = PlayingCards.from(
            listOf(
                PlayingCard(Suit.DIAMONDS, CardNumber.KING),
                PlayingCard(Suit.SPADES, CardNumber.JACK)
            )
        )
        val newHands = hands + additionalCards

        assertAll(
            { assertThat(newHands.cards.size).isEqualTo(4) },
            { assertThat(newHands.cards.takeLast(2)).isEqualTo(additionalCards) }
        )
    }

    @Test
    fun `score를 통해 현재 손패의 점수를 구할 수 있다`() {
        assertThat(hands().score.value).isEqualTo(20)
    }

    @Test
    fun `isReceivable을 통해 손패에 카드를 추가할 수 있는 상태인지 알 수 있다`() {
        val hands = hands()

        assertThat(hands.isReceivable()).isTrue

        val stayHands = hands.stay()

        assertThat(stayHands.isReceivable()).isFalse
    }

    private fun hands(): Hands {
        val cards = PlayingCards.from(
            listOf(
                PlayingCard(Suit.CLUBS, CardNumber.NINE),
                PlayingCard(Suit.HEARTS, CardNumber.ACE)
            )
        )

        return Hands.from(cards)
    }
}

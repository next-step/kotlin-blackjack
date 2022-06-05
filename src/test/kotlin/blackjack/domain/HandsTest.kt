package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

class HandsTest {
    @Test
    fun `Hands는 플레이어가 손패에 보관하고 있는 카드 목록을 나타낸다`() {
        val cards = PlayingCards.from(
            listOf(
                PlayingCard.of(Suit.CLUBS, CardNumber.NINE),
                PlayingCard.of(Suit.HEARTS, CardNumber.ACE)
            )
        )
        val hands = Hands(cards)

        assertThat(hands.value).isEqualTo(cards)
    }

    @Test
    fun `더하기 연산자를 이용하여 새 카드들을 손패에 추가할 수 있다`() {
        val cards = PlayingCards.from(
            listOf(
                PlayingCard.of(Suit.CLUBS, CardNumber.NINE),
                PlayingCard.of(Suit.HEARTS, CardNumber.ACE)
            )
        )
        val hands = Hands(cards)
        val additionalCards = PlayingCards.from(
            listOf(
                PlayingCard.of(Suit.DIAMONDS, CardNumber.KING),
                PlayingCard.of(Suit.SPADES, CardNumber.JACK)
            )
        )
        val newHands = hands + additionalCards

        assertAll(
            { assertThat(newHands.value.size).isEqualTo(4) },
            { assertThat(newHands.value.takeLast(2)).isEqualTo(additionalCards) }
        )
    }

    @Test
    fun `score를 통해 현재 손패의 점수를 구할 수 있다`() {
        val cards = PlayingCards.from(
            listOf(
                PlayingCard.of(Suit.CLUBS, CardNumber.NINE),
                PlayingCard.of(Suit.HEARTS, CardNumber.ACE)
            )
        )
        val hands = Hands(cards)

        assertThat(hands.score().value).isEqualTo(20)
    }
}

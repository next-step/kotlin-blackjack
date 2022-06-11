package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

class PlayingCardTest {
    @Test
    fun `PlayingCard는 카드 모양과 카드 번호로 구성된다`() {
        val playingCard = PlayingCard.of(Suit.CLUBS, CardNumber.ACE)

        assertThat(playingCard.suit).isEqualTo(Suit.CLUBS)
        assertThat(playingCard.number).isEqualTo(CardNumber.ACE)
    }

    @Test
    fun `all을 통해 모든 카드 목록을 가져올 수 있다`() {
        val playingCards = PlayingCard.all()

        Suit.values().forEach { suit ->
            CardNumber.values().forEach { number ->
                val playingCard = PlayingCard.of(suit, number)

                assertThat(playingCards).contains(playingCard)
            }
        }
    }

    @Test
    fun `isCardOf를 통해 PlayingCard가 특정 번호의 카드인지 확인할 수 있다`() {
        val playingCard = PlayingCard.of(Suit.DIAMONDS, CardNumber.ACE)

        assertAll(
            { assertThat(playingCard.isCardOf(CardNumber.ACE)).isTrue },
            { assertThat(playingCard.isCardOf(CardNumber.KING)).isFalse }
        )
    }
}

package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

class PlayingCardTest {
    @Test
    fun `PlayingCard는 카드 모양과 카드 번호로 구성된다`() {
        val playingCard = PlayingCard.of(Suit.CLUBS, CardNumber.ACE)

        assertAll(
            { assertThat(playingCard.suit).isEqualTo(Suit.CLUBS) },
            { assertThat(playingCard.number).isEqualTo(CardNumber.ACE) }
        )
    }

    @Test
    fun `all을 호출해 얻은 전체 카드는 52개의 유니크한 카드 리스트이다`() {
        val playingCards = PlayingCard.all()

        assertAll(
            { assertThat(playingCards).hasSize(52) },
            { assertThat(playingCards.distinct()).containsAll(playingCards) }
        )
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

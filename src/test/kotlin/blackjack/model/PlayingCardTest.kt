package blackjack.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PlayingCardTest {
    @Test
    fun `PlayingCard는 카드 모양과 카드 번호로 구성된다`() {
        val playingCard = PlayingCard.of(
            suit = Suit.CLUBS,
            number = CardNumber.ACE
        )

        assertThat(playingCard.suit).isEqualTo(Suit.CLUBS)
        assertThat(playingCard.number).isEqualTo(CardNumber.ACE)
    }

    @Test
    fun `text를 통해 문자로 표현된 카드를 가져올 수 있다`() {
        val playingCard = PlayingCard.of(
            suit = Suit.CLUBS,
            number = CardNumber.ACE
        )
        val expected = "${CardNumber.ACE.text}${Suit.CLUBS.text}"

        assertThat(playingCard.text).isEqualTo(expected)
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
}

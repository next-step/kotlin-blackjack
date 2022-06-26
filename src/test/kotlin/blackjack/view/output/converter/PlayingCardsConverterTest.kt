package blackjack.view.output.converter

import blackjack.domain.CardNumber
import blackjack.domain.PlayingCard
import blackjack.domain.PlayingCards
import blackjack.domain.Suit
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class PlayingCardsConverterTest {
    @Test
    fun `PlayingCardsConverter는 PlayingCards를 출력을 위한 문자열로 변환해 반환한다`() {
        val playingCards = PlayingCards.from(
            listOf(
                PlayingCard(Suit.CLUBS, CardNumber.KING),
                PlayingCard(Suit.HEARTS, CardNumber.ACE)
            )
        )
        val expected = "K클로버, A하트"
        Assertions.assertThat(PlayingCardsConverter.convert(playingCards)).isEqualTo(expected)
    }
}

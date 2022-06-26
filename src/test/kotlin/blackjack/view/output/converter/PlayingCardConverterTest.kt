package blackjack.view.output.converter

import blackjack.domain.CardNumber
import blackjack.domain.PlayingCard
import blackjack.domain.Suit
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class PlayingCardConverterTest {
    @Test
    fun `PlayingCardConverter는 PlayingCard를 출력을 위한 문자열로 변환해 반환한다`() {
        val playingCard = PlayingCard(Suit.SPADES, CardNumber.TWO)

        val expected = "2스페이드, A하트"
        Assertions.assertThat(PlayingCardConverter.convert(playingCard)).isEqualTo(expected)
    }
}

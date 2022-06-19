package blackjack.view.output.converter

import blackjack.domain.CardNumber
import blackjack.domain.Participant
import blackjack.domain.PlayingCard
import blackjack.domain.Suit
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class EndOfGameConverterTest {
    @Test
    fun `EndOfGameConverter는 게임의 결과를 출력을 위한 문자열로 변환해 반환한다`() {
        val player1 = Participant.Player(
            "panther",
            PlayingCard(Suit.SPADES, CardNumber.TWO),
            PlayingCard(Suit.HEARTS, CardNumber.ACE),
            PlayingCard(Suit.SPADES, CardNumber.EIGHT)
        )
        val player2 = Participant.Player(
            "fox",
            PlayingCard(Suit.DIAMONDS, CardNumber.JACK),
            PlayingCard(Suit.CLUBS, CardNumber.SEVEN)
        )

        val expected = "panther카드: 2스페이드, A하트, 8스페이드 - 결과: 21\nfox카드: J다이아몬드, 7클로버 - 결과: 17"
        Assertions.assertThat(EndOfGameConverter.convert(listOf(player1, player2))).isEqualTo(expected)
    }
}

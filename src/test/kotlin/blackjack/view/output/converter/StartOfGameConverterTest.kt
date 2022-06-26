package blackjack.view.output.converter

import blackjack.domain.CardNumber
import blackjack.domain.Dealer
import blackjack.domain.Participants
import blackjack.domain.Player
import blackjack.domain.PlayingCard
import blackjack.domain.Suit
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class StartOfGameConverterTest {
    @Test
    fun `StartOfGameConverter는 게임이 시작될 때의 플레이어 정보들을 출력을 위한 문자열로 변환해 반환한다`() {
        val dealer = Dealer(
            "딜러",
            PlayingCard(Suit.CLUBS, CardNumber.QUEEN),
            PlayingCard(Suit.HEARTS, CardNumber.EIGHT)
        )
        val player1 = Player(
            "mona",
            10_000,
            PlayingCard(Suit.SPADES, CardNumber.TWO),
            PlayingCard(Suit.HEARTS, CardNumber.ACE)
        )
        val player2 = Player(
            "skull",
            10_000,
            PlayingCard(Suit.DIAMONDS, CardNumber.JACK),
            PlayingCard(Suit.CLUBS, CardNumber.SEVEN)
        )
        val participants = Participants(
            dealer = dealer,
            players = listOf(player1, player2)
        )

        val expected = "딜러와 mona, skull에게 2장을 나누었습니다.\n딜러: Q클로버\nmona카드: 2스페이드, A하트\nskull카드: J다이아몬드, 7클로버"
        Assertions.assertThat(StartOfGameConverter.convert(participants)).isEqualTo(expected)
    }
}

package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

class BlackjackGameResultTest {
    @Test
    fun `BlackjackGameResult는 참가자들을 받아 그들의 블랙잭 게임 결과를 보관한다`() {
        val dealer = Dealer(
            "딜러",
            PlayingCard(Suit.HEARTS, CardNumber.NINE),
            PlayingCard(Suit.DIAMONDS, CardNumber.JACK)
        )
        val players = listOf(
            Player(
                "파이리",
                10_000,
                PlayingCard(Suit.SPADES, CardNumber.ACE),
                PlayingCard(Suit.HEARTS, CardNumber.JACK)
            ),
            Player(
                "꼬부기",
                20_000,
                PlayingCard(Suit.CLUBS, CardNumber.JACK),
                PlayingCard(Suit.SPADES, CardNumber.NINE)
            ),
            Player(
                "이상해씨",
                15_000,
                PlayingCard(Suit.CLUBS, CardNumber.TWO),
                PlayingCard(Suit.DIAMONDS, CardNumber.FOUR)
            )
        )
        val participants = Participants(dealer, players)
        val blackjackGameResult = BlackjackGameResult.from(participants).value

        assertAll(
            { assertThat(blackjackGameResult[0].revenue.value).isEqualTo(0) },
            { assertThat(blackjackGameResult[1].revenue.value).isEqualTo(15_000) },
            { assertThat(blackjackGameResult[2].revenue.value).isEqualTo(0) },
            { assertThat(blackjackGameResult[3].revenue.value).isEqualTo(-15_000) }
        )
    }
}

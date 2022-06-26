package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

class BlackjackGameRuleTest {
    private val bustPlayer = getBustPlayer()
    private val bustDealer = getDealerFrom(bustPlayer)

    private val blackjackPlayer = getBlackjackPlayer()
    private val blackjackDealer = getDealerFrom(blackjackPlayer)

    private val normal21ScorePlayer = getNormal21ScorePlayer()
    private val normal21ScoreDealer = getDealerFrom(normal21ScorePlayer)

    private val under21ScorePlayer = getUnder21ScorePlayer()
    private val under21ScoreDealer = getDealerFrom(under21ScorePlayer)

    @Test
    fun `플레이어가 버스트인 경우, 딜러의 상태에 무관하게 무조건 패배한다`() {
        assertAll(
            { assertThat(bustPlayer matchWith bustDealer).isEqualTo(MatchStatus.Lose) },
            { assertThat(bustPlayer matchWith blackjackDealer).isEqualTo(MatchStatus.Lose) },
            { assertThat(bustPlayer matchWith normal21ScoreDealer).isEqualTo(MatchStatus.Lose) },
            { assertThat(bustPlayer matchWith under21ScoreDealer).isEqualTo(MatchStatus.Lose) }
        )
    }

    @Test
    fun `플레이어가 버스트가 아니고 딜러가 버스트라면 무조건 승리한다`() {
        assertAll(
            { assertThat(blackjackPlayer matchWith bustDealer).isEqualTo(MatchStatus.Win) },
            { assertThat(normal21ScorePlayer matchWith bustDealer).isEqualTo(MatchStatus.Win) },
            { assertThat(under21ScorePlayer matchWith bustDealer).isEqualTo(MatchStatus.Win) }
        )
    }

    @Test
    fun `플레이어와 딜러가 모두 블랙잭이라면 무승부가 된다`() {
        assertThat(blackjackPlayer matchWith blackjackDealer).isEqualTo(MatchStatus.Push)
    }

    @Test
    fun `플레이어가 블랙잭이고 딜러가 블랙잭이 아니라면 승리한다`() {
        assertAll(
            { assertThat(blackjackPlayer matchWith normal21ScoreDealer).isEqualTo(MatchStatus.Blackjack) },
            { assertThat(blackjackPlayer matchWith under21ScoreDealer).isEqualTo(MatchStatus.Blackjack) }
        )
    }

    @Test
    fun `플레이어가 블랙잭이 아니고 딜러가 블랙잭이라면 패배한다`() {
        assertAll(
            { assertThat(normal21ScorePlayer matchWith blackjackDealer).isEqualTo(MatchStatus.Lose) },
            { assertThat(under21ScorePlayer matchWith blackjackDealer).isEqualTo(MatchStatus.Lose) }
        )
    }

    @Test
    fun `플레이어와 딜러 모두 블랙잭과 버스트가 없다면 점수가 높은 쪽이 승리한다`() {
        assertAll(
            { assertThat(normal21ScorePlayer matchWith under21ScoreDealer).isEqualTo(MatchStatus.Win) },
            { assertThat(under21ScorePlayer matchWith normal21ScoreDealer).isEqualTo(MatchStatus.Lose) },
            { assertThat(normal21ScorePlayer matchWith normal21ScoreDealer).isEqualTo(MatchStatus.Push) },
            { assertThat(under21ScorePlayer matchWith under21ScoreDealer).isEqualTo(MatchStatus.Push) },
        )
    }

    private fun getBustPlayer(): Player = Player(
        "버스트",
        10_000,
        PlayingCard(Suit.HEARTS, CardNumber.NINE),
        PlayingCard(Suit.HEARTS, CardNumber.TEN),
        PlayingCard(Suit.HEARTS, CardNumber.KING)
    )

    private fun getBlackjackPlayer(): Player = Player(
        "블랙잭",
        10_000,
        PlayingCard(Suit.SPADES, CardNumber.ACE),
        PlayingCard(Suit.SPADES, CardNumber.QUEEN)
    )

    private fun getNormal21ScorePlayer(): Player = Player(
        "일반 21점",
        10_000,
        PlayingCard(Suit.CLUBS, CardNumber.SEVEN),
        PlayingCard(Suit.SPADES, CardNumber.SIX),
        PlayingCard(Suit.DIAMONDS, CardNumber.EIGHT)
    )

    private fun getUnder21ScorePlayer(): Player = Player(
        "21점 보다 아래",
        10_000,
        PlayingCard(Suit.HEARTS, CardNumber.SEVEN),
        PlayingCard(Suit.DIAMONDS, CardNumber.JACK)
    )

    private fun getDealerFrom(player: Player): Dealer = Dealer(
        "딜러",
        *player.hands.cards.toTypedArray()
    )
}

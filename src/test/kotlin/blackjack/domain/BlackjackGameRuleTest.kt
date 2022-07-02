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
            { assertThat(bustPlayer vs bustDealer).isEqualTo(MatchStatus.LOSE) },
            { assertThat(bustPlayer vs blackjackDealer).isEqualTo(MatchStatus.LOSE) },
            { assertThat(bustPlayer vs normal21ScoreDealer).isEqualTo(MatchStatus.LOSE) },
            { assertThat(bustPlayer vs under21ScoreDealer).isEqualTo(MatchStatus.LOSE) }
        )
    }

    @Test
    fun `플레이어가 버스트가 아니고 딜러가 버스트라면 무조건 승리한다`() {
        assertAll(
            { assertThat(blackjackPlayer vs bustDealer).isEqualTo(MatchStatus.BLACKJACK) },
            { assertThat(normal21ScorePlayer vs bustDealer).isEqualTo(MatchStatus.WIN) },
            { assertThat(under21ScorePlayer vs bustDealer).isEqualTo(MatchStatus.WIN) }
        )
    }

    @Test
    fun `플레이어와 딜러가 모두 블랙잭이라면 무승부가 된다`() {
        assertThat(blackjackPlayer vs blackjackDealer).isEqualTo(MatchStatus.PUSH)
    }

    @Test
    fun `플레이어가 블랙잭이고 딜러가 블랙잭이 아니라면 승리한다`() {
        assertAll(
            { assertThat(blackjackPlayer vs normal21ScoreDealer).isEqualTo(MatchStatus.BLACKJACK) },
            { assertThat(blackjackPlayer vs under21ScoreDealer).isEqualTo(MatchStatus.BLACKJACK) }
        )
    }

    @Test
    fun `플레이어가 블랙잭이 아니고 딜러가 블랙잭이라면 패배한다`() {
        assertAll(
            { assertThat(normal21ScorePlayer vs blackjackDealer).isEqualTo(MatchStatus.LOSE) },
            { assertThat(under21ScorePlayer vs blackjackDealer).isEqualTo(MatchStatus.LOSE) }
        )
    }

    @Test
    fun `플레이어와 딜러 모두 블랙잭과 버스트가 없다면 점수가 높은 쪽이 승리한다`() {
        assertAll(
            { assertThat(normal21ScorePlayer vs under21ScoreDealer).isEqualTo(MatchStatus.WIN) },
            { assertThat(under21ScorePlayer vs normal21ScoreDealer).isEqualTo(MatchStatus.LOSE) },
            { assertThat(normal21ScorePlayer vs normal21ScoreDealer).isEqualTo(MatchStatus.PUSH) },
            { assertThat(under21ScorePlayer vs under21ScoreDealer).isEqualTo(MatchStatus.PUSH) },
        )
    }

    @Test
    fun `getRevenueFrom을 통해 플레이어가 딜러로 부터 얻을 수 있는 수익을 계산할 수 있다`() {
        assertAll(
            { assertThat(blackjackPlayer getRevenueFrom normal21ScoreDealer).isEqualTo(Revenue(22_500)) },
            { assertThat(normal21ScorePlayer getRevenueFrom under21ScoreDealer).isEqualTo(Revenue(10_000)) },
            { assertThat(bustPlayer getRevenueFrom normal21ScoreDealer).isEqualTo(Revenue(-20_000)) },
            { assertThat(normal21ScorePlayer getRevenueFrom normal21ScoreDealer).isEqualTo(Revenue(0)) }
        )
    }

    @Test
    fun `getDealerRevenue를 통해 플레이어들의 수익으로부터 딜러의 수익을 계산할 수 있다`() {
        val bustDealerRevenue = getPlayerResults(bustDealer).getDealerRevenue()
        val blackjackDealerRevenue = getPlayerResults(blackjackDealer).getDealerRevenue()
        val normal21ScoreDealerRevenue = getPlayerResults(normal21ScoreDealer).getDealerRevenue()
        val under21ScoreDealerRevenue = getPlayerResults(under21ScoreDealer).getDealerRevenue()

        assertAll(
            { assertThat(bustDealerRevenue).isEqualTo(Revenue(-17500)) },
            { assertThat(blackjackDealerRevenue).isEqualTo(Revenue(35000)) },
            { assertThat(normal21ScoreDealerRevenue).isEqualTo(Revenue(2500)) },
            { assertThat(under21ScoreDealerRevenue).isEqualTo(Revenue(-12500)) }
        )
    }

    private fun getBustPlayer(): Player = Player(
        "버스트",
        20_000,
        PlayingCard(Suit.HEARTS, CardNumber.NINE),
        PlayingCard(Suit.HEARTS, CardNumber.TEN),
        PlayingCard(Suit.HEARTS, CardNumber.KING)
    )

    private fun getBlackjackPlayer(): Player = Player(
        "블랙잭",
        15_000,
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
        5_000,
        PlayingCard(Suit.HEARTS, CardNumber.SEVEN),
        PlayingCard(Suit.DIAMONDS, CardNumber.JACK)
    )

    private fun getDealerFrom(player: Player): Dealer = Dealer(
        "딜러",
        *player.hands.cards.toTypedArray()
    )

    private fun getPlayerResults(dealer: Dealer): List<BlackjackParticipantResult> {
        return listOf(bustPlayer, blackjackPlayer, normal21ScorePlayer, under21ScorePlayer).map { player ->
            BlackjackParticipantResult(
                participant = player,
                revenue = player getRevenueFrom dealer
            )
        }
    }
}

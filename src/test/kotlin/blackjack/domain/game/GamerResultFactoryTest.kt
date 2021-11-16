package blackjack.domain.game

import blackjack.Hand
import blackjack.domain.card.Score
import blackjack.domain.player.Dealer
import blackjack.domain.player.Player
import blackjack.domain.player.PlayerName
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

@Suppress("NonAsciiCharacters")
class GamerResultFactoryTest {

    private val name = PlayerName("aaj")
    private val bustScore = Score.BLACK_JACK_SCORE.value + 1

    @Test
    fun `플레이어가 bust라면 플레이어는 패배한다`() {
        val dealer = Dealer(hand = Hand(bustScore))
        val player = Player(name, hand = Hand(bustScore))

        val result = GamerResultFactory.getPlayerResult(dealer, player)

        assertThat(result.result).isEqualTo(GameResult.Type.LOSE)
    }

    @Test
    fun `Dealer만 bust라면 플레이어는 승리한다`() {
        val dealer = Dealer(hand = Hand(bustScore))
        val player = Player(name, hand = Hand(2))

        val result = GamerResultFactory.getPlayerResult(dealer, player)

        assertThat(result.result).isEqualTo(GameResult.Type.WIN)
    }

    @Test
    fun `딜러와 플레이어가 bust가 아닐 때 플레이어의 점수가 더 높다면 플레이어가 승리한다`() {
        val dealer = Dealer(hand = Hand(18))
        val player = Player(name, hand = Hand(19))

        val result = GamerResultFactory.getPlayerResult(dealer, player)

        assertThat(result.result).isEqualTo(GameResult.Type.WIN)
    }

    @Test
    fun `딜러와 플레이어가 bust가 아닐 때 플레이어의 점수가 같다면 플레이어는 비긴다`() {
        val dealer = Dealer(hand = Hand(18))
        val player = Player(name, hand = Hand(18))

        val result = GamerResultFactory.getPlayerResult(dealer, player)

        assertThat(result.result).isEqualTo(GameResult.Type.DRAW)
    }

    @Test
    fun `딜러와 플레이어가 bust가 아닐 때 플레이어의 점수가 더 낮다면 플레이어는 진다`() {
        val dealer = Dealer(hand = Hand(18))
        val player = Player(name, hand = Hand(17))

        val result = GamerResultFactory.getPlayerResult(dealer, player)

        assertThat(result.result).isEqualTo(GameResult.Type.LOSE)
    }

    @Test
    fun `딜러의 승패는 플레이어들의 승패의 반대이다`() {
        val player = Player(name)
        val playerResults = listOf(
            PlayerResult(GameResult.Type.WIN, player),
            PlayerResult(GameResult.Type.WIN, player),
            PlayerResult(GameResult.Type.DRAW, player),
            PlayerResult(GameResult.Type.DRAW, player),
            PlayerResult(GameResult.Type.DRAW, player),
            PlayerResult(GameResult.Type.LOSE, player),
        )

        val result = GamerResultFactory.getDealerResult(playerResults)

        assertAll(
            { assertThat(result.win).isEqualTo(1) },
            { assertThat(result.draw).isEqualTo(3) },
            { assertThat(result.lose).isEqualTo(2) },
        )
    }
}

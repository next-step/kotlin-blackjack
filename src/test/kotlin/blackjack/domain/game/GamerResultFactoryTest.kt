package blackjack.domain.game

import blackjack.Hand
import blackjack.domain.card.Score
import blackjack.domain.card.Score.Companion.BLACK_JACK_SCORE
import blackjack.domain.player.Dealer
import blackjack.domain.player.Player
import blackjack.domain.player.PlayerName
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

@Suppress("NonAsciiCharacters")
class GamerResultFactoryTest {

    private val name = PlayerName("aaj")
    private val bustScore = Score.BLACK_JACK_SCORE.value + 1

    @Test
    fun `플레이어가 bust라면 딜러 결과와는 상관없이 플레이어는 패배, 딜러는 승리한다`() {
        val dealer = Dealer(Hand(bustScore))
        val player = Player(name, Hand(bustScore))

        val result = GamerResultFactory.getGamerResult(dealer, player)

        assertThat(result.playerResult.result).isEqualTo(GameResult.Type.LOSE)
        assertThat(result.dealerResult.result).isEqualTo(GameResult.Type.WIN)
    }

    @Test
    fun `딜러만 bust라면 플레이어는 승리, 딜러는 패배한다`() {
        val dealer = Dealer(Hand(bustScore))
        val player = Player(name, Hand(2))

        val result = GamerResultFactory.getGamerResult(dealer, player)

        assertThat(result.playerResult.result).isEqualTo(GameResult.Type.WIN)
        assertThat(result.dealerResult.result).isEqualTo(GameResult.Type.LOSE)
    }

    @Test
    fun `플레이어만 blackjack이라면 플레이어는 블랙잭, 딜러는 패배한다`() {
        val dealer = Dealer(Hand(BLACK_JACK_SCORE.value, isBlackJack = false))
        val player = Player(name, Hand(BLACK_JACK_SCORE.value, isBlackJack = true))

        val result = GamerResultFactory.getGamerResult(dealer, player)

        assertThat(result.playerResult.result).isEqualTo(GameResult.Type.BLACK_JACK)
        assertThat(result.dealerResult.result).isEqualTo(GameResult.Type.LOSE)
    }

    @Test
    fun `딜러만 blackjack이라면 플레이어는 패배, 딜러는 승리한다`() {
        val dealer = Dealer(Hand(BLACK_JACK_SCORE.value, isBlackJack = true))
        val player = Player(name, Hand(BLACK_JACK_SCORE.value, isBlackJack = false))

        val result = GamerResultFactory.getGamerResult(dealer, player)

        assertThat(result.playerResult.result).isEqualTo(GameResult.Type.LOSE)
        assertThat(result.dealerResult.result).isEqualTo(GameResult.Type.WIN)
    }

    @Test
    fun `플레이어와 딜러 모두 blackjack이라면 플레이와 딜러는 비긴다`() {
        val dealer = Dealer(Hand(BLACK_JACK_SCORE.value, isBlackJack = true))
        val player = Player(name, Hand(BLACK_JACK_SCORE.value, isBlackJack = true))

        val result = GamerResultFactory.getGamerResult(dealer, player)

        assertThat(result.playerResult.result).isEqualTo(GameResult.Type.DRAW)
        assertThat(result.dealerResult.result).isEqualTo(GameResult.Type.DRAW)
    }

    @Test
    fun `딜러와 플레이어가 bust가 아닐 때 플레이어의 점수가 더 높다면 플레이어는 승리, 딜러는 패배한다`() {
        val dealer = Dealer(Hand(18))
        val player = Player(name, Hand(19))

        val result = GamerResultFactory.getGamerResult(dealer, player)

        assertThat(result.playerResult.result).isEqualTo(GameResult.Type.WIN)
        assertThat(result.dealerResult.result).isEqualTo(GameResult.Type.LOSE)
    }

    @Test
    fun `딜러와 플레이어가 bust가 아닐 때 플레이어의 점수가 같다면 플레이어와 딜러는 비긴다`() {
        val dealer = Dealer(Hand(18))
        val player = Player(name, Hand(18))

        val result = GamerResultFactory.getGamerResult(dealer, player)

        assertThat(result.playerResult.result).isEqualTo(GameResult.Type.DRAW)
        assertThat(result.dealerResult.result).isEqualTo(GameResult.Type.DRAW)
    }

    @Test
    fun `딜러와 플레이어가 bust가 아닐 때 플레이어의 점수가 더 낮다면 플레이어는 패배, 딜러는 승리한다`() {
        val dealer = Dealer(Hand(18))
        val player = Player(name, Hand(17))

        val result = GamerResultFactory.getGamerResult(dealer, player)

        assertThat(result.playerResult.result).isEqualTo(GameResult.Type.LOSE)
        assertThat(result.dealerResult.result).isEqualTo(GameResult.Type.WIN)
    }
}

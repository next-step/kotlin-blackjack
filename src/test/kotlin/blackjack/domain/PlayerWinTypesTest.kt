package blackjack.domain

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

internal class PlayerWinTypesTest {

    @Test
    fun `승패를 계산한다(player보다 dealer의 점수가 높은 경우)`() {
        val player = Player("song", makeCardSetPointOf(CardType.TWO, CardType.THREE))
        val players = Players(mutableListOf(player))
        val dealerPoint = PlayerPoint(19, false)

        val result = PlayerWinTypes.of(players, dealerPoint)
        Assertions.assertThat(result.dealerResult).isEqualTo("1승 0패")

        Assertions.assertThat(result["song"]).isEqualTo(PlayerWinType.LOSE)
    }

    @Test
    fun `승패를 계산한다(player보다 dealer의 점수가 낮은 경우)`() {
        val player = Player("song", makeCardSetPointOf(CardType.EIGHT, CardType.ACE), false)
        val players = Players(mutableListOf(player))
        val dealerPoint = PlayerPoint(9, false)

        val result = PlayerWinTypes.of(players, dealerPoint)
        Assertions.assertThat(result.dealerResult).isEqualTo("0승 1패")
        Assertions.assertThat(result["song"]).isEqualTo(PlayerWinType.WIN)
    }

    @Test
    fun `승패를 계산한다(dealer가 블랙잭인 경우)`() {
        val player = Player("song", makeCardSetPointOf(CardType.EIGHT, CardType.ACE), false)
        val players = Players(mutableListOf(player))
        val dealerPoint = PlayerPoint(21, true)

        val result = PlayerWinTypes.of(players, dealerPoint)
        Assertions.assertThat(result.dealerResult).isEqualTo("1승 0패")
        Assertions.assertThat(result["song"]).isEqualTo(PlayerWinType.LOSE)
    }

    @Test
    fun `승패를 계산한다(player가 블랙잭인 경우)`() {
        val player = Player("song", makeCardSetPointOf(CardType.JACK, CardType.ACE), true)
        val players = Players(mutableListOf(player))
        val dealerPoint = PlayerPoint(19, false)

        val result = PlayerWinTypes.of(players, dealerPoint)
        Assertions.assertThat(result["song"]).isEqualTo(PlayerWinType.BLACKJACK)
        Assertions.assertThat(result.dealerResult).isEqualTo("0승 1패")
    }

    @Test
    fun `승패를 계산한다(player와 dealer가 모두 블랙잭인 경우)`() {
        val player = Player("song", makeCardSetPointOf(CardType.JACK, CardType.ACE), true)
        val players = Players(mutableListOf(player))
        val dealerPoint = PlayerPoint(21, true)

        val result = PlayerWinTypes.of(players, dealerPoint)
        Assertions.assertThat(player.isBlackjack).isTrue()
        Assertions.assertThat(result["song"]).isEqualTo(PlayerWinType.DRAW)
        Assertions.assertThat(result.dealerResult).isEqualTo("0승 0패")
    }

    private fun makeCardSetPointOf(vararg cardTypes: CardType): Set<Card> =
        cardTypes.map { Card(CardShape.CLOVER, it) }.toSet()
}

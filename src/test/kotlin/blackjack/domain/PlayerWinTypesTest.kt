package blackjack.domain

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

internal class PlayerWinTypesTest {

    @Test
    fun `승패를 계산한다(player보다 dealer의 점수가 높은 경우)`() {
        val player = Player("song", makeCardSetPointOf(CardType.TWO, CardType.THREE))
        val players = Players(mutableListOf(player))

        val result = PlayerWinTypes.of(players, 19)
        Assertions.assertThat(result.dealerResult).isEqualTo("1승 0패")

        Assertions.assertThat(result["song"]).isEqualTo(PlayerWinType.LOSE)
    }

    @Test
    fun `승패를 계산한다(player보다 dealer의 점수가 낮은 경우)`() {
        val player = Player("song", makeCardSetPointOf(CardType.EIGHT, CardType.ACE))
        val players = Players(mutableListOf(player))

        val result = PlayerWinTypes.of(players, 9)
        Assertions.assertThat(result.dealerResult).isEqualTo("0승 1패")
        Assertions.assertThat(result["song"]).isEqualTo(PlayerWinType.WIN)
    }

    private fun makeCardSetPointOf(vararg cardTypes: CardType): Set<Card> =
        cardTypes.map { Card(CardShape.CLOVER, it) }.toSet()
}

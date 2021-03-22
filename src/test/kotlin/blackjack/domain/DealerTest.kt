package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class DealerTest {

    @Test
    fun `player에게 두 장의 카드씩 준다`() {
        val players = Players(mutableListOf(Player("song"), Player("kim")))
        val dealer = Dealer(players, CardPack())

        val playerDtos = dealer.giveTwoCardsToAllPlayers()
        assertThat(playerDtos).extracting("name").contains("song", "kim")
        assertThat(playerDtos[0].cards).hasSize(2)
        assertThat(playerDtos[1].cards).hasSize(2)
    }

    @Test
    fun `특정 player가 accept하면 카드를 준다`() {
        val players = Players(mutableListOf(Player("song")))
        val dealer = Dealer(players, CardPack())

        val playerDto1 = dealer.giveCard(players[0], false)
        assertThat(playerDto1.cards).hasSize(0)

        val playerDto2 = dealer.giveCard(players[0], true)
        assertThat(playerDto2.cards).hasSize(1)
    }

    @Test
    fun `승패를 계산한다(dealer가 21이 넘은 경우)`() {
        val players = Players(mutableListOf(Player("song"), Player("kim")))
        val dealer = Dealer(players, CardPack(), makeCardSetPointOf(CardType.SEVEN, CardType.EIGHT, CardType.NINE))

        val result = dealer.findPlayerWinTypes()
        assertThat(result.dealerResult).isEqualTo("0승 2패")
    }

    @Test
    fun `승패를 계산한다(player보다 dealer의 점수가 높은 경우)`() {
        val player = Player("song", makeCardSetPointOf(CardType.TWO, CardType.THREE))
        val players = Players(mutableListOf(player))
        val dealer = Dealer(players, CardPack(), makeCardSetPointOf(CardType.EIGHT, CardType.ACE))

        val result = dealer.findPlayerWinTypes()
        assertThat(result.dealerResult).isEqualTo("1승 0패")

        assertThat(result["song"]).isEqualTo(PlayerWinType.LOSE)
    }

    @Test
    fun `승패를 계산한다(player보다 dealer의 점수가 낮은 경우)`() {
        val player = Player("song", makeCardSetPointOf(CardType.EIGHT, CardType.ACE))
        val players = Players(mutableListOf(player))
        val dealer = Dealer(players, CardPack(), makeCardSetPointOf(CardType.TWO, CardType.THREE, CardType.FOUR))

        val result = dealer.findPlayerWinTypes()
        assertThat(result.dealerResult).isEqualTo("0승 1패")

        assertThat(result["song"]).isEqualTo(PlayerWinType.WIN)
    }

    @Test
    fun `16이하면 카드를 더 받는다`() {
        val player = Player("song", makeCardSetPointOf(CardType.EIGHT, CardType.ACE))
        val players = Players(mutableListOf(player))
        val dealer = Dealer(players, CardPack(), makeCardSetPointOf(CardType.TWO, CardType.THREE))

        val canTake = dealer.takeCardIfUnderSixteen()
        assertThat(canTake).isTrue()
    }

    @Test
    fun `16초과면 카드를 더 받지 않는다`() {
        val player = Player("song", makeCardSetPointOf(CardType.EIGHT, CardType.ACE))
        val players = Players(mutableListOf(player))
        val dealer = Dealer(players, CardPack(), makeCardSetPointOf(CardType.NINE, CardType.TEN))

        val canTake = dealer.takeCardIfUnderSixteen()
        assertThat(canTake).isFalse()
    }

    private fun makeCardSetPointOf(vararg cardTypes: CardType): Set<Card> =
        cardTypes.map { Card(CardShape.CLOVER, it) }.toSet()
}

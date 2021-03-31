package blackjack.domain

import blackjack.ui.model.PlayerDto
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BlackjackGameTest {

    @Test
    fun `모든 player에게 카드를 준다`() {
        val players = Players(mutableListOf(Player("song"), Player("kim")))
        val blackjackGame = BlackjackGame(players)

        blackjackGame.giveTwoCardsToAllPlayers()

        val playerDtos = blackjackGame.players.map { PlayerDto(it) }
        assertThat(playerDtos).extracting("name").contains("song", "kim")
        assertThat(playerDtos[0].cards).hasSize(2)
        assertThat(playerDtos[1].cards).hasSize(2)
    }

    @Test
    fun `player가 동의한 경우 카드를 준다`() {
        val player = Player("song", makeCardSetPointOf(CardType.TWO, CardType.THREE))
        val players = Players(mutableListOf(player))
        val blackjackGame = BlackjackGame(players)

        blackjackGame.giveCard(player, true)
        assertThat(player.cardNames).hasSize(3)
    }

    @Test
    fun `player가 동의하지 않은 경우 카드를 준다`() {
        val player = Player("song", makeCardSetPointOf(CardType.TWO, CardType.THREE))
        val players = Players(mutableListOf(player))
        val blackjackGame = BlackjackGame(players)

        blackjackGame.giveCard(player, false)
        assertThat(player.cardNames).hasSize(2)
    }

    @Test
    fun `승패를 계산한다(dealer가 21이 넘은 경우)`() {
        val players = Players(mutableListOf(Player("song"), Player("kim")))
        val blackjackGame = BlackjackGame(players, Dealer(makeCardSetPointOf(CardType.SEVEN, CardType.EIGHT, CardType.NINE)))

        val result = blackjackGame.findPlayerWinTypes()
        assertThat(result.dealerResult).isEqualTo("0승 2패")
    }

    @Test
    fun `승패를 계산한다(player보다 dealer의 점수가 높은 경우)`() {
        val player = Player("song", makeCardSetPointOf(CardType.TWO, CardType.THREE))
        val players = Players(mutableListOf(player))
        val dealer = Dealer(makeCardSetPointOf(CardType.EIGHT, CardType.ACE))
        val blackjackGame = BlackjackGame(players, dealer)

        val result = blackjackGame.findPlayerWinTypes()
        assertThat(result.dealerResult).isEqualTo("1승 0패")

        assertThat(result["song"]).isEqualTo(PlayerWinType.LOSE)
    }

    @Test
    fun `승패를 계산한다(player보다 dealer의 점수가 낮은 경우)`() {
        val player = Player("song", makeCardSetPointOf(CardType.EIGHT, CardType.ACE))
        val players = Players(mutableListOf(player))
        val dealer = Dealer(makeCardSetPointOf(CardType.TWO, CardType.THREE, CardType.FOUR))
        val blackjackGame = BlackjackGame(players, dealer)

        val result = blackjackGame.findPlayerWinTypes()
        assertThat(result.dealerResult).isEqualTo("0승 1패")

        assertThat(result["song"]).isEqualTo(PlayerWinType.WIN)
    }

    @Test
    fun `dealer의 카드가 16이하면 카드를 더 받는다`() {
        val player = Player("song")
        val players = Players(mutableListOf(player))
        val dealer = Dealer(makeCardSetPointOf(CardType.TWO, CardType.THREE))
        val blackjackGame = BlackjackGame(players, dealer)

        blackjackGame.giveCardsToDealer()
        assertThat(PlayerDto(blackjackGame.dealer).cards.size).isGreaterThan(2) // 2장 이상을 갖는다
    }

    @Test
    fun `dealer의 카드가 16초과면 카드를 더 받지 않는다`() {
        val player = Player("song")
        val players = Players(mutableListOf(player))
        val dealer = Dealer(makeCardSetPointOf(CardType.JACK, CardType.QUEEN))
        val blackjackGame = BlackjackGame(players, dealer)

        blackjackGame.giveCardsToDealer()
        assertThat(PlayerDto(blackjackGame.dealer).cards.size).isEqualTo(2) // 2장만을 가지고 있다
    }

    private fun makeCardSetPointOf(vararg cardTypes: CardType): Set<Card> =
        cardTypes.map { Card(CardShape.CLOVER, it) }.toSet()
}

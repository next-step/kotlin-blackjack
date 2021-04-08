package blackjack.domain

import blackjack.ui.model.PlayerDto
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.math.BigDecimal

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

    @Test
    fun `player 한 명이 win인 경우 profit을 계산한다`() {
        val player = Player("song", 10000, makeCardSetPointOf(CardType.JACK, CardType.EIGHT))
        val players = Players(mutableListOf(player))
        val dealer = Dealer(makeCardSetPointOf(CardType.NINE, CardType.EIGHT))
        val blackjackGame = BlackjackGame(players, dealer)

        val profits = blackjackGame.findProfits()
        assertThat(profits.dealerProfit.amount).isEqualTo(BigDecimal(-10000))
        assertThat(profits.playerProfits[0].amount).isEqualTo(BigDecimal(10000))
    }

    @Test
    fun `player 한 명이 Lose인 경우 profit을 계산한다`() {
        val player = Player("song", 10000, makeCardSetPointOf(CardType.NINE, CardType.EIGHT))
        val players = Players(mutableListOf(player))
        val dealer = Dealer(makeCardSetPointOf(CardType.JACK, CardType.EIGHT))
        val blackjackGame = BlackjackGame(players, dealer)

        val profits = blackjackGame.findProfits()
        assertThat(profits.dealerProfit.amount).isEqualTo(BigDecimal(10000))
        assertThat(profits.playerProfits[0].amount).isEqualTo(BigDecimal(-10000))
    }

    @Test
    fun `player 한 명이 Blackjack인 경우 profit을 계산한다`() {
        val player = Player("song", 10000, makeCardSetPointOf(CardType.ACE, CardType.JACK), true)
        val players = Players(mutableListOf(player))
        val dealer = Dealer(makeCardSetPointOf(CardType.JACK, CardType.EIGHT))
        val blackjackGame = BlackjackGame(players, dealer)

        val profits = blackjackGame.findProfits()
        assertThat(profits.dealerProfit.amount).isEqualTo(BigDecimal("-15000"))
        assertThat(profits.playerProfits[0].amount).isEqualTo(BigDecimal("15000"))
    }

    @Test
    fun `player 한 명이 Blackjack이고, 다른 한 명이 win인 경우 profit을 계산한다`() {
        val player1 = Player("song", 10000, makeCardSetPointOf(CardType.ACE, CardType.JACK), true)
        val player2 = Player("kim", 20000, makeCardSetPointOf(CardType.QUEEN, CardType.JACK))
        val players = Players(mutableListOf(player1, player2))
        val dealer = Dealer(makeCardSetPointOf(CardType.NINE, CardType.EIGHT))
        val blackjackGame = BlackjackGame(players, dealer)

        val profits = blackjackGame.findProfits()
        assertThat(profits.dealerProfit.amount).isEqualTo(BigDecimal(-35000))
        assertThat(profits.playerProfits[0].amount).isEqualTo(BigDecimal(15000))
        assertThat(profits.playerProfits[1].amount).isEqualTo(BigDecimal(20000))

    }

    private fun makeCardSetPointOf(vararg cardTypes: CardType): Set<Card> =
        cardTypes.map { Card(CardShape.CLOVER, it) }.toSet()
}

package blackjack.domain

import blackjack.domain.card.CardType
import blackjack.domain.state.started.Running.Hit
import blackjack.domain.state.started.finished.BlackJack
import blackjack.domain.state.started.finished.Bust
import blackjack.domain.state.started.finished.Stay
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.math.BigDecimal

internal class BlackjackGameTest {

    @Test
    fun `모든 player에게 카드를 준다`() {
        val players = Players(listOf(Player("song"), Player("kim")))
        val blackjackGame = BlackjackGame(players)

        blackjackGame.giveTwoCardsToAllPlayers()

        assertThat(blackjackGame.players[0].cardSize).isEqualTo(2)
        assertThat(blackjackGame.players[1].cardSize).isEqualTo(2)
    }

    @Test
    fun `player가 동의한 경우 카드를 준다`() {
        val player = Player("song", Hit(cards(CardType.TWO, CardType.THREE)))
        val players = Players(listOf(player))
        val blackjackGame = BlackjackGame(players)

        blackjackGame.giveCard(player, true)
        assertThat(player.cardNames).hasSize(3)
    }

    @Test
    fun `player가 동의하지 않은 경우 카드를 주지 않는다`() {
        val player = Player("song", Hit(cards(CardType.TWO, CardType.THREE)))
        val players = Players(listOf(player))
        val blackjackGame = BlackjackGame(players)

        blackjackGame.giveCard(player, false)
        assertThat(player.cardNames).hasSize(2)
    }

    @Test
    fun `dealer의 카드가 16이하면 카드를 더 받는다`() {
        val player = Player("song")
        val players = Players(listOf(player))
        val dealer = Dealer(Hit(cards(CardType.TWO, CardType.THREE)))
        val blackjackGame = BlackjackGame(players, dealer)

        blackjackGame.giveCardsToDealer()
        assertThat(blackjackGame.dealer.cardSize).isGreaterThan(2) // 2장 이상을 갖는다
    }

    @Test
    fun `dealer의 카드가 16초과면 카드를 더 받지 않는다`() {
        val player = Player("song")
        val players = Players(listOf(player))
        val dealer = Dealer(Hit(cards(CardType.JACK, CardType.QUEEN)))
        val blackjackGame = BlackjackGame(players, dealer)

        blackjackGame.giveCardsToDealer()
        assertThat(blackjackGame.dealer.cardSize).isEqualTo(2) // 2장만을 가지고 있다
    }

    @Test
    fun `player 한 명이 stay(win)인 경우 profit을 계산한다`() {
        val player = Player("song", Stay(cards(CardType.JACK, CardType.EIGHT)), 10000)
        val players = Players(listOf(player))
        val dealer = Dealer(Stay(cards(CardType.NINE, CardType.EIGHT)))
        val blackjackGame = BlackjackGame(players, dealer)

        val profits = blackjackGame.findProfits()
        assertThat(profits.dealerProfit.amount).isEqualTo(BigDecimal(-10000))
        assertThat(profits.playerProfits[0].amount).isEqualTo(BigDecimal(10000))
    }

    @Test
    fun `player 한 명이 stay(Lose)인 경우 profit을 계산한다`() {
        val player = Player("song", Stay(cards(CardType.NINE, CardType.EIGHT)), 10000)
        val players = Players(listOf(player))
        val dealer = Dealer(Stay(cards(CardType.JACK, CardType.EIGHT)))
        val blackjackGame = BlackjackGame(players, dealer)

        val profits = blackjackGame.findProfits()
        assertThat(profits.dealerProfit.amount).isEqualTo(BigDecimal(10000))
        assertThat(profits.playerProfits[0].amount).isEqualTo(BigDecimal(-10000))
    }

    @Test
    fun `player 한 명이 Blackjack인 경우 profit을 계산한다`() {
        val player = Player("song", BlackJack(cards(CardType.ACE, CardType.JACK)), 10000)
        val players = Players(listOf(player))
        val dealer = Dealer(Stay(cards(CardType.JACK, CardType.EIGHT)))
        val blackjackGame = BlackjackGame(players, dealer)

        val profits = blackjackGame.findProfits()
        assertThat(profits.dealerProfit.amount).isEqualTo(BigDecimal("-15000"))
        assertThat(profits.playerProfits[0].amount).isEqualTo(BigDecimal("15000"))
    }

    @Test
    fun `player와 dealer가 모두 Bust인 경우 profit을 계산한다`() {
        val player = Player("song", Bust(cards(CardType.KING, CardType.JACK, CardType.QUEEN)), 10000)
        val players = Players(listOf(player))
        val dealer = Dealer(Bust(cards(CardType.KING, CardType.JACK, CardType.QUEEN)))
        val blackjackGame = BlackjackGame(players, dealer)

        val profits = blackjackGame.findProfits()
        assertThat(profits.dealerProfit.amount).isEqualTo(BigDecimal("10000"))
        assertThat(profits.playerProfits[0].amount).isEqualTo(BigDecimal("-10000"))
    }

    @Test
    fun `player 한 명이 Blackjack이고, 다른 한 명이 stay(win)인 경우 profit을 계산한다`() {
        val player1 = Player("song", BlackJack(cards(CardType.ACE, CardType.JACK)), 10000)
        val player2 = Player("kim", Stay(cards(CardType.QUEEN, CardType.JACK)), 20000)
        val players = Players(listOf(player1, player2))
        val dealer = Dealer(Hit(cards(CardType.NINE, CardType.EIGHT)))
        val blackjackGame = BlackjackGame(players, dealer)

        val profits = blackjackGame.findProfits()
        assertThat(profits.dealerProfit.amount).isEqualTo(BigDecimal(-35000))
        assertThat(profits.playerProfits[0].amount).isEqualTo(BigDecimal(15000))
        assertThat(profits.playerProfits[1].amount).isEqualTo(BigDecimal(20000))
    }
}

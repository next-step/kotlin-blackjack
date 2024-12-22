package blackjack.domain

import blackjack.domain.calculator.BettingCalculator
import blackjack.domain.calculator.WinningCalculator
import blackjack.domain.player.Dealer
import blackjack.domain.player.Player
import blackjack.domain.player.Players
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import java.math.BigDecimal

class BettingCalculatorTest {
    @Test
    fun `testBettingCalculator - player win with blackjack`() {
        val player1 = Player("Player 1", BigDecimal(1000))
        player1.drawCard(Card.createCard("A", "클로버"))
        player1.drawCard(Card.createCard("10", "하트"))

        val dealer = Dealer()
        dealer.drawCard(Card.createCard("5", "클로버"))
        dealer.drawCard(Card.createCard("7", "하트"))

        WinningCalculator.calculatorGameResult(Players(listOf(player1)), dealer)
        BettingCalculator.calculateBalance(Players(listOf(player1)), dealer)

        player1.balance.toInt() shouldBe 1500
        dealer.balance.toInt() shouldBe -1500
    }

    @Test
    fun `testBettingCalculator - player lose`() {
        val player1 = Player("Player 1", BigDecimal(1000))
        player1.drawCard(Card.createCard("10", "하트"))
        player1.drawCard(Card.createCard("9", "하트"))
        player1.drawCard(Card.createCard("8", "하트"))

        val dealer = Dealer()
        dealer.drawCard(Card.createCard("3", "클로버"))
        dealer.drawCard(Card.createCard("9", "클로버"))
        dealer.drawCard(Card.createCard("8", "다이아몬드"))

        WinningCalculator.calculatorGameResult(Players(listOf(player1)), dealer)
        BettingCalculator.calculateBalance(Players(listOf(player1)), dealer)

        player1.balance.toInt() shouldBe -1000
        dealer.balance.toInt() shouldBe 1000
    }

    @Test
    fun `testBettingCalculator - player draw`() {
        val player1 = Player("Player 1", BigDecimal(1000))
        player1.drawCard(Card.createCard("A", "하트"))
        player1.drawCard(Card.createCard("10", "하트"))

        val dealer = Dealer()
        dealer.drawCard(Card.createCard("A", "클로버"))
        dealer.drawCard(Card.createCard("10", "클로버"))

        WinningCalculator.calculatorGameResult(Players(listOf(player1)), dealer)
        BettingCalculator.calculateBalance(Players(listOf(player1)), dealer)

        player1.balance shouldBe 1000
        dealer.balance shouldBe -1000
    }

    @Test
    fun `testBettingCalculator - dealer bust`() {
        val player1 = Player("Player 1", BigDecimal(1000))
        player1.drawCard(Card.createCard("A", "하트"))
        player1.drawCard(Card.createCard("10", "하트"))
        player1.drawCard(Card.createCard("9", "하트"))

        val dealer = Dealer()
        dealer.drawCard(Card.createCard("9", "클로버"))
        dealer.drawCard(Card.createCard("8", "클로버"))
        dealer.drawCard(Card.createCard("7", "다이아몬드"))

        WinningCalculator.calculatorGameResult(Players(listOf(player1)), dealer)
        BettingCalculator.calculateBalance(Players(listOf(player1)), dealer)

        println(player1.gameResult)
        player1.balance.toInt() shouldBe 1000
        dealer.balance.toInt() shouldBe -1000
    }
}

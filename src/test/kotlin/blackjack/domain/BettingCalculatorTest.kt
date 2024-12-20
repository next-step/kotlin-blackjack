package blackjack.domain

import blackjack.domain.calculator.BettingCalculator
import blackjack.domain.player.Dealer
import blackjack.domain.player.Player
import blackjack.domain.player.Players
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class BettingCalculatorTest {

    @Test
    fun `testBettingCalculator - player win with blackjack`() {
        val player1 = Player("Player 1", 1000)
        player1.drawCard(Card.createCard("A", "클로버"))
        player1.drawCard(Card.createCard("10", "하트"))

        val dealer = Dealer()
        dealer.drawCard(Card.createCard("5", "클로버"))
        dealer.drawCard(Card.createCard("7", "하트"))

        BettingCalculator.calculateBettingAmount(Players(listOf(player1)), dealer)

        player1.betMoney shouldBe 1500
        dealer.betMoney shouldBe -1500
    }

    @Test
    fun `testBettingCalculator - player lose`() {
        val player1 = Player("Player 1", 1000)
        player1.drawCard(Card.createCard("10", "하트"))
        player1.drawCard(Card.createCard("9", "하트"))
        player1.drawCard(Card.createCard("8", "하트"))

        val dealer = Dealer()
        dealer.drawCard(Card.createCard("3", "클로버"))
        dealer.drawCard(Card.createCard("9", "클로버"))
        dealer.drawCard(Card.createCard("8", "다이아몬드"))

        BettingCalculator.calculateBettingAmount(Players(listOf(player1)), dealer)

        player1.betMoney shouldBe -1000
        dealer.betMoney shouldBe 1000
    }

    @Test
    fun `testBettingCalculator - player draw`() {
        val player1 = Player("Player 1", 1000)
        player1.drawCard(Card.createCard("A", "하트"))
        player1.drawCard(Card.createCard("10", "하트"))

        val dealer = Dealer()
        dealer.drawCard(Card.createCard("A", "클로버"))
        dealer.drawCard(Card.createCard("10", "클로버"))

        BettingCalculator.calculateBettingAmount(Players(listOf(player1)), dealer)

        player1.betMoney shouldBe 1000
        dealer.betMoney shouldBe 0
    }

    @Test
    fun `testBettingCalculator - dealer bust`() {
        val player1 = Player("Player 1", 1000)
        player1.drawCard(Card.createCard("A", "하트"))
        player1.drawCard(Card.createCard("10", "하트"))
        player1.drawCard(Card.createCard("9", "하트"))

        val dealer = Dealer()
        dealer.drawCard(Card.createCard("9", "클로버"))
        dealer.drawCard(Card.createCard("8", "클로버"))
        dealer.drawCard(Card.createCard("7", "다이아몬드"))

        BettingCalculator.calculateBettingAmount(Players(listOf(player1)), dealer)

        player1.betMoney shouldBe 1000
        dealer.betMoney shouldBe 0
    }
}
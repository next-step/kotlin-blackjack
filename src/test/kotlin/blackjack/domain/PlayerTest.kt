package blackjack.domain

import blackjack.domain.player.AbstractPlayer
import blackjack.domain.player.Player
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class PlayerTest {
    @Test
    fun drawCardTest() {
        val player = Player("Tester")
        val mockCard =
            listOf(
                Card.createCard("3", "클로버"),
                Card.createCard("4", "하트"),
            )

        mockCard.forEach { card ->
            player.drawCard(card)
        }

        player.getAllCards() shouldBe mockCard
    }

    @Test
    fun calculateCardTest() {
        val player = Player("Tester")

        val mockCard =
            listOf(
                Card.createCard("3", "클로버"),
                Card.createCard("4", "하트"),
            )

        mockCard.forEach { card ->
            player.drawCard(card)
        }
        val expected = 7
        player.calculateCard() shouldBe expected
    }

    @Test
    fun playerDoneStatusTest() {
        val player = Player("Tester")
        val mockCard =
            listOf(
                Card.createCard("8", "클로버"),
                Card.createCard("9", "하트"),
                Card.createCard("9", "하트"),
            )

        mockCard.forEach { card ->
            player.drawCard(card)
        }

        player.isBust() shouldBe true
    }

    @Test
    fun `calculateAceTest - ace should be 1`() {
        val player = Player("Tester")
        val mockCard =
            listOf(
                Card.createCard("A", "클로버"),
                Card.createCard("9", "하트"),
                Card.createCard("9", "하트"),
            )

        mockCard.forEach { card ->
            player.drawCard(card)
        }

        player.calculateCard() shouldBe 19
    }

    @Test
    fun `calculateAceTest - ace should be 11`() {
        val player = Player("Tester")
        val mockCard =
            listOf(
                Card.createCard("A", "클로버"),
                Card.createCard("9", "하트"),
            )

        mockCard.forEach { card ->
            player.drawCard(card)
        }

        player.calculateCard() shouldBe 20
    }

    @Test
    fun `test Player can't draw when busted`() {
        val player = Player("Tester")
        val mockCard =
            listOf(
                Card.createCard("9", "클로버"),
                Card.createCard("9", "하트"),
                Card.createCard("10", "하트")
            )

        mockCard.forEach { card ->
            player.drawCard(card)
        }

        var turnCallbackCalled = 0
        val turnCallback: ((AbstractPlayer) -> String) = {
            turnCallbackCalled++
            "y"
        }

        var printCallbackCalled = 0
        val printCallback: ((List<AbstractPlayer>) -> Unit) = {
            printCallbackCalled++
        }

        player.startTurn(turnCallback, printCallback)
        player.calculateCard() shouldBe 28
        turnCallbackCalled shouldBe 0
        printCallbackCalled shouldBe 0
    }
}

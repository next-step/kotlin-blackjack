package blackjack.domain

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

        player.isDone() shouldBe true
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
}

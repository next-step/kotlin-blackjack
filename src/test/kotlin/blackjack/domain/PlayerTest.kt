package blackjack.domain

import blackjack.domain.Card.Companion.SYMBOL
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class PlayerTest {
    @Test
    fun drawCardTest() {
        val player = Player("Tester")
        val mockCard =
            listOf(
                Card("3", SYMBOL.CLUB),
                Card("4", SYMBOL.HEART),
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
                Card("3", SYMBOL.CLUB),
                Card("4", SYMBOL.HEART),
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
                Card("8", SYMBOL.CLUB),
                Card("9", SYMBOL.HEART),
                Card("9", SYMBOL.HEART),
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
                Card("A", SYMBOL.CLUB),
                Card("9", SYMBOL.HEART),
                Card("9", SYMBOL.HEART),
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
                Card("A", SYMBOL.CLUB),
                Card("9", SYMBOL.HEART),
            )

        mockCard.forEach { card ->
            player.drawCard(card)
        }

        player.calculateCard() shouldBe 20
    }
}

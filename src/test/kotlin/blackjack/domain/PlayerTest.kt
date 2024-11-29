package blackjack.domain

import org.junit.jupiter.api.Test
import blackjack.domain.Card.Companion.SYMBOL
import io.kotest.matchers.shouldBe

class PlayerTest {
    @Test
    fun drawCardTest() {
        val player = Player("Tester")
        val mockCard = listOf(
            Card("3", SYMBOL.CLUB),
            Card("4", SYMBOL.HEART)
        )

        mockCard.forEach { card ->
            player.drawCard(card)
        }

        player.getAllCards() shouldBe mockCard
    }

    @Test
    fun calculateCardTest() {
        val player = Player("Tester")
        val mockCard = listOf(
            Card("3", SYMBOL.CLUB),
            Card("4", SYMBOL.HEART)
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
        val mockCard = listOf(
            Card("8", SYMBOL.CLUB),
            Card("9", SYMBOL.HEART),
            Card("9", SYMBOL.HEART)
        )

        mockCard.forEach { card ->
            player.drawCard(card)
        }

        player.isDone() shouldBe true
    }
}

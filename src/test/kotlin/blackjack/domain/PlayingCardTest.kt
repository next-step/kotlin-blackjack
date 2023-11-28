package blackjack.domain

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class PlayingCardTest {
    @Test
    fun `카드의 총 개수는 52장이다`() {
        val shapes = listOf(*CardShape.values())
        val characters = listOf(*CardCharacter.values())
        val cards = Cards(shapes.flatMap { suit -> (characters).map { num -> Card(num, suit) } }.toMutableSet())

        PlayingCard(cards).cards.size shouldBe 52
    }
}

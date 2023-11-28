package blackjack.domain

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class PlayingCardTest {
    @Test
    fun `카드는 중복없이 52장으로 구성된다`() {
        val shapes = listOf(*CardShape.values())
        val characters = listOf(*CardCharacter.values())
        val cards = Cards(shapes.flatMap { suit -> (characters).map { num -> Card(num, suit) } }.toMutableSet())

        PlayingCard(cards).cards.size shouldBe 52
    }

    @Test
    fun `카드는 52장보다 작을 경우 IllegalArgumentException 이 발생한다`() {
        val shapes = listOf(CardShape.CLUB, CardShape.SPADE)
        val characters = listOf(*CardCharacter.values())
        val cards = Cards(shapes.flatMap { suit -> (characters).map { num -> Card(num, suit) } }.toMutableSet())

        assertThrows<IllegalArgumentException> { PlayingCard(cards) }
    }

    @Test
    fun `카드 덱에 중복된 카드를 넣더라도 중복이 제거된다`() {
        val shapes = listOf(*CardShape.values(), CardShape.SPADE)
        val characters = listOf(*CardCharacter.values())
        val cards = Cards(shapes.flatMap { suit -> (characters).map { num -> Card(num, suit) } }.toMutableSet())

        PlayingCard(cards).cards.size shouldBe 52
    }
}

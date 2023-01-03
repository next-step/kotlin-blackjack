package blackjack.domain.player

import blackjack.domain.card.Card
import blackjack.domain.card.Denomination
import blackjack.domain.card.Shape
import blackjack.domain.state.FirstTurn
import io.kotest.matchers.collections.shouldContainInOrder
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

internal class UserTest {
    @Test
    fun `플레이어는 이름과 핸드를 가진다`() {
        val firstCard = Card(Shape.CLUB, Denomination.ACE)
        val secondCard = Card(Shape.DIAMOND, Denomination.FOUR)
        val player = User("최원준", FirstTurn.draw(firstCard, secondCard))

        player.name shouldBe "최원준"
        player.state.cards() shouldContainInOrder listOf(firstCard, secondCard)
    }

    @Test
    fun `플레이어는 카드 합을 가진다`() {
        val firstCard = Card(Shape.CLUB, Denomination.TEN)
        val secondCard = Card(Shape.DIAMOND, Denomination.FOUR)
        val player = User("최원준", FirstTurn.draw(firstCard, secondCard))

        player.score() shouldBe 14
    }
}

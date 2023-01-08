package blackjack.domain.player

import blackjack.domain.Bat
import blackjack.domain.card.Card
import blackjack.domain.card.Denomination
import blackjack.domain.card.Shape
import blackjack.domain.state.FirstTurn
import blackjack.domain.state.Stay
import io.kotest.matchers.collections.shouldContainInOrder
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeInstanceOf
import org.junit.jupiter.api.Test

internal class UserTest {
    @Test
    fun `유저는 이름과 핸드를 가진다`() {
        val firstCard = Card(Shape.CLUB, Denomination.ACE)
        val secondCard = Card(Shape.DIAMOND, Denomination.FOUR)
        val user = User("최원준", FirstTurn.draw(firstCard, secondCard))

        user.name shouldBe "최원준"
        user.state.cards() shouldContainInOrder listOf(firstCard, secondCard)
    }

    @Test
    fun `유저는 카드 합을 가진다`() {
        val firstCard = Card(Shape.CLUB, Denomination.TEN)
        val secondCard = Card(Shape.DIAMOND, Denomination.FOUR)
        val user = User("최원준", FirstTurn.draw(firstCard, secondCard))

        user.score() shouldBe 14
    }

    @Test
    fun `유저는 Stay를 할 수 있다`() {
        val firstCard = Card(Shape.CLUB, Denomination.TEN)
        val secondCard = Card(Shape.DIAMOND, Denomination.FOUR)
        val user = User("최원준", FirstTurn.draw(firstCard, secondCard))

        user.stay().state.shouldBeInstanceOf<Stay>()
    }

    @Test
    fun `유저는 베팅을 할 수 있다`() {
        val firstCard = Card(Shape.CLUB, Denomination.TEN)
        val secondCard = Card(Shape.DIAMOND, Denomination.FOUR)
        val user = User("최원준", FirstTurn.draw(firstCard, secondCard))

        user.bat(10000.0).bat.shouldBeInstanceOf<Bat>()
    }
}

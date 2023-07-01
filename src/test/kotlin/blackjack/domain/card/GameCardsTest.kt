package blackjack.domain.card

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

internal class GameCardsTest {

    @Test
    internal fun `카드는 트럼프카드 안에 존재하는 카드만 생성된다`() {
        val card = GameCards(GameCardGenerator.crete()).draw()
        Shape.values().contains(card.shape) shouldBe true
        Character.values().contains(card.character) shouldBe true
    }

    @Test
    internal fun `생성한 게임 카드 리스트 부터 순차적으로 나온다`() {
        val gameCards = GameCards(mutableListOf(Card(Shape.DIAMOND, Character.SIX), Card(Shape.CLOVER, Character.J)))
        val card1 = gameCards.draw()
        card1.shape shouldBe Shape.DIAMOND
        card1.character shouldBe Character.SIX
        val card2 = gameCards.draw()
        card2.shape shouldBe Shape.CLOVER
        card2.character shouldBe Character.J
    }

    @Test
    internal fun `생성한 게임 카드 리스트이상으로 카드를 뽑으면 에러가 발생한다`() {
        val gameCards = GameCards(mutableListOf(Card(Shape.DIAMOND, Character.SIX), Card(Shape.CLOVER, Character.J)))
        shouldThrow<IllegalStateException> {
            gameCards.draw()
            gameCards.draw()
            gameCards.draw()
        }
    }
}

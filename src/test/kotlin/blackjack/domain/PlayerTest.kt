package blackjack.domain

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

internal class PlayerTest {
    @Test
    fun `플레이어는 이름과 핸드를 가진다`() {
        val player = Player("최원준")
        val cards = listOf(Card(Shape.CLUB, Denomination.ACE), Card(Shape.DIAMOND, Denomination.FOUR))
        player.draw(cards[0])
        player.draw(cards[1])

        player.name shouldBe "최원준"
        player.open() shouldBe cards
    }

    @Test
    fun `플레이어는 카드 합을 가진다`() {
        val card = Card(Shape.CLUB, Denomination.FIVE)
        val player = Player("최원준")
        player.draw(card)

        player.name shouldBe "최원준"
        player.score().value shouldBe 5
    }

    @Test
    fun `딜러와의 승패를 구분할 수 있다`() {
        val player = Player("최원준")
        player.draw(Card(Shape.CLUB, Denomination.TEN))
        player.draw(Card(Shape.CLUB, Denomination.ACE))

        val dealer = Dealer()
        dealer.draw(Card(Shape.CLUB, Denomination.TEN))
        dealer.draw(Card(Shape.CLUB, Denomination.NINE))

        val result = player.result(dealer.score())

        result shouldBe Result.WIN
    }
}

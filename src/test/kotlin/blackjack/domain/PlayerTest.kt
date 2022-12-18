package blackjack.domain

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

internal class PlayerTest {
    @Test
    fun `플레이어는 이름과 핸드를 가진다`() {
        val player = Player("최원준")
        val cards = listOf(Card(Shape.CLUB, Denomination.ACE), Card(Shape.DIAMOND, Denomination.FOUR))
        player.addCard(cards[0])
        player.addCard(cards[1])

        player.name shouldBe "최원준"
        player.hand shouldBe cards
    }

    @Test
    fun `플레이어는 카드 합을 가진다`() {
        val card = Card(Shape.CLUB, Denomination.FIVE)
        val player = Player("최원준")
        player.addCard(card)

        player.name shouldBe "최원준"
        player.score() shouldBe 5
    }

    @Test
    fun `Ace카드는 11로 계산될 수 있다`() {
        val player = Player("최원준")
        player.addCard(Card(Shape.CLUB, Denomination.TEN))
        player.addCard(Card(Shape.CLUB, Denomination.ACE))

        player.name shouldBe "최원준"
        player.score() shouldBe 21
    }

    @Test
    fun `딜러와의 승패를 구분할 수 있다`() {
        val player = Player("최원준")
        player.addCard(Card(Shape.CLUB, Denomination.TEN))
        player.addCard(Card(Shape.CLUB, Denomination.ACE))

        val dealer = Dealer()
        dealer.addCard(Card(Shape.CLUB, Denomination.TEN))
        dealer.addCard(Card(Shape.CLUB, Denomination.NINE))

        val result = player.result(dealer)

        result shouldBe Result.WIN
    }
}

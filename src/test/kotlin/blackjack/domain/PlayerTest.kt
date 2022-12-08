package blackjack.domain

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

internal class PlayerTest {
    @Test
    fun `플레이어는 이름과 핸드를 가진다`() {
        val cards = mutableListOf(Card(Shape.CLUB, Denomination.ACE))
        val player = Player("최원준", cards)

        player.name shouldBe "최원준"
        player.hand shouldBe cards
    }

    @Test
    fun `플레이어는 카드 합을 가진다`() {
        val cards = mutableListOf(Card(Shape.CLUB, Denomination.ACE))
        val player = Player("최원준", cards)

        player.name shouldBe "최원준"
        player.hand shouldBe cards
    }

    @Test
    fun `Ace카드는 11로 계산될 수 있다`() {
        val cards = mutableListOf(Card(Shape.CLUB, Denomination.ACE), Card(Shape.CLUB, Denomination.JACK))
        val player = Player("최원준", cards)

        player.name shouldBe "최원준"
        player.hand shouldBe cards
        player.score() shouldBe 21
    }
}

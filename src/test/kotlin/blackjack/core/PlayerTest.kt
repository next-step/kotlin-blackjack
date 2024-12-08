package blackjack.core

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class PlayerTest {
    @Test
    fun `카드 Draw를 테스트한다`() {
        val player = Player(Name("test"))

        player.point() shouldBe 0

        player.draw(Card(Denomination.JACK, Suit.SPADES))
        player.point() shouldBe 10

        player.draw(Card(Denomination.JACK, Suit.SPADES))
        player.point() shouldBe 10

        player.draw(Card(Denomination.QUEEN, Suit.SPADES))
        player.point() shouldBe 20
        player.cards.status shouldBe Status.HIT

        player.draw(Card(Denomination.KING, Suit.SPADES))
        player.point() shouldBe 30
        player.cards.checkBust()
        player.cards.status shouldBe Status.BUSTED
    }
}

package blackjack.core

import blackjack.core.card.Card
import blackjack.core.card.Denomination
import blackjack.core.card.Suit
import blackjack.core.player.Name
import blackjack.core.player.Player
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class PlayerTest {
    @Test
    fun `카드 Draw를 테스트한다`() {
        val player = Player(Name("test"))

        player.point shouldBe 0

        player.draw(Card(Denomination.JACK, Suit.SPADES))
        player.point shouldBe 10

        player.draw(Card(Denomination.JACK, Suit.SPADES))
        player.point shouldBe 10

        player.draw(Card(Denomination.QUEEN, Suit.SPADES))
        player.point shouldBe 20

        player.draw(Card(Denomination.KING, Suit.SPADES))
        player.point shouldBe 30
        player.cards.checkBust() shouldBe true
    }
}

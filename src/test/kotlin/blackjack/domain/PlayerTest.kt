package blackjack.domain

import blackjack.enums.Rank
import blackjack.enums.Symbol
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class PlayerTest {

    @Test
    fun `플레이어는 이름과 카드를 가진다`() {
        val cards = mutableListOf(Card(rank = Rank.ACE, symbol = Symbol.SPADES))
        val player = Player(name = "플레이어1", cards = cards)

        player.name shouldBe "플레이어1"
        player.cards shouldBe cards
    }
}

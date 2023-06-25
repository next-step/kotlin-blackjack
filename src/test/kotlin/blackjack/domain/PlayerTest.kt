package blackjack.domain

import blackjack.enums.Condition
import blackjack.enums.Rank
import blackjack.enums.Symbol
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class PlayerTest {

    @Test
    fun `플레이어는 이름과 카드를 가진다`() {
        val cards = Cards(listOf((Card(rank = Rank.ACE, symbol = Symbol.SPADES))))
        val player = Player(name = "플레이어1", cards = cards)

        player.name shouldBe "플레이어1"
        player.cards shouldBe cards
    }

    @Test
    fun `플레이어는 카드를 받기 위한 응답 여부의 조건값을 가진다`() {
        val cards = Cards(listOf((Card(rank = Rank.ACE, symbol = Symbol.SPADES))))
        val player = Player(name = "플레이어1", cards = cards)

        player.condition shouldBe Condition.YES
    }
}

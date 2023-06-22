package blackjack.domain

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class PlayerTest {

    @Test
    fun `플레이어는 카드 이름과 2장의카드를 소지한다`() {
        val cardSize = BlackJackTable.DEFAULT_CARD_SIZE
        val player = Player("pobi", cardSize)
        player.name shouldBe "pobi"
        player.cards.size shouldBe cardSize
    }

    @Test
    fun `플레이어는 카드를 더 받을 수 있다`() {
        val cardSize = BlackJackTable.DEFAULT_CARD_SIZE
        val player = Player("pobi", cardSize)
        player.addCard(BlackJackTable.hitCard())
        player.cards.size shouldBe cardSize+1
    }
}

package blackjack.domain

import blackjack.Player
import blackjack.card.CardDeck
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class PlayerTest {

    @Test
    fun `플레이어는 이름을 가질 수 있다`() {
        val name = "홍길동"
        val player = Player(name)

        player.name shouldBe name
    }

    @Test
    fun `플레이어는 카드를 뽑을 수 있다`() {
        val name = "홍길동"
        val player = Player(name)
        val cardDeck = CardDeck()

        player.drawCard(cardDeck.draw(2))
        player.cards.size shouldBe 2
    }
}

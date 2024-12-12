package blackjack.core

import blackjack.core.card.Card
import blackjack.core.card.CardDispenser
import blackjack.core.player.Name
import blackjack.core.player.Player
import blackjack.core.player.Players
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import org.junit.jupiter.api.Test

class CardDispenserTest {
    @Test
    fun `Class 생성 시 Cards 매번 Suffle됨을 확인한다`() {
        val player = Player(Name("test"))
        CardDispenser().apply { repeat(Card.CARD_COUNT) { deal(player) } }

        val player2 = Player(Name("test"))
        CardDispenser().apply { repeat(Card.CARD_COUNT) { deal(player2) } }

        player.cards shouldNotBe player2.cards
    }

    @Test
    fun `카드 발급을 확인한다`() {
        val cardDispenser = CardDispenser()
        val player = Player(Name("Test"))

        cardDispenser.dealDefault(player)
        player.cards.size shouldBe 2

        val players = Players(List(30) { Player(Name("test")) })

        cardDispenser.checkRemainCard() shouldBe true
        cardDispenser.dealDefault(players)

        players[0].cards.size shouldBe 2
        players[1].cards.size shouldBe 2

        cardDispenser.checkRemainCard() shouldBe false
    }
}

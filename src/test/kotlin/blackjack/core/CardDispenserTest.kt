package blackjack.core

import blackjack.core.card.Card
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
        CardDispenser().apply { deal(player, Card.CARD_COUNT) }

        val player2 = Player(Name("test"))
        CardDispenser().apply { deal(player2, Card.CARD_COUNT) }

        player.getCardNames() shouldNotBe player2.getCardNames()
    }

    @Test
    fun `카드 발급을 확인한다`() {
        val cardDispenser = CardDispenser()
        val player = Player(Name("Test"))

        cardDispenser.deal(player) shouldBe true
        player.cards.size shouldBe 1

        cardDispenser.deal(player, 10) shouldBe true
        player.cards.size shouldBe 11

        cardDispenser.deal(player, 100) shouldBe false
        player.cards.size shouldBe Card.CARD_COUNT

        val players = Players(setOf(Name("test1"), Name("test2")))
        CardDispenser().deal(players, 10)

        players[0].cards.size shouldBe 10
        players[1].cards.size shouldBe 10

        val players2 = Players(setOf(Name("test1"), Name("test2")))
        val cardDispenser2 = CardDispenser()

        cardDispenser2.checkRemainCard() shouldBe true

        cardDispenser2.deal(players2, 100) shouldBe false
        players2[0].cards.size shouldBe Card.CARD_COUNT
        players2[1].cards.size shouldBe 0

        cardDispenser2.checkRemainCard() shouldBe false
    }
}

package blackjack.domain

import blackjack.helper.DeckHelper
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class PlayerTest : StringSpec({

    "플레이어는 카드를 한 장 받을 수 있다." {
        // given
        val player = Player(
            name = PlayerName("플레이어1"),
            cards = PlayerCards()
        )
        val deck = DeckHelper.createMockDeck()
        val card = deck.draw()

        // when
        player.handCard(card)

        // then
        player.cards.values.size shouldBe 1
        player.cards.values shouldBe listOf(card)
    }
})

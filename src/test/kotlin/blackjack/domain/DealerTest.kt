package blackjack.domain

import blackjack.helper.DeckHelper
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class DealerTest : StringSpec({

    "딜러는 카드를 플레이어에게 지정된 수만큼 분배할 수 있다." {
        // given
        val players = Players(
            listOf(
                Player(
                    name = PlayerName("플레이어1")
                ),
                Player(
                    name = PlayerName("플레이어2")
                )
            )
        )
        val deck = DeckHelper.createMockDeck()
        val dealer = Dealer(deck)

        // when
        dealer.handCards(2, players)

        // then
        players.values.forEach { player ->
            player.cards.values.size shouldBe 2
        }
    }
})

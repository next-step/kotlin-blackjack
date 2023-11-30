package blackjack.domain

import blackjack.domain.player.Player
import blackjack.domain.player.PlayerName
import blackjack.helper.DeckHelper
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class DealerTest : StringSpec({

    "딜러는 카드를 플레이어에게 카드를 분배할 수 있다." {
        // given
        val player = Player(
            name = PlayerName("플레이어1")
        )
        val deck = DeckHelper.createMockDeck()
        val dealer = Dealer(deck)

        // when
        dealer.handCard(player)

        // then
        player.cards.values.size shouldBe 1
    }
})

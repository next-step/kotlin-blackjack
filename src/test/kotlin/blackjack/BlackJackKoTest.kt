package blackjack

import blackjack.domain.Dealer
import blackjack.domain.Player
import blackjack.service.DeckManager
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class BlackJackKoTest : StringSpec({
    "참여자들에게 카드를 두 장 나누어 진다" {
        val players = arrayOf(Player("test1"), Player("test2"))
        val dealer = Dealer()
        val deckManager = DeckManager()
        dealer.beginRound(deckManager, players)
        players.forEach { it.hands().size shouldBe 2 }
    }
})

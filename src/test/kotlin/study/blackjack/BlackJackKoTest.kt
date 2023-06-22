package study.blackjack

import blackjack.domain.Dealer
import blackjack.domain.Deck
import blackjack.domain.Player
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class BlackJackKoTest : StringSpec({
    "참여자들에게 카드를 두 장 나누어 진다" {
        val deck = Deck()
        val dealer = Dealer(deck)
        val players = arrayOf(Player("test1"), Player("test2"))
        dealer.startRound(players)
        players.forEach { it.hands.size shouldBe 2 }
    }
})

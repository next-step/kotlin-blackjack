package blackjack.domain

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class PlayerTest : FunSpec({
    context("플레이어는 카드를 추가할 수 있다.") {
        val player = Player("june")
        val card = Card(Suit.SPADE, Denomination.ACE)
        player.addCard(card)
        player.cards.size shouldBe 1
        player.cards.contains(card) shouldBe true
    }
})

package blackjack

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.collections.shouldContainAll

class PlayerTest : FreeSpec({
    "플레이어는 카드를 받아서 소유할 수 있다." {
        val player = Player()
        val card = Card(Suit.SPADE, CardNumber.ACE)
        val card2 = Card(Suit.CLUB, CardNumber.EIGHT)

        player.take(card)
        player.take(card2)

        player.currentCards shouldContainAll listOf(card, card2)
    }
})

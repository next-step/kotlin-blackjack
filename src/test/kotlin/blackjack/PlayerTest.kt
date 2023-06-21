package blackjack

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class PlayerTest : FunSpec({
    test("이름과 카드를 플레이어를 생성한다.") {
        val cards = Cards(
            Card(Denomination.ACE, Suit.SPADES),
            Card(Denomination.JACK, Suit.SPADES),
            Card(Denomination.TWO, Suit.HEARTS),
        )
        val player = Player("pobi", cards)

        player.name shouldBe "pobi"
        player.cards shouldBe cards
    }

    test("카드를 더 받는다.") {
        val cards = Cards(
            Card(Denomination.ACE, Suit.SPADES),
            Card(Denomination.JACK, Suit.SPADES),
        )
        val card = Card(Denomination.TWO, Suit.HEARTS)
        val player = Player("pobi", cards)

        player.addCard(card)

        player.cards shouldBe Cards(
            Card(Denomination.ACE, Suit.SPADES),
            Card(Denomination.JACK, Suit.SPADES),
            Card(Denomination.TWO, Suit.HEARTS),
        )
    }
})

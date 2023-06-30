package blackjack

import baclkjack.domain.card.Card
import baclkjack.domain.card.Deck
import baclkjack.domain.card.Number
import baclkjack.domain.card.Suit
import baclkjack.domain.play.Player
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.shouldBe

class PlayerTest : StringSpec({

    "a Player 를 생성한다" {
        val player = Player("a")
        player.name shouldBe "a"
    }

    "내 카드가 21 인경우 blackjack" {
        val player = Player("a")
        val deck = Deck(
            cards = mutableListOf<Card>().apply {
                add(Card(Suit.HEART, Number.ACE))
                add(Card(Suit.HEART, Number.KING))
            }
        )
        player.start(deck)
        player.blackJack().shouldBeTrue()
        player.result() shouldBe 21
    }

    "내 카드가 22 이상이 경우  burst" {
        val player = Player("a")
        val deck = Deck(
            cards = mutableListOf<Card>().apply {
                add(Card(Suit.HEART, Number.KING))
                add(Card(Suit.HEART, Number.QUEEN))
                add(Card(Suit.HEART, Number.JACK))
            }
        )
        player.start(deck)
        player.hit(deck)
        player.burst().shouldBeTrue()
        player.result() shouldBe 30
    }
})

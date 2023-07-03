package blackjack

import baclkjack.domain.card.Card
import baclkjack.domain.card.Deck
import baclkjack.domain.card.Number
import baclkjack.domain.card.Suit
import baclkjack.domain.play.Dealer
import baclkjack.domain.play.GameState
import baclkjack.domain.play.Player
import io.kotest.assertions.assertSoftly
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.shouldBe

class DealerTest : StringSpec({

    "Dealer 를 생성한다" {
        val dealer = Dealer()
        dealer.name shouldBe "딜러"
    }

    "Dealer 카드가 16 인경우 isDraw" {
        val dealer = Dealer()
        val deck = Deck(
            cards = mutableListOf<Card>().apply {
                add(Card(Suit.HEART, Number.KING))
                add(Card(Suit.HEART, Number.SIX))
            }
        )
        dealer.start(deck)
        dealer.isDraw().shouldBeTrue()
    }

    "Dealer 20 Player 카드가 19,20,21 이면 Lose " {
        val dealer = Dealer()
        val playerA = Player("a")
        val playerB = Player("b")
        val playerC = Player("c")
        val deck = Deck(
            cards = mutableListOf<Card>().apply {
                add(Card(Suit.HEART, Number.KING))
                add(Card(Suit.HEART, Number.KING))
                add(Card(Suit.HEART, Number.KING))
                add(Card(Suit.HEART, Number.NINE))
                add(Card(Suit.HEART, Number.KING))
                add(Card(Suit.HEART, Number.KING))
                add(Card(Suit.HEART, Number.KING))
                add(Card(Suit.HEART, Number.ACE))
            }
        )
        dealer.start(deck)
        playerA.start(deck)
        playerB.start(deck)
        playerC.start(deck)

        val players = mutableListOf<Player>().apply {
            add(playerA)
            add(playerB)
            add(playerC)
        }

        val result = dealer.result(players)

        assertSoftly(result.toList()) {
            this[0].first shouldBe GameState.WIN
            this[0].second shouldBe 1
            this[1].first shouldBe GameState.DRAW
            this[1].second shouldBe 1
            this[2].first shouldBe GameState.LOSE
            this[2].second shouldBe 1
        }
    }
})

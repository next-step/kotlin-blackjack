package blackjack.play

import baclkjack.domain.card.Card
import baclkjack.domain.card.Deck
import baclkjack.domain.card.Number
import baclkjack.domain.card.Suit
import baclkjack.domain.play.Dealer
import baclkjack.domain.play.Money
import baclkjack.domain.play.Player
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

    "Dealer 카드가 20,  Player([a, 1000],[b, 2000], [c, 3000]) 카드가 19,20,21 이면 LOSE, DRAW, BLACKJACK 딜러의 수익은 -3500 이다  " {
        val dealer = Dealer()
        val playerA = Player(name = "a", money = Money(1_000))
        val playerB = Player(name = "b", money = Money(2_000))
        val playerC = Player(name = "c", money = Money(3_000))
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

        val price = dealer.result(players)
        price shouldBe -3500
    }
})

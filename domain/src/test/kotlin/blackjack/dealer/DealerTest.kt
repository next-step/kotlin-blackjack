package blackjack.dealer

import action.BlackJackAction
import blackjack.card.Card
import blackjack.card.CardRank
import blackjack.card.CardSuit
import blackjack.deck.Deck
import blackjack.hand.StandardHand
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class DealerTest : FunSpec({

    test("처음 딜러의 손패 수는 0이다.") {
        val dealer = Dealer(hand = StandardHand())
        dealer.cards.size shouldBe 0
    }

    test("손패의 수가 0일 때 결정할 액션은 HIT이다") {
        val dealer = Dealer(hand = StandardHand())
        dealer.decideAction(deck = Deck()) shouldBe BlackJackAction.HIT
    }

    test("딜러는 카드를 받으면 손패의 수가 1 증가한다.") {
        Dealer(hand = StandardHand()).also {
            it.cards.size shouldBe 0
        }.receiveCard(card = Card(suit = CardSuit.CLUBS, rank = CardRank.ACE))
            .cards.size shouldBe 1
    }

    test("ACE와 JACK을 가지고 있을 때, 베스트는 21이다.") {
        Dealer(hand = StandardHand())
            .receiveCard(card = Card(suit = CardSuit.CLUBS, rank = CardRank.ACE))
            .receiveCard(card = Card(suit = CardSuit.DIAMONDS, rank = CardRank.JACK))
            .calculateBestValue() shouldBe 21
    }
})

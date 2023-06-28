package blackjack.player

import blackjack.card.Card
import blackjack.card.CardNumber
import blackjack.card.CardSuit
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class ScoreCalculatorTest : StringSpec({

    "손에 Ace와 King이 있을 때 점수는 21점이어야 한다" {
        val hand = Hand()
        hand.addCard(Card(CardNumber.ACE, CardSuit.HEART))
        hand.addCard(Card(CardNumber.KING, CardSuit.CLUB))

        ScoreCalculator.calculateScore(hand) shouldBe 21
    }

    "손에 Ace 두 장이 있을 때 점수는 12점이어야 한다" {
        val hand = Hand()
        hand.addCard(Card(CardNumber.ACE, CardSuit.HEART))
        hand.addCard(Card(CardNumber.ACE, CardSuit.CLUB))

        ScoreCalculator.calculateScore(hand) shouldBe 12
    }

    "손에 Ace, King, Queen이 있을 때 점수는 21점이어야 한다" {
        val hand = Hand()
        hand.addCard(Card(CardNumber.ACE, CardSuit.HEART))
        hand.addCard(Card(CardNumber.KING, CardSuit.CLUB))
        hand.addCard(Card(CardNumber.QUEEN, CardSuit.DIAMOND))

        ScoreCalculator.calculateScore(hand) shouldBe 21
    }

    "손에 9, 10, Ace가 있을 때 점수는 20점이어야 한다" {
        val hand = Hand()
        hand.addCard(Card(CardNumber.NINE, CardSuit.HEART))
        hand.addCard(Card(CardNumber.TEN, CardSuit.CLUB))
        hand.addCard(Card(CardNumber.ACE, CardSuit.DIAMOND))

        ScoreCalculator.calculateScore(hand) shouldBe 20
    }
})

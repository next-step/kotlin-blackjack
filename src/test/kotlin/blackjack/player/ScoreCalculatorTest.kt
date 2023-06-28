package blackjack.player

import blackjack.card.Card
import blackjack.card.CardNumber
import blackjack.card.CardPattern
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class ScoreCalculatorTest : StringSpec({

    "손에 Ace와 King이 있을 때 점수는 21점이어야 한다" {
        val hand = Hand()
        hand.addCard(Card(CardNumber.ACE, CardPattern.HEART))
        hand.addCard(Card(CardNumber.KING, CardPattern.CLOVER))

        ScoreCalculator.calculateScore(hand) shouldBe 21
    }

    "손에 Ace 두 장이 있을 때 점수는 12점이어야 한다" {
        val hand = Hand()
        hand.addCard(Card(CardNumber.ACE, CardPattern.HEART))
        hand.addCard(Card(CardNumber.ACE, CardPattern.CLOVER))

        ScoreCalculator.calculateScore(hand) shouldBe 12
    }

    "손에 Ace, King, Queen이 있을 때 점수는 21점이어야 한다" {
        val hand = Hand()
        hand.addCard(Card(CardNumber.ACE, CardPattern.HEART))
        hand.addCard(Card(CardNumber.KING, CardPattern.CLOVER))
        hand.addCard(Card(CardNumber.QUEEN, CardPattern.DIAMOND))

        ScoreCalculator.calculateScore(hand) shouldBe 21
    }

    "손에 9, 10, Ace가 있을 때 점수는 20점이어야 한다" {
        val hand = Hand()
        hand.addCard(Card(CardNumber.NINE, CardPattern.HEART))
        hand.addCard(Card(CardNumber.TEN, CardPattern.CLOVER))
        hand.addCard(Card(CardNumber.ACE, CardPattern.DIAMOND))

        ScoreCalculator.calculateScore(hand) shouldBe 20
    }
})

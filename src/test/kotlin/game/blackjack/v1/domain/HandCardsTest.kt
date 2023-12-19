package game.blackjack.v1.domain

import game.blackjack.v1.domain.CardNumber.ACE
import game.blackjack.v1.domain.CardNumber.EIGHT
import game.blackjack.v1.domain.CardNumber.FOUR
import game.blackjack.v1.domain.CardNumber.JACK
import game.blackjack.v1.domain.CardNumber.KING
import game.blackjack.v1.domain.CardNumber.NINE
import game.blackjack.v1.domain.CardNumber.QUEEN
import game.blackjack.v1.domain.CardNumber.SEVEN
import game.blackjack.v1.domain.CardNumber.TEN
import game.blackjack.v1.domain.CardNumber.THREE
import game.blackjack.v1.domain.CardNumber.TWO
import game.blackjack.v1.domain.CardShape.HEART
import game.blackjack.v1.domain.CardShape.SPADE
import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

class HandCardsTest : StringSpec({

    "현재 점수가 11점 이상일 경우, ACE 카드를 뽑으면 1점으로 계산한다." {
        forAll(
            row(Card(NINE, SPADE), Card(TWO, SPADE)),
            row(Card(TEN, SPADE), Card(TWO, SPADE)),
            row(Card(JACK, SPADE), Card(TWO, SPADE)),
            row(Card(QUEEN, SPADE), Card(TWO, SPADE)),
            row(Card(KING, SPADE), Card(TWO, SPADE)),
            row(Card(KING, SPADE), Card(KING, HEART)),
        ) { card1: Card, card2: Card ->
            val handCards = HandCards()
            handCards.addAll(listOf(card1, card2))
            val originScore = handCards.getCurrentScore()

            handCards.add(Card(ACE, SPADE))

            handCards.getCurrentScore() shouldBe originScore + 1
        }
    }

    "현재 점수가 10점 이하일 경우, ACE 카드를 뽑으면 11점으로 계산한다." {
        forAll(
            row(Card(TWO, SPADE), Card(EIGHT, SPADE)),
            row(Card(TWO, SPADE), Card(TWO, HEART)),
            row(Card(TWO, SPADE), Card(THREE, SPADE)),
            row(Card(TWO, SPADE), Card(FOUR, SPADE)),
            row(Card(THREE, SPADE), Card(SEVEN, SPADE)),
        ) { card1: Card, card2: Card ->
            val handCards = HandCards()
            handCards.addAll(listOf(card1, card2))
            val originScore = handCards.getCurrentScore()

            handCards.add(Card(ACE, SPADE))

            handCards.getCurrentScore() shouldBe originScore + 11
        }
    }
})

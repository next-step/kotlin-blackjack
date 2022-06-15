package blackjack.domain

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

internal class HandTest : FreeSpec({
    val cards = listOf(
        Card(Suite.CLUBS, Denomination.JACK),
        Card(Suite.DIAMONDS, Denomination.KING)
    )

    "주어진 카드와 배팅액을 통해 인스턴스를 생성한다" {
        val bet = Bet(100)
        val hand = Hand(cards, bet)

        hand.count shouldBe cards.size
        hand.betAmount shouldBe bet.amount
    }

    "새로운 카드를 더해 새로운 손패를 생성한다" {
        val hand = Hand(cards)
        val newCard = Card(Suite.HEARTS, Denomination.ACE)

        val newHand = hand.addCard(newCard)

        newHand.cards shouldBe cards + newCard
    }
})

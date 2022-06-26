package camp.nextstep.blackjack.ui.cli

import camp.nextstep.blackjack.card.Card
import camp.nextstep.blackjack.card.Hand

object HandWriter {

    fun write(hand: Hand) {
        val faceUpCard = { card: Card -> "{${card.number.value}:${card.suit}}" }
        val faceUpCards = hand.faceUpCards.joinToString(",") { faceUpCard(it) }
        print(faceUpCards)

        val faceDownCard = "{?:?}"
        repeat(hand.faceDownCardCount) {
            print(faceDownCard)
        }
    }
}

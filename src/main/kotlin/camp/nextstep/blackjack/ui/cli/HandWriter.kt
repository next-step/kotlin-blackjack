package camp.nextstep.blackjack.ui.cli

import camp.nextstep.blackjack.card.Hand
import camp.nextstep.blackjack.ui.FaceDownCard
import camp.nextstep.blackjack.ui.FaceUpCard

object HandWriter {

    fun write(hand: Hand) {
        writeFaceUpCards(FaceUpCard.from(hand.faceUpCards))
        writeFaceDownCards(hand.faceDownCardCount)
    }

    private fun writeFaceUpCards(cards: List<FaceUpCard>) {
        print(cards.joinToString { it.toString() })
    }

    private fun writeFaceDownCards(faceDownCardCount: Int) {
        repeat(faceDownCardCount) { print(FaceDownCard) }
    }
}

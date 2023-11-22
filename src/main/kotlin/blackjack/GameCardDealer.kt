package blackjack

import java.lang.IllegalArgumentException

class GameCardDealer(
    cardShuffleStrategy: CardShuffleStrategy
) {

    private val cards: Iterator<Card> = cardShuffleStrategy.shuffle().iterator()

    fun deal(): Card {
        return if (cards.hasNext()) cards.next()
        else throw IllegalArgumentException("카드가 존재하지 않습니다.")
    }

    fun deal(count: Int): List<Card> = (1..count).map { deal() }
}

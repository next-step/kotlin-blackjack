package blackjack

import java.lang.IllegalArgumentException

object GameDealer {

    private val cards: Iterator<Card> = Card.Symbol.values()
        .flatMap { symbol ->
            Card.Number.values().map { number ->
                Card(symbol, number)
            }
        }
        .shuffled()
        .iterator()

    fun deal(): Card =
        if (cards.hasNext()) cards.next()
        else throw IllegalArgumentException("카드가 존재하지 않습니다.")

    fun deal(count: Int): List<Card> = (1..count).map { deal() }
}

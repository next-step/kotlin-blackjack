package blackjack.domain.participant.state

import blackjack.domain.deck.Card
import blackjack.domain.deck.CardNumber

class Hand(
    val cards: MutableList<Card> = mutableListOf(),
) {

    fun receiveCard(card: Card) {
        cards.add(card)
    }

    fun isBust(): Boolean = score().isBust()

    private fun score(): Score {
        var score = Score(this.cards.sumOf { it.number.score })
        val countOfAce = this.cards.count { it.number == CardNumber.ACE }
        return score.increaseAceScoreBeforeBust(countOfAce = countOfAce)
    }
}

package blackjack.domain.participant.state

import blackjack.domain.deck.Card
import blackjack.domain.deck.CardNumber

class Cards(
    values: List<Card> = listOf(),
) : List<Card> by values {

    val isBustScore: Boolean = score().isBust

    val isBlackjackScore: Boolean = score().isBlackjack

    fun receiveCard(card: Card): Cards = Cards(values = this + card)

    fun score(): Score {
        val score = Score(value = sumOf { it.number.score })
        val countOfAce = count { it.number == CardNumber.ACE }
        return score.increaseAceScoreBeforeBust(countOfAce = countOfAce)
    }
}

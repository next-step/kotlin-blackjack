package blackjack.domain.player

import blackjack.domain.card.Card
import blackjack.domain.card.Score
import blackjack.error.DuplicatePlayingCardException

@JvmInline
value class Hands private constructor(val hands: List<Card>) {

    fun score(): Score = calculateAceScore(sumScore())

    private fun sumScore() = hands
        .map(Card::score)
        .reduce(Score::plus)

    private fun calculateAceScore(sum: Score): Score {
        if (hands.any(Card::hasAce) && sum.canAddExtraAceScore()) {
            return sum.plus(Score.extraAceScore())
        }
        return sum
    }

    operator fun plus(extraCard: List<Card>): Hands {
        if (hands.any(extraCard::contains)) {
            throw DuplicatePlayingCardException
        }
        return Hands(hands.plus(extraCard))
    }

    companion object {
        fun initialize(): Hands = from(listOf())
        fun from(cards: List<Card>): Hands = Hands(cards.toList())
    }
}

package blackjack.domain.player.state.hands

import blackjack.domain.card.Card
import blackjack.domain.card.Score
import blackjack.error.DuplicatePlayingCardException

@JvmInline
value class Hands private constructor(val hands: List<Card>) {

    fun draw(card: Card): Hands {
        if (hands.contains(card)) {
            throw DuplicatePlayingCardException()
        }
        return Hands(hands + card)
    }

    fun isReady(): Boolean = hands.size < READY_MAXIMUM_RANGE

    fun isBust(): Boolean = score().isBust()

    fun isBlackJack(): Boolean = (score().isMaximumScore()) && (hands.size == READY_MAXIMUM_RANGE)

    fun isOverDealerDrawStandard(): Boolean = (score().isOverDealerDrawStandard())

    fun score(): Score = calculateAceScore(sumScore())

    private fun sumScore(): Score =
        hands.map(Card::score)
            .reduce(Score::plus)

    private fun calculateAceScore(sum: Score): Score {
        if (hands.any(Card::isAce) && sum.canPlusExtraAceScore()) {
            return sum + Score.EXTRA_ACE_SCORE
        }
        return sum
    }

    companion object {
        const val READY_MAXIMUM_RANGE = 2
        val EMPTY: Hands = from(listOf())

        fun from(cards: List<Card>): Hands = Hands(cards.toList())
    }
}

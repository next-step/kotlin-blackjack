package blackjack.domain

import blackjack.domain.rule.PlayingCardsRule

class CardDeck private constructor(private var cards: PlayingCards) {
    fun draw(count: Int): PlayingCards {
        val drawnCards = cards.take(count)
        cards = PlayingCards.from(cards.drop(count))

        return PlayingCards.from(drawnCards)
    }

    companion object {
        fun from(cards: PlayingCards): CardDeck {
            return CardDeck(cards)
        }

        fun of(cards: PlayingCards, vararg rules: PlayingCardsRule): CardDeck {
            val ruleAppliedCards = rules.fold(cards) { acc, playingCardsRule ->
                playingCardsRule.applyTo(acc)
            }

            return CardDeck(PlayingCards.from(ruleAppliedCards))
        }
    }
}

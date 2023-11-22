package blackjack.deck

import blackjack.card.Card

class RandomCardShuffler : CardShuffler {
    override fun shuffle(cards: List<Card>): List<Card> = cards.shuffled()
}

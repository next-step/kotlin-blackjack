package blackjack.domain.shuffle

import blackjack.domain.card.Card

class CardShuffler : Shuffler<Card> {

    override fun shuffled(list: List<Card>): List<Card> {
        return list.shuffled()
    }
}

package blackjack.domain.shuffle

import blackjack.domain.card.Card

class CardNotShuffler : Shuffler<Card> {

    override fun shuffled(list: List<Card>): List<Card> {
        return list
    }
}

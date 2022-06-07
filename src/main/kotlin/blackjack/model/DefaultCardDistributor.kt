package blackjack.model

import blackjack.model.card.PlayingCards
import blackjack.model.player.CardRecipient

class DefaultCardDistributor : CardDistributor {
    private lateinit var playingCardSet: PlayingCards

    override fun resetCardSet() {
        this.playingCardSet = PlayingCards.createNew()
    }

    override fun giveCardsTo(recipient: CardRecipient, count: Int) {
        repeat(count) { recipient.addCard(this.playingCardSet.next()) }
    }
}

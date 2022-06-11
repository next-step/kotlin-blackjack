package blackjack.model

import blackjack.model.card.PlayingCards
import blackjack.model.player.CardRecipient

class DefaultCardDistributor : CardDistributor {
    private var playingCardSet: PlayingCards? = null

    override fun resetCardSet() {
        createNewPlayingCards()
    }

    override fun giveCardsTo(recipient: CardRecipient, count: Int) {
        val playingCardSet = this.playingCardSet ?: createNewPlayingCards()
        repeat(count) { recipient.addCard(playingCardSet.next()) }
    }

    private fun createNewPlayingCards(): PlayingCards {
        val newPlayCardSet = PlayingCards.createNew()
        this.playingCardSet = newPlayCardSet
        return newPlayCardSet
    }
}

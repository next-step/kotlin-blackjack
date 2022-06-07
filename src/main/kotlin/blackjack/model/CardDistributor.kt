package blackjack.model

import blackjack.model.player.CardRecipient

interface CardDistributor {
    fun resetCardSet()

    fun giveCardsTo(recipients: Collection<CardRecipient>, count: Int = 1) {
        recipients.forEach { this.giveCardsTo(it, count) }
    }

    fun giveCardsTo(recipient: CardRecipient, count: Int = 1)
}

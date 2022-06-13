package blackjack.model

import blackjack.model.player.CardRecipient

interface CardDistributor {

    fun resetCardSet()

    fun giveInitialCardsTo(recipients: Collection<CardRecipient>) {
        this.giveCardsTo(recipients, INITIAL_CARD_COUNT_FOR_EACH_PLAYER)
    }

    fun giveCardsTo(recipients: Collection<CardRecipient>, count: Int = 1) {
        recipients.forEach { this.giveCardsTo(it, count) }
    }

    fun giveCardsTo(recipient: CardRecipient, count: Int = 1)

    companion object {
        const val INITIAL_CARD_COUNT_FOR_EACH_PLAYER = 2
    }
}

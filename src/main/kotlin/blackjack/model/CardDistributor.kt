package blackjack.model

import blackjack.model.player.CardRecipient

interface CardDistributor {

    val initialCardCountForEachPlayer: Int
        get() = DEFAULT_INITIAL_CARD_COUNT_FOR_EACH_PLAYER

    fun resetCardSet()

    fun giveInitialCardsTo(recipients: Collection<CardRecipient>) {
        this.giveCardsTo(recipients, this.initialCardCountForEachPlayer)
    }

    fun giveCardsTo(recipients: Collection<CardRecipient>, count: Int = 1) {
        recipients.forEach { this.giveCardsTo(it, count) }
    }

    fun giveCardsTo(recipient: CardRecipient, count: Int = 1)

    companion object {
        private const val DEFAULT_INITIAL_CARD_COUNT_FOR_EACH_PLAYER = 2
    }
}

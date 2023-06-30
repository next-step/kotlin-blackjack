package blackjack.model

import blackjack.model.participant.BlackjackPlayer

@JvmInline
value class PlayerNames(private val names: Collection<PlayerName>) {

    fun registerPlayer(
        deck: CardDeck,
        bettingMoneyProvider: BettingMoneyProvider,
        blackjackPlayerConsumer: BlackjackPlayerConsumer,
        moreWantedCardPredicate: MoreWantedCardPredicate,
    ): Collection<BlackjackPlayer> {
        return names.map {
            BlackjackPlayer(deck, bettingMoneyProvider, it, blackjackPlayerConsumer, moreWantedCardPredicate)
        }
    }
}

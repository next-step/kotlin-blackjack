package blackjack.domain

import java.util.function.Supplier

class BlackJack(
    private val cardDeck: CardDeck,
    vararg names: String
) {
    private val dealer = Dealer(cardDeck.next(), cardDeck.next())
    private val players = names.map { Player(it, cardDeck.next(), cardDeck.next()) }

    fun openCardsOfParticipant(): Map<String, List<Card>> {
        return participants().associate { it.name to it.openCards() }
    }

    fun isDealerObtainable(): Boolean {
        return dealer.isObtainable()
    }

    fun obtainDealerCard(): List<Card> {
        dealer.obtain(cardDeck.next())
        return dealer.hands
    }

    fun isPlayerObtainable(name: String, wantToTake: Supplier<Boolean>): Boolean {
        val player = players.first { it.name == name }
        return player.isObtainable() && wantToTake.get()
    }

    fun obtainPlayerCard(name: String): List<Card> {
        val player = players.first { it.name == name }
        player.obtain(cardDeck.next())
        return player.hands
    }

    fun compareResults(): Map<String, CompareResult> {
        return dealer.compareWith(*players.toTypedArray())
    }

    fun participants(): List<Participant> {
        return players + listOf(dealer)
    }
}

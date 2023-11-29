package blackjack.domain.player

import blackjack.domain.cards.Deck
import blackjack.domain.cards.HandCards

class Dealer(private val deck: Deck) {
    private lateinit var _player: Player
    val asPlayer get() = _player

    init {
        deck.shuffle()
    }

    fun provideCard() = deck.draw()

    fun createInitialHand(): Hand = Hand(HandCards(mutableListOf(deck.draw(), deck.draw())))

    fun initHand() {
        _player = Player(
            "딜러",
            hand = createInitialHand()
        )
    }

    fun processTurn(onResult: (Boolean) -> Unit) {
        val addCard = _player.hand.valueSum() <= 16

        if (addCard) _player.addCard(deck.draw())

        onResult(addCard)
    }
}

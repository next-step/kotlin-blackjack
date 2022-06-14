package blackjack.state

import blackjack.Card
import blackjack.PlayerDeck

class Hit(private val playerDeck: PlayerDeck) : State {
    init {
        validate(playerDeck.cards)
    }
    override fun currentCard(): PlayerDeck = playerDeck

    override fun isFinish(): Boolean = false

    override fun draw(card: Card): State {
        playerDeck.addCard(card)

        if (score(playerDeck.cards) > BLACKJACK_NUMBER) {
            return Burst(playerDeck)
        }
        return this
    }

    private fun validate(cards: List<Card>) {
        require(cards.size >= 2) { "카드를 두 장 이상 가지고 있어야 합니다." }
    }
}

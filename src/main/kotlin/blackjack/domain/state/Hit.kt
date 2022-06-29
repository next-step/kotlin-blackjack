package blackjack.domain.state

import blackjack.domain.card.Card
import blackjack.domain.card.PlayerDeck

class Hit(private val playerDeck: PlayerDeck) : State {
    init {
        validate(playerDeck)
    }
    override fun currentCard(): PlayerDeck = playerDeck

    override fun isFinish(): Boolean = false

    override fun draw(card: Card): State {
        playerDeck.addCard(card)

        if (score(playerDeck) > BLACKJACK_NUMBER) {
            return Burst(playerDeck)
        }
        return this
    }

    private fun validate(playerDeck: PlayerDeck) {
        require(playerDeck.cards.size >= 2) { "카드를 두 장 이상 가지고 있어야 합니다." }
    }
}

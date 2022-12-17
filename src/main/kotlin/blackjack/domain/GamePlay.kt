package blackjack.domain

import blackjack.model.Card

interface GamePlay {
    fun readyToPlay(initialCards: List<Card>)
    fun hit(card: Card): Boolean
    fun sumCards(): Int
    fun burst(): Boolean
    fun blackjack(): Boolean
}

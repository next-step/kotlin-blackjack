package blackjack.domain

import blackjack.model.Card

interface Player {
    fun readyToPlay(initialCards: List<Card>)
    fun hit(card: Card): Boolean
    fun sumCards(): Int
    fun burst(): Boolean
    fun blackjack(): Boolean
}

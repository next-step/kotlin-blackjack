package domain.player

import domain.card.Card
import domain.card.Cards

class Player(val name: String, private val hands: Cards = Cards(listOf())) {
    fun drawCard(vararg cards: Card) {
        repeat(cards.size) {
            this.hands.add( cards[it] )
        }
    }

    fun score() : Int = hands.score()

    fun canDraw(): Boolean{
        return hands.score() < WIN_SCORE
    }

    fun handsStatus(): String{
        return hands.toString()
    }

    companion object {
        const val WIN_SCORE = 21
    }
}
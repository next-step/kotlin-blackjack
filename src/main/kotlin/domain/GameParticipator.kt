package domain

abstract class GameParticipator {

    val cards: Cards = Cards()

    fun takeCards(vararg cards: Card) {
        cards.forEach { this.cards.add(it) }
    }

    fun choiceBestScore(): Int = cards.bestScore()


    abstract fun canDrawCard() : Boolean
    
}

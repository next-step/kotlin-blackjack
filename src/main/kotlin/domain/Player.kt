package domain

class Player(val name: PlayerName) {

    val cards: Cards = Cards()

    fun takeCard(card:Card){
        cards.add(card)
    }
}

package blackjack

class Dealer(name: String = "딜러", playingCards: PlayingCards = PlayingCards()) : Player(name, playingCards) {
    fun hitUntil(deck: CardDeck): Int {
        var count = 0
        while(playingCards.calculatePoint() < Point.DEALER_MIN_POINT){
            addCard(deck.deal())
            count++
        }
        return count
    }
}


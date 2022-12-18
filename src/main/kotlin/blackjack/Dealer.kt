package blackjack

class Dealer(playingCards: PlayingCards = PlayingCards()) : Player("딜러", playingCards) {

    fun hitUntil(deck: CardDeck): Int {
        var count = 0
        while(playingCards.calculatePoint() < Point.DEALER_MIN_POINT){
            addCard(deck.deal())
            count++
        }
        return count
    }
}


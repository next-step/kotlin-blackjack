package camp.nextstep.blackjack

class BlackJackGame private constructor(val cardDeck: CardDeck) {

    companion object {
        fun new(): BlackJackGame {
            return BlackJackGame(CardDeck.new())
        }
    }
}

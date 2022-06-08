package domain

class BlackJackGame {

    fun initialIssue() {
        return CardDeck.pop(2)
    }
}
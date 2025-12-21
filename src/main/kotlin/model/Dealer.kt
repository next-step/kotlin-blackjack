package model

class Dealer : Participant(name = "딜러") {
    override fun getPublicCardsOnFirstRound(): Cards {
        return Cards(cards.cards().take(1).toMutableList())
    }
}

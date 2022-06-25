package blackjack.model

class BlackjackGame(initPlayers: Players) {
    private var cards: Cards = Cards.shuffledCards()
    private var players: Players = initPlayers

    init {
        players = players.withAllPlayers {
            val (extractedCards, newCards) = cards.pollCards(Cards.NUMBER_OF_INIT_CARDS)
            cards = newCards
            it.addCards(extractedCards)
        }
    }

    fun isGameOver(): Boolean {
        return players.isAllOver()
    }

    fun playTurn(getHit: (Player) -> Boolean): Player {
        val player = players.findNotOver().first()
        if (getHit(player)) {
            val (extracted, newCards) = cards.pollCards(Cards.NUMBER_OF_GIVE_CARDS)
            cards = newCards
            players = players.hit(player, extracted)
        } else {
            players = players.stay(player)
        }
        return players.find(player.name)!!
    }

    fun <T> withPlayers(f: (Players) -> T): T {
        return f(players)
    }
}

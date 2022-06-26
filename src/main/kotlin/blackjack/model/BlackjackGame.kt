package blackjack.model

class BlackjackGame(initPlayers: Players) {
    private var cards: Cards = Cards.shuffledCards()
    var players: Players = initPlayers
    var dealer: Player = Player.createDealer()

    init {
        players = players.withAllPlayers {
            val (extractedCards, newCards) = cards.pollCards(Cards.NUMBER_OF_INIT_CARDS)
            cards = newCards
            it.addCards(extractedCards)
        }

        dealer = hit(dealer, Cards.NUMBER_OF_INIT_CARDS)
    }

    private fun hit(player: Player, numOfCards: Int = Cards.NUMBER_OF_GIVE_CARDS): Player {
        val (extractedCards, newCards) = cards.pollCards(numOfCards)
        cards = newCards
        return player.addCards(extractedCards)
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

    fun isDealerGameOver(): Boolean {
        return dealer.cards.optimalScore().value > Score.DELAER_HIT_CRITERIA
    }

    fun playDealer(): Player {
        dealer = hit(dealer)
        return dealer
    }

    fun createResults(): Results {
        return Results(players, dealer)
    }

    fun <T> withPlayers(f: (Players) -> T): T {
        return f(players)
    }
}

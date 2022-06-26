package blackjack.model

class BlackjackGame(initPlayers: Players) {
    private var cards: Cards = Cards.shuffledCards()
    var players: Players = initPlayers
    var dealer: Player = Player.createDealer()

    init {
        players = players.withAllPlayers {
            hit(it, Cards.NUMBER_OF_INIT_CARDS)
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
            players = players.update(hit(player))
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
}

package blackjack.domain

class Game(
    val players: Players,
    val deck: Deck = Deck()
) {
    fun getDealer() = Dealer().initialCard(deck)

    fun initialCard(): Players {
        return players.initialCard(deck)
    }

    fun scratchDealer(dealer: Player, printDealerDrawOneMoreCard: () -> Unit): Player {
        return if (dealer.canHit()) {
            printDealerDrawOneMoreCard()
            dealer.hit(deck)
        } else {
            dealer
        }
    }

    fun scratchPlayers(
        players: Players,
        inputScratch: (name: String) -> Boolean,
        printPlayerCards: (player: Player) -> Unit
    ): Players {
        val result = mutableListOf<Player>()
        players.list.map {
            var player = it
            while (player.canHit() && inputScratch(player.name.value)) {
                player = player.hit(deck)
                printPlayerCards(player)
            }
            result.add(player)
        }
        return Players(result)
    }

    companion object {
        const val INITIAL_CARD_COUNT = 2
    }
}

package blackjack.model

class BlackjackState(val players: Players, private val cards: Cards = createShuffledCards()) {

    fun initDeal(): BlackjackState {
        val (newCards, newPlayerList) = players.values.fold(Pair(cards, emptyList<Player>())) { (cards, accPlayers), player ->
            val (extractedCards, newCards) = cards.pollCards(NUMBER_OF_INIT_CARDS)
            val newPlayer = player.addCards(extractedCards)
            Pair(newCards, accPlayers + newPlayer)
        }
        return BlackjackState(Players(newPlayerList), newCards)
    }

    fun findNotOverPlayer(): Player {
        return players.findNotOver().first()
    }

    fun stay(player: Player): BlackjackState {
        return BlackjackState(players.setStay(player), cards)
    }

    fun hit(player: Player): BlackjackState {
        val (extractCards, newCards) = cards.pollCards(NUMBER_OF_GIVE_CARDS)
        return BlackjackState(players.hit(player, extractCards), newCards)
    }

    fun isAllPlayersOver(): Boolean {
        return players.isAllOver()
    }

    fun findPlayer(name: String): Player {
        return players.find(name)!!
    }

    companion object {
        private const val NUMBER_OF_INIT_CARDS = 2
        private const val NUMBER_OF_GIVE_CARDS = 1

        private fun createShuffledCards(): Cards {
            val cardList = CardNumber.values().flatMap { cardNumber ->
                Suit.values().map { suit ->
                    Card(cardNumber, suit)
                }
            }
            return Cards(cardList.shuffled())
        }
    }
}

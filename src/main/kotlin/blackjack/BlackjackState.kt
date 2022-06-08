package blackjack

class BlackjackState(val players: Players, private val cards: Cards = createShuffledCards()) {

    fun giveInitCards(): BlackjackState {
        val (newCards, newPlayerList) = players.values.fold(Pair(cards, emptyList<Player>())) { (cards, accPlayers), player ->
            val (extractedCards, newCards) = cards.pollCards(NUMBER_OF_INIT_CARDS)
            val newPlayer = player.addCards(extractedCards)
            Pair(newCards, accPlayers + newPlayer)
        }
        return BlackjackState(Players(newPlayerList), newCards)
    }

    fun findNotOverPlayers(): List<Player> {
        return players.findNotOver()
    }

    fun setGameOver(player: Player): BlackjackState {
        return BlackjackState(players.setGameOver(player), cards)
    }

    fun giveCard(player: Player): BlackjackState {
        val (extractCards, newCards) = cards.pollCards(NUMBER_OF_GIVE_CARDS)
        return BlackjackState(players.giveCard(player, extractCards), newCards)
    }

    fun isAllPlayersGameOver(): Boolean {
        return players.isAllOver()
    }

    companion object {
        private const val NUMBER_OF_INIT_CARDS = 2
        private const val NUMBER_OF_GIVE_CARDS = 1

        fun createShuffledCards(): Cards {
            val cardList = CardNumber.values().flatMap { cardNumber ->
                Suit.values().map { suit ->
                    Card(cardNumber, suit)
                }
            }
            return Cards(cardList.shuffled())
        }
    }
}

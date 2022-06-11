package camp.nextstep.blackjack

class BlackJackGame private constructor(val cardDeck: CardDeck) {

    private val _participants = mutableListOf<Player>()
    val participants get() = _participants.toList()

    fun participate(player: Player) {
        _participants.add(player)
    }

    companion object {
        fun new(): BlackJackGame {
            return BlackJackGame(CardDeck.new())
        }
    }
}

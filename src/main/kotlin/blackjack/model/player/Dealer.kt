package blackjack.model.player

class Dealer(
    name: PlayerName = PlayerName(DEALER_NAME)
) : Player(name = name) {

    companion object {
        private const val DEALER_NAME = "딜러"
    }
}

package blackjack
import model.AbstractPlayer
import model.Dealer

class BlackJackGame(private val players: List<AbstractPlayer>, val dealer: Dealer) {
    fun start() {
        repeat(START_PICK_COUNT) { players.forEach { players -> players.receive(dealer.draw()) } }
    }

    fun receiveCard(player: AbstractPlayer) {
        player.receive(dealer.draw())
    }

    companion object {
        private const val START_PICK_COUNT = 2
    }
}

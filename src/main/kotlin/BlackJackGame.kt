
import model.Dealer
import model.Player

class BlackJackGame(val players: List<Player>, val dealer: Dealer) {
    fun start() {
        repeat(START_PICK_COUNT) { players.forEach { players -> players.receive(dealer.draw()) } }
    }

    fun receiveCard(player: Player) {
        player.receive(dealer.draw())
    }

    companion object {
        const val START_PICK_COUNT = 2
    }
}

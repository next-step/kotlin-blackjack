package blackjack.domain

class Round(val dealer: Dealer, val player: Player) {

    var status: RoundStatus = RoundStatus.NOT_STARTED
        private set
    fun next() {
        try {
            dealer.dealing(player)
            status = RoundStatus.PROGRESS
        } catch (e: Exception) {
            status = RoundStatus.FINISH
        }
    }

    fun stop() {
        player.stay()
        status = RoundStatus.FINISH
    }
}

enum class RoundStatus {
    NOT_STARTED,
    PROGRESS,
    FINISH,
}

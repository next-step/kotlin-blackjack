package blackjack.domain

open class Player(
    playerName: String,
    val battingAmount: Int,
    playerCards: PlayerCards = PlayerCards()
) : Participant(name = playerName, participantCards = playerCards) {

    override fun isDealer(): Boolean {
        return false
    }

    override fun getEarnAmount(players: List<Player>, dealer: Dealer): Int {
        return GameResult.earnMoney(this, dealer)
    }
}

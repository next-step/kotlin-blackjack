package blackjack.domain.participants

class Players(
    val values: List<Player>
) {
    fun getWinners(dealer: Dealer): Map<String, Boolean> {
        return values.associate {
            it.name to it.isWinner(dealer)
        }
    }
}
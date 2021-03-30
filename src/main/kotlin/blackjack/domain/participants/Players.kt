package blackjack.domain.participants

class Players(
    val values: List<Player>
) {
    fun getPlayersEarnRate(dealer: Dealer): Map<Player, Double> {
        return values.associate {
            it to it.getEarnRate(dealer)
        }
    }
}
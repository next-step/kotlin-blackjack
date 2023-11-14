package blackjack.model

class Participants(
    val players: Players,
    val dealer: Dealer
) {

    fun initialCardDealing() {
        players.initialCardDealing(dealer)
        dealer.initialCardDealing()
    }

    fun makeResult() {
        dealer.compareWithPlayers(players)
    }

    fun processGame(
        moreCardComment: (Boolean) -> Unit,
        hitOrStand: (Player) -> Boolean,
        showCard: (Player) -> Unit
    ) {
        players.values.forEach {
            it.processGame(dealer, hitOrStand, showCard)
        }
        moreCardComment.invoke(dealer.moreCard())
    }
}

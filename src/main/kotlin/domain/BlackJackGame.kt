package domain

object BlackJackGame {

    fun setInitialCards(players: List<Player>) {
        players.forEach {
            val cards = initialIssue()
            it.offer(cards)
        }
    }

    fun isBust(player: Player, isIssued: Boolean): Boolean {
        var isBust = false
        if (isIssued) {
            if (player.getSumOfCards() >= BUST_THRESHOLD_SCORE) isBust = true
        }

        return isBust
    }

    fun endCheck(noCnt: Int, playersSize: Int, isBust: Boolean): Boolean {
        return (noCnt != playersSize || isBust).not()
    }

    fun drawCard(player: Player) {
        player.offer(ordinaryIssue())
    }

    private fun initialIssue(): MutableList<Card> {
        return CardDeck.pop(INITIAL_POP_SIZE)
    }

    private fun ordinaryIssue(): MutableList<Card> {
        return CardDeck.pop(ORDINARY_POP_SIZE)
    }

    fun computeWinner(players: List<Player>) {
        players
            .filterNot { isBustScore(it) }
            .sortedByDescending { it.getSumOfCards() }.get(0).match = Match.WIN
    }

    fun calculateRevenue(players: List<Player>): Map<String, Int> {
        val isBustForDealer = treatDealerHasBustScore(players)
        if(isBustForDealer.not()) {
            treatBustScore(players)
            treatPlayerIsBlackJack(players)
        }

        return players.associate { it.name to it.revenue }
    }

    private fun treatBustScore(players: List<Player>) {
        players.forEach {
            if (isBustScore(it)) {
                it.revenue = it.battingAmount * -1
            }
        }
    }

    private fun treatPlayerIsBlackJack(players: List<Player>) {
        val blackJackWinners = players.filter { isBlackJack(it) }

        if (blackJackWinners.size == players.size) {
            players.forEach { it.revenue = it.battingAmount }
        } else {
            players.forEach { getRevenueForTwoCardsPoint(it) }
        }
    }

    private fun treatDealerHasBustScore(players: List<Player>): Boolean {
        val dealer = players.filter { it.name == DEALER_NAME }.first()
        if (isBustScore(dealer)) {
            players.forEach { it.revenue = it.battingAmount }
            return true
        }

        return false
    }

    private fun getRevenueForTwoCardsPoint(player: Player) {
        if (player.getSumOfCards() == BUST_THRESHOLD_SCORE && player.cards.size == 2) {
            player.revenue = (player.battingAmount * TWO_CARDS_POINT).toInt()
        }
    }

    private fun isBlackJack(player: Player): Boolean {
        return player.getSumOfCards() == BUST_THRESHOLD_SCORE
    }

    private fun isBustScore(player: Player): Boolean {
        return player.getSumOfCards() > BUST_THRESHOLD_SCORE
    }

    private const val BUST_THRESHOLD_SCORE = 21
    private const val INITIAL_POP_SIZE = 2
    private const val ORDINARY_POP_SIZE = 1
    private const val TWO_CARDS_POINT = 1.5
    private const val DEALER_NAME = "딜러"
}

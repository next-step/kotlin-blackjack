package blackjack.domain.player

import blackjack.domain.card.Card
import blackjack.domain.card.CardsDeck

class Dealer {

    val dealer = Participant("딜러")
    private val _resultStatuses = mutableListOf<ResultStatus>()
    val resultStatuses: List<ResultStatus>
        get() {
            return _resultStatuses.toList()
        }

    fun addCardWhenLessThanStandard(
        cardsDeck: CardsDeck,
    ): Card? {
        if (dealer.getCardSum() >= STANDARD) {
            return null
        }

        val card = cardsDeck.divide()
        dealer.addCard(card)

        return card
    }

    fun getMatchResult(): Map<ResultStatus, Int> {
        return resultStatuses
            .groupingBy { it }
            .eachCount()
    }

    fun match(players: List<Player>) {
        val dealerCardSum = dealer.getCardSum()

        if (dealerCardSum > DEADLINE) {
            allLose(players)
            return
        }

        determineWinOrLose(players, dealerCardSum)
    }

    private fun allLose(players: List<Player>) {
        players.forEach { player ->
            _resultStatuses.add(ResultStatus.LOSE)
            player.determineWinOrLose(ResultStatus.WIN)
        }
    }

    private fun determineWinOrLose(players: List<Player>, dealerCardSum: Int) {
        players.forEach { player ->
            val playerCardSum = player.player.getCardSum()

            if (playerCardSum > dealerCardSum) {
                _resultStatuses.add(ResultStatus.LOSE)
                player.determineWinOrLose(ResultStatus.WIN)
            } else if (playerCardSum < dealerCardSum) {
                _resultStatuses.add(ResultStatus.WIN)
                player.determineWinOrLose(ResultStatus.LOSE)
            } else {
                _resultStatuses.add(ResultStatus.TIE)
                player.determineWinOrLose(ResultStatus.TIE)
            }
        }
    }

    companion object {
        private const val STANDARD = 17
        private const val DEADLINE = 21
    }
}

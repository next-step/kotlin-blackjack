package model

import view.InputView
import view.ResultView

class CardVendor {
    private val inputView = InputView()
    private val resultView = ResultView()

    private fun dealOut(players: Players, name: String) {
        for (i in DEAL_OUT) {
            hit(players, name)
        }
    }

    private fun hit(players: Players, name: String) {
        players.updateCard(name, Card.generate())
    }

    private fun findUsersWhoNeedExtraCard(
        players: Players,
        cardNumberCalculator: CardNumberCalculator
    ): Map<String, List<Card>> {
        return players.get().filter {
            cardNumberCalculator.isGetExtraCard(it.value)
        }
    }

    fun checkExtraCard(players: Players, cardNumberCalculator: CardNumberCalculator) {
        findUsersWhoNeedExtraCard(players, cardNumberCalculator).forEach {
            if (inputView.getExtraCard(it.key)) {
                hit(players, it.key)
                resultView.showSpecificUserCardState(it.key, it.value)
            }
        }
    }

    fun giveCardToPlayer(players: Players, names: List<String>) {
        names.forEach {
            dealOut(players, it)
        }
    }

    companion object {
        private val DEAL_OUT = 0..1
    }
}

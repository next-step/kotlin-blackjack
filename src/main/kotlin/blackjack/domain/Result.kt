package blackjack.domain

import kotlin.math.abs

class Result(private val blackJackGame: BlackJackGame) {
    private val dealerScore = blackJackGame.dealerCards.score()
    private val dealer = blackJackGame.dealer
    private val normalPlayer = blackJackGame.normalPlayer

    fun getGameResult(): List<Participant> {
        if (dealerScore > BLACK_JACK_SCORE) {
            setDealerWinGameResult()
        } else {
            setGameResult()
        }
        return blackJackGame.players
    }

    private fun setDealerWinGameResult() {
        dealer.addStatus(List(normalPlayer.size) { GameResult.WIN })
        normalPlayer.forEach {
            it.setLose()
        }
    }

    private fun setGameResult() {
        normalPlayer.forEach {
            it.setResult()
        }
    }

    private fun Participant.setLose() {
        addStatus(listOf(GameResult.LOSE))
    }

    private fun Participant.setResult() {
        if (abs(BLACK_JACK_SCORE - playerCards.score()) < BLACK_JACK_SCORE - dealerScore) {
            addStatus(listOf(GameResult.WIN))
            dealer.addStatus(listOf(GameResult.LOSE))
        } else if (playerCards.score() == dealerScore) {
            addStatus(listOf(GameResult.DRAW))
            dealer.addStatus(listOf(GameResult.DRAW))
        } else {
            addStatus(listOf(GameResult.LOSE))
            dealer.addStatus(listOf(GameResult.WIN))
        }
    }

    companion object {
        private const val BLACK_JACK_SCORE = 21
    }
}

enum class GameResult() {
    WIN, LOSE, DRAW
}

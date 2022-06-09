package blackjack.domain

import kotlin.math.abs

data class BlackJackGame(
    val players: List<Participant>,
    val cardDeck: Deck
) {
    private val playerMap = players.associate { it.name to it.playerCards }
    val dealer = players.first { it.isDealer }
    val normalPlayer = players.filter { !it.isDealer }
    val dealerCards = dealer.playerCards
    private val dealerScore = dealerCards.score()

    fun firstCardDistribution() {
        players.forEach { participant ->
            participant.addFirstCard()
        }
    }

    fun drawTo(playerName: String) {
        playerMap[playerName]!!.addCard(cardDeck.draw())
    }

    fun drawToDealer() {
        dealer.addCard(cardDeck.draw())
    }

    fun getGameResult(): List<Participant> {
        if (dealerScore > BLACK_JACK_SCORE) {
            setDealerWinGameResult()
        } else {
            setGameResult()
        }
        return players
    }

    private fun setDealerWinGameResult() {
        dealer.addGameResult(List(normalPlayer.size) { GameResult.WIN })
        normalPlayer.forEach {
            it.setLose()
        }
    }

    private fun setGameResult() {
        normalPlayer.forEach {
            it.setGameResult()
        }
    }

    private fun Participant.setLose() {
        addGameResult(listOf(GameResult.LOSE))
    }

    private fun Participant.setGameResult() {
        if (abs(BLACK_JACK_SCORE - playerCards.score()) < BLACK_JACK_SCORE - dealerScore) {
            addGameResult(listOf(GameResult.WIN))
            dealer.addGameResult(listOf(GameResult.LOSE))
        } else if (playerCards.score() == dealerScore) {
            addGameResult(listOf(GameResult.DRAW))
            dealer.addGameResult(listOf(GameResult.DRAW))
        } else {
            addGameResult(listOf(GameResult.LOSE))
            dealer.addGameResult(listOf(GameResult.WIN))
        }
    }

    private fun Participant.addFirstCard() {
        repeat(FIRST_DISTRIBUTION_CARD_COUNT) {
            addCard(cardDeck.draw())
        }
    }

    companion object {
        fun of(players: List<Participant>, deck: Deck): BlackJackGame {
            return BlackJackGame(
                players, deck
            )
        }

        private const val BLACK_JACK_SCORE = 21
        private const val FIRST_DISTRIBUTION_CARD_COUNT = 2
    }
}

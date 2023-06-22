package blackjack.controller

import blackjack.model.BlackjackDealer
import blackjack.model.BlackjackPlayer
import blackjack.model.BlackjackScoreJudge
import blackjack.model.CardDeck
import blackjack.model.TrumpCard
import blackjack.model.TrumpCardNumber
import blackjack.model.TrumpCardShape
import blackjack.view.InputView
import blackjack.view.OutputView

object BlackjackGame {

    private val ALL_TRUMP_CARDS: CardDeck =
        TrumpCardShape.values().flatMap { shape -> TrumpCardNumber.values().map { TrumpCard(shape, it) } }
    private const val INITIAL_DEALING_COUNT: Int = 2

    private val DEALER_CARD_SELECTOR: (CardDeck) -> TrumpCard = { it.random() }

    private const val BLACKJACK_SCORE_LIMIT = 21
    private val BLACKJACK_SCORE_JUDGE = BlackjackScoreJudge(BLACKJACK_SCORE_LIMIT)

    fun start() {
        val players: Collection<BlackjackPlayer> = InputView.players.map { BlackjackPlayer(it) }
        val dealer = BlackjackDealer(ALL_TRUMP_CARDS, DEALER_CARD_SELECTOR)

        dealInitialCount(players, dealer)
            .map { dealMoreCard(it, dealer) }
            .map { OutputView.printPlayerResult(it, BLACKJACK_SCORE_JUDGE.score(it.deck)) }
    }

    private fun dealInitialCount(
        players: Collection<BlackjackPlayer>,
        dealer: BlackjackDealer,
    ): Collection<BlackjackPlayer> {
        var dealtPlayers = players
        repeat(INITIAL_DEALING_COUNT) {
            dealtPlayers = dealtPlayers.map { it.addedCard(dealer.drawCardAndRemoved()) }
        }
        OutputView.printInitialDealing(dealtPlayers, INITIAL_DEALING_COUNT)
        return dealtPlayers
    }

    private fun dealMoreCard(player: BlackjackPlayer, dealer: BlackjackDealer): BlackjackPlayer {
        var currentPlayer: BlackjackPlayer = player
        var isReceivedMoreCard = false
        while (isLessScoreThanLimit(currentPlayer) && InputView.isWantedMoreCard(currentPlayer.name)) {
            currentPlayer = currentPlayer.addedCard(dealer.drawCardAndRemoved())
            OutputView.printPlayerCards(currentPlayer)
            isReceivedMoreCard = true
        }
        if (!isReceivedMoreCard) {
            OutputView.printPlayerCards(currentPlayer)
        }
        return currentPlayer
    }

    private fun isLessScoreThanLimit(player: BlackjackPlayer): Boolean =
        BLACKJACK_SCORE_JUDGE.score(player.deck) < BLACKJACK_SCORE_LIMIT
}

fun main() {
    BlackjackGame.start()
}

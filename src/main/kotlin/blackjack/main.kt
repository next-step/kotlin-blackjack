package blackjack

import blackjack.domain.card.CardDeck
import blackjack.domain.card.RandomCardDeck
import blackjack.domain.game.BlackJack
import blackjack.domain.player.Dealer
import blackjack.domain.player.Player
import blackjack.domain.score.PlayerScore
import blackjack.domain.score.Score
import blackjack.domain.winning.RevenueCalculator
import blackjack.domain.winning.WinningStat
import blackjack.dto.BlackJackRequest
import blackjack.view.GameView
import blackjack.view.InputView
import blackjack.view.ResultView
import blackjack.view.RevenueView

fun main() {
    val inputView = InputView()
    val resultView = ResultView()

    val inputPlayers = inputView.inputPlayers()
    inputView.inputBetting(inputPlayers)

    startBlackJack(inputView, resultView, inputPlayers)
}

fun startBlackJack(
    inputView: InputView,
    resultView: ResultView,
    inputPlayers: List<String>
) {
    val dto: BlackJackRequest = BlackJackRequest.of(inputView.inputPlayerAndBetting)
    val cardDeck: CardDeck = RandomCardDeck()
    resultView.players(inputPlayers)
    val blackJack = BlackJack(dto, cardDeck)

    val players: List<Player> = dto.players
    val dealer: Dealer = dto.dealer

    val gameView = GameView(blackJack, dealer, players)
    gameView.firstRoundState()
    gameView.run()

    calculateScore(resultView, players, dealer)
}

fun calculateScore(resultView: ResultView, players: List<Player>, dealer: Dealer) {
    val scores: List<PlayerScore> = players.map { player -> Score.calculatePlayerScore(player) }
    val dealerScore: PlayerScore = Score.calculatePlayerScore(dealer)
    resultView.dealerScore(dealerScore)
    resultView.playerScore(scores)

    revenue(WinningStat(scores, dealerScore))
}

fun revenue(winingStat: WinningStat) {
    val revenueCalculator = RevenueCalculator(winingStat)
    val a = revenueCalculator.calculate()
    val revenueView = RevenueView(a)
    revenueView.title()
    revenueView.indicator()
}

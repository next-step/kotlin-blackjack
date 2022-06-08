package blackjack

import blackjack.domain.card.CardDeck
import blackjack.domain.card.CardDeckImpl
import blackjack.domain.game.BlackJack
import blackjack.domain.player.Player
import blackjack.domain.score.Score
import blackjack.dto.BlackJackRequest
import blackjack.view.GameView
import blackjack.view.InputView
import blackjack.view.ResultView

fun main() {
    val inputView = InputView()
    val resultView = ResultView()

    val inputPlayers = inputView.inputPlayers()
    resultView.players(inputPlayers)

    startBlackJack(resultView, inputPlayers)
}

fun startBlackJack(resultView: ResultView, inputPlayers: List<String>) {
    val dto: BlackJackRequest = BlackJackRequest.of(inputPlayers)
    val cardDeck: CardDeck = CardDeckImpl()
    val blackJack = BlackJack(dto, cardDeck)

    val players: List<Player> = dto.players
    val gameView = GameView(blackJack, players)
    gameView.firstRoundState()
    gameView.run()

    score(resultView, players)
}

fun score(resultView: ResultView, players: List<Player>) {
    val score = Score(players)
    score.run()
    resultView.score(score.playerScore)
}

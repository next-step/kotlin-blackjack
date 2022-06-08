package blackjack

import blackjack.domain.BlackJack
import blackjack.domain.CardDeck
import blackjack.domain.CardDeckImpl
import blackjack.domain.Player
import blackjack.domain.Score
import blackjack.dto.BlackJackRequest
import blackjack.view.GameView
import blackjack.view.InputView
import blackjack.view.ResultView

fun main() {
    val inputView = InputView()
    val resultView = ResultView()

    val inputPlayers = inputView.inputPlayers()
    resultView.players(inputPlayers)

    val dto: BlackJackRequest = BlackJackRequest.of(inputPlayers)
    val cardDeck: CardDeck = CardDeckImpl()
    val blackJack = BlackJack(dto, cardDeck)

    val players: List<Player> = dto.players
    val gameView = GameView(inputView, blackJack, players)
    gameView.firstRoundState()
    gameView.run()

    val score = Score(players)
    score.run()
    resultView.score(score.playerScore)
}

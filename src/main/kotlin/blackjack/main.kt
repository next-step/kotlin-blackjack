package blackjack

import blackjack.domain.BlackJack
import blackjack.domain.CardDeck
import blackjack.domain.CardDeckImpl
import blackjack.domain.Player
import blackjack.dto.BlackJackRequest
import blackjack.view.InputView
import blackjack.view.ResultView

fun main() {
    val inputView = InputView()
    val resultView = ResultView()

    val inputPlayers = inputView.inputPlayers()
    resultView.players(inputPlayers)

    val dto: BlackJackRequest = BlackJackRequest.of(inputPlayers)
    val cardDeck: CardDeck = CardDeckImpl()
    BlackJack(dto, cardDeck)

    val players: List<Player> = dto.players
    resultView.firstRoundState(players)
}

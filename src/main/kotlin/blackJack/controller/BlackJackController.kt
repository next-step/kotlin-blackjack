package blackJack.controller

import blackJack.domain.CardDeck
import blackJack.domain.Cards
import blackJack.domain.Dealer
import blackJack.domain.Player
import blackJack.domain.Players
import blackJack.dto.PlayerDto
import blackJack.dto.PlayersDto
import blackJack.view.InputView
import blackJack.view.OutputView

fun main() {
    val cardDeck = CardDeck.createShuffledDeck()
    val dealer = Dealer(cardDeck)

    OutputView.printEnterName()
    val inputNames = InputView.inputNames()
    val playerList = Player.splitNames(inputNames)
    OutputView.printPlayer(playerList)

    val players: Players = Players.createPlayers(playerList)
    val initialCards = dealer.initialCards(playerList.size)
    players.receiveInitialCards(initialCards)

    val playersDto = PlayersDto(players)
    OutputView.printPlayerCards(playersDto)

    val finishGamePlayers = players.players
        .map {
            val player = playGame(it, dealer.cardDeck)
            PlayerDto(player, player.getTotalScore())
        }
    val playersResult = PlayersDto(finishGamePlayers)
    OutputView.printResult(playersResult)
}

private fun playGame(player: Player, cardDeck: Cards): Player {
    var answer = promptForAction(player)

    while (player.isFinished(answer)) {
        player.addCard(cardDeck)
        OutputView.printPlayerCard(PlayerDto(player))
        answer = promptForAction(player)
    }

    OutputView.printPlayerCard(PlayerDto(player))
    return player
}

private fun promptForAction(player: Player): String {
    val playerDto = PlayerDto(player)
    OutputView.printQuestionYesOrNo(playerDto)
    return InputView.answerYesOrNo()
}

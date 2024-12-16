package blackjack

import blackjack.domain.BetInfo
import blackjack.domain.BlackJackGame
import blackjack.domain.BlackJackGame.DEFAULT_FACE_UP
import blackjack.domain.BlackJackGame.INIT_FACE_UP
import blackjack.domain.Deal
import blackjack.entity.Dealer
import blackjack.entity.Game
import blackjack.entity.Player
import blackjack.view.InputView
import blackjack.view.OutputView

fun main() {
    val playersList = InputView.getPlayers()
    val dealerName = InputView.getDealerName()
    initBlackJack(dealerName, playersList)

    gameContinue()
    gameCardResult()
    gameEnd()
}

fun gameContinue() {
    val game = BlackJackGame.getGameInfo()
    val playersList = game.players
    val dealer = game.dealer
    val isLessThanSixTeen = dealer.hand.isLessThanSixteen()
    playersList.forEach { player ->
        playerGameProgress(player)
    }
    if (isLessThanSixTeen) {
        dealerGameProgress(dealer.name)
    }
}

private fun initBlackJack(
    dealerName: String,
    playersName: List<String>,
) {
    val dealer = initDealer(dealerName)
    val betInfos =
        playersName.map { playerName ->
            BetInfo(
                playerName,
                InputView.getBetAmount(playerName),
            )
        }
    val players = initPlayers(betInfos)
    val game = Game(dealer, players)
    OutputView.gameStart(game)
    BlackJackGame.setGameRepository(game)
}

private fun initDealer(dealerName: String): Dealer {
    val initDealerCard =
        (Deal.giveCards(DEFAULT_FACE_UP, false) + Deal.giveCards(DEFAULT_FACE_UP, true))
    return Dealer(dealerName, initDealerCard)
}

private fun initPlayers(betInfos: List<BetInfo>): Set<Player> {
    return betInfos.map { Player(it.name, it.betAmount, Deal.giveCards(INIT_FACE_UP)) }.toSet()
}

private fun gameCardResult() {
    val game = BlackJackGame.getGameInfo()
    OutputView.gameCardResult(game)
}

private fun gameProgress(playerName: String): Boolean {
    val player = BlackJackGame.gameContinue(playerName)
    InputView.playerInfo(player)
    return player.hand.isBust()
}

private fun playerGameProgress(player: Player) {
    while (InputView.isGameContinue(player.name)) {
        val isBust = gameProgress(player.name)
        if (isBust) break
    }
}

private fun dealerGameProgress(dealerName: String) {
    InputView.dealerAddCardComment(dealerName)
    BlackJackGame.gameContinueDealer()
}

fun gameEnd() {
    val results = BlackJackGame.getGameResult()
    OutputView.results(results)
}

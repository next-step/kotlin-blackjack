package camp.nextstep.edu.step.step2

import camp.nextstep.edu.step.step2.domain.card.Card
import camp.nextstep.edu.step.step2.domain.card.CardDeck
import camp.nextstep.edu.step.step2.domain.card.Cards
import camp.nextstep.edu.step.step2.domain.player.Player
import camp.nextstep.edu.step.step2.dto.BlackJackResultDto
import camp.nextstep.edu.step.step2.dto.CardDistributionDto
import camp.nextstep.edu.step.step2.dto.EnterPlayerDto
import camp.nextstep.edu.step.step2.dto.PlayerCardDto
import camp.nextstep.edu.step.step2.view.InputView
import camp.nextstep.edu.step.step2.view.OutputView


fun main() {
    val blackJackPlayers = startBlackJack()

    val players = blackJackGameProcess(enterPlayers = blackJackPlayers)

    blackJackGameResult(players = players)
}

private fun startBlackJack(): List<EnterPlayerDto> {
    val players = InputView.enterPlayers()
    return players.map { EnterPlayerDto(it) }
}

private fun firstDrawTwoCards(): List<Card> {
    val cardList = mutableListOf<Card>()
    cardList.add(CardDeck.DrawCard().draw())
    cardList.add(CardDeck.DrawCard().draw())
    return cardList
}

private fun blackJackGameProcess(enterPlayers: List<EnterPlayerDto>): List<Player> {
    val players = enterPlayers.stream()
        .map { player -> Player(player.name, Cards(cards = firstDrawTwoCards())) }
        .toList()

    drawFirstCard(players = players)

    players.forEach { drawCardByPlayerAgree(player = it) }

    return players
}

private fun drawFirstCard(players: List<Player>) {
    val playerNames = players.map { it.name }.toList()

    OutputView.displayInitialPlayer(
        cardDistributionDto = CardDistributionDto(
            playerNames = playerNames,
            initialCardCount = players.size
        )
    )

    for (player in players) {
        OutputView.disPlayPlayerCard(
            PlayerCardDto(name = player.name, cards = player.getPlayerCards())
        )
    }
}

private fun drawCardByPlayerAgree(player: Player): Player {
    while(isObtainCard(player)) {
        player.drawCard()

        OutputView.disPlayPlayerCard(
            PlayerCardDto(name = player.name, cards = player.getPlayerCards())
        )
    }
    return player
}

private fun isObtainCard(player: Player): Boolean {
    return player.isObtainable() && InputView.recommandDrawDisplay(player.name)
}

private fun blackJackGameResult(players: List<Player>) {
    for (player in players) {
        val playerCards = player.getPlayerCards()
        val playerScore = player.sumOfCards()

        OutputView.displayBlackJackResult(
            BlackJackResultDto(
                playerName = player.name,
                playerCards = playerCards,
                playerScore = playerScore,
            )
        )
    }
}

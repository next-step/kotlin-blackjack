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

/**
 * @description : 블랙잭 게임을 진행한다.
 */
fun main() {
    // 1. 게임에 참여하는 사람들의 이름을 입력받는다.
    val blackJackPlayers = startBlackJack()

    // 2. 블랙잭 게임 프로세스를 진행한다.
    val players = blackJackGameProcess(enterPlayers = blackJackPlayers)

    // 3. 게임을 진행한 플레이어들을 대상으로 결과를 진행한다.
    blackJackGameResult(players = players)
}

/**
 * @description : 블랙잭 게임을 시작한다. 사람들의 이름을 입력받는다.
 */
private fun startBlackJack(): List<EnterPlayerDto> {
    val players = InputView.enterPlayers()
    return players.map { EnterPlayerDto(it) }
}

/**
 * @description : 최초 카드 덱에서 두장의 카드를 뽑는다.
 */
private fun firstDrawTwoCards(): List<Card> {
    val cardList = mutableListOf<Card>()
    cardList.add(CardDeck.DrawCard().draw())
    cardList.add(CardDeck.DrawCard().draw())
    return cardList
}

/**
 * @description : 블랙잭 게임을 진행한다.
 */
private fun blackJackGameProcess(enterPlayers: List<EnterPlayerDto>): List<Player> {
    // 1. 플레이어로 참여하는 사람들만큼의 카드를 생성하고 각 플레이어에게 카드를 2장씩 나눠준다.
    val players = enterPlayers.stream()
        .map { player -> Player(player.name, Cards(cards = firstDrawTwoCards())) }
        .toList()

    // 2. 각 플레이어의 첫 드로우 카드를 출력한다.
    drawFirstCard(players = players)

    // 3. 각 플레이어의 블랙잭 게임을 실행한다.
    players.forEach { drawCardByPlayerAgree(player = it) }

    return players
}

/**
 * @description : 최초 각 플레이어는 카드를 뽑는다.
 */
private fun drawFirstCard(players: List<Player>) {
    val playerNames = players.map { it.name }.toList()

    OutputView.displayInitialPlayer(
        cardDistributionDto = CardDistributionDto(
            playerNames = playerNames,
            initialCardCount = players.size
        )
    )
    // 2. 최초 각 플레이어의 카드를 출력한다.
    for (player in players) {
        OutputView.disPlayPlayerCard(
            PlayerCardDto(name = player.name, cards = player.getPlayerCards())
        )
    }
}

/**
 * @description : 플레이어가 카드를 더 받을지 결정하고 받는다면 카드를 더 받는다.
 */
private fun drawCardByPlayerAgree(player: Player): Player {
    while(isObtainCard(player)) {
        player.drawCard()

        OutputView.disPlayPlayerCard(
            PlayerCardDto(name = player.name, cards = player.getPlayerCards())
        )
    }
    return player
}

/**
 * @description : 플레이어가 카드를 더 받을지 결정한다.
 */
private fun isObtainCard(player: Player): Boolean {
    return player.isObtainable() && InputView.recommandDrawDisplay(player.name)
}

/**
 * @description : 게임에 참여한 대상을 토대로 결과를 출력한다.
 */
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

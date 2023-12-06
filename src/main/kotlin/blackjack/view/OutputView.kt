package blackjack.view

import blackjack.domain.Cards
import blackjack.domain.Deck
import blackjack.domain.GameResult
import blackjack.domain.Participants
import blackjack.domain.Player
import blackjack.domain.PlayerGameResult
import java.util.EnumMap

object OutputView {
    fun printDealingHeader(playerNames: String) {
        println("\n딜러와 ${playerNames}에게 ${Deck.INITIAL_DEAL_SIZE}장의 나누었습니다.")
    }

    fun printParticipantCards(participants: Participants) {
        printDealerCard(participants.dealer.showCards())
        participants.players.forEach {
            printPlayerCards(it)
        }
        println()
    }

    private fun printDealerCard(cards: Cards) {
        println("딜러 카드: ${cards.values.first()}")
    }

    fun printPlayerCards(player: Player) {
        println("${player.name}카드: ${player.state.cards}")
    }

    fun printGameScore(participants: Participants) {
        participants.dealer.let {
            print("\n딜러 카드: ${it.state.cards} - 결과: ${it.getScore().value}")
        }
        participants.players.forEach {
            print("\n${it.name}카드: ${it.state.cards} - 결과: ${it.getScore().value}")
        }
        println("\n")
    }

    fun printPlayerGameResult(playerGameResult: List<PlayerGameResult>) {
        playerGameResult.forEach {
            println("${it.name}: ${it.gameResult.message}")
        }
    }

    fun printDealerGameResult(dealerGameResult: EnumMap<GameResult, Int>) {
        print("딜러: ")
        dealerGameResult.entries.forEach {
            print("${it.value}${it.key.message} ")
        }
        println()
    }

    fun printDealerReceiveMessage() {
        println("\n딜러는 16이하라 한장의 카드를 더 받았습니다.")
    }
}

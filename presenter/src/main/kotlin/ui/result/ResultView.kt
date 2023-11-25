package ui.result

import action.BlackJackAction
import blackjack.BlackjackParticipant
import blackjack.card.Card
import blackjack.dealer.Dealer
import blackjack.game.BlackjackGame
import blackjack.game.BlackjackResult
import blackjack.player.Player
import toUiString

class ResultView {

    fun showCards(cardOwner: String, cards: List<Card>) {
        println("${cardOwner}카드: ${cards.joinToString(", ") { it.toUiString() }}")
    }

    fun showCards(participant: BlackjackParticipant) {
        when (participant) {
            is Dealer -> println("딜러 카드: ${participant.cards.joinToString(", ") { it.toUiString() }}")
            is Player -> println("${participant.name} 카드: ${participant.cards.joinToString(", ") { it.toUiString() }}")
        }
    }

    fun showCards(players: List<Player>) {
        players.forEach(::showCards)
        println()
    }

    fun showDealerTurn(dealerAction: BlackJackAction) {
        val message = if (dealerAction == BlackJackAction.HIT) {
            "딜러는 16 이하라 1장 더 받습니다."
        } else {
            "딜러는 16을 초과하여 받지 않습니다."
        }
        println(message)
    }

    fun showInitialDealMessage(players: List<Player>) {
        println("딜러와 ${players.joinToString(", "){ it.name }}에게 2장의 카드를 나누었습니다.")
    }

    fun showParticipantsRecord(blackjackGame: BlackjackGame, result: Map<BlackjackParticipant, BlackjackResult>) {
        println()
        println("## 최종 승패")
        showDealerRecord(result[blackjackGame.dealer] ?: throw IllegalArgumentException())
        blackjackGame.players.forEach {
            showPlayerRecord(it, result[it] ?: throw IllegalArgumentException())
        }
    }
    private fun showDealerRecord(dealerResult: BlackjackResult) {
        println("딜러: ${dealerResult.win}승 ${dealerResult.lose}패")
    }

    private fun showPlayerRecord(player: Player, playerResult: BlackjackResult) {
        val winOrLose = if (playerResult.win == 1) "승" else "패"
        println("${player.name}: $winOrLose")
    }
}

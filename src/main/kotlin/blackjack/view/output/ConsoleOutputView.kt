package blackjack.view.output

import blackjack.model.Config
import blackjack.model.PlayRoom
import blackjack.model.card.Card
import blackjack.model.card.CardShape
import blackjack.model.player.Player
import blackjack.model.player.PlayerRecord
import blackjack.model.player.PlayerRecords

class ConsoleOutputView : OutputView {

    override fun printInitialMessage(playRoom: PlayRoom) {
        val dealerName = playRoom.dealer.name
        val playerNames = playRoom.guests.joinToString(",") { it.name }
        println("${dealerName}와 ${playerNames}에게 ${Config.INITIAL_CARD_COUNT_OF_PLAYER}장씩 카드를 나누었습니다.")
        this.printCardsOfPlayer(playRoom)
    }

    override fun onPlayerHit(player: Player) {
        when (player) {
            is Player.Guest -> printCardsOfGuest(player, false)
            is Player.Dealer -> println("${player.name}는 ${Config.MAX_SCORE_FOR_DEALER_CAN_HIT}이하라 한장의 카다를 더 받았습니다.")
        }
    }

    override fun printCardsOfPlayer(player: Player, withScore: Boolean) {
        when (player) {
            is Player.Guest -> printCardsOfGuest(player, withScore)
            is Player.Dealer -> printCardsOfDealer(player, withScore)
        }
    }

    override fun printPlayerRecords(playerRecords: PlayerRecords) {
        val dealerRecord = playerRecords.find { it.player is Player.Dealer } ?: return
        val guestsRecord = playerRecords.filter { it.player is Player.Guest }

        println("## 최종 승패 ")
        printDealerRecord(dealerRecord)
        guestsRecord.forEach {
            printGuestRecord(it)
        }
    }

    private fun printDealerRecord(playerRecord: PlayerRecord) {
        val recordString = StringBuilder()
        if (playerRecord.win != 0) {
            recordString.append("${playerRecord.win}승 ")
        }
        if (playerRecord.lost != 0) {
            recordString.append("${playerRecord.lost}패 ")
        }
        if (playerRecord.draw != 0) {
            recordString.append("${playerRecord.lost}무 ")
        }

        println("${playerRecord.player.name} : $recordString")
    }

    private fun printGuestRecord(playerRecord: PlayerRecord) {
        print("${playerRecord.player.name} : ")
        when {
            playerRecord.win != 0 -> println("승")
            playerRecord.lost != 0 -> println("패")
            playerRecord.draw != 0 -> println("무")
        }
    }

    private fun printCardsOfGuest(player: Player, withScore: Boolean) {
        print("${player.name} 카드: ")
        print(player.cards.joinToString(", ") { it.displayName })
        if (withScore) {
            print("  - 결과 : :${player.state.finalScore}")
        }
        println()
    }

    private fun printCardsOfDealer(player: Player.Dealer, withScore: Boolean) {
        if (withScore) {
            printCardsOfGuest(player, true)
            return
        }

        print("${player.name}: ")

        val firstCard = player.cards.first()
        val cardsExceptFirst = player.cards.filter { it != firstCard }
        print(cardsExceptFirst.joinToString(", ") { it.displayName })
        println()
    }

    companion object {
        private val CardShape.displayName: String
            get() = when (this) {
                CardShape.SPADES -> "스페이드"
                CardShape.DIAMONDS -> "다이아몬드"
                CardShape.HEARTS -> "하트"
                CardShape.CLUBS -> "클로버"
            }

        val Card.displayName: String
            get() = "${denomination.displayName}${shape.displayName}"
    }
}

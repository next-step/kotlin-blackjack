package blackjack.view.output

import blackjack.model.CardDistributor
import blackjack.model.PlayRoom
import blackjack.model.card.Card
import blackjack.model.card.CardShape
import blackjack.model.player.MAX_SCORE_FOR_DEALER_CAN_HIT
import blackjack.model.player.Player
import blackjack.model.player.PlayerRecord
import blackjack.model.player.PlayerRecords

class ConsoleOutputView : OutputView {

    override fun printInitialMessage(playRoom: PlayRoom) {
        val dealerName = playRoom.dealer.name
        val playerNames = playRoom.guests.joinToString(",") { it.name }
        val initialCardCountForEachPlayer = CardDistributor.INITIAL_CARD_COUNT_FOR_EACH_PLAYER

        println("${dealerName}와 ${playerNames}에게 ${initialCardCountForEachPlayer}장씩 카드를 나누었습니다.")
        this.printCardsOfPlayRoom(playRoom, isGameOver = false)
    }

    override fun onPlayerHit(player: Player) {
        when (player) {
            is Player.Guest -> printCardsOfGuest(player, isGameOver = false)
            is Player.Dealer -> println("${player.name}는 ${MAX_SCORE_FOR_DEALER_CAN_HIT}이하라 한장의 카드를 더 받았습니다.")
        }
    }

    override fun printCardsOfPlayer(player: Player, isGameOver: Boolean) {
        when (player) {
            is Player.Guest -> printCardsOfGuest(player, isGameOver = isGameOver)
            is Player.Dealer -> printCardsOfDealer(player, isGameOver = isGameOver)
        }
    }

    override fun printPlayerRecords(playerRecords: PlayerRecords) {
        println("## 최종 수익 ")
        playerRecords.forEach(this::printPlayerRecord)
    }

    private fun printPlayerRecord(playerRecord: PlayerRecord) {
        println("${playerRecord.player.name} :  ${playerRecord.earnMoneyString}")
    }

    private fun printCardsOfGuest(player: Player, isGameOver: Boolean) {
        printAllCardsOfPlayer(player, withScore = isGameOver)
    }

    private fun printCardsOfDealer(player: Player, isGameOver: Boolean) {
        if (isGameOver) {
            printAllCardsOfPlayer(player, withScore = true)
            return
        }

        print("${player.name}: ")
        val firstCard = player.cards.first()
        val cardsExceptFirst = player.cards.filter { it != firstCard }
        print(cardsExceptFirst.joinToString(", ") { it.displayName })
        println()
    }

    private fun printAllCardsOfPlayer(player: Player, withScore: Boolean) {
        print("${player.name} 카드: ")
        print(player.cards.joinToString(", ") { it.displayName })
        if (withScore) {
            print("  - 결과 : :${player.state.finalScore}")
        }
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

        private val PlayerRecord.earnMoneyString: String
            get() = this.earnMoney.toString()
    }
}

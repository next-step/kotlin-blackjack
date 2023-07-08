package blackjack.domain

import blackjack.domain.gamer.BlackJackGamer
import blackjack.domain.gamer.Dealer
import blackjack.domain.gamer.GameRecordType
import blackjack.domain.gamer.GamerType
import blackjack.domain.gamer.Player

class RuleChecker {

    fun checkSumOfCardNumbers(blackJackGamer: BlackJackGamer): Boolean {
        return blackJackGamer.calculateSumOfCardNumbers() <= proceedDrawCondition(blackJackGamer.getGamerType())
    }

    private fun proceedDrawCondition(gamerType: GamerType): Int {
        if (gamerType == GamerType.PLAYER) return CONDITION_TO_WIN_BLACK_JACK
        return Dealer.CONDITION_TO_DEALER_DRAW_CARD
    }

    fun proceedWhoIsWinner(player: Player, dealer: Dealer) {
        if (dealer.calculateSumOfCardNumbers() > CONDITION_TO_WIN_BLACK_JACK) {
            playerIsWinner(player, dealer)
            return
        }

        if (player.calculateSumOfCardNumbers() > CONDITION_TO_WIN_BLACK_JACK) {
            dealerIsWinner(player, dealer)
            return
        }

        if (player.calculateSumOfCardNumbers() > dealer.calculateSumOfCardNumbers()) {
            playerIsWinner(player, dealer)
            return
        }

        if (player.calculateSumOfCardNumbers() < dealer.calculateSumOfCardNumbers()) {
            dealerIsWinner(player, dealer)
            return
        }

        if (player.calculateSumOfCardNumbers() == dealer.calculateSumOfCardNumbers()) {
            noOneIsWinner(player, dealer)
            return
        }
    }

    private fun playerIsWinner(player: Player, dealer: Dealer) {
        player.proceedGameRecord(GameRecordType.WIN)
        dealer.proceedGameRecord(GameRecordType.LOSE)
        if (player.calculateSumOfCardNumbers() == CONDITION_TO_WIN_BLACK_JACK) {
            player.blackJackMoney()
        }
    }

    private fun dealerIsWinner(player: Player, dealer: Dealer) {
        player.proceedGameRecord(GameRecordType.LOSE)
        dealer.proceedGameRecord(GameRecordType.WIN)
        val playerLoseMoney = player.loseMoney()
        dealer.winMoney(playerLoseMoney)
    }

    private fun noOneIsWinner(player: Player, dealer: Dealer) {
        player.proceedGameRecord(GameRecordType.DRAW)
        dealer.proceedGameRecord(GameRecordType.DRAW)
    }

    companion object {
        const val CONDITION_TO_WIN_BLACK_JACK = 21
    }
}

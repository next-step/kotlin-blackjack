package blackjack.domain

class RuleChecker {

    fun checkSumOfCardNumbers(blackJackGamer: BlackJackGamer): Boolean {
        return blackJackGamer.calculateSumOfCardNumbers() <= proceedCondition(blackJackGamer.getGamerType())
    }

    private fun proceedCondition(gamerType: GamerType): Int {
        if (gamerType == GamerType.PLAYER) return CONDITION_TO_WIN_BLACK_JACK
        return CONDITION_TO_DEALER_DRAW_CARD
    }

    fun proceedWhoIsWinner(player: Player, dealer: Dealer): Int {
        if (dealer.calculateSumOfCardNumbers() > CONDITION_TO_WIN_BLACK_JACK) {
            player.proceedGameRecord(GameRecordType.WIN)
            dealer.proceedGameRecord(GameRecordType.LOSE)
            return 0
        }

        if (player.calculateSumOfCardNumbers() > CONDITION_TO_WIN_BLACK_JACK) {
            player.proceedGameRecord(GameRecordType.LOSE)
            dealer.proceedGameRecord(GameRecordType.WIN)
            return 0
        }

        if (player.calculateSumOfCardNumbers() > dealer.calculateSumOfCardNumbers()) {
            player.proceedGameRecord(GameRecordType.WIN)
            dealer.proceedGameRecord(GameRecordType.LOSE)
            return 0
        }

        if (player.calculateSumOfCardNumbers() < dealer.calculateSumOfCardNumbers()) {
            player.proceedGameRecord(GameRecordType.LOSE)
            dealer.proceedGameRecord(GameRecordType.WIN)
            return 0
        }

        if (player.calculateSumOfCardNumbers() == dealer.calculateSumOfCardNumbers()) {
            player.proceedGameRecord(GameRecordType.DRAW)
            dealer.proceedGameRecord(GameRecordType.DRAW)
            return 0
        }

        return 0
    }

    companion object {
        const val CONDITION_TO_WIN_BLACK_JACK = 21
        const val CONDITION_TO_DEALER_DRAW_CARD = 16
    }
}

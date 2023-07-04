package blackjack.domain

import blackjack.domain.gamer.*

class RuleChecker {

    fun checkSumOfCardNumbers(blackJackGamer: BlackJackGamer): Boolean {
        return blackJackGamer.calculateSumOfCardNumbers() <= proceedCondition(blackJackGamer.getGamerType())
    }

    private fun proceedCondition(gamerType: GamerType): Int {
        if (gamerType == GamerType.PLAYER) return CONDITION_TO_WIN_BLACK_JACK
        return Dealer.CONDITION_TO_DEALER_DRAW_CARD
    }

    fun proceedWhoIsWinner(player: Player, dealer: Dealer): BlackJackGamer? {
        if (dealer.calculateSumOfCardNumbers() > CONDITION_TO_WIN_BLACK_JACK) {
            return playerIsWinner(player, dealer)
        }

        if (player.calculateSumOfCardNumbers() > CONDITION_TO_WIN_BLACK_JACK) {
            return dealerIsWinner(player, dealer)
        }

        if (player.calculateSumOfCardNumbers() > dealer.calculateSumOfCardNumbers()) {
            return playerIsWinner(player, dealer)
        }

        if (player.calculateSumOfCardNumbers() < dealer.calculateSumOfCardNumbers()) {
            return dealerIsWinner(player, dealer)
        }

        if (player.calculateSumOfCardNumbers() == dealer.calculateSumOfCardNumbers()) {
            return noOneIsWinner(player, dealer)
        }

        return null
    }

    private fun playerIsWinner(player: Player, dealer: Dealer): BlackJackGamer {
        player.proceedGameRecord(GameRecordType.WIN)
        dealer.proceedGameRecord(GameRecordType.LOSE)
        return player
    }

    private fun dealerIsWinner(player: Player, dealer: Dealer): BlackJackGamer {
        player.proceedGameRecord(GameRecordType.LOSE)
        dealer.proceedGameRecord(GameRecordType.WIN)
        return dealer
    }

    private fun noOneIsWinner(player: Player, dealer: Dealer): BlackJackGamer? {
        player.proceedGameRecord(GameRecordType.DRAW)
        dealer.proceedGameRecord(GameRecordType.DRAW)
        return null
    }

    companion object {
        const val CONDITION_TO_WIN_BLACK_JACK = 21
    }
}

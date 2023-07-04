package blackjack.domain.status

import blackjack.domain.user.Dealer
import blackjack.domain.user.Player

interface Status

interface EndStatus : Status

enum class FixedEndStatus(val desc: String) : EndStatus {
    STAY("카드를 더 뽑지 않는 경우"),
}

enum class ConditionalEndStatus(val desc: String) : EndStatus {
    BURST("카드 점수 합이 21점이 초과된 경우") {
        override fun isMatch(resultPoint: Int) = resultPoint > BLACK_JACK_POINT
    },
    BLACK_JACK("카드 점수 합이 21인 경우") {
        override fun isMatch(resultPoint: Int) = resultPoint == BLACK_JACK_POINT
    };
    abstract fun isMatch(resultPoint: Int): Boolean

    companion object {
        private const val BLACK_JACK_POINT = 21
    }
}

enum class PlayingStatus(val desc: String) : Status {
    HIT("카드를 한장 더 뽑는 경우"),
    READY("최초 상태"),
}

enum class ResultStatus(val desc: String, val isPlayerWin: Boolean, val isDealerWin: Boolean) : Status {
    DEALER_BURST("dealer가 BURST 상태, player가 이긴 경우", true, false) {
        override fun isMatch(player: Player, dealer: Dealer): Boolean {
            return dealer.isBurst()
        }
    },
    PLAYER_BURST("player가 BURST 상태, dealer가 이긴 경우", false, true) {
        override fun isMatch(player: Player, dealer: Dealer): Boolean {
            return player.isBurst()
        }
    },
    COMPARE_POINT_PLAYER_WIN("Point 비교, player가 이긴 경우", true, false) {
        override fun isMatch(player: Player, dealer: Dealer) = player.cards.getOptimizedDiff() < dealer.cards.getOptimizedDiff()
    },

    COMPARE_POINT_DEALER_WIN("Point 비교, dealer가 이긴 경우", false, true) {
        override fun isMatch(player: Player, dealer: Dealer) = player.cards.getOptimizedDiff() > dealer.cards.getOptimizedDiff()
    },

    COMPARE_POINT_DRAW("Point 비교, player와 dealer가 비긴 경우", false, false) {
        override fun isMatch(player: Player, dealer: Dealer) = player.cards.getOptimizedDiff() == dealer.cards.getOptimizedDiff()
    };

    abstract fun isMatch(player: Player, dealer: Dealer): Boolean
}
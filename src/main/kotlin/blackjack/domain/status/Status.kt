package blackjack.domain.status

import blackjack.domain.user.Player

interface Status

interface EndStatus : Status

enum class FixedEndStatus(val desc: String) : EndStatus {
    STAY("카드를 더 뽑지 않는 경우"),
}

enum class ConditionalEndStatus(val desc: String) : EndStatus {
    BURST("카드 점수 합이 21점이 초과된 경우") {
        override fun isMatch(pointResult: Player.PointResult) = pointResult.min > BLACK_JACK_POINT
    },
    BLACK_JACK("카드 점수 합이 21인 경우") {
        override fun isMatch(pointResult: Player.PointResult) = pointResult.min == BLACK_JACK_POINT || pointResult.max == BLACK_JACK_POINT
    };
    abstract fun isMatch(pointResult: Player.PointResult): Boolean

    companion object {
        private const val BLACK_JACK_POINT = 21
    }
}

enum class PlayingStatus(val desc: String) : Status {
    HIT("카드를 한장 더 뽑는 경우"),
    READY("최초 상태"),
}

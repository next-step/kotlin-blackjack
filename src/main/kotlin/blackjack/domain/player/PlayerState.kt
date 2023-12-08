package blackjack.domain.player

enum class PlayerState {
    Hit, Stay, Bust, Blackjack;

    fun isHit() = this == Hit
    fun isBust() = this == Bust
}

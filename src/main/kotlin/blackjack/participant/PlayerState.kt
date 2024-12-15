package blackjack.participant

enum class PlayerState(
    private val description: String,
) {
    HIT("플레이어가 카드를 더 받기로 결정했을 때"),
    STAND("플레이어가 카드를 더 받지 않고 턴을 종료했을 때"),
    BLACKJACK("처음 두 장의 카드 합이 21점일 때"),
    BUST("플레이어가 21점을 초과했을 때"),
    ;

    fun isBust(): Boolean {
        return this == BUST
    }
}

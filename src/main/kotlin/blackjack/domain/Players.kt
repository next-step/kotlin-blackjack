package blackjack.domain

import blackjack.domain.Game.Companion.INITIAL_CARDS_COUNT

class Players(val value: List<Player>) {

    init {
        require(value.size >= MIN_NUMBER_PLAYERS) { "유효하지 않는 플레이어 참가 인원 수 입니다. 최소 ${MIN_NUMBER_PLAYERS}명 이상 입력해주세요." }
        require(value.size <= MAX_NUMBER_PLAYERS) { "유효하지 않는 플레이어 참가 인원 수 입니다. 최대 ${MAX_NUMBER_PLAYERS}명 까지 가능합니다." }
    }

    fun drawInitialCards(dealer: Dealer) {
        repeat(INITIAL_CARDS_COUNT) {
            value.forEach { player ->
                dealer.deliverCard()
                    .let(player::draw)
            }
        }
    }

    fun allReadyToPlay(): Boolean = value.all { it.shouldBeReadyToPlay() }
    companion object {
        const val MIN_NUMBER_PLAYERS = 2
        const val MAX_NUMBER_PLAYERS = 8
    }
}

package blackjack.domain.player

class Players(val dealer: Dealer, val list: List<Player>) {
    init {
        require(list.isNotEmpty()) { ONE_OR_MORE_ELEMENTS_REQUIRED }
    }

    companion object {
        private const val ONE_OR_MORE_ELEMENTS_REQUIRED = "최소 1개 이상의 플레이어를 입력해주세요."
    }
}

package blackjack.model

import blackjack.dto.Card
import blackjack.dto.GameResult
import blackjack.dto.Money
import blackjack.dto.PlayerResultStatus
import blackjack.dto.PlayerStatus
import blackjack.model.Point.Companion.WINNING_POINT

open class Player(val name: String) {

    val cards = mutableListOf<Card>()
    var status = PlayerStatus.HIT
        private set
    var bettingMoney: Money = Money(0)
        private set
    var gameResult: GameResult? = null
        private set
        get() {
            require(field != null) { "게임이 아직 끝나지 않았습니다" }
            return field
        }

    init {
        require(name.isNotBlank()) { "이름은 공백일 수 없습니다." }
    }

    fun addCard(card: Card) {
        cards.add(card)
        if (getPoints() > WINNING_POINT) {
            bust()
        }
    }

    // 처음 두 장의 카드를 받기 위한 함수
    fun addCards(cards: List<Card>) {
        this.cards.addAll(cards)
    }

    fun getPoints(): Int = toPoint().calculatePoints()

    fun stay() {
        if (status == PlayerStatus.HIT) {
            status = PlayerStatus.STAY
        }
    }

    fun bust() {
        if (status == PlayerStatus.HIT) {
            status = PlayerStatus.BUST
        }
    }

    private fun toPoint(): Point = Point(
        cards.map { it.number }
    )

    fun compare(dealer: Player) {
        val point = getPoints()
        val dealerPoint = dealer.getPoints()

        gameResult = if (status == PlayerStatus.BUST || (dealer.status == PlayerStatus.STAY && dealerPoint > point)) {
            GameResult(point, PlayerResultStatus.LOSE)
        } else if (point == dealerPoint) {
            GameResult(point, PlayerResultStatus.TIE)
        } else {
            GameResult(point, isBlackJack(point))
        }
    }

    private fun isBlackJack(point: Int): PlayerResultStatus =
        if (point == WINNING_POINT && cards.size == 2) {
            PlayerResultStatus.BLACKJACK
        } else {
            PlayerResultStatus.WIN
        }

    fun setGameResult(gameResult: GameResult) {
        this.gameResult = gameResult
    }

    fun processGame(
        dealer: Dealer,
        hitOrStand: (Player) -> Boolean,
        showCard: (Player) -> Unit
    ) {
        while (status == PlayerStatus.HIT) {
            if (hitOrStand.invoke(this).not()) {
                stay()
                return
            }
            addCard(dealer.dealingOneCard())
            showCard.invoke(this)
        }
    }

    fun setBettingMoney(money: Money) {
        bettingMoney = money
    }

    open fun getPrice(): Money =
        when (gameResult!!.playerResultStatus) {
            PlayerResultStatus.BLACKJACK -> bettingMoney * 1.5
            PlayerResultStatus.WIN -> bettingMoney
            PlayerResultStatus.TIE -> Money(0)
            PlayerResultStatus.LOSE -> bettingMoney * -1
        }
}

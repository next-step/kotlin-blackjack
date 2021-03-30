package blackjack

const val SEPARATOR = ", "

fun User.printResult() {
    println("${this.cardText()} - 결과: ${this.cardDeck.getScore()}")
}

fun User.cardText(): String {
    val cardTexts = cardDeck.cards.map { it.cardNumber.text() + it.type.text() }
    return "${name}카드: ${cardTexts.joinToString(", ")}"
}

fun User.firstDealCardText(): String {
    val cardTexts = getFirstDeal().map { it.cardNumber.text() + it.type.text() }
    return "${name}카드: ${cardTexts.joinToString(", ")}"
}

fun CardType.text(): String {
    return when (this) {
        CardType.SPADE -> "스페이드"
        CardType.CLOVER -> "클로버"
        CardType.HEART -> "하트"
        CardType.DIAMOND -> "다이아"
    }
}

fun CardNumber.text(): String {
    return when (this) {
        CardNumber.ACE -> "A"
        CardNumber.TWO -> "2"
        CardNumber.THREE -> "3"
        CardNumber.FOUR -> "4"
        CardNumber.FIVE -> "5"
        CardNumber.SIX -> "6"
        CardNumber.SEVEN -> "7"
        CardNumber.EIGHT -> "8"
        CardNumber.NINE -> "9"
        CardNumber.TEN -> "10"
        CardNumber.JACK -> "J"
        CardNumber.QUEEN -> "Q"
        CardNumber.KING -> "K"
    }
}

fun printFirstDeal(users: Users) {
    println(users.users.joinToString(SEPARATOR) { it.name } + "에게 2장의 카드를 나누어주었습니다.")
    users.users.forEach {
        println(it.firstDealCardText())
    }
    println()
}

fun printResult(users: Users) {
    users.users.forEach { it.printResult() }
    println()

    println("## 최종 수익")
    users.users.forEach {
        println("${it.name}: ${it.getEvaluate(users)}")
    }
    println()
}

fun printDealerBust(dealer: Dealer) {
    dealer.printResult()
    println("딜러가 Bust하였습니다. 모든 플레이어가 승리합니다.")
}

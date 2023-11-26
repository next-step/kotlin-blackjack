package blackjack

fun startOutput() {
    println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)")
}

fun giveCardsOutput(name: String) {
    println("${name}에게 카드를 2장 나누었습니다.")
}

fun whatCardsOutput(name: String, cardsName: String) {
    println("${name}카드: $cardsName")
}

fun drawResultOutput(name: String, cardsName: String) {
    println("${name}카드: $cardsName")
}

fun resultOutput(players: List<Player>) {
    players.forEach {
        println("${it.name}카드: ${it.cards.joinToString(separator = ",") { card -> card.name }}-결과: ${it.stop()}")
    }
}

fun oneMoreCardOutput(name: String) {
    println("${name}는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)")
}
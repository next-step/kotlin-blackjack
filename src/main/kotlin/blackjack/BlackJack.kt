package blackjack

fun main() {
    val blackJack = BlackJack()
    val cardExtractor = CardExtractor()
    val players = blackJack.parsePlayers(inputName())

    players.players.forEach {
        it.cardDeck.add(cardExtractor.getCard())
        it.cardDeck.add(cardExtractor.getCard())
    }
    println(players.players.joinToString(", ") { it.name } + "에게 2장의 카드를 나누어주었습니다.")
    players.players.forEach {
        println(it.getCardText())
    }
}

class BlackJack {

    fun parsePlayers(names: String?): Players {
        require(names != null) { "이름을 입력해주세요" }

        return Players(
            names.split(",").map {
                require(it.isNotBlank()) { "이름이 공백이어서는 안됩니다." }
                Player(it)
            }
        )
    }
}

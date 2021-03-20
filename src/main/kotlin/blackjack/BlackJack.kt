package blackjack

fun main() {
    val blackJack = BlackJack()
    val cardExtractor = CardExtractor()
    val players = blackJack.parsePlayers(inputName())

    players.addCardAllPlayer(cardExtractor)

    println(players.players.joinToString(", ") { it.name } + "에게 2장의 카드를 나누어주었습니다.")
    players.players.forEach {
        println(getPlayerCardText(it))
    }

    players.players.forEach {
        blackJack.moreCard(it, cardExtractor)
    }

    players.players.forEach {
        print(getPlayerCardText(it))
        println(" - 결과: ${it.cardDeck.getScore()}")
    }
}

class BlackJack {

    fun parsePlayers(names: String?): Players {
        require(names != null) { "이름을 입력해주세요" }

        return Players(names.split(",").map { Player(it) })
    }

    fun parseAnswer(answer: String?): Boolean {
        require(answer != null && (answer == "y" || answer == "n")) { "응답은 y나 n을 해주세요." }
        return answer == "y"
    }

    fun moreCard(player: Player, cardExtractor: CardExtractor) {
        while (!player.isDead()) {
            val answer = inputReceiveCardAnswer(player.name)
            if (parseAnswer(answer)) {
                player.cardDeck.add(cardExtractor.getCard())
            } else {
                println(getPlayerCardText(player))
                break
            }
            println(getPlayerCardText(player))
        }
    }
}
